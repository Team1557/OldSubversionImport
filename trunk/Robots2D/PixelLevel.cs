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
using System.Drawing;

namespace Robots2D
{
    /// <summary>
    /// A level that is composed of colored pixels.
    /// </summary>
    public class PixelLevel : Level
    {
        public System.Drawing.Color[,] Data;
        public Vector2 LevelOffset;
        public float Scale;

        private List<PhysicalObject> physicalObjects;

        public PixelLevel(System.Drawing.Color[,] data, Vector2 offset, float scale)
        {
            Data = data;
            LevelOffset = offset;
            Scale = scale;
            physicalObjects = new List<PhysicalObject>();
        }

        /// <summary>
        /// Loads the information from 'Data', allowing the level to be drawn and simulated.
        /// </summary>
        /// <param name="simulator"></param>
        public void LoadLevel(PhysicsSimulator simulator)
        {
            int lengthX = Data.GetLength(0);
            int lengthY = Data.GetLength(1);

            for (int x = 0; x < lengthX; x++)
            {
                for (int y = 0; y < lengthY; y++)
                {
                    bool visible = true;

                    if (Data[x, y].A == 0)
                        visible = false;

                    if (Data[x, y].R == 255 && Data[x, y].G == 255 && Data[x, y].B == 255)
                        visible = false;

                    if (visible)
                    {
                        Body rectBody = BodyFactory.Instance.CreateRectangleBody(Scale, Scale, 1);
                        rectBody.Position = new Microsoft.Xna.Framework.Vector2(Scale * x, Scale * y) + LevelOffset;
                        rectBody.IsStatic = true;
                        Geom rectGeom = GeomFactory.Instance.CreateRectangleGeom(rectBody, Scale, Scale);

                        simulator.Add(rectBody);
                        simulator.Add(rectGeom);


                        PhysicalObject physObj = new PhysicalObject(rectBody, rectGeom, new Microsoft.Xna.Framework.Vector2(Scale, Scale));
                        physObj.Info.Color = Microsoft.Xna.Framework.Color.FromNonPremultiplied(Data[x, y].R,Data[x, y].G, Data[x, y].B, Data[x, y].A);
                        
                        //Game1.physicalObjects.Add(physObj);
                        physicalObjects.Add(physObj);

                    }
                }
            }
        }

        /// <summary>
        /// Saves the level to a new image, using the colors from the Data variable.
        /// </summary>
        /// <param name="filename"></param>
        public void SaveLevel(string filename)
        {
            //  TODO: Test that SaveLevel works.
            int lengthX = Data.GetLength(0);
            int lengthY = Data.GetLength(1);

            System.Drawing.Bitmap image = new System.Drawing.Bitmap(lengthX, lengthY);

            for (int x = 0; x < lengthX; x++)
            {
                for (int y = 0; y < lengthY; y++)
                {
                    image.SetPixel(x, y, Data[x, y]);
                }
            }

            image.Save(filename, System.Drawing.Imaging.ImageFormat.Png);

            image.Dispose();
        }
        
        /// <summary>
        /// Generates a PixelLevel from an image, at the given path.
        /// </summary>
        /// <param name="path">Location of the image to use for data.</param>
        /// <param name="offset">The offset of the map's rendering and collisions.</param>
        /// <param name="scale">How large each pixel is in Box2D meters.</param>
        /// <returns></returns>
        public static PixelLevel FromFile(string path, Vector2 offset, float scale)
        {
            Bitmap map = (Bitmap)Bitmap.FromFile(path);
            System.Drawing.Color[,] data = new System.Drawing.Color[map.Width, map.Height];

            for (int x = 0; x < map.Width; x++)
            {
                for (int y = 0; y < map.Height; y++)
                {
                    System.Drawing.Color pixel = map.GetPixel(x, y);
                    data[x, y] = pixel;
                }
            }

            return new PixelLevel(data, offset, scale);
        }

        public override void Draw(GameTime time, Camera2D camera, SpriteBatch spriteBatch)
        {
            foreach (PhysicalObject physObject in physicalObjects)
            {
                physObject.Draw(time, camera, spriteBatch);
            }
        }

        public override void Update(GameTime time)
        {
        }
    }
}
