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

namespace Robots2D
{
    public class Tile
    {
        public static Dictionary<string, Tile> TileNames = new Dictionary<string, Tile>();

        /// <summary>
        /// Initializes the tile list (referencing WorldGrid.png)
        /// </summary>
        static Tile()
        {
            Tile.TileNames["Soccer Field"]                  = new Tile(0, 0, false);
            Tile.TileNames["Soccer Vertical Line"]          = new Tile(1, 0, false);
            Tile.TileNames["Soccer Horizontal Line"]        = new Tile(2, 0, false);
            Tile.TileNames["Soccer 3rd Quad Turn"]          = new Tile(3, 0, false);
            Tile.TileNames["Soccer 2nd Quad Turn"]          = new Tile(4, 0, false);
            Tile.TileNames["Soccer 4th Quad Turn"]          = new Tile(5, 0, false);
            Tile.TileNames["Soccer 1st Quad Turn"]          = new Tile(6, 0, false);
            Tile.TileNames["Soccer Left Intersect"]         = new Tile(7, 0, false);
            Tile.TileNames["Soccer Top Intersect"]          = new Tile(8, 0, false);
            Tile.TileNames["Soccer Right Intersect"]        = new Tile(9, 0, false);

            Tile.TileNames["Soccer Bottom Intersect"]       = new Tile(0, 1, false);
            Tile.TileNames["Soccer Circle Top Lined"]       = new Tile(1, 1, false);
            Tile.TileNames["Soccer Circle Left Lined"]      = new Tile(2, 1, false);
            Tile.TileNames["Soccer Circle Right Lined"]     = new Tile(3, 1, false);
            Tile.TileNames["Soccer Circle Bottom Lined"]    = new Tile(4, 1, false);
            Tile.TileNames["Soccer Guardrail Top"]          = new Tile(5, 1, true);
            Tile.TileNames["Soccer Guardrail Top Right"]    = new Tile(6, 1, true);
            Tile.TileNames["Soccer Guardrail Top Left"]     = new Tile(7, 1, true);
            Tile.TileNames["Soccer Guardrail Bottom Right"] = new Tile(8, 1, true);
            Tile.TileNames["Soccer Guardrail Bottom Left"]  = new Tile(9, 1, true);

            Tile.TileNames["Soccer Circle Top Left"]        = new Tile(0, 2, false);
            Tile.TileNames["Soccer Circle Top"]             = new Tile(1, 2, false);
            Tile.TileNames["Soccer Circle Top Right"]       = new Tile(2, 2, false);
            Tile.TileNames["Soccer Circle Left"]            = new Tile(3, 2, false);
            Tile.TileNames["Soccer Circle Right"]           = new Tile(4, 2, false);
            Tile.TileNames["Soccer Circle Bottom Left"]     = new Tile(5, 2, false);
            Tile.TileNames["Soccer Circle Bottom"]          = new Tile(6, 2, false);
            Tile.TileNames["Soccer Circle Bottom Right"]    = new Tile(7, 2, false);
            Tile.TileNames["Soccer Guardrail Left"]         = new Tile(8, 2, true);
            Tile.TileNames["Soccer Guardrail Right"]        = new Tile(9, 2, true);

            Tile.TileNames["Soccer Guardrail Bottom"]       = new Tile(0, 3, true);
        }

        public static Tile FromId(short id)
        {
            foreach (KeyValuePair<string, Tile> tile in TileNames)
            {
                if (tile.Value.ID == id)
                {
                    return tile.Value;
                }
            }

            return null;
        }

        public readonly short ID;
        public bool Collides;
        public int X;
        public int Y;
        public DrawLevel DrawLevel = DrawLevel.AbovePhysicalObjects;

        public Tile(int x, int y, bool collides)
        {
            X = x;
            Y = y;
            Collides = collides;

            // If the sprite sheet changes, set 10 to (1 + number of sprites in a row.) note that this will break any previous versions as well.
            ID = (short)(y * 10 + x);
        }

        public Tile(int x, int y)
            : this(x, y, false)
        {
        }
    }
}