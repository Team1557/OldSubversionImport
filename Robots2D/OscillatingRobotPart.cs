using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;

namespace Robots2D
{
    public class OscillatingRobotPart : DynamicRobotPart
    {
        private List<Vector2> spritePositions = new List<Vector2>();

        private float timeOscillated = 1;
        
        public float OscillationTime
        {
            get;
            set;
        }

        private int currentSpritePositionIndex = 0;

        public OscillatingRobotPart(List<Vector2> spritePositions, Vector2 offset, float oscTime)
            : base(spritePositions[0], offset)
        {
            this.OscillationTime = oscTime;
            this.spritePositions = spritePositions;
        }


        public OscillatingRobotPart(List<Vector2> spritePositions, Vector2 offset)
            : this(spritePositions, offset, 1)
        {
        }

        public OscillatingRobotPart(List<Vector2> spritePositions, float oscTime)
            : this(spritePositions, Vector2.Zero, oscTime)
        {
        }

        public OscillatingRobotPart(List<Vector2> spritePositions)
            : this(spritePositions, Vector2.Zero)
        {
        }

        public override void Update(GameTime time)
        {
            timeOscillated += (float)time.ElapsedGameTime.TotalSeconds;

            if (timeOscillated >= OscillationTime)
            {
                timeOscillated -= OscillationTime;
                currentSpritePositionIndex++;
                
                if (currentSpritePositionIndex >= spritePositions.Count)
                {
                    currentSpritePositionIndex = 0;
                }
                this.SpritePosition = spritePositions[currentSpritePositionIndex];
            }

            //base.Update(time);
        }

        public override void UpdateSpritePosition(GameTime time)
        {
            Update(time);
        }
    }
}
