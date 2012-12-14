using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;

namespace Robots2D
{
    public abstract class Level
    {
        public abstract void Draw(GameTime time, Camera2D camera, SpriteBatch spriteBatch);
        public abstract void Update(GameTime time);
    }
}
