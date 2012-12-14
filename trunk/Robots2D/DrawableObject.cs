using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;

namespace Robots2D
{
    public class DrawableObject
    {
        public Vector2 Size
        {
            get;
            set;
        }

        public RenderInfo Info
        {
            get;
            set;
        }

        public virtual Vector2 Position
        {
            get;
            set;
        }

        public virtual float Rotation
        {
            get;
            set;
        }

        public virtual void Draw(GameTime time, Camera2D camera, SpriteBatch spriteBatch)
        {
            if (Info.Mode == RenderMode.Box)
            {
                spriteBatch.Draw(
                    Game1.WhiteTexture,
                    camera.GetPositionFloat(Position),
                    null,
                    Info.Color,
                    rotation: Rotation,
                    origin: new Vector2(.5f, .5f), // == WhiteTexture.Width/2, WhiteTexture.Height/2
                    scale: Size * Game1.Scale * Game1.PhysicsScale,
                    effects: SpriteEffects.None,
                    layerDepth: 1
                );
            }
            else if (Info.Mode == RenderMode.Circle)
            {
                // TODO: Add circle rendering
                throw new NotSupportedException();
            }
            else if (Info.Mode == RenderMode.Texture)
            {
                spriteBatch.Draw(
                    texture: Info.Texture,
                    position: camera.GetPosition(Position),
                    sourceRectangle: null,
                    color: Info.Color,
                    rotation: Rotation,
                    origin: new Vector2(Info.Texture.Width / 2, Info.Texture.Height / 2),
                    scale: (Size * Game1.Scale * Game1.PhysicsScale) / new Vector2(Info.Texture.Width, Info.Texture.Height) * Info.TextureScale,
                    effects: SpriteEffects.None,
                    layerDepth: 1
                );
            }
            else if (Info.Mode == RenderMode.Sprite)
            {
                spriteBatch.Draw(
                    texture: Info.Texture,
                    position: camera.GetPosition(Position),
                    sourceRectangle: Info.SpriteSection,
                    color: Info.Color,
                    rotation: Rotation,
                    origin: new Vector2(50,50)/2,
                    scale: (Size * Game1.Scale * Game1.PhysicsScale) / new Vector2(50, 50) * Info.TextureScale,
                    effects: SpriteEffects.None,
                    layerDepth: 1
                );
            }
        }

        public static Vector2 Floor(Vector2 input)
        {
            return new Vector2((int)input.X, (int)input.Y);
        }
    }
}
