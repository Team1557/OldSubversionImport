using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework;

namespace Robots2D
{
    class Gui3x3Grid : GuiImage
    {
        public Gui3x3Grid(Texture2D image)
            : base(image)
        {

        }

        public override void Draw(GameTime time, SpriteBatch spriteBatch, Vector2 globalSize)
        {
            Vector2 gridSize = new Vector2(Image.Width, Image.Height) / 3;
            Vector2 pos = GetAbsolutePosition(globalSize);

            // TODO: This is all wrong, make it so it splits it up properly.
            //spriteBatch.Draw(Image, new Rectangle((int)pos.X + ((int)gridSize.X * x), (int)pos.Y + ((int)gridSize.Y * y), (int)gridSize.X, (int)gridSize.Y), new Rectangle((int)gridSize.X * x, (int)gridSize.Y * y, (int)gridSize.X, (int)gridSize.Y), Color);
        }
    }
}
