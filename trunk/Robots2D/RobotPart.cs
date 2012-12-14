using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;

namespace Robots2D
{
    public class RobotPart
    {
        public static Dictionary<string, Vector2> PartNames = new Dictionary<string, Vector2>();

        /// <summary>
        /// The sprite map used for the lookup of the parts.
        /// </summary>
        public static Texture2D SpriteMap;

        /// <summary>
        /// The position of the item in the sprite sheet (ex 4,2)
        /// </summary>
        public Vector2 SpritePosition;

        /// <summary>
        /// The offset of the part, based on a 0* rotation.
        /// When the parent is rotated, the offset is rotated as well.
        /// </summary>
        public Vector2 Offset;

        /// <summary>
        /// The offset of the part, on the screen.
        /// This offset is not affected by parent rotation.
        /// </summary>
        public Vector2 AbsoluteOffset;

        /// <summary>
        /// The rotation of the part, in radians.
        /// </summary>
        public float Rotation;

        public RobotPart(Vector2 spritePosition, Vector2 offset)
        {
            if (SpriteMap == null)
                throw new NullReferenceException("RobotPart.SpriteMap is undefined!");

            SpritePosition = spritePosition;
            Offset = offset;
        }

        public RobotPart(Vector2 spritePosition) : this(spritePosition, Vector2.Zero)
        {
        }

        public RobotPart(string spritePosition) : this(PartNames[spritePosition], Vector2.Zero)
        {
        }

        public RobotPart(string spritePosition, Vector2 offset) : this(PartNames[spritePosition], offset)
        {
        }

        public virtual void Draw(GameTime gameTime, Robot robot, Camera2D camera, SpriteBatch spriteBatch)
        {
            Vector3 partOffsetV3 = (Matrix.CreateTranslation(new Vector3(Offset.X, 0, Offset.Y)) * Matrix.CreateRotationY(-robot.Rotation + Rotation - MathHelper.PiOver2)).Translation * 1;
            Vector2 partPosition = camera.GetPosition((robot.Position + new Vector2(partOffsetV3.X, partOffsetV3.Z) + AbsoluteOffset) * Game1.Scale);

            float partRotation = robot.Rotation + Rotation;
            
            spriteBatch.Draw(SpriteMap, partPosition, new Rectangle((int)SpritePosition.X * 51, (int)SpritePosition.Y * 51, 50, 50), Color.White, partRotation, new Vector2(50,50)/2, Game1.Scale, SpriteEffects.None, 0);
        }

        public virtual void Update(GameTime gameTime)
        {
        }

        static RobotPart()
        {
            PartNames["Wheel1"] = new Vector2(0, 0);
            PartNames["Wheel2"] = new Vector2(1, 0);
            PartNames["Metal Separator"] = new Vector2(2, 0);
            PartNames["Blue Padding"] = new Vector2(3, 0);
            PartNames["Red Padding"] = new Vector2(4, 0);
            PartNames["Construction Padding"] = new Vector2(5, 0);
            PartNames["Circuit Board"] = new Vector2(6, 0);
            PartNames["Circuit Controller"] = new Vector2(7, 0);
            PartNames["WIFI Controller Off"] = new Vector2(8, 0);
            PartNames["WIFI Controller On"] = new Vector2(9, 0);

            PartNames["Tread1"] = new Vector2(0, 1);
            PartNames["Tread2"] = new Vector2(1, 1);
            PartNames["Plastic Separator"] = new Vector2(2, 1);
            PartNames["Colored Wires"] = new Vector2(3, 1);
            PartNames["Wires"] = new Vector2(4, 1);
            PartNames["Pneumatic Controller"] = new Vector2(5, 1);

        }
    }
}
