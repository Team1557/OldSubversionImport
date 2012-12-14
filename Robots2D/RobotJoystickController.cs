using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;

namespace Robots2D
{
    public class RobotJoystickController : RobotController
    {
        public SlimDX.DirectInput.Joystick Joystick
        {
            get;
            set;
        }

        private SlimDX.DirectInput.JoystickState previousState;
        
        public float GetUpdateRotation(GameTime time, SlimDX.DirectInput.JoystickState joystickState)
        {
            return (JoystickManager.GetInputValue(joystickState.X, 32768) / 20) * ((float)time.ElapsedGameTime.TotalSeconds * 60f);
        }

        public void Rotate(GameTime time, SlimDX.DirectInput.JoystickState joystickState)
        {
            Robot.Body.Rotation += GetUpdateRotation(time, joystickState);
        }

        public float GetUpdateMove(GameTime time, SlimDX.DirectInput.JoystickState joystickState)
        {
            return JoystickManager.GetInputValue(joystickState.Y, 32768);
        }

        public void Move(GameTime time, SlimDX.DirectInput.JoystickState joystickState)
        {
            
            float forwardPush = GetUpdateMove(time, joystickState);
            if (Math.Abs(forwardPush) > .1f)
            {
                Robot.Body.ApplyImpulse(-10 * Robot.GetForwardVector() * forwardPush * Speed);
            }
        }

        private List<string> pressedButtons = new List<string>();
        
        public void UpdateButtons(GameTime time, SlimDX.DirectInput.JoystickState joystickState)
        {
            if (previousState != null)
            {
                if (JoystickManager.WasButtonPressed(joystickState, previousState, 0))
                    pressedButtons.Add("X");

                if (JoystickManager.WasButtonPressed(joystickState, previousState, 1))
                    pressedButtons.Add("A");

                if (JoystickManager.WasButtonPressed(joystickState, previousState, 2))
                    pressedButtons.Add("B");

                if (JoystickManager.WasButtonPressed(joystickState, previousState, 3))
                    pressedButtons.Add("Y");

                if (joystickState.GetPointOfViewControllers()[0] != previousState.GetPointOfViewControllers()[0])
                {
                    pressedButtons.Add((joystickState.GetPointOfViewControllers()[0] / 4500).ToString().Substring(0, 1));
                }
            }

            if (pressedButtons.Count >= 2)
            {
                if (pressedButtons[pressedButtons.Count - 1] == "0")
                {
                    string buttonPressed = pressedButtons[pressedButtons.Count - 2];
                    Console.WriteLine(buttonPressed + " pressed!");


                    if (Game1.CheatMenu)
                    {
                        if (buttonPressed == "0")
                            if (Game1.SelectedCheat > 0)
                                Game1.SelectedCheat--;

                        if (buttonPressed == "4")
                        {
                            if (Game1.SelectedCheat < Enum.GetValues(typeof(Cheat)).Length - 1)
                                Game1.SelectedCheat++;
                        }
                    }

                    pressedButtons.RemoveAt(pressedButtons.Count - 1);
                    pressedButtons.RemoveAt(pressedButtons.Count - 1);
                }

                if (Game1.CheatMenu)
                {
                    if (JoystickManager.WasButtonPressed(joystickState, previousState, 1))
                    {
                        pressedButtons.RemoveAt(pressedButtons.Count - 1);
                        CheatCode.Cheat((Cheat)Enum.GetValues(typeof(Cheat)).GetValue(Game1.SelectedCheat));
                    }
                }
            }

            if (pressedButtons.Count > 100)
            {
                pressedButtons.Clear();
            }
            
            if (joystickState.IsPressed(9) && previousState.IsReleased(9))
            {
                string combinedPressed = "";
                foreach (string button in pressedButtons)
                {
                    combinedPressed += button;
                }

                pressedButtons.Clear();

                CheatCode.Call(combinedPressed);
            }

            previousState = joystickState;

        }

        public override void Update(GameTime time)
        {
            // Joystick time!
            SlimDX.DirectInput.JoystickState joystickState = Joystick.GetCurrentState();

            if (joystickState != null)
            {
                // Rotate based on how far left/right the stick is
                Rotate(time, joystickState);

                Move(time, joystickState);

                UpdateButtons(time, joystickState);
            }
            else
            {
                // TODO: Handle Lack of Joysticks
            }
        }
    }

