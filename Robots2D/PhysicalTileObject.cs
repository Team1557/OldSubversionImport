using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using FarseerGames.FarseerPhysics.Dynamics;
using FarseerGames.FarseerPhysics.Collisions;
using Microsoft.Xna.Framework.Graphics;

namespace Robots2D
{
    class PhysicalTileObject : PhysicalObject
    {
        public int X
        {
            get;
            set;
        }

        public int Y
        {
            get;
            set;
        }

        public Vector2 LevelOffset
        {
            get;
            set;
        }

        public override Vector2 Position
        {
            get
            {
                //return Game1.Scale * Game1.PhysicsScale * (new Vector2(X, Y) * Size + LevelOffset);
                
                //Vector2 z = DrawableObject.Floor(Game1.PhysicsScale * Game1.Scale * (new Vector2(X, Y) * Size + LevelOffset));
                //float adjustedGameScale = Game1.Scale - (Game1.Scale % (Size.X/50));
                float adjustedGameScale = Game1.Scale;

                Vector2 z = new Vector2((int)(Game1.PhysicsScale * adjustedGameScale * (X * Size.X + LevelOffset.X)), (int)(Game1.PhysicsScale * adjustedGameScale * (Y * Size.Y + LevelOffset.Y)));
                return z;
            }
        }

        public PhysicalTileObject(Body body, Geom geometry, Vector2 size, RenderInfo info, int x, int y)
            : base(body, geometry, size, info)
        {
            X = x;
            Y = y;

            // HACK: This scale fixes the flickering, but messes up the graphics.
            Info.TextureScale = new Vector2(1.04f, 1.04f);
        }

        Vector2 origin = new Vector2(50, 50) / 2;
        public override void Draw(GameTime time, Camera2D camera, SpriteBatch spriteBatch)
        {
            spriteBatch.Draw(
                texture: Info.Texture,
                position: camera.GetPosition(Position),
                sourceRectangle: Info.SpriteSection,
                color: Info.Color,
                rotation: Rotation,
                origin: origin,
                scale: (Size * Game1.Scale * Game1.PhysicsScale) / new Vector2(50, 50) * Info.TextureScale,
                effects: SpriteEffects.None,
                layerDepth: 1
            );
        }
    }
}
