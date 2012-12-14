using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Robots2D;

namespace Robots2D
{
    /// <summary>
    /// List of OPCodes:
    /// 
    /// -- Fun --
    /// Cheats menu
    ///     X X X Y Y Y B B B A A A
    /// 
    /// -- Functional --
    /// Debug view
    ///     YYAAXBXBBAStart
    /// 
    /// 
    /// 
    /// </summary>
    class CheatCode
    {
        /// <summary>
        /// Calls an CheatCode
        /// </summary>
        /// <param name="code"></param>
        public static void Call(string code)
        {
            Console.WriteLine("Calling OPCode \"" + code + "\"");

            if (code == "XXXYYYBBBAAA")
                Cheat(Robots2D.Cheat.DebugView);

            if (code == "YYAAXBXBBA")
            {
                Cheat(Robots2D.Cheat.CheatMenu);
            }
        }

        public static void Cheat(Cheat cheat)
        {
            if (cheat == Robots2D.Cheat.DebugView)
            {
                Game1.DebugView = !Game1.DebugView;
            }
            else if (cheat == Robots2D.Cheat.CheatMenu)
            {
                Game1.CheatMenu = !Game1.CheatMenu;
            }
            else if (cheat == Robots2D.Cheat.SpeedyGonzales)
            {
                foreach (Robot robot in Game1.robots)
                {
                    robot.SpeedMultiplier *= 3;
                }
            }
            else if (cheat == Robots2D.Cheat.SlowGonzales)
            {
                foreach (Robot robot in Game1.robots)
                {
                    robot.SpeedMultiplier /= 3;
                }
            }
            else if (cheat == Robots2D.Cheat.NoClip)
            {
                Game1.NoClip = !Game1.NoClip;
            }
            else if (cheat == Robots2D.Cheat.SmallScale)
            {
                if (Game1.Scale > 1)
                {
                    Game1.Scale--;
                }
                else
                {
                    Game1.Scale -= .2f;
                }
            }
            else if (cheat == Robots2D.Cheat.BigScale)
            {
                if (Game1.Scale >= 1)
                {
                    Game1.Scale++;
                }
                else
                {
                    Game1.Scale += .2f;
                }
            }
            else if (cheat == Robots2D.Cheat.Exit)
            {
                Game1.ShouldExit = true;
            }
        }
    }

    public enum Cheat
    {
        DebugView,
        CheatMenu,
        SpeedyGonzales,
        SlowGonzales,
        NoClip,
        SmallScale,
        BigScale,
        Exit,
    }
}
