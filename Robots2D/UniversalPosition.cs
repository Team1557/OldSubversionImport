using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;

namespace Robots2D
{
    public class UniversalPosition
    {
        public Vector2 Scale
        {
            get;
            set;
        }

        public Vector2 Offset
        {
            get;
            set;
        }

        public static UniversalPosition Zero
        {
            get
            {
                return new UniversalPosition(Vector2.Zero);
            }
        }

        public UniversalPosition(Vector2 scale, Vector2 offset)
        {
            Scale = scale;
            Offset = offset;
        }

        public UniversalPosition(Vector2 offset)
        {
            Scale = Vector2.Zero;
            Offset = offset;
        }

        /// <summary>
        /// Gets the absolute position of a position, given the size of the container.
        /// </summary>
        /// <param name="pos"></param>
        /// <param name="globalSize"></param>
        /// <returns></returns>
        public static Vector2 Resolve(UniversalPosition pos, Vector2 globalSize)
        {
            return pos.Scale * globalSize + pos.Offset;
        }
    }
}
