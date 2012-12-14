using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework.Input;
using Microsoft.Xna.Framework;

namespace Robots2D
{
    public class RobotKeyboardController : RobotController
    {
        public KeyboardManager KeyboardManager
        {
            get;
            set;
        }

        public Keys Forward
        {
            get;
            set;
        }

        public Keys Backward
        {
            get;
            set;
        }

        public Keys Left
        {
            get;
            set;
        }

        public Keys Right
        {
            get;
            set;
        }

        public RobotKeyboardController(Robot robot, KeyboardManager manager, Keys forward, Keys backward, Keys left, Keys right)
        {
            Robot = robot;
            KeyboardManager = manager;
            Forward = forward;
            Backward = backward;
            Left = left;
            Right = right;
            Speed = 1;
        }

        public override void Update(GameTime time)
        {
            float turnSpeedMultiplier = 1;

            if (KeyboardManager.IsKeyDown(Forward))
            {
                Robot.Body.ApplyImpulse(Robot.GetForwardVector() * 10 * Speed);
                turnSpeedMultiplier = 2;
            }

            if (KeyboardManager.IsKeyDown(Backward))
            {
                Robot.Body.ApplyImpulse(Robot.GetForwardVector() * -8 * Speed);
                turnSpeedMultiplier = 2;
            }

            if (KeyboardManager.IsKeyDown(Left))
            {
                Robot.Body.ApplyTorque(-125 * turnSpeedMultiplier);
            }

            if (KeyboardManager.IsKeyDown(Right))
            {
                Robot.Body.ApplyTorque(125 * turnSpeedMultiplier);
            }
        }
    }
}
