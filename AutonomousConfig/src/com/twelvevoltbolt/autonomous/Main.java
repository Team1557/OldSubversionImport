package com.twelvevoltbolt.autonomous;

import java.util.logging.Level;
import java.util.logging.Logger;
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
        driveTime.setValue(AutonomousTables.getAutoNumber("DRIVE_TIME_1", 1 * 100) == 1 ? 100 : (int) (AutonomousTables.getAutoNumber("DRIVE_TIME_1", 0) * 100));
        driveTimeAfterFire.setValue(AutonomousTables.getAutoNumber("DRIVE_TIME_2", 1 * 100) == 1 ? 100 : (int) (AutonomousTables.getAutoNumber("DRIVE_TIME_2", 0) * 100));
        driveSpeed.setValue(AutonomousTables.getAutoNumber("DRIVE_SPEED", 1 * 100) == 1 ? 100 : (int) (AutonomousTables.getAutoNumber("DRIVE_SPEED", 0) * 100));
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
        lockValues = new javax.swing.JToggleButton();

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roborealmAimBox, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(driveForwardBox)
                            .addComponent(fireBallBox)
                            .addComponent(waitForHotGoalBox)
                            .addComponent(debugBox))
                        .addGap(26, 26, 26)))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(fireBallBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(driveForwardBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roborealmAimBox)
                .addGap(1, 1, 1)
                .addComponent(waitForHotGoalBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(debugBox)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        driveTimeLabel.setText("3 Seconds");

        driveTimeAfterFire.setMaximum(300);
        driveTimeAfterFire.setValue(300);
        driveTimeAfterFire.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                driveTimeAfterFireStateChanged(evt);
            }
        });

        driveTimeAfterFireLabel.setText("3 Seconds");

        jLabel3.setText("Drive Time After Firing:");

        jLabel1.setText("Drive Time:");

        driveTime.setMaximum(300);
        driveTime.setValue(300);
        driveTime.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                driveTimeStateChanged(evt);
            }
        });

        driveSpeedLabel.setText("Power of 1");

        jLabel10.setText("Drive Speed:");
        jLabel10.setToolTipText("");

        driveSpeed.setValue(100);
        driveSpeed.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                driveSpeedStateChanged(evt);
            }
        });

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
                        .addComponent(driveTimeLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(driveSpeedLabel))
                    .addComponent(driveSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel10)))
                .addGap(10, 10, 10))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(driveTimeAfterFire, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel3))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(driveTimeAfterFireLabel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(driveSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(driveSpeedLabel))
                            .addComponent(jLabel10))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(driveTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(driveTimeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(driveTimeAfterFire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(driveTimeAfterFireLabel)
                        .addGap(20, 20, 20))))
        );

        lockValues.setText("Lock Values");
        lockValues.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                lockValuesStateChanged(evt);
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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(16, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lockValues, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(214, 214, 214)
                        .addComponent(driveTime1Slider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(lockValues)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        driveTimeLabel.setText((driveTime.getValue() / 100.0) > 1.0 ? (driveTime.getValue() / 100.0) + " seconds" : (driveTime.getValue() / 100.0) + " second");
    }//GEN-LAST:event_driveTimeStateChanged

    private void driveTimeAfterFireStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_driveTimeAfterFireStateChanged
        try {
            AutonomousTables.setAutoNumber("DRIVE_TIME_2", driveTimeAfterFire.getValue() / 100.0);
        } catch (NotConnectedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        driveTimeAfterFireLabel.setText((driveTimeAfterFire.getValue() / 100.0) > 1.0 ? (driveTimeAfterFire.getValue() / 100.0) + " seconds" : (driveTimeAfterFire.getValue() / 100.0) + " second");
    }//GEN-LAST:event_driveTimeAfterFireStateChanged

    private void driveSpeedStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_driveSpeedStateChanged
        try {
            AutonomousTables.setAutoNumber("DRIVE_SPEED", driveSpeed.getValue() / 100.0);
        } catch (NotConnectedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        driveSpeedLabel.setText("Power of " + (driveSpeed.getValue() / 100.0));
    }//GEN-LAST:event_driveSpeedStateChanged

    private void lockValuesStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_lockValuesStateChanged
        if (lockValues.isSelected()) {
            fireBallBox.setEnabled(false);
            driveForwardBox.setEnabled(false);
            roborealmAimBox.setEnabled(false);
            waitForHotGoalBox.setEnabled(false);
            debugBox.setEnabled(false);
            driveTime.setEnabled(false);
            driveTimeAfterFire.setEnabled(false);
            driveSpeed.setEnabled(false);
        } else {
            fireBallBox.setEnabled(true);
            driveForwardBox.setEnabled(true);
            roborealmAimBox.setEnabled(true);
            waitForHotGoalBox.setEnabled(true);
            debugBox.setEnabled(true);
            driveTime.setEnabled(true);
            driveTimeAfterFire.setEnabled(true);
            driveSpeed.setEnabled(true);
        }
    }//GEN-LAST:event_lockValuesStateChanged

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
    private javax.swing.JCheckBox driveForwardBox;
    private javax.swing.JSlider driveSpeed;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JToggleButton lockValues;
    private javax.swing.JCheckBox roborealmAimBox;
    private javax.swing.JCheckBox waitForHotGoalBox;
    // End of variables declaration//GEN-END:variables
}
