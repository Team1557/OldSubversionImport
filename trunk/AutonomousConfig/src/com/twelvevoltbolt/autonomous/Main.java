package com.twelvevoltbolt.autonomous;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main extends javax.swing.JFrame {

    public Main() {
        initComponents();
        AutonomousTables.init();
        fireBallBox.setSelected(AutonomousTables.getAutoBoolean("FIRE", true));
        driveForwardBox.setSelected(AutonomousTables.getAutoBoolean("DRIVE", true));
        roborealmAimBox.setSelected(AutonomousTables.getAutoBoolean("ROBOREALM", true));
        waitForHotGoalBox.setSelected(AutonomousTables.getAutoBoolean("WAIT_FOR_HOTGOAL", true));
        debugBox.setSelected(AutonomousTables.getAutoBoolean("DEBUG", false));
        driveTime.setValue(AutonomousTables.getAutoNumber("DRIVE_TIME_1", 0) == 0 ? 100 : (int) (AutonomousTables.getAutoNumber("DRIVE_TIME_1", 0) * 100));
        driveTimeAfterFire.setValue(AutonomousTables.getAutoNumber("DRIVE_TIME_2", 0) == 0 ? 100 : (int) (AutonomousTables.getAutoNumber("DRIVE_TIME_2", 0) * 100));
        driveSpeed.setValue(AutonomousTables.getAutoNumber("DRIVE_SPEED_1", 0) == 0 ? 100 : (int) (AutonomousTables.getAutoNumber("DRIVE_SPEED_1", 0) * 100));
        driveSpeedAfterFire.setValue(AutonomousTables.getAutoNumber("DRIVE_SPEED_2", 0) == 0 ? 100 : (int) (AutonomousTables.getAutoNumber("DRIVE_SPEED_2", 0) * 100));
        fireBallBoxStateChanged(null);
        driveForwardBoxStateChanged(null);
        roborealmAimBoxStateChanged(null);
        fireBallBoxStateChanged(null);
        waitForHotGoalBoxStateChanged(null);
        debugBoxStateChanged(null);
        driveTimeStateChanged(null);
        driveTimeAfterFireStateChanged(null);
        driveSpeedStateChanged(null);
        driveSpeedAfterFireStateChanged(null);
        this.setIconImage(new ImageIcon(this.getClass().getResource("icon.png")).getImage());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox2 = new javax.swing.JCheckBox();
        buttonGroup1 = new javax.swing.ButtonGroup();
        driveTime1Slider = new javax.swing.JSlider();
        jPanel1 = new javax.swing.JPanel();
        fireBallBox = new javax.swing.JCheckBox();
        driveForwardBox = new javax.swing.JCheckBox();
        debugBox = new javax.swing.JCheckBox();
        roborealmAimBox = new javax.swing.JCheckBox();
        waitForHotGoalBox = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        directionBox = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        driveTimeLabel = new javax.swing.JLabel();
        driveTimeAfterFire = new javax.swing.JSlider();
        driveTimeAfterFireLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        driveTime = new javax.swing.JSlider();
        driveSpeedLabel = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        driveSpeed = new javax.swing.JSlider();
        jLabel11 = new javax.swing.JLabel();
        driveSpeedAfterFire = new javax.swing.JSlider();
        driveSpeedAfterFireLabel = new javax.swing.JLabel();
        lockValues = new javax.swing.JToggleButton();
        resetButton = new javax.swing.JButton();

        jCheckBox2.setText("jCheckBox2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Autonomous Config");
        setResizable(false);

        driveTime1Slider.setMaximum(300);
        driveTime1Slider.setValue(300);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        fireBallBox.setText("Fire Ball");
        fireBallBox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                fireBallBoxStateChanged(evt);
            }
        });

        driveForwardBox.setText("Drive Forward");
        driveForwardBox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                driveForwardBoxStateChanged(evt);
            }
        });

        debugBox.setText("Debug Mode");
        debugBox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                debugBoxStateChanged(evt);
            }
        });

        roborealmAimBox.setText("Use RoboRealm Aiming");
        roborealmAimBox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                roborealmAimBoxStateChanged(evt);
            }
        });

        waitForHotGoalBox.setText("Wait for Hot Goal");
        waitForHotGoalBox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                waitForHotGoalBoxStateChanged(evt);
            }
        });

        jLabel2.setText("Location:");

        directionBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Left", "Middle", "Right", "Goalie Zone" }));
        directionBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                directionBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(roborealmAimBox, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(driveForwardBox)
                                        .addComponent(fireBallBox))
                                    .addGap(42, 42, 42)))
                            .addComponent(debugBox)
                            .addComponent(waitForHotGoalBox)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(directionBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fireBallBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(driveForwardBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roborealmAimBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(waitForHotGoalBox)
                .addGap(1, 1, 1)
                .addComponent(debugBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(directionBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        driveTimeLabel.setText("3 Seconds");

        driveTimeAfterFire.setMaximum(1000);
        driveTimeAfterFire.setValue(150);
        driveTimeAfterFire.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                driveTimeAfterFireStateChanged(evt);
            }
        });

        driveTimeAfterFireLabel.setText("3 Seconds");

        jLabel3.setText("Drive Time After Firing:");

        jLabel1.setText("Drive Time:");

        driveTime.setMaximum(1000);
        driveTime.setValue(150);
        driveTime.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                driveTimeStateChanged(evt);
            }
        });

        driveSpeedLabel.setText("Power of 1");

        jLabel10.setText("Drive Speed:");
        jLabel10.setToolTipText("");

        driveSpeed.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                driveSpeedStateChanged(evt);
            }
        });

        jLabel11.setText("Drive Speed After Firing:");
        jLabel11.setToolTipText("");

        driveSpeedAfterFire.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                driveSpeedAfterFireStateChanged(evt);
            }
        });

        driveSpeedAfterFireLabel.setText("Power of 1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jLabel1))
                            .addComponent(driveTime, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(driveTimeLabel))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(driveTimeAfterFire, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(driveTimeAfterFireLabel))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(driveSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(jLabel10)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(driveSpeedLabel)
                            .addGap(45, 45, 45)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(driveSpeedAfterFire, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(driveSpeedAfterFireLabel)
                            .addGap(45, 45, 45))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addGap(11, 11, 11))))
                .addGap(10, 10, 10))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(driveTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(driveTimeLabel))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(driveSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(driveSpeedLabel)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(driveTimeAfterFire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(driveTimeAfterFireLabel))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(driveSpeedAfterFire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(driveSpeedAfterFireLabel)))
                .addGap(11, 11, 11))
        );

        lockValues.setText("Lock Values");
        lockValues.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                lockValuesStateChanged(evt);
            }
        });

        resetButton.setText("Reset to Default");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(driveTime1Slider, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(resetButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lockValues, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(214, 214, 214)
                        .addComponent(driveTime1Slider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(lockValues)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(resetButton)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fireBallBoxStateChanged(javax.swing.event.ChangeEvent evt) {                                         
        try {
            AutonomousTables.setAutoBoolean("FIRE", fireBallBox.isSelected());
        } catch (NotConnectedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }                                           

    private void driveForwardBoxStateChanged(javax.swing.event.ChangeEvent evt) {                                             
        try {
            AutonomousTables.setAutoBoolean("DRIVE", driveForwardBox.isSelected());
        } catch (NotConnectedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }                                               

    private void roborealmAimBoxStateChanged(javax.swing.event.ChangeEvent evt) {                                             
        try {
            AutonomousTables.setAutoBoolean("ROBOREALM", roborealmAimBox.isSelected());
        } catch (NotConnectedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }                                               

    private void waitForHotGoalBoxStateChanged(javax.swing.event.ChangeEvent evt) {                                               
        try {
            AutonomousTables.setAutoBoolean("WAIT_FOR_HOTGOAL", waitForHotGoalBox.isSelected());
        } catch (NotConnectedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }                                                 

    private void debugBoxStateChanged(javax.swing.event.ChangeEvent evt) {                                      
        try {
            AutonomousTables.setAutoBoolean("DEBUG", debugBox.isSelected());
        } catch (NotConnectedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }                                        

    private void driveTimeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_driveTimeStateChanged
        try {
            AutonomousTables.setAutoNumber("DRIVE_TIME_1", driveTime.getValue() / 100.0);
        } catch (NotConnectedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        driveTimeLabel.setText((driveTime.getValue() / 100.0) != 1.0 ? (driveTime.getValue() / 100.0) + " seconds" : (driveTime.getValue() / 100.0) + " second");
    }//GEN-LAST:event_driveTimeStateChanged

    private void driveTimeAfterFireStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_driveTimeAfterFireStateChanged
        try {
            AutonomousTables.setAutoNumber("DRIVE_TIME_2", driveTimeAfterFire.getValue() / 100.0);
        } catch (NotConnectedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        driveTimeAfterFireLabel.setText((driveTimeAfterFire.getValue() / 100.0) != 1.0 ? (driveTimeAfterFire.getValue() / 100.0) + " seconds" : (driveTimeAfterFire.getValue() / 100.0) + " second");
    }//GEN-LAST:event_driveTimeAfterFireStateChanged

    private void driveSpeedStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_driveSpeedStateChanged
        try {
            AutonomousTables.setAutoNumber("DRIVE_SPEED_1", driveSpeed.getValue() / 100.0);
        } catch (NotConnectedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        driveSpeedLabel.setText("Power of " + (driveSpeed.getValue() / 100.0));
    }//GEN-LAST:event_driveSpeedStateChanged
	
	private void driveSpeedAfterFireStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_driveSpeedAfterFireStateChanged
        try {
            AutonomousTables.setAutoNumber("DRIVE_SPEED_2", driveSpeedAfterFire.getValue() / 100.0);
        } catch (NotConnectedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        driveSpeedAfterFireLabel.setText("Power of " + (driveSpeedAfterFire.getValue() / 100.0));
    }//GEN-LAST:event_driveSpeedAfterFireStateChanged

    private void lockValuesStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_lockValuesStateChanged
        if (lockValues.isSelected()) {
            resetButton.setEnabled(false);
            fireBallBox.setEnabled(false);
            driveForwardBox.setEnabled(false);
            roborealmAimBox.setEnabled(false);
            waitForHotGoalBox.setEnabled(false);
            debugBox.setEnabled(false);
            driveTime.setEnabled(false);
            driveTimeAfterFire.setEnabled(false);
            driveSpeed.setEnabled(false);
            driveSpeedAfterFire.setEnabled(false);
            directionBox.setEnabled(false);
        } else {
            resetButton.setEnabled(true);
            fireBallBox.setEnabled(true);
            driveForwardBox.setEnabled(true);
            roborealmAimBox.setEnabled(true);
            waitForHotGoalBox.setEnabled(true);
            debugBox.setEnabled(true);
            driveTime.setEnabled(true);
            driveTimeAfterFire.setEnabled(true);
            driveSpeed.setEnabled(true);
            driveSpeedAfterFire.setEnabled(true);
            directionBox.setEnabled(true);
        }
    }//GEN-LAST:event_lockValuesStateChanged

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        fireBallBox.setSelected(true);
        driveForwardBox.setSelected(true);
        roborealmAimBox.setSelected(true);
        waitForHotGoalBox.setSelected(true);
        debugBox.setSelected(false);
        driveTime.setValue(100);
        driveTimeAfterFire.setValue(100);
        driveSpeed.setValue(100);
        driveSpeedAfterFire.setValue(100);
    }//GEN-LAST:event_resetButtonActionPerformed

    private void directionBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_directionBoxActionPerformed
        try {
            Object selected = directionBox.getSelectedItem();
            if (selected.equals("Left")) {
                AutonomousTables.setAutoString("DIRECTION", "LEFT");
            } else if (selected.equals("Middle")) {
                AutonomousTables.setAutoString("DIRECTION", "MIDDLE");
            } else if (selected.equals("Right")) {
                AutonomousTables.setAutoString("DIRECTION", "RIGHT");
            } else {
                AutonomousTables.setAutoString("DIRECTION", "GOALIE");
            }           
        } catch (NotConnectedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_directionBoxActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            /* Create and display the form */
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox debugBox;
    private javax.swing.JComboBox directionBox;
    private javax.swing.JCheckBox driveForwardBox;
    private javax.swing.JSlider driveSpeed;
    private javax.swing.JSlider driveSpeedAfterFire;
    private javax.swing.JLabel driveSpeedAfterFireLabel;
    private javax.swing.JLabel driveSpeedLabel;
    private javax.swing.JSlider driveTime;
    private javax.swing.JSlider driveTime1Slider;
    private javax.swing.JSlider driveTimeAfterFire;
    private javax.swing.JLabel driveTimeAfterFireLabel;
    private javax.swing.JLabel driveTimeLabel;
    private javax.swing.JCheckBox fireBallBox;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JToggleButton lockValues;
    private javax.swing.JButton resetButton;
    private javax.swing.JCheckBox roborealmAimBox;
    private javax.swing.JCheckBox waitForHotGoalBox;
    // End of variables declaration//GEN-END:variables
}
