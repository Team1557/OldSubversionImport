using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FarseerGames.FarseerPhysics;
using FarseerGames.FarseerPhysics.Factories;
using FarseerGames.FarseerPhysics.Collisions;
using FarseerGames.FarseerPhysics.Dynamics;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using System.IO;

namespace Robots2D
{
    /// <summary>
    /// UNFINSHED, DO NOT USE
    /// </summary>
    public class TileLevel : Level
    {
        public Tile[,] Tiles;
        public Vector2 LevelOffset = Vector2.Zero;
        public float Scale = 3;

        private List<PhysicalObject> physicalObjects;

        public TileLevel(Tile[,] tiles)
        {
            physicalObjects = new List<PhysicalObject>();
            Tiles = tiles;
        }

        public void LoadLevel(PhysicsSimulator simulator)
        {
            int lengthX = Tiles.GetLength(0);
            int lengthY = Tiles.GetLength(1);

            LevelOffset = DrawableObject.Floor(Scale * new Vector2(lengthX * -.5f, lengthY * -.5f));

            for (int x = 0; x < lengthX; x++)
            {
                for (int y = 0; y < lengthY; y++)
                {
                    Tile tile = Tiles[x, y];
                    
                    if (tile != null)
                    {
                        if (tile.Collides)
                        {
                            Body rectBody = BodyFactory.Instance.CreateRectangleBody(Scale, Scale, 1);
                            rectBody.Position = new Microsoft.Xna.Framework.Vector2(Scale * x, Scale * y) + LevelOffset;
                            rectBody.IsStatic = true;
                            Geom rectGeom = GeomFactory.Instance.CreateRectangleGeom(rectBody, Scale, Scale);

                            PhysicalTileObject physObj = new PhysicalTileObject(rectBody, rectGeom, new Vector2(Scale, Scale), new RenderInfo(), x,y);

                            physObj.LevelOffset = LevelOffset;
                            
                            physObj.Info.Mode = RenderMode.Sprite;
                            physObj.Info.SpriteSection = new Rectangle(51 * tile.X, 51 * tile.Y, 50, 50);
                            physObj.Info.Texture = Game1.WorldGridTexture;

                            physObj.Simulate(simulator);

                            //Game1.physicalObjects.Add(physObj);
                            physicalObjects.Add(physObj);
                        }
                        else
                        {
                            PhysicalTileObject physObj = new PhysicalTileObject(null, null, new Vector2(Scale, Scale), new RenderInfo(), x, y);
                            
                            physObj.LevelOffset = LevelOffset;

                            //physObj.Position = (Scale * new Vector2(x, y)) + LevelOffset;

                            physObj.Info.Mode = RenderMode.Sprite;
                            physObj.Info.Texture = Game1.WorldGridTexture;
                            physObj.Info.SpriteSection = new Rectangle(51 * tile.X, 51 * tile.Y, 50, 50);

                            // TODO: Does a PhysicalObject take up more memory than a DrawableObject?  Probably yes, if laggy then make a new List<DrawableObject> for noncollidables

                            physicalObjects.Add(physObj);
                        }
                    }
                }
            }
        }

        public void UnloadLevel()
        {
            foreach (PhysicalObject phyobj in physicalObjects)
            {
                if (phyobj.Body != null)
                {
                    phyobj.Body.Dispose();
                    phyobj.Geometry.Dispose();
                }
            }

            physicalObjects.Clear();
        }

        public void SaveLevel(string filename)
        {
            // TODO: Confirm that this works correctly.
            try
            {
                BinaryWriter writer = new BinaryWriter(File.Open(filename, FileMode.Create));

                writer.Write(this.Tiles.GetLength(0));
                writer.Write(this.Tiles.GetLength(1));

                for (int y = 0; y < Tiles.GetLength(1); y++)
                {
                    for (int x = 0; x < Tiles.GetLength(0); x++)
                    {
                        writer.Write(Tiles[x, y].ID);
                    }
                }

                writer.Close();
                writer.Dispose();
            }
            catch (Exception e)
            {
            }
        }

        public static TileLevel FromAsciiFile(string filename)
        {
            Dictionary<char, short> idDictionary = new Dictionary<char, short>();
            List<string> tiledLines = new List<string>();

            using (TextReader reader = new StreamReader(filename))
            {
                string line;
                while ((line = reader.ReadLine()) != null)
                {
                    // #a 321
                    if (line.StartsWith("#"))
                    {
                        if (line.Length >= 2)
                        {
                            try
                            {
                                idDictionary[line[1]] = short.Parse(line.Substring(3));
                            }
                            catch (Exception e)
                            {
                            }
                        }
                    }
                    else
                    {
                        if (line.Length > 0)
                        {
                            tiledLines.Add(line);
                        }
                    }
                }
            }

            int longestLine = 0;
            foreach (string line in tiledLines)
            {
                if (line.Length > longestLine)
                {
                    longestLine = line.Length;
                }
            }

            Tile[,] tiles = new Tile[longestLine, tiledLines.Count];

            for (int y = 0; y < tiledLines.Count; y++)
            {
                for (int x = 0; x < tiledLines[y].Length; x++)
                {
                    if (idDictionary.ContainsKey(tiledLines[y][x]))
                    {
                        tiles[x,y] = Tile.FromId(idDictionary[tiledLines[y][x]]);
                    }
                }
            }

            return new TileLevel(tiles);
        }

        public static TileLevel FromBinaryFile(string filename)
        {
            // TODO: Confirm that this works correctly.

            /*
             * File format specs:
             * int TileCountX
             * int TileCountY
             * short data-0-0
             * short data-1-0
             * short data-2-0
             * ...
             * short data 0-1
             * ...
             * */
            try
            {
                BinaryReader reader = new BinaryReader(File.Open(filename, FileMode.Open));

                int pos = 0;

                int width = reader.ReadInt32();
                int height = reader.ReadInt32();

                Tile[,] data = new Tile[width, height];
                
                int yTile = 0;
                int xTile = 0;

                int length = (int)reader.BaseStream.Length;
                
                while (pos < length)
                {
                    short tileID = reader.ReadInt16();
                    data[xTile, yTile] = Tile.FromId(tileID);

                    pos += sizeof(short);

                    xTile++;
                    if (xTile >= width)
                    {
                        xTile = 0;
                        yTile++;

                        if (yTile >= height)
                        {
                            Console.WriteLine("There is more data available than the amount specified in the file header.");
                            Console.WriteLine("Expected: " + width + ", " + height + ".");
                            break;
                        }
                    }
                }

                reader.Close();
                reader.Dispose();

                return new TileLevel(data);
            }
            catch (Exception e)
            {
                Console.WriteLine("Error loading Tile Level! " + e.ToString());
                // TODO: When there is a menu, handle this in the GUI.
                return null;
            }
        }

        public override void Draw(GameTime time, Camera2D camera, SpriteBatch spriteBatch)
        {
            ;
            foreach (PhysicalObject physObject in physicalObjects)
            {   
                physObject.Draw(time, camera, spriteBatch);
            }

            if (Game1.keyboard.IsKeyDown(Microsoft.Xna.Framework.Input.Keys.Z))
            {
                Console.Write(physicalObjects[0].Position.ToString() + " ");
                Console.Write(physicalObjects[01].Position.ToString() + " ");
            }
        }

        public override void Update(GameTime time)
        {
        }
    }
}
