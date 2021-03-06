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
public class MainGUIOLD extends javax.swing.JFrame {

    private final ProductList list;
    private final ServerConnection sc;

    /**
     * Creates new form MainGUI
     *
     * @param sc The server connection handler class.
     * @param list The ProductList class.
     */
    public MainGUIOLD(ServerConnection sc, ProductList list) {
        this.sc = sc;
        this.list = list;
        initComponents();
        this.setVisible(true);
        connectToServer(sc);
    }

    /**
     * Method to update the list boxes with the latest server data.
     */
    public void updateListings() {
        list.getListFromServer();
        this.updateList();
    }

    /**
     * Method to make a connection to the server.
     *
     * @param sc the ServerConnection object.
     */
    public final void connectToServer(ServerConnection sc) {
        try {
            sc.connect(Settings.SERVER_ADDRESS, Settings.PORT);
            list.getListFromServer();
            this.updateList();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error connecting to server");
        }
    }

    /**
     * Method to update the list.
     */
    public final void updateList() {
        System.out.println("Updating the list");

        lstItems.removeAll();

        String[] cameraItems = new String[list.camerasSize()];

        for (int i = 0; i <= (cameraItems.length - 1); i++) {
            cameraItems[i] = "<html><pre>" + list.getCamera(i).getMake() + " " + list.getCamera(i).getModel() + ((list.getCamera(i).getMake() + " " + list.getCamera(i).getModel()).length() < 17 ? "\t\t" : "\t") + list.getCamera(i).getMegapixles() + "\t" + list.getCamera(i).getSensor() + "\t\t" + list.getCamera(i).getStock() + "\t" + list.getCamera(i).getPrice() + "</pre></html>";
            System.out.println("" + list.getCamera(i).getMake() + " " + list.getCamera(i).getModel() + ((list.getCamera(i).getMake() + " " + list.getCamera(i).getModel()).length() < 17 ? "\t\t" : "\t") + list.getCamera(i).getMegapixles() + "\t" + list.getCamera(i).getSensor() + "\t" + list.getCamera(i).getStock() + "\t" + list.getCamera(i).getPrice());
        }

        lstItems.setListData(cameraItems);

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
            updateListings();
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
            list.removeProduct(code);
            System.out.println("Camera " + code + " deleted");
            updateListings();
        } catch (ProductNotFoundException e) {
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
            updateListings();
        } catch (ProductNotFoundException e) {
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
            sc.purchaseCamera(code);
            JOptionPane.showMessageDialog(this, "Camera has been purchased");
            list.getCamera(index).setStock(sc.getStock(code));
            updateListings();
        } catch (ProductNotFoundException e) {
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

        btnAddCamera = new javax.swing.JButton();
        btnDeleteCamera = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstItems = new javax.swing.JList();
        btnIncreaseStock = new javax.swing.JButton();
        btnPurchaseCamera = new javax.swing.JButton();
        jLabelName = new javax.swing.JLabel();
        jLabelMegapixles = new javax.swing.JLabel();
        JLabelSensor = new javax.swing.JLabel();
        jLabelStock = new javax.swing.JLabel();
        jLabelPrice = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuUpdateListings = new javax.swing.JMenuItem();
        jMenuSettings = new javax.swing.JMenuItem();
        JMenuExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        JMenuAddCamera = new javax.swing.JMenuItem();
        JMenuDeleteCamera = new javax.swing.JMenuItem();
        jMenuIncreaseStock = new javax.swing.JMenuItem();
        jMenuPurchaseCamera = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ClickIt");

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

        lstItems.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstItems.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstItemsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(lstItems);

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

        jMenuUpdateListings.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        jMenuUpdateListings.setText("Update Listings");
        jMenuUpdateListings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuUpdateListingsActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuUpdateListings);

        jMenuSettings.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
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

