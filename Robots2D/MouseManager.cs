using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework.Input;
using Microsoft.Xna.Framework;

namespace Robots2D
{
    public class MouseManager
    {
        private MouseState previousMouseState;
        private MouseState mouseState;

        public event EventHandler OnLeftMouseDown;
        public event EventHandler OnLeftMouseUp;
        public event EventHandler OnRightMouseDown;
        public event EventHandler OnRightMouseUp;
        public event EventHandler OnMiddleMouseDown;
        public event EventHandler OnMiddleMouseUp;

        public delegate void MouseScrollEvent(float amount);
        public event MouseScrollEvent OnScroll;

        public delegate void MouseMoveEvent(int dx, int dy);
        public event MouseMoveEvent OnMove;

        public MouseManager()
        {
            this.mouseState = Mouse.GetState();
            this.previousMouseState = mouseState;
        }

        public void Update()
        {
            this.previousMouseState = mouseState;
            this.mouseState = Mouse.GetState();

            if (this.WasLeftPressed())
            {
                if (OnLeftMouseDown != null)
                    OnLeftMouseDown(this, EventArgs.Empty);
            }
            else if (this.WasLeftReleased())
            {
                if (OnLeftMouseUp != null)
                    OnLeftMouseUp(this, EventArgs.Empty);
            }

            if (this.WasRightPressed())
            {
                if (OnRightMouseDown != null)
                    OnRightMouseDown(this, EventArgs.Empty);
            }
            else if (this.WasRightReleased())
            {
                if (OnRightMouseUp != null)
                    OnRightMouseUp(this, EventArgs.Empty);
            }

            if (this.WasMiddlePressed())
            {
                if (OnMiddleMouseDown != null)
                    OnMiddleMouseDown(this, EventArgs.Empty);
            }
            else if (this.WasMiddleReleased())
            {
                if (OnMiddleMouseUp != null)
                    OnMiddleMouseUp(this, EventArgs.Empty);
            }

            if (this.X != previousMouseState.X || this.Y != previousMouseState.Y)
            {
                if (OnMove != null)
                    OnMove(this.X - previousMouseState.X, this.Y - previousMouseState.Y);
            }

            float scrollAmount = GetScrollAmount();
            if (scrollAmount != 0)
            {
                if (OnScroll != null)
                    OnScroll(scrollAmount);
            }
        }

        public int X
        {
            get
            {
                return mouseState.X;
            }

            set
            {
                Mouse.SetPosition(value, mouseState.Y);
            }
        }

        public int Y
        {
            get
            {
                return mouseState.Y;
            }

            set
            {
                Mouse.SetPosition(mouseState.X, value);
            }
        }

        public Vector2 Position
        {
            get
            {
                return new Vector2(mouseState.X, mouseState.Y);
            }

            set
            {
                Mouse.SetPosition((int)value.X, (int)value.Y);
            }
        }

        public bool IsLeftDown()
        {
            return mouseState.LeftButton == ButtonState.Pressed;
        }

        public bool IsRightDown()
        {
            return mouseState.RightButton == ButtonState.Pressed;
        }

        public bool IsMiddleDown()
        {
            return mouseState.MiddleButton == ButtonState.Pressed;
        }

        public bool WasLeftPressed()
        {
            return mouseState.LeftButton == ButtonState.Pressed && previousMouseState.LeftButton == ButtonState.Released;
        }

        public bool WasRightPressed()
        {
            return mouseState.RightButton == ButtonState.Pressed && previousMouseState.RightButton == ButtonState.Released;
        }

        public bool WasMiddlePressed()
        {
            return mouseState.MiddleButton == ButtonState.Pressed && previousMouseState.MiddleButton == ButtonState.Released;
        }

        public bool WasLeftReleased()
        {
            return mouseState.LeftButton == ButtonState.Released && previousMouseState.LeftButton == ButtonState.Pressed;
        }

        public bool WasRightReleased()
        {
            return mouseState.RightButton == ButtonState.Released && previousMouseState.RightButton == ButtonState.Pressed;
        }

        public bool WasMiddleReleased()
        {
            return mouseState.MiddleButton == ButtonState.Released && previousMouseState.MiddleButton == ButtonState.Pressed;
        }

        /// <summary>
        /// Gets the difference in scrolls from the previous update, then divides by 120
        /// </summary>
        /// <returns></returns>
        public float GetScrollAmount()
        {
            return (mouseState.ScrollWheelValue - previousMouseState.ScrollWheelValue) / 120;
        }
    }
}
