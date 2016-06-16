/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickit;

import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * The main GUI for the ClickIt application.
 *
 * @author David
 * @version 23/03/2015
 */
public class MainGUI extends javax.swing.JFrame {

    protected final CameraList list;
    private final ServerConnection sc;

    /**
     * Creates new form MainGUI
     *
     * @param sc The server connection handler class.
     */
    public MainGUI(ServerConnection sc) {
        this.sc = sc;
        list = new CameraList();
        initComponents();
        this.setVisible(true);
        try {
            this.sc.connect(Settings.SERVER_ADDRESS, Settings.PORT);
            list.getListFromServer();
            this.updateList();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error connecting to server");
        }
    }

    /**
     * Method to update the lists.
     */
    public final void updateList() {
        System.out.println("Updating the list");

        lstName.removeAll();
        lstMegapixles.removeAll();
        lstSensor.removeAll();
        lstStock.removeAll();
        lstPrice.removeAll();

        String[] nameItems = new String[list.size()];
        String[] megapixlesItems = new String[list.size()];
        String[] sensorItems = new String[list.size()];
        String[] stockItems = new String[list.size()];
        String[] priceItems = new String[list.size()];

        for (int i = 0; i <= (nameItems.length - 1); i++) {
            nameItems[i] = list.getCamera(i).getMake() + " " + list.getCamera(i).getModel();
            megapixlesItems[i] = "" + list.getCamera(i).getMegapixles();
            sensorItems[i] = list.getCamera(i).getSensor();
            stockItems[i] = "" + list.getCamera(i).getStock();
            priceItems[i] = "£" + list.getCamera(i).getPrice();
        }

        lstName.setListData(nameItems);
        lstMegapixles.setListData(megapixlesItems);
        lstSensor.setListData(sensorItems);
        lstStock.setListData(stockItems);
        lstPrice.setListData(priceItems);

        System.out.println("List updated");
    }