        jMenuIncreaseStock.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jMenuIncreaseStock.setText("Increase Stock");
        jMenuIncreaseStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuIncreaseStockActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuIncreaseStock);

        jMenuPurchaseCamera.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMenuPurchaseCamera.setText("Purhcase Camera");
        jMenuPurchaseCamera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuPurchaseCameraActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuPurchaseCamera);

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
                        .addComponent(jLabelName)
                        .addGap(71, 71, 71)
                        .addComponent(jLabelMegapixles)
                        .addGap(45, 45, 45)
                        .addComponent(JLabelSensor)
                        .addGap(65, 65, 65)
                        .addComponent(jLabelStock)
                        .addGap(72, 72, 72)
                        .addComponent(jLabelPrice)
                        .addGap(57, 57, 57))
                    .addComponent(jScrollPane2))
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        //AddCameraGUI newAddCameraGUI = new AddCameraGUI(this);
    }//GEN-LAST:event_btnAddCameraActionPerformed

    /**
     * Method to listen for the mouse being clicked within the lstName list box
     * and highlighting the same index in all the other list boxes.
     *
     * @param evt the event of the mouse being clicked.
     */
    private void lstItemsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstItemsMouseClicked
        if(evt.getClickCount() == 2){
            //new CameraDetails(list.getCamera(lstItems.getSelectedIndex()), lstItems.getSelectedIndex(), this).setVisible(true);
        }
    }//GEN-LAST:event_lstItemsMouseClicked

    /**
     * Method to listen for the delete button being clicked.
     *
     * @param evt the event of the button being pressed.
     */
    private void btnDeleteCameraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteCameraActionPerformed
        int index = this.lstItems.getSelectedIndex();
        if (index == -1) {
            String code = JOptionPane.showInputDialog("Enter product code to delete");
            if (!code.equals("")) {
                try {
                    sc.deleteCamera(code);
                    list.removeProduct(code);
                    System.out.println("Camera " + code + " deleted");
                    updateListings();
                } catch (ProductNotFoundException e) {
                    JOptionPane.showMessageDialog(this, "Camera " + code + " not found");
                } catch (Exception ex) {
                }
            }
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
        int index = this.lstItems.getSelectedIndex();
        if (index == -1) {
            String code = JOptionPane.showInputDialog("Enter product code");
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
                list.getCamera(code).increaceStock(stock);
                updateListings();
            } catch (ProductNotFoundException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
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
        int index = this.lstItems.getSelectedIndex();
        if (index == -1) {
            String code = JOptionPane.showInputDialog("Enter product code");
            if (!code.equals("")) {
                try {
                    sc.purchaseCamera(code);
                    JOptionPane.showMessageDialog(this, "Camera has been purchased");
                    list.getCamera(code).setStock(sc.getStock(code));
                    updateListings();
                } catch (ProductNotFoundException e) {
                    JOptionPane.showMessageDialog(this, code + " has not been found");
                } catch (OutOfStockException ex) {
                    JOptionPane.showMessageDialog(this, code + " is out of stock");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            }
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
        int option = JOptionPane.showOptionDialog(this, "Are you sure you want to close the application?", "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

        if (option == 0) {
            try {
                for (int i = 0; i < list.camerasSize(); i++) {
                    if (list.getCamera(i).getStock() == 0) {
                        JOptionPane.showMessageDialog(this, list.getCamera(i).getMake() + " " + list.getCamera(i).getModel() + " is out of stock");
                    } else if (list.getCamera(i).getStock() < 3) {
                        JOptionPane.showMessageDialog(this, list.getCamera(i).getMake() + " " + list.getCamera(i).getModel() + " only has " + list.getCamera(i).getStock() + " left in stock");
                    }
                }
                System.out.println("Closing ClickIt application");

                sc.terminateConnection();
            } catch (IOException | NullPointerException ex) {

            } finally {
                System.exit(0);
            }
        }
    }//GEN-LAST:event_JMenuExitActionPerformed

    /**
     * Method to listen for the add camera option being selected in the menu
     * bar.
     *
     * @param evt the event of the button being pressed.
     */
    private void JMenuAddCameraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuAddCameraActionPerformed
        //AddCameraGUI newAddCameraGUI = new AddCameraGUI(this);
    }//GEN-LAST:event_JMenuAddCameraActionPerformed

    /**
     * Method to listen for the delete camera option being selected in the menu
     * bar.
     *
     * @param evt the event of the button being pressed.
     */
    private void JMenuDeleteCameraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuDeleteCameraActionPerformed
        int index = this.lstItems.getSelectedIndex();
        if (index == -1) {
            String code = JOptionPane.showInputDialog("Enter product code to delete");
            try {
                sc.deleteCamera(code);
                list.removeProduct(code);
                System.out.println("Camera " + code + " deleted");
                updateListings();
            } catch (ProductNotFoundException e) {
                JOptionPane.showMessageDialog(this, "Camera " + code + " not found");
            } catch (Exception ex) {
            }
        } else {
            int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the selected camera?");
            if (option == 0) {
                this.deleteCamera(index);
            }
        }
    }//GEN-LAST:event_JMenuDeleteCameraActionPerformed

    /**
     * Displays the settings window if the settings button is pressed.
     *
     * @param evt
     */
    private void jMenuSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuSettingsActionPerformed
        //new Settings(this, sc, list).setVisible(true);
    }//GEN-LAST:event_jMenuSettingsActionPerformed

    /**
     * Increases the stock level of a specified camera or a camera selected in
     * the list.
     *
     * @param evt
     */
    private void jMenuIncreaseStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuIncreaseStockActionPerformed
        int index = this.lstItems.getSelectedIndex();
        if (index == -1) {
            String code = JOptionPane.showInputDialog("Enter product code");
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
                list.getCamera(code).increaceStock(stock);
                updateListings();
            } catch (ProductNotFoundException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        } else {
            this.increaseStock(index);
        }
    }//GEN-LAST:event_jMenuIncreaseStockActionPerformed

    /**
     * Purchases a specified camera or a camera selected in the list
     *
     * @param evt
     */
    private void jMenuPurchaseCameraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuPurchaseCameraActionPerformed
        int index = this.lstItems.getSelectedIndex();
        if (index == -1) {
            String code = JOptionPane.showInputDialog("Enter product code");
            try {
                sc.purchaseCamera(code);
                JOptionPane.showMessageDialog(this, "Camera has been purchased");
                list.getCamera(code).setStock(sc.getStock(code));
                updateListings();
            } catch (ProductNotFoundException e) {
                JOptionPane.showMessageDialog(this, code + " has not been found");
            } catch (OutOfStockException ex) {
                JOptionPane.showMessageDialog(this, code + " is out of stock");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        } else {
            this.purchaseCamera(index);
        }
    }//GEN-LAST:event_jMenuPurchaseCameraActionPerformed

    private void jMenuUpdateListingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuUpdateListingsActionPerformed
        list.getListFromServer();
        this.updateList();
    }//GEN-LAST:event_jMenuUpdateListingsActionPerformed

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
    private javax.swing.JMenuItem jMenuIncreaseStock;
    private javax.swing.JMenuItem jMenuPurchaseCamera;
    private javax.swing.JMenuItem jMenuSettings;
    private javax.swing.JMenuItem jMenuUpdateListings;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList lstItems;
    // End of variables declaration//GEN-END:variables
}
