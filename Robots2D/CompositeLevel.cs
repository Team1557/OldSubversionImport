using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FarseerGames.FarseerPhysics;
using FarseerGames.FarseerPhysics.Factories;
using FarseerGames.FarseerPhysics.Collisions;
using FarseerGames.FarseerPhysics.Dynamics;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;

namespace Robots2D
{
    public class CompositeLevel : TileLevel
    {
        private List<PhysicalObject> physicalObjects;

        // TODO: Finish integration with TileLevel
        private List<DrawableObject> topObjects;
        private List<DrawableObject> bottomObjects;

        public PhysicsSimulator Simulator
        {
            get;
            set;
        }

        public CompositeLevel(PhysicsSimulator simulator, Tile[,] tiles)
            : base(tiles)
        {
            physicalObjects = new List<PhysicalObject>();
            topObjects = new List<DrawableObject>();
            bottomObjects = new List<DrawableObject>();

            Simulator = simulator;
        }

        public void AddCollidable(PhysicalObject obj)
        {
            physicalObjects.Add(obj);
            obj.Simulate(Simulator);
        }

        public void AddNonCollidable(DrawableObject obj, DrawLevel level)
        {
            if (level == DrawLevel.AbovePhysicalObjects)
            {
                topObjects.Add(obj);
            }
            else if (level == DrawLevel.BelowPhysicalObjects)
            {
                bottomObjects.Add(obj);
            }
        }

        public new void Draw(GameTime time, Camera2D camera, SpriteBatch spriteBatch)
        {
            foreach (DrawableObject drawableObject in bottomObjects)
            {
                drawableObject.Draw(time, camera, spriteBatch);
            }

            foreach (PhysicalObject physObject in physicalObjects)
            {
                physObject.Draw(time, camera, spriteBatch);
            }

            foreach (DrawableObject drawableObject in topObjects)
            {
                drawableObject.Draw(time, camera, spriteBatch);
            }
        }

        public new void Update(GameTime time)
        {
        }
    }

    public enum DrawLevel 
    {
        AbovePhysicalObjects,
        BelowPhysicalObjects,
    }
}