    /**
     * Static method to add a new camera to the list of cameras.
     *
     * @param newCamera the new camera to be added as a Camera object.
     */
    public void addCamera(Camera newCamera) {
        try {
            sc.addCamera(newCamera);
            list.addCamera(newCamera);
            System.out.println("New camera added");
            this.updateList();
        } catch (CodeAlreadyExistsException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    /**
     * Method to delete a Camera from the list using the given index.
     *
     * @param index the index of the camera to be deleted as an int.
     */
    public void deleteCamera(int index) {
        String code = list.getCamera(index).getCode();

        try {
            sc.deleteCamera(code);
            list.removeCamera(code);
            System.out.println("Camera " + code + " deleted");
            this.updateList();
        } catch (CameraNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Camera " + code + " not found");
        } catch (Exception ex) {

        }
    }

    /**
     * Method to increase the stock level of a Camera using a given index.
     *
     * @param index the index of the Camera to have stock added as an int.
     */
    public void increaseStock(int index) {
        String code = list.getCamera(index).getCode();
        int stock;

        do {
            stock = Integer.parseInt(JOptionPane.showInputDialog("Please enter stock to be added"));
            if (stock < 0) {
                JOptionPane.showMessageDialog(this, "Please enter a number of 0 or greater");
                System.out.println("Number less than 0 entered");
            }
        } while (stock < 0);

        try {
            sc.increaceStock(code, stock);
            list.getCamera(index).increaceStock(stock);
            this.updateList();
        } catch (CameraNotFoundException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    /**
     * Method to purchase a Camera.
     *
     * @param index the index of the Camera to be purchased.
     */
    public void purchaseCamera(int index) {
        String code = list.getCamera(index).getCode();

        try {
            sc.purchaceCamera(code);
            JOptionPane.showMessageDialog(this, "Camera has been purchased");
            list.getCamera(index).setStock(sc.getStock(code));
            this.updateList();
        } catch (CameraNotFoundException e) {
            JOptionPane.showMessageDialog(this, code + " has not been found");
        } catch (OutOfStockException ex) {
            JOptionPane.showMessageDialog(this, code + " is out of stock");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lstMegapixles = new javax.swing.JList();
        btnAddCamera = new javax.swing.JButton();
        btnDeleteCamera = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstName = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstSensor = new javax.swing.JList();
        jScrollPane4 = new javax.swing.JScrollPane();
        lstStock = new javax.swing.JList();
        jScrollPane5 = new javax.swing.JScrollPane();
        lstPrice = new javax.swing.JList();
        btnIncreaseStock = new javax.swing.JButton();
        btnPurchaseCamera = new javax.swing.JButton();
        jLabelName = new javax.swing.JLabel();
        jLabelMegapixles = new javax.swing.JLabel();
        JLabelSensor = new javax.swing.JLabel();
        jLabelStock = new javax.swing.JLabel();
        jLabelPrice = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuSettings = new javax.swing.JMenuItem();
        JMenuExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        JMenuAddCamera = new javax.swing.JMenuItem();
        JMenuDeleteCamera = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ClickIt");

        lstMegapixles.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstMegapixles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstMegapixlesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lstMegapixles);

        btnAddCamera.setText("Add Camera");
        btnAddCamera.setActionCommand("addCamera");
        btnAddCamera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCameraActionPerformed(evt);
            }
        });

        btnDeleteCamera.setText("Delete Camera");
        btnDeleteCamera.setActionCommand("deleteCamera");
        btnDeleteCamera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteCameraActionPerformed(evt);
            }
        });

        lstName.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstNameMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(lstName);

        lstSensor.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstSensor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstSensorMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(lstSensor);

        lstStock.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstStockMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(lstStock);

        lstPrice.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstPrice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstPriceMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(lstPrice);

        btnIncreaseStock.setText("Increase Stock");
        btnIncreaseStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncreaseStockActionPerformed(evt);
            }
        });

        btnPurchaseCamera.setText("Purchase Camera");
        btnPurchaseCamera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPurchaseCameraActionPerformed(evt);
            }
        });

        jLabelName.setText("Name");

        jLabelMegapixles.setText("Megapixles");

        JLabelSensor.setText("Sensor");

        jLabelStock.setText("Stock");

        jLabelPrice.setText("Price");

        jMenu1.setText("File");

        jMenuSettings.setText("Settings");
        jMenuSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuSettingsActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuSettings);

        JMenuExit.setText("Exit");
        JMenuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuExitActionPerformed(evt);
            }
        });
        jMenu1.add(JMenuExit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        JMenuAddCamera.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        JMenuAddCamera.setText("Add new camera...");
        JMenuAddCamera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuAddCameraActionPerformed(evt);
            }
        });
        jMenu2.add(JMenuAddCamera);

        JMenuDeleteCamera.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        JMenuDeleteCamera.setText("Delete camera...");
        JMenuDeleteCamera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuDeleteCameraActionPerformed(evt);
            }
        });
        jMenu2.add(JMenuDeleteCamera);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAddCamera, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDeleteCamera)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnIncreaseStock)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPurchaseCamera))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelName))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelMegapixles))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JLabelSensor))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelStock))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelPrice)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelName)
                    .addComponent(jLabelMegapixles)
                    .addComponent(JLabelSensor)
                    .addComponent(jLabelStock)
                    .addComponent(jLabelPrice))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddCamera)
                    .addComponent(btnDeleteCamera)
                    .addComponent(btnIncreaseStock)
                    .addComponent(btnPurchaseCamera))
                .addContainerGap(120, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Method to listen for the add camera button being pressed and to add a new
     * camera to the list.
     *
     * @param evt the event of the button being pressed.
     */
    private void btnAddCameraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCameraActionPerformed
        AddCameraGUI newAddCameraGUI = new AddCameraGUI();
    }//GEN-LAST:event_btnAddCameraActionPerformed

    /**
     * Method to listen for the mouse being clicked within the lstName list box
     * and highlighting the same index in all the other list boxes.
     *
     * @param evt the event of the mouse being clicked.
     */
    private void lstNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstNameMouseClicked
        int index = this.lstName.getSelectedIndex();

        this.lstMegapixles.setSelectedIndex(index);
        this.lstSensor.setSelectedIndex(index);
        this.lstStock.setSelectedIndex(index);
        this.lstPrice.setSelectedIndex(index);
    }//GEN-LAST:event_lstNameMouseClicked

    /**
     * Method to listen for the mouse being clicked within the lstMegapixles
     * list box and highlighting the same index in all the other list boxes.
     *
     * @param evt the event of the mouse being clicked.
     */
    private void lstMegapixlesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstMegapixlesMouseClicked
        int index = this.lstMegapixles.getSelectedIndex();

        this.lstName.setSelectedIndex(index);
        this.lstSensor.setSelectedIndex(index);
        this.lstStock.setSelectedIndex(index);
        this.lstPrice.setSelectedIndex(index);
    }//GEN-LAST:event_lstMegapixlesMouseClicked

    /**
     * Method to listen for the mouse being clicked within the lstSensor list
     * box and highlighting the same index in all the other list boxes.
     *
     * @param evt the event of the mouse being clicked.
     */
    private void lstSensorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstSensorMouseClicked
        int index = this.lstSensor.getSelectedIndex();

        this.lstName.setSelectedIndex(index);
        this.lstMegapixles.setSelectedIndex(index);
        this.lstStock.setSelectedIndex(index);
        this.lstPrice.setSelectedIndex(index);
    }//GEN-LAST:event_lstSensorMouseClicked

    /**
     * Method to listen for the mouse being clicked within the lstStock list box
     * and highlighting the same index in all the other list boxes.
     *
     * @param evt the event of the mouse being clicked.
     */
    private void lstStockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstStockMouseClicked
        int index = this.lstStock.getSelectedIndex();

        this.lstName.setSelectedIndex(index);
        this.lstMegapixles.setSelectedIndex(index);
        this.lstSensor.setSelectedIndex(index);
        this.lstPrice.setSelectedIndex(index);
    }//GEN-LAST:event_lstStockMouseClicked

    /**
     * Method to listen for the mouse being clicked within the lstPrice list box
     * and highlighting the same index in all the other list boxes.
     *
     * @param evt the event of the mouse being clicked.
     */
    private void lstPriceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstPriceMouseClicked
        int index = this.lstPrice.getSelectedIndex();

        this.lstName.setSelectedIndex(index);
        this.lstMegapixles.setSelectedIndex(index);
        this.lstSensor.setSelectedIndex(index);
        this.lstStock.setSelectedIndex(index);
    }//GEN-LAST:event_lstPriceMouseClicked

    /**
     * Method to listen for the delete button being clicked.
     *
     * @param evt the event of the button being pressed.
     */
    private void btnDeleteCameraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteCameraActionPerformed
        int index = this.lstName.getSelectedIndex();
        if (index == -1) {
            System.out.println("No index selected");
            JOptionPane.showMessageDialog(this, "Please select a camera");
        } else {
            int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the selected camera?");
            if (option == 0) {
                this.deleteCamera(index);
            }
        }
    }//GEN-LAST:event_btnDeleteCameraActionPerformed

    /**
     * Method to listen for the increase button being clicked.
     *
     * @param evt the event of the mouse being clicked.
     */
    private void btnIncreaseStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncreaseStockActionPerformed
        int index = this.lstName.getSelectedIndex();
        if (index == -1) {
            System.out.println("No index selected");
            JOptionPane.showMessageDialog(this, "Please select a camera");
        } else {
            this.increaseStock(index);
        }
    }//GEN-LAST:event_btnIncreaseStockActionPerformed

    /**
     * Method to listen for the purchase button being clicked.
     *
     * @param evt the event of the mouse being clicked.
     */
    private void btnPurchaseCameraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPurchaseCameraActionPerformed
        int index = this.lstName.getSelectedIndex();
        if (index == -1) {
            System.out.println("No index selected");
            JOptionPane.showMessageDialog(this, "Please select a camera");
        } else {
            this.purchaseCamera(index);
        }
    }//GEN-LAST:event_btnPurchaseCameraActionPerformed

    /**
     * Method to listen for the exit option being selected in the menu bar.
     *
     * @param evt the event of the button being pressed.
     */
    private void JMenuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuExitActionPerformed
        int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to close the application?");

        if (option == 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.getCamera(i).getStock() == 0) {
                    JOptionPane.showMessageDialog(this, list.getCamera(i).getMake() + " " + list.getCamera(i).getModel() + " is out of stock");
                } else if (list.getCamera(i).getStock() < 3) {
                    JOptionPane.showMessageDialog(this, list.getCamera(i).getMake() + " " + list.getCamera(i).getModel() + " only has " + list.getCamera(i).getStock() + " left in stock");
                }
            }
            System.out.println("Closing ClickIt application");
            sc.terminateConnection();
            System.exit(0);
        }
    }//GEN-LAST:event_JMenuExitActionPerformed

    /**
     * Method to listen for the add camera option being selected in the menu
     * bar.
     *
     * @param evt the event of the button being pressed.
     */
    private void JMenuAddCameraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuAddCameraActionPerformed
        AddCameraGUI newAddCameraGUI = new AddCameraGUI();
    }//GEN-LAST:event_JMenuAddCameraActionPerformed

    /**
     * Method to listen for the delete camera option being selected in the menu
     * bar.
     *
     * @param evt the event of the button being pressed.
     */
    private void JMenuDeleteCameraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuDeleteCameraActionPerformed
        int index = this.lstName.getSelectedIndex();
        if (index == -1) {
            System.out.println("No index selected");
            JOptionPane.showMessageDialog(this, "Please select a camera");
        } else {
            int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the selected camera?");
            if (option == 0) {
                this.deleteCamera(index);
            }
        }
    }//GEN-LAST:event_JMenuDeleteCameraActionPerformed

    private void jMenuSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuSettingsActionPerformed
        new Settings().setVisible(true);
    }//GEN-LAST:event_jMenuSettingsActionPerformed

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new MainGUI().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLabelSensor;
    private javax.swing.JMenuItem JMenuAddCamera;
    private javax.swing.JMenuItem JMenuDeleteCamera;
    private javax.swing.JMenuItem JMenuExit;
    private javax.swing.JButton btnAddCamera;
    private javax.swing.JButton btnDeleteCamera;
    private javax.swing.JButton btnIncreaseStock;
    private javax.swing.JButton btnPurchaseCamera;
    private javax.swing.JLabel jLabelMegapixles;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelPrice;
    private javax.swing.JLabel jLabelStock;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuSettings;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JList lstMegapixles;
    private javax.swing.JList lstName;
    private javax.swing.JList lstPrice;
    private javax.swing.JList lstSensor;
    private javax.swing.JList lstStock;
    // End of variables declaration//GEN-END:variables
}
