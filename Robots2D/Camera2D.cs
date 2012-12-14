using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;

namespace Robots2D
{
    public class Camera2D
    {
        // TODO: Make camera rotatable
        public Game Game
        {
            get;
            set;
        }

        public Vector2 DesiredPosition
        {
            get;
            set;
        }

        public Vector2 Center
        {
            get;
            set;
        }

        private Vector2 lerpedPosition;

        /// <summary>
        /// Change _ONLY_ when absolutely needed.
        /// </summary>
        public Vector2 Position
        {
            get
            {
                return lerpedPosition;
            }
            set
            {
                lerpedPosition = value;
            }
        }

        public List<Vector2> FocusPoints
        {
            get;
            set;
        }

        public Camera2D(Game game)
        {
            Game = game;
            FocusPoints = new List<Vector2>();
            CenterCameraOnWindow();
        }

        public void SetDesiredPositionToFocusPoints()
        {
            Vector2 averagePoint = Vector2.Zero;

            foreach (Vector2 point in FocusPoints)
            {
                averagePoint += point;
            }

            averagePoint /= FocusPoints.Count;

            DesiredPosition = averagePoint;
        }

        public void SetScaleToFocusPoints()
        {
            float maxDistanceFromCenter = ((Center.X < Center.Y) ? Center.X : Center.Y) * .75f;
            float minDistanceFromCenter = ((Center.X < Center.Y) ? Center.X : Center.Y) * .5f;
            
            float greatestDistance = 0;
            foreach (Vector2 point in FocusPoints)
            {
                float distance = GetPositionFloatWithoutCentering(point).Length();
                if (distance > greatestDistance)
                {
                    greatestDistance = distance;
                }
            }

            if (greatestDistance > maxDistanceFromCenter)
            {
                Game1.Scale *= (maxDistanceFromCenter / greatestDistance);
            }
            else if (greatestDistance < minDistanceFromCenter)
            {
                Game1.Scale *= (minDistanceFromCenter / greatestDistance);
            }
        }

        public void CenterCameraOnWindow()
        {
            Center = new Vector2(Game.Window.ClientBounds.Width, Game.Window.ClientBounds.Height) / 2;
            Center = DrawableObject.Floor(Center);
        }

        public void Update(GameTime gameTime)
        {
            lerpedPosition = Vector2.Lerp(lerpedPosition, DesiredPosition, Math.Min(3f * (float)gameTime.ElapsedGameTime.TotalSeconds, 1));
        }

        /// <summary>
        /// Gets the onscreen position of an item, adjusted by the camera's position.
        /// Coordinates are rounded down to ints.
        /// </summary>
        /// <param name="input"></param>
        /// <returns></returns>
        public Vector2 GetPosition(Vector2 input)
        {
            Vector2 pos = GetPositionFloat(input);
            //pos.X = (float)Math.Floor(pos.X);
            //pos.Y = (float)Math.Floor(pos.Y);
            
            //pos.X = (int)pos.X;
            //pos.Y = (int)pos.Y;

            pos.X = (int)(pos.X - .05f);
            pos.Y = (int)(pos.Y - .05f);
            
            return pos;
        }

        /// <summary>
        /// Gets the onscreen position of an item, adjusted by the camera's position.
        /// </summary>
        /// <param name="input"></param>
        /// <returns></returns>
        public Vector2 GetPositionFloat(Vector2 input)
        {
            Vector2 pos = input - lerpedPosition + Center;
            return pos;
        }

        /// <summary>
        /// Gets the onscreen position of an item, adjusted by the camera's position, without centering to the game window.
        /// </summary>
        /// <param name="input"></param>
        /// <returns></returns>
        public Vector2 GetPositionFloatWithoutCentering(Vector2 input)
        {
            Vector2 pos = input - lerpedPosition;
            return pos;
        }
    }
}
