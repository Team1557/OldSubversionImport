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
    /// <summary>
    /// This is the main type for your game
    /// </summary>
    public class Game1 : Microsoft.Xna.Framework.Game
    {
        // Only set if ABSOLUTELY needed, unnecessary setting will make the scaling jerky.
        public static float _scale = 3f;
        private static float _desiredScale = _scale;

        public static float MaximumScale;

        /// <summary>
        /// Setting this sets the _Desired_ scale, getting this gets the _Current_ lerped scale.
        /// </summary>
        public static float Scale
        {
            get
            {
                if (_scale > MaximumScale)
                {
                    _scale = MaximumScale;
                }
                
                return _scale;
            }
            set
            {
                _desiredScale = value;

                if (_desiredScale > MaximumScale)
                {
                    _desiredScale = MaximumScale;
                }
            }
        }

        /// <summary>
        /// How the size of a Physics object is multiplyed.
        /// Size in onscreen pixels = Object.Scale * Game1.Scale * Game1.PhysicsScale
        /// Should remain a CONSTANT.
        /// </summary>
        public static readonly float PhysicsScale = 10;
        public static bool DebugView = false;
        public static bool CheatMenu = false;
        public static int SelectedCheat = 0;
        public static bool NoClip = false;
        public static bool ShouldExit = false;

        public static Random Rand = new Random();

        GraphicsDeviceManager graphics;
        SpriteBatch spriteBatch;
        SpriteBatch guiSpriteBatch;

        public static List<Robot> robots;
        public static Camera2D camera;

        public static Texture2D PartGridTexture;
        public static SpriteFont RobotFont;
        public static Texture2D WhiteTexture;
        public static Texture2D RobotTourneyTexture;
        public static SoundEffect MetalClangSound;
        public static Texture2D ArenaCenterTexture;
        public static Texture2D FIRSTTexture;
        public static Texture2D WorldGridTexture;

        public static List<GuiObject> guiObjects;

        public static PhysicsSimulator simulator;
        public static List<PhysicalObject> physicalObjects;
        
        /*PhysicalObject sampleBody;
        PhysicalObject sampleCollider;
        PhysicalObject tourneySign;
        PhysicalObject controller;*/

        public static KeyboardManager keyboard;
        public static MouseManager mouse;
        public static JoystickManager joystickManager;

        public Game1()
        {
            joystickManager = new JoystickManager();
            joystickManager.LoadJoysticks();

            this.Exiting += new EventHandler<EventArgs>(Game1_Exiting);
            simulator = new PhysicsSimulator(new Vector2(0, 5));

            robots = new List<Robot>();
            camera = new Camera2D(this);
            
            graphics = new GraphicsDeviceManager(this);
            Window.AllowUserResizing = true;
            graphics.PreferredBackBufferWidth = 960;
            graphics.PreferredBackBufferHeight = 720;

            keyboard = new KeyboardManager();
            mouse = new MouseManager();

            physicalObjects = new List<PhysicalObject>();
            guiObjects = new List<GuiObject>();

            Content.RootDirectory = "Content";
        }

        void Game1_Exiting(object sender, EventArgs e)
        {
        }

        /// <summary>
        /// Allows the game to perform any initialization it needs to before starting to run.
        /// This is where it can query for any required services and load any non-graphic
        /// related content.  Calling base.Initialize will enumerate through any components
        /// and initialize them as well.
        /// </summary>
        protected override void Initialize()
        {
            base.Initialize();
        }

        //level v1 PixelLevel level;
        //level v2 CompositeLevel level;
        //level v3
        TileLevel level;
        public void InitializeAfterContent()
        {
            MaximumScale = 3;
            
            RobotPart.SpriteMap = PartGridTexture;
            
            //level v1 level = PixelLevel.FromFile("level1.png", new Vector2(-50, -60), 2);
            //level v1 level.LoadLevel(simulator);

            //level v2 level = new CompositeLevel(simulator);
            //level v2 PhysicalObject physObj = PhysicalObject.FromBox(3, 3, 1);
            //level v2 level.AddCollidable(physObj);

            //level v3
            /*
            Tile[,] tiles = new Tile[14, 6];
            tiles[0, 0] = Tile.TileNames["Soccer 2nd Quad Turn"];
            tiles[1, 0] = Tile.TileNames["Soccer Horizontal Line"];
            tiles[2, 0] = Tile.TileNames["Soccer Horizontal Line"];
            tiles[3, 0] = Tile.TileNames["Soccer Horizontal Line"];
            tiles[4, 0] = Tile.TileNames["Soccer Horizontal Line"];
            tiles[5, 0] = Tile.TileNames["Soccer Horizontal Line"];
            tiles[6, 0] = Tile.TileNames["Soccer Bottom Intersect"];
            tiles[7, 0] = Tile.TileNames["Soccer Horizontal Line"];
            tiles[8, 0] = Tile.TileNames["Soccer Horizontal Line"];
            tiles[9, 0] = Tile.TileNames["Soccer Horizontal Line"];
            tiles[10, 0] = Tile.TileNames["Soccer Horizontal Line"];
            tiles[11, 0] = Tile.TileNames["Soccer Horizontal Line"];
            tiles[12, 0] = Tile.TileNames["Soccer Horizontal Line"];
            tiles[13, 0] = Tile.TileNames["Soccer 3rd Quad Turn"];
            level = new TileLevel(tiles);
            
            */
            level = TileLevel.FromAsciiFile("level1.txt");
            level.LoadLevel(simulator);
            
            // Robots
            {
                Robot mainRobot = new Robot(simulator, new Vector2(10, -10), new Vector2(2.3f, 2.5f));
                mainRobot.Parts.Add(new OscillatingRobotPart(new List<Vector2>() { RobotPart.PartNames["Wheel1"], RobotPart.PartNames["Wheel2"] }));
                mainRobot.Parts.Add(new OscillatingRobotPart(new List<Vector2>() { RobotPart.PartNames["Wheel1"], RobotPart.PartNames["Wheel2"] }, new Vector2(10, 0)));
                mainRobot.Parts.Add(new OscillatingRobotPart(new List<Vector2>() { RobotPart.PartNames["Wheel1"], RobotPart.PartNames["Wheel2"] }, new Vector2(-10, 0)));
                mainRobot.Parts.Add(new RobotPart("Metal Separator"));
                mainRobot.Parts.Add(new RobotPart("Construction Padding"));
                mainRobot.Parts.Add(new RobotPart("Circuit Board"));
                
                mainRobot.Body.Rotation = MathHelper.Pi;
                mainRobot.ControllerID = 0;
                robots.Add(mainRobot);
            }
            //*
            {
                Robot mainRobot = new Robot(simulator, new Vector2(5, -10), new Vector2(2.3f, 2.5f));
                mainRobot.Parts.Add(new OscillatingRobotPart(new List<Vector2>() { RobotPart.PartNames["Wheel1"], RobotPart.PartNames["Wheel2"] }));
                mainRobot.Parts.Add(new OscillatingRobotPart(new List<Vector2>() { RobotPart.PartNames["Wheel1"], RobotPart.PartNames["Wheel2"] }, new Vector2(10, 0)));
                mainRobot.Parts.Add(new OscillatingRobotPart(new List<Vector2>() { RobotPart.PartNames["Wheel1"], RobotPart.PartNames["Wheel2"] }, new Vector2(-10, 0)));
                mainRobot.Parts.Add(new RobotPart("Metal Separator"));
                mainRobot.Parts.Add(new RobotPart("Blue Padding"));
                mainRobot.Parts.Add(new RobotPart("Circuit Board"));

                mainRobot.KeyboardController.Forward = Keys.W;
                mainRobot.KeyboardController.Backward = Keys.S;
                mainRobot.KeyboardController.Left = Keys.A;
                mainRobot.KeyboardController.Right = Keys.D;

                mainRobot.ControllerID = 2;
                robots.Add(mainRobot);
            }

            /*
            Robot secondRobot = new Robot(simulator, new Vector2(-10, -10), new Vector2(2.3f, 2.5f));
            secondRobot.Parts.Add(new OscillatingRobotPart(new List<Vector2>() { RobotPart.PartNames["Tread1"], RobotPart.PartNames["Tread2"] }));
            secondRobot.Parts.Add(new RobotPart("Metal Separator"));
            secondRobot.Parts.Add(new RobotPart("Red Padding"));
            secondRobot.Parts.Add(new RobotPart("Circuit Board"));
            
            secondRobot.KeyboardController.Forward = Keys.W;
            secondRobot.KeyboardController.Backward = Keys.S;
            secondRobot.KeyboardController.Left = Keys.A;
            secondRobot.KeyboardController.Right = Keys.D;

            robots.Add(secondRobot);

            secondRobot.ControllerID = 1;
            */

            mouse.OnLeftMouseDown += new EventHandler(mouse_OnLeftMouseDown);

            // Physical Objects
            /*
            sampleBody = PhysicalObject.FromBox(100, 1, 1);
            sampleBody.Body.Position = new Vector2(0, 1);
            sampleBody.Body.IsStatic = true;
            sampleBody.Simulate(simulator);

            sampleCollider = PhysicalObject.FromBox(1, 1, 1);
            sampleCollider.Body.Position = new Vector2(0, -10);
            sampleCollider.Body.AngularVelocity = 3;
            sampleCollider.Simulate(simulator);

            tourneySign = PhysicalObject.FromBox(246 / PhysicsScale, 56 / PhysicsScale, 100);
            tourneySign.Info.Mode = RenderMode.Texture;
            tourneySign.Info.Texture = RobotTourneyTexture;
            tourneySign.Body.Position = new Vector2(0, -20);
            tourneySign.Simulate(simulator);

            controller = PhysicalObject.FromCircle((91 / 2) / PhysicsScale, 1);
            controller.Info.Mode = RenderMode.Texture;
            controller.Info.Texture = ControllerTexture;
            controller.Body.Position = new Vector2(0, -35);
            controller.Info.TextureScale = new Vector2(1, 74f / 91f);
            controller.Simulate(simulator);

            physicalObjects.Add(sampleBody);
            physicalObjects.Add(sampleCollider);
            physicalObjects.Add(tourneySign);
            physicalObjects.Add(controller);
            */

            GuiImage gui = new GuiImage(FIRSTTexture);
            gui.Name = "TestParent";
            //gui.Position = new UniversalPosition(new Vector2(1, 1), gui.Size * -2);
            gui.Size /= 10;
            gui.Position = new UniversalPosition(new Vector2(0, 0));
            guiObjects.Add(gui);

            /*GuiImage gui2 = new GuiImage(PartGridTexture);
            gui2.Name = "TestChild";
            gui2.Parent = gui;
            gui2.Position = new UniversalPosition(new Vector2(1, 1), new Vector2(-100, -100));
            guiObjects.Add(gui2);*/
        }

        void mouse_OnLeftMouseDown(object sender, EventArgs e)
        {
            Console.WriteLine("LeftMouseButton down!");
        }

        /// <summary>
        /// LoadContent will be called once per game and is the place to load
        /// all of your content.
        /// </summary>
        protected override void LoadContent()
        {
            // Create a new SpriteBatch, which can be used to draw textures.
            spriteBatch = new SpriteBatch(GraphicsDevice);
            guiSpriteBatch = new SpriteBatch(GraphicsDevice);

            RobotFont = Content.Load<SpriteFont>("RobotFont");
            PartGridTexture = Content.Load<Texture2D>("PartGrid");
            RobotTourneyTexture = Content.Load<Texture2D>("RobotTourney");
            MetalClangSound = Content.Load<SoundEffect>("MetalClang");
            ArenaCenterTexture = Content.Load<Texture2D>("ArenaCenter");
            FIRSTTexture = Content.Load<Texture2D>("FIRST");
            WorldGridTexture = Content.Load<Texture2D>("WorldGrid");
            
            
            WhiteTexture = new Texture2D(this.GraphicsDevice, 1, 1);
            WhiteTexture.SetData(new Color[] { Color.White });

            InitializeAfterContent();
        }

        /// <summary>
        /// UnloadContent will be called once per game and is the place to unload
        /// all content.
        /// </summary>
        protected override void UnloadContent()
        {
            joystickManager.ClearJoysticks();
            level.UnloadLevel();
        }

        /// <summary>
        /// Allows the game to run logic such as updating the world,
        /// checking for collisions, gathering input, and playing audio.
        /// </summary>
        /// <param name="gameTime">Provides a snapshot of timing values.</param>
        protected override void Update(GameTime gameTime)
        {
            keyboard.Update();
            mouse.Update();

            // Allows the game to exit
            if (GamePad.GetState(PlayerIndex.One).Buttons.Back == ButtonState.Pressed || ShouldExit)
                this.Exit();

            float newScale = MathHelper.Lerp(_scale, _desiredScale, .05f);
            if (Math.Abs(newScale - _scale) < (_desiredScale * .00001f))
            {
                newScale = _desiredScale;
            }
            
            camera.Position /= (_scale / newScale);
            _scale = newScale;

            //camera.DesiredPosition = robots[0].Position * Game1.Scale;
            camera.Update(gameTime);

            simulator.Update((float)gameTime.ElapsedGameTime.TotalSeconds);

            if (keyboard.IsKeyDown(Keys.Escape))
            {
                this.Exit();
            }

            if (keyboard.WasPressed(Keys.F3))
            {
                DebugView = !DebugView;
            }

            if (keyboard.WasPressed(Keys.R) && keyboard.IsKeyDown(Keys.LeftControl))
            {
                level.UnloadLevel();
                level = TileLevel.FromAsciiFile("level1.txt");
                level.LoadLevel(simulator);
            }

            if (keyboard.WasPressed(Keys.OemOpenBrackets))
            {
                Scale *= .75f;
            }

            if (keyboard.WasPressed(Keys.OemCloseBrackets))
            {
                Scale /= .75f;
            }

            foreach (Robot robot in robots)
            {
                SlimDX.DirectInput.Joystick joystick = null;
                if (joystickManager.Joysticks.Count > robot.ControllerID && robot.ControllerID >= 0)
                {
                    joystick = joystickManager.Joysticks[robot.ControllerID];
                }
                robot.Update(gameTime, joystick);
            }

            camera.CenterCameraOnWindow();
            camera.FocusPoints.Clear();
            foreach (Robot robot in robots)
            {
                camera.FocusPoints.Add(robot.Position * Scale);
            }
            camera.SetDesiredPositionToFocusPoints();
            camera.SetScaleToFocusPoints();
                
            base.Update(gameTime);
        }

        public static string DebugText="";

        /// <summary>
        /// This is called when the game should draw itself.
        /// </summary>
        /// <param name="gameTime">Provides a snapshot of timing values.</param>
        protected override void Draw(GameTime gameTime)
        {
            GraphicsDevice.Clear(Color.White);
            
            spriteBatch.Begin(SpriteSortMode.Immediate, BlendState.AlphaBlend, SamplerState.PointClamp, DepthStencilState.None, RasterizerState.CullCounterClockwise);
            //spriteBatch.Begin(SpriteSortMode.Immediate, BlendState.AlphaBlend, SamplerState.LinearClamp, DepthStencilState.None, RasterizerState.CullCounterClockwise);

            level.Draw(gameTime, camera, spriteBatch);

            foreach (PhysicalObject physObject in physicalObjects)
                physObject.Draw(gameTime, camera, spriteBatch);

            foreach (Robot robot in robots)
            {
                robot.Draw(gameTime, camera, spriteBatch);
                if (DebugView)
                    robot.DrawCollision(gameTime, camera, spriteBatch);
            }

            spriteBatch.End();


            
            //guiSpriteBatch.Begin();
            guiSpriteBatch.Begin(SpriteSortMode.Immediate, BlendState.AlphaBlend, SamplerState.LinearClamp, DepthStencilState.None, RasterizerState.CullCounterClockwise);

            foreach (GuiObject guiObject in guiObjects)
                guiObject.Draw(gameTime, guiSpriteBatch, new Vector2(this.Window.ClientBounds.Width, this.Window.ClientBounds.Height));

            // TODO: Create a Menu class, and make cheatmenu into one.
            if (CheatMenu)
            {
                guiSpriteBatch.DrawString(RobotFont, "Cheat Menu", new Vector2(10, 10), Color.Black);
                
                int cheatNum = 0;
                foreach (Cheat cheat in Enum.GetValues(typeof(Cheat)))
                {
                    bool highlightedCheat = false;
                    if (SelectedCheat == cheatNum)
                        highlightedCheat = true;

                    guiSpriteBatch.DrawString(RobotFont, cheat.ToString(), new Vector2(20, 10 + (cheatNum + 1) * 14), highlightedCheat ? Color.Red : Color.Black);
                    cheatNum++;
                }
            }

            DebugText = Scale.ToString();
            guiSpriteBatch.DrawString(RobotFont, DebugText, new Vector2(10,10), Color.Black);

            guiSpriteBatch.End();

            base.Draw(gameTime);
        }
    }
}
