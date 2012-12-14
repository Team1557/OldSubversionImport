using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FarseerGames.FarseerPhysics.Dynamics;
using Microsoft.Xna.Framework;

namespace Robots2D
{
    public abstract class RobotController
    {
        public Robot Robot
        {
            get;
            set;
        }

        public float Speed
        {
            get;
            set;
        }

        public abstract void Update(GameTime time);
    }
}
