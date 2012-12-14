using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using FarseerGames.FarseerPhysics.Dynamics;
using FarseerGames.FarseerPhysics;
using FarseerGames.FarseerPhysics.Factories;
using FarseerGames.FarseerPhysics.Collisions;
using Microsoft.Xna.Framework.Input;
using FarseerGames.FarseerPhysics.Mathematics;

namespace Robots2D
{
    // TODO: Add auditory feedback when moving, that would make driving more satisfying.
    public class Robot
    {
        public List<RobotPart> Parts
        {
            get;
            set;
        }

        /// <summary>
        /// The robot's position.
        /// </summary>
        public Vector2 Position
        {
            get;
            set;
        }

        public Body Body
        {
            get;
            set;
        }

        public Geom BodyGeometry
        {
            get;
            set;
        }

        public bool Controllable
        {
            get;
            set;
        }

        public string Name
        {
            get;
            set;
        }

        /// <summary>
        /// The index of the controller in Game1.Joysticks
        /// </summary>
        public int ControllerID
        {
            get;
            set;
        }

        private Vector2 _bodySize;

        /// <summary>
        /// The size of the body.
        /// </summary>
        public Vector2 BodySize
        {
            get
            {
                return _bodySize;
            }
            set
            {
                _bodySize = value;
                // TODO: Make this update geometry size
            }
        }

        /// <summary>
        /// Used if no joystick is found.
        /// </summary>
        public RobotKeyboardController KeyboardController
        {
            get;
            set;
        }

        public RobotJoystickController JoystickController
        {
            get;
            set;
        }

        /// <summary>
        /// The robot's rotation, in degrees.
        /// </summary>
        public float Rotation
        {
            get;
            private set;
        }

        public float SpeedMultiplier
        {
            get;
            set;
        }
        
        public Robot(PhysicsSimulator simulator, Vector2 position, Vector2 size)
        {
            // Default to an always nonexistant controller
            ControllerID = -1;
            SpeedMultiplier = 1;
            
            Parts = new List<RobotPart>();
            Position = position;
            BodySize = size;

            Controllable = true;

            KeyboardController = new RobotKeyboardController(this, Game1.keyboard, Keys.Up, Keys.Down, Keys.Left, Keys.Right);
            JoystickController = new RobotJoystickController();
            
            Body = BodyFactory.Instance.CreateRectangleBody(size.X, size.Y, 10);
            Body.Position = Position;
            Body.IgnoreGravity = true;
            simulator.Add(Body);

            BodyGeometry = GeomFactory.Instance.CreateRectangleGeom(Body, BodySize.X, BodySize.Y);
            BodyGeometry.RestitutionCoefficient = 0.3f;
            BodyGeometry.FrictionCoefficient = 0.5f;
            
            BodyGeometry.OnCollision += OnRobotCollision;
            
            // Default .001f for both
            Body.LinearDragCoefficient = 80;
            Body.RotationalDragCoefficient = 100;
            Console.WriteLine(Body.RotationalDragCoefficient);

            simulator.Add(BodyGeometry);

        }

        DateTime lastClang = DateTime.Now;
        public bool OnRobotCollision(Geom a, Geom b, ContactList contacts)
        {
            float collideSpeed = (a.Body.LinearVelocity - b.Body.LinearVelocity).Length();
            float collideRot = (a.Body.AngularVelocity - b.Body.AngularVelocity);

            if (collideSpeed > 3f * SpeedMultiplier || collideRot > .02f)
            {
                if ((DateTime.Now - lastClang).TotalSeconds > 1)
                {
                    //float volume = Math.Min(1, Math.Max((collideSpeed - (3f * SpeedMultiplier))/30f, Math.Abs(collideRot - .02f) * 3));
                    float volume = Math.Min(1, Math.Abs((collideSpeed - (3f * SpeedMultiplier)) / 30f));
                    volume /= 4f;
                    Console.WriteLine("Volume: " + volume + ", collide: " + ((collideSpeed - (3f * SpeedMultiplier)) / 30f).ToString() + " rot: " + (Math.Abs(collideRot - .02f) * 3).ToString());
                    
                    Game1.MetalClangSound.Play(volume, ((float)Game1.Rand.NextDouble()*2-1)/1.5f, 0);
                    lastClang = DateTime.Now;
                }
            }

            // Clip through
            return !Game1.NoClip;
        }

        public void DrawCollision(GameTime time, Camera2D camera, SpriteBatch spriteBatch)
        {
            spriteBatch.Draw(Game1.WhiteTexture,
                camera.GetPosition(Body.Position * Game1.Scale * Game1.PhysicsScale),
                null,
                Color.FromNonPremultiplied(Color.HotPink.R, Color.HotPink.G, Color.HotPink.B, 128),
                rotation: Body.Rotation,
                origin: new Vector2(.5f, .5f), // == WhiteTexture.Width/2, WhiteTexture.Height/2
                scale: BodySize * Game1.Scale * Game1.PhysicsScale,
                effects: SpriteEffects.None,
                layerDepth: 1);
        }

        public void Draw(GameTime time, Camera2D camera, SpriteBatch spriteBatch)
        {
            foreach (RobotPart part in Parts)
            {
                part.Draw(time, this, camera, spriteBatch);
            }
        }

        public Vector2 GetForwardVector()
        {
            Vector3 forward = (Matrix.CreateTranslation(new Vector3(0, 0, 1)) * Matrix.CreateRotationY(-Rotation - MathHelper.PiOver2)).Left;
            return new Vector2(forward.X, forward.Z);
        }

        SlimDX.DirectInput.JoystickState previousState;
        List<string> pressedButtons = new List<string>();

        public void Update(GameTime time, SlimDX.DirectInput.Joystick joystick)
        {
            // TODO: Confirm that keyboard and joysticks work properly for controllers
            // TODO: Add joystick / keyboard select
            // TODO: Make custom joystick configs

            Vector2 forward = GetForwardVector();

            if (Controllable)
            {
                if (joystick == null)
                {
                    if (KeyboardController != null)
                    {
                        KeyboardController.Update(time);
                    }
                }
                else
                {
                    if (JoystickController != null)
                    {
                        JoystickController.Robot = this;
                        JoystickController.Joystick = joystick;
                        JoystickController.Update(time);
                    }
                }
            }

            foreach (RobotPart part in Parts)
            {
                part.Update(time);
            }

            this.Position = Body.Position * Game1.PhysicsScale;
            this.Rotation = Body.Rotation;
        }

        public void Update(GameTime time)
        {
            Update(time, null);
        }
    }
}
