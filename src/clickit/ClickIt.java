/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickit;

/**
 * An application which stores details about cameras
 *
 * @author David
 * @version 23/03/2015
 */
public class ClickIt {

    static MainGUI gui;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Starting ClickIt application");
        gui = new MainGUI();
    }
}
