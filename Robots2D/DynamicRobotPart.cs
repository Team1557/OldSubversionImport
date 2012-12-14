using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;

namespace Robots2D
{
    /// <summary>
    /// This class allows for a RobotPart that can dynamically change it's sprite.
    /// </summary>
    public abstract class DynamicRobotPart : RobotPart
    {
        public DynamicRobotPart(Vector2 spritePosition, Vector2 offset) : base(spritePosition, offset)
        {
        }

        public abstract void UpdateSpritePosition(GameTime time);

        public override void Draw(GameTime gameTime, Robot robot, Camera2D camera, SpriteBatch spriteBatch)
        {
            base.Draw(gameTime, robot, camera, spriteBatch);
        }

        public override void Update(GameTime time)
        {
            UpdateSpritePosition(time);
            base.Update(time);
        }
    }
}