    #region InputTesting code blocks
    // For the Logitech Duo:
    // X and Y give the position of the left movable stick
    // X = 0 to 65536
    // Y = 0 to 65536
    // POVControllers[0]
    //  0 = up
    //  4500 = up-right
    //  9000 = right
    //  13500 = down-right
    //  18000 = down
    //  22000 = down-left
    //  27000 = left
    //  31500 = up-left
    //  (+4500 for each in a clockwise direction)
    //
    // Z is the right movable stick's left and right.
    // RotationZ is the right movable stick's up and down
    // Z = 0 to 65536
    //
    // Buttons are: (note that the index is off by 1)
    // 1 = X
    // 2 = A
    // 3 = B
    // 4 = Y
    // 5 = LB
    // 6 = RB (broken on test controller)
    // 7 = LT
    // 8 = RT
    // 9 = Back
    // 10 = Start
    // 11 = Press Left stick
    // 12 = Press Right stick


    // For the Logitech Attack3 Joystick:
    // X and Y have the same range as the Logitech Duo, and are left/right and forward/backward for the stick.
    // Z has the same range as the Duo, and represents the slider in the front.

    /*Console.Write((joystickState.X / 32768f) - 1 + ",\t");
    Console.Write((joystickState.Y / 32768f) - 1 + ",\t");
    Console.WriteLine((joystickState.Z / 32768f) - 1 + ",\t");

    Console.Write("GetAccelerationSliders: ");
    foreach (int val in joystickState.GetAccelerationSliders())
        Console.Write(val + ", ");
    Console.WriteLine();

    Console.Write("GetBtttons: ");
    foreach (bool val in joystickState.GetButtons())
        Console.Write(val + ", ");
    Console.WriteLine();

    Console.Write("GetForceSliders: ");
    foreach (int val in joystickState.GetForceSliders())
        Console.Write(val + ", ");
    Console.WriteLine();

    Console.Write("GetPointOfViewControllers: ");
    foreach (int val in joystickState.GetPointOfViewControllers())
        Console.Write(val + ", ");
    Console.WriteLine();

    Console.Write("GetSliders: ");
    foreach (int val in joystickState.GetSliders())
        Console.Write(val + ", ");
    Console.WriteLine();

    Console.Write("GetVelocitySliders: ");
    foreach (int val in joystickState.GetVelocitySliders())
        Console.Write(val + ", ");
    Console.WriteLine();

    Console.WriteLine("AccelerationX" + " " + joystickState.AccelerationX);
    Console.WriteLine("AccelerationY" + " " + joystickState.AccelerationY);
    Console.WriteLine("AccelerationZ" + " " + joystickState.AccelerationZ);
    Console.WriteLine("AngularAccelerationX" + " " + joystickState.AngularAccelerationX);
    Console.WriteLine("AngularAccelerationY" + " " + joystickState.AngularAccelerationY);
    Console.WriteLine("AngularAccelerationZ" + " " + joystickState.AngularAccelerationZ);
    Console.WriteLine("AngularVelocityX" + " " + joystickState.AngularVelocityX);
    Console.WriteLine("AngularVelocityY" + " " + joystickState.AngularVelocityY);
    Console.WriteLine("AngularVelocityZ" + " " + joystickState.AngularVelocityZ);
    Console.WriteLine("ForceX" + " " + joystickState.ForceX);
    Console.WriteLine("ForceX" + " " + joystickState.ForceY);
    Console.WriteLine("ForceY" + " " + joystickState.ForceZ);
    Console.WriteLine("RotationX" + " " + joystickState.RotationX);
    Console.WriteLine("RotationY" + " " + joystickState.RotationY);
    Console.WriteLine("RotationZ" + " " + joystickState.RotationZ);
    Console.WriteLine("TorqueX" + " " + joystickState.TorqueX);
    Console.WriteLine("TorqueY" + " " + joystickState.TorqueY);
    Console.WriteLine("TorqueZ" + " " + joystickState.TorqueZ);
    Console.WriteLine("VelocityX" + " " + joystickState.VelocityX);
    Console.WriteLine("VelocityY" + " " + joystickState.VelocityY);
    Console.WriteLine("VelocityZ" + " " + joystickState.VelocityZ);
    Console.WriteLine("X" + " " + joystickState.X);
    Console.WriteLine("Y" + " " + joystickState.Y);
    Console.WriteLine("Z" + " " + joystickState.Z);
                    
    Console.WriteLine();

    foreach (int slider in joystickState.GetAccelerationSliders()) {
        Console.Write(slider + ", ");
    }

    Console.WriteLine();
    */
    #endregion
}
