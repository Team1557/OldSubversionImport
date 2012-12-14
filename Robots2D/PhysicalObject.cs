using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FarseerGames.FarseerPhysics;
using FarseerGames.FarseerPhysics.Collisions;
using FarseerGames.FarseerPhysics.Dynamics;
using FarseerGames.FarseerPhysics.Factories;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;

namespace Robots2D
{
    public class PhysicalObject : DrawableObject
    {
        public Body Body
        {
            get;
            set;
        }

        public Geom Geometry
        {
            get;
            set;
        }

        private Vector2 setPosition;
        /// <summary>
        /// If the Body is set, use it for getting the position.
        /// Otherwise, use the set position multiplied by the scale.
        /// </summary>
        public override Vector2 Position
        {
            get
            {
                if (Body != null)
                {
                    return Body.Position* Game1.Scale * Game1.PhysicsScale;
                }
                else
                {
                    return setPosition * Game1.Scale * Game1.PhysicsScale;
                }
            }

            set
            {
                setPosition = value;
            }
        }

        private float setRotation;
        public override float Rotation
        {
            get
            {
                if (Body != null)
                {
                    return Body.Rotation;
                }
                else
                {
                    return setRotation;
                }
            }
            set
            {
                setRotation = value;
            }
        }

        /// <summary>
        /// Note: the static members of PhysicalObject can be used to construct the PhysicalObject easier for simple shapes.
        /// </summary>
        public PhysicalObject(Body body, Geom geometry, Vector2 size, RenderInfo info)
        {
            Body = body;
            Geometry = geometry;
            Size = size;
            Info = info;
        }

        public PhysicalObject(Body body, Geom geometry, Vector2 size)
            : this(body, geometry, size, new RenderInfo())
        {
        }

        public PhysicalObject(Vector2 size, RenderInfo info)
            : this(null, null, size, info)
        {
        }

        public PhysicalObject(Vector2 size)
            : this(size, new RenderInfo())
        {
        }

        public void Simulate(PhysicsSimulator simulator)
        {
            simulator.Add(Body);
            simulator.Add(Geometry);
        }

        public static PhysicalObject FromBox(float width, float height, float mass)
        {
            Body body = BodyFactory.Instance.CreateRectangleBody(width, height, mass);
            Geom geom = GeomFactory.Instance.CreateRectangleGeom(body, width, height);

            PhysicalObject.ApplyDefaultPhysicalProperties(body, geom);

            return new PhysicalObject(body, geom, new Vector2(width, height));
        }

        public static PhysicalObject FromPolygon(int sides, float radius, float mass)
        {
            Body body = BodyFactory.Instance.CreateCircleBody(radius, mass);
            Geom geom = GeomFactory.Instance.CreateCircleGeom(body, radius, sides);

            PhysicalObject.ApplyDefaultPhysicalProperties(body, geom);

            return new PhysicalObject(body, geom, new Vector2(radius * 2, radius * 2));
        }

        public static PhysicalObject FromCircle(float radius, float mass)
        {
            return FromPolygon(15, radius, mass);
        }

        public static void ApplyDefaultPhysicalProperties(Body body, Geom geom)
        {
            // Receive less force from any collision objects
            geom.RestitutionCoefficient = 0.03f;
            geom.FrictionCoefficient = 0.05f;

            // Slow down quickly
            body.LinearDragCoefficient = 8;
            body.RotationalDragCoefficient = 10;

            // Use 2d "top view"
            //body.IgnoreGravity = true;
        }

        public virtual void Draw(GameTime time, Camera2D camera, SpriteBatch spriteBatch)
        {
            base.Draw(time, camera, spriteBatch);
        }

    }
}
