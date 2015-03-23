/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickit;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

/**
 * A class which stores a list of objects of type Camera.
 *
 * @author David
 * @version 23/03/2015
 */
public class CameraList {

    private ArrayList<Camera> cameras;

    /**
     * Constructor method for the CameraList class which creates an instance of
     * the CameraList object. It creates an ArrayList of type Camera and then
     * calls the method to read from the file.
     */
    public CameraList() {
        cameras = new ArrayList<>();

        /*this.openFile();*/
    }

    /**
     * Method to add a new camera to the list.
     *
     * @param camera the camera to be added as a Camera.
     */
    public void addCamera(Camera camera) {
        cameras.add(camera);
    }

    /**
     * Metho to remove a camera from the list.
     *
     * @param camera the camera to be removed from the list as a Camera.
     */
    public void removeCamera(Camera camera) {
        for (int i = 0; i <= (cameras.size() - 1); i++) {
            if (cameras.get(i).equals(camera)) {
                cameras.remove(i);
            }
        }
    }

    /**
     * Method to save the contents of the list to a file.
     */
    public void saveToFile() {
        try {
            PrintWriter writer = new PrintWriter("cameras.txt", "UTF-8");
            System.out.println("Starting to write to file");
            for (int i = 0; i <= (cameras.size() - 1); i++) {
                writer.println(cameras.get(i).writeToFile());
                System.out.println("Writing to file complete");
            }
        } catch (IOException ex) {

        }
    }

    /**
     * Method to open a file and save it to the list.
     */
    public void openFile() {
        File file = new File("cameras.txt");
        System.out.println("Starting to open file");
        try {
            Scanner fileReader = new Scanner(file);

            do {
                cameras.add(new Camera(fileReader.nextLine()));
            } while (fileReader.hasNextLine());
            System.out.println("Open file complete");
        } catch (IOException e) {

        }
    }
}
