/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package autonomousconfig;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Mel
 */
public class Board extends JPanel implements ActionListener {
    
    int overallY = 139;
    int leftX = 36;
    int middleX = 181;
    int rightX = 339;
    Image bot = new ImageIcon(this.getClass().getResource("bot.png")).getImage();
    Image otherBot = new ImageIcon(this.getClass().getResource("otherBot.png")).getImage();
    Image background = new ImageIcon(this.getClass().getResource("background.png")).getImage();
    Timer timer = new Timer(25, this);
    
    public Board() {
        timer.start();
        System.out.println(this.getSize());
    }
    
    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        Graphics2D g = (Graphics2D) graphics;
        g.drawImage(background, 0, 0, null);
        String selected = Main.sideSelect.getSelectedItem().toString();
        if (selected.equals("Left")) {
            g.drawImage(bot, leftX, overallY, null);
        } else if (selected.equals("Middle")) {
            g.drawImage(bot, middleX, overallY, null);
        } else {
            g.drawImage(bot, rightX, overallY, null);
        }
        if (Main.leftOccupiedButton.isSelected()) {
            g.drawImage(otherBot, leftX, overallY, null);
        }
        if (Main.middleOccupiedButton.isSelected()) {
            g.drawImage(otherBot, middleX, overallY, null);
        }
        if (Main.rightOccupiedButton.isSelected()) {
            g.drawImage(otherBot, rightX, overallY, null);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        repaint();
    }
    
}
