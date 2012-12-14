using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;

namespace Robots2D
{
    public class GuiImage : GuiObject
    {
        public Texture2D Image
        {
            get;
            set;
        }

        public Color Color
        {
            get;
            set;
        }

        public GuiImage(Texture2D image)
        {
            Image = image;
            Size = new Vector2(image.Width, image.Height);
            Color = Color.White;
        }

        public override void Draw(GameTime time, SpriteBatch spriteBatch, Vector2 globalSize)
        {
            Vector2 pos = GetAbsolutePosition(globalSize);
            spriteBatch.Draw(Image, new Rectangle((int)pos.X, (int)pos.Y, (int)Size.X, (int)Size.Y), Color);
            base.Draw(time, spriteBatch, globalSize);
        }
    }
}
