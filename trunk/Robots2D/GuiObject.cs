using System;
using System.Collections.Generic;
using System.Linq;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Audio;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.GamerServices;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;
using Microsoft.Xna.Framework.Media;

using FarseerGames.FarseerPhysics;
using FarseerGames.FarseerPhysics.Collisions;
using FarseerGames.FarseerPhysics.Controllers;
using FarseerGames.FarseerPhysics.Dynamics;
using FarseerGames.FarseerPhysics.Factories;
using FarseerGames.FarseerPhysics.Interfaces;
using FarseerGames.FarseerPhysics.Mathematics;
using Microsoft.Xna.Framework.Net;

namespace Robots2D
{
    public class GuiObject
    {
        GuiObject _parent;
        public GuiObject Parent
        {
            get
            {
                return _parent;
            }
            set
            {
                _parent = value;

                if (_parent != null)
                {
                    if (!_parent.Children.Contains(this))
                    {
                        _parent.Children.Add(this);
                    }
                }
            }
        }

        public List<GuiObject> Children
        {
            get;
            set;
        }

        public string Name
        {
            get;
            set;
        }

        public object Tag
        {
            get;
            set;
        }

        public UniversalPosition Position
        {
            get;
            set;
        }

        public Vector2 Size
        {
            get;
            set;
        }

        public Texture2D Background
        {
            get;
            set;
        }

        public GuiObject()
        {
            Parent = null;
            Children = new List<GuiObject>();
            Position = UniversalPosition.Zero;

        }

        public virtual void Update(GameTime time)
        {

        }

        public virtual void Draw(GameTime time, SpriteBatch spriteBatch, Vector2 globalSize)
        {
            if (Background != null)
            {
                Vector2 position = GetAbsolutePosition(globalSize);
                spriteBatch.Draw(Background, new Rectangle((int)position.X, (int)position.Y, (int)Size.X, (int)Size.Y), Color.White);
            }
        }

        public Vector2 GetAbsolutePosition(Vector2 globalSize)
        {
            Vector2 absPosition;
            if (this.Parent == null)
            {
                absPosition = UniversalPosition.Resolve(Position, globalSize);
                return absPosition;
            }
            else
            {
                absPosition = UniversalPosition.Resolve(Position, Parent.Size);
                Vector2 x = this.Parent.GetAbsolutePosition(globalSize) + absPosition;
                return this.Parent.GetAbsolutePosition(globalSize) + absPosition;
            }
        }

        public bool IsMouseOver(Vector2 globalSize, Vector2 mousePosition)
        {
            Vector2 absPosition = GetAbsolutePosition(globalSize);
            return ((mousePosition.X >= absPosition.X) && (mousePosition.Y >= absPosition.Y) && (mousePosition.X <= absPosition.X + Size.X) && (mousePosition.Y <= absPosition.Y + Size.Y));
        }

        private bool _mouseWasOver = false;
        public void MouseMove(Vector2 globalSize, Vector2 mousePosition)
        {
            if (IsMouseOver(globalSize, mousePosition))
            {
                if (!_mouseWasOver)
                {
                    _mouseWasOver = true;
                    if (OnMouseEnter != null)
                        OnMouseEnter(this, EventArgs.Empty);
                }

                // Mouse is over, and the mouse moved.
                if (OnMouseMoved != null)
                    OnMouseMoved(this, EventArgs.Empty);
            }
            else
            {
                if (_mouseWasOver)
                {
                    _mouseWasOver = false;
                    if (OnMouseLeave != null)
                        OnMouseLeave(this, EventArgs.Empty);
                }
            }
        }

        public void MouseButtonChanged(Vector2 globalSize, Vector2 mousePosition, MouseState state)
        {
            
        }

        public event EventHandler OnMouseEnter;
        public event EventHandler OnMouseLeave;
        public event EventHandler OnMouseMoved;
        public event EventHandler OnMouseLeftDown;
        public event EventHandler OnMouseLeftUp;
        public event EventHandler OnMouseRightDown;
        public event EventHandler OnMouseRightUp;
        public event EventHandler OnMouseMiddleDown;
        public event EventHandler OnMouseMiddleUp;
    }
}
