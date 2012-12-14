using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework.Input;

namespace Robots2D
{
    public delegate void KeyChanged(Keys key);

    public class KeyboardManager
    {
        //public event KeyChanged OnKeyDown;
        //public event KeyChanged OnKeyUp;
        //public event KeyChanged OnKeyPressed;

        private KeyboardState previousKeyboardState;
        private KeyboardState keyboardState;

        public void Update()
        {
            this.previousKeyboardState = keyboardState;
            this.keyboardState = Keyboard.GetState();
        }

        public bool IsKeyDown(Keys key)
        {
            return keyboardState.IsKeyDown(key);
        }

        public bool IsKeyUp(Keys key)
        {
            return !IsKeyDown(key);
        }

        /// <summary>
        /// Gets the change state of the provided key, based on the previous state.
        /// </summary>
        /// <param name="key"></param>
        /// <returns></returns>
        public KeyChange GetChange(Keys key)
        {
            bool previousDown = previousKeyboardState.IsKeyDown(key);
            bool currentDown = keyboardState.IsKeyDown(key);
            if (previousDown == currentDown)
            {
                return KeyChange.Unchanged;
            }
            else
            {
                if (currentDown) // if they are different and the current is down, then it was just pressed.
                {
                    return KeyChange.Pressed;
                }
                else
                {
                    return KeyChange.Released;
                }
            }
        }

        /// <summary>
        /// Gets whether the key was just pressed down.  Equivalent to GetChange() == KeyChange.Pressed.
        /// </summary>
        /// <param name="key"></param>
        /// <returns></returns>
        public bool WasPressed(Keys key)
        {
            return GetChange(key) == KeyChange.Pressed;
        }

        /// <summary>
        /// Gets whether the key was just released.  Equivalent to GetChange() == KeyChange.Released;
        /// </summary>
        /// <param name="key"></param>
        /// <returns></returns>
        public bool WasReleased(Keys key)
        {
            return GetChange(key) == KeyChange.Released;
        }
    }

    public enum KeyChange
    {
        Unchanged,
        Pressed,
        Released
    }
}
