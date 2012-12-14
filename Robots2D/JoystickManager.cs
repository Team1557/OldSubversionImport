using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Robots2D
{
    public class JoystickManager
    {
        // Joysticks
        public List<SlimDX.DirectInput.DeviceInstance> directInputDevices;
        public SlimDX.DirectInput.DirectInput directInput;
        public List<SlimDX.DirectInput.Joystick> Joysticks;

        public void ClearJoysticks()
        {
            try
            {
                directInputDevices.Clear();
                directInput.Dispose();

                foreach (SlimDX.DirectInput.Joystick joystick in Joysticks)
                {
                    joystick.Unacquire();
                    joystick.Dispose();
                }

                Joysticks.Clear();
            }
            catch (Exception error)
            {
                Console.WriteLine(error.ToString());
            }
        }

        public void LoadJoysticks()
        {
            ClearJoysticks();

            directInputDevices = new List<SlimDX.DirectInput.DeviceInstance>();
            directInput = new SlimDX.DirectInput.DirectInput();
            Joysticks = new List<SlimDX.DirectInput.Joystick>();

            directInputDevices.AddRange(directInput.GetDevices(SlimDX.DirectInput.DeviceClass.GameController, SlimDX.DirectInput.DeviceEnumerationFlags.AttachedOnly));

            foreach (SlimDX.DirectInput.DeviceInstance device in directInputDevices)
            {
                SlimDX.DirectInput.Joystick joystickDevice = new SlimDX.DirectInput.Joystick(directInput, device.InstanceGuid);
                Joysticks.Add(joystickDevice);
                joystickDevice.Properties.AxisMode = SlimDX.DirectInput.DeviceAxisMode.Absolute;
                joystickDevice.Properties.DeadZone = 0;
                joystickDevice.Acquire();
            }
        }

        public static bool WasButtonPressed(SlimDX.DirectInput.JoystickState currentState, SlimDX.DirectInput.JoystickState previousState, int buttonID)
        {
            return (currentState.IsPressed(buttonID) && previousState.IsReleased(buttonID));
        }

        public static bool WasButtonReleased(SlimDX.DirectInput.JoystickState currentState, SlimDX.DirectInput.JoystickState previousState, int buttonID)
        {
            return !WasButtonPressed(currentState, previousState, buttonID);
        }

        /// <summary>
        /// Gets the input value from -1 to 1, from a raw input value.
        /// (The normal is 32768)
        /// </summary>
        /// <param name="input"></param>
        /// <param name="max"></param>
        /// <returns></returns>
        public static float GetInputValue(int input, int max)
        {
            return ((input / (float)max) - 1);
        }

        /// <summary>
        /// Gets the rotation of the joystick input, based on the X and Y position of the stick.
        /// </summary>
        /// <param name="x"></param>
        /// <param name="y"></param>
        /// <returns></returns>
        public static float GetInputRotation(float x, float y)
        {
            return -(float)Math.Atan2((double)x, (double)y);
        }
    }
}
