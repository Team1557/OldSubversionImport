using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework;

namespace Robots2D
{
    public class RenderInfo
    {
        private RenderMode _mode;

        /// <summary>
        /// The RenderMode of the object.
        /// If setting the Mode to Texture, the color is changed to white.
        /// </summary>
        public RenderMode Mode
        {
            get
            {
                return _mode;
            }
            set
            {
                if (value == RenderMode.Texture)
                {
                    Color = Color.White;
                }

                _mode = value;
            }
        }

        public Texture2D Texture
        {
            get;
            set;
        }

        /// <summary>
        /// The part of the image to draw
        /// </summary>
        public Rectangle SpriteSection
        {
            get;
            set;
        }

        /// <summary>
        /// The scaling applied to the texture, if TextureScale is .5,.5, then a 100x100 texture would be drawn as 50x50.
        /// </summary>
        public Vector2 TextureScale
        {
            get;
            set;
        }

        /// <summary>
        /// The tint applied to drawn sprites  (for simple, white objects that means the color of the drawn object)
        /// </summary>
        public Color Color
        {
            get;
            set;
        }

        public RenderInfo(RenderMode mode, Texture2D texture, Color color)
        {
            Mode = mode;
            Texture = texture;
            Color = color;
            TextureScale = Vector2.One;
        }

        public RenderInfo(RenderMode mode, Texture2D texture) : this(mode, texture, Color.White)
        {
        }

        public RenderInfo(RenderMode mode) : this(mode, null)
        {
        }

        public RenderInfo()
            : this(RenderMode.Box, null)
        {
        }
    }

    public enum RenderMode
    {
        Box,
        Circle,
        Texture,
        Sprite,
    }
}
