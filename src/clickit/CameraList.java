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

        this.openFile();
    }
    
    /**
     * Gets the ArrayList which stores all the Camera objects.
     * @return returns an ArrayList of type Camera.
     */
    public ArrayList<Camera> getCameras() {
        return cameras;
    }
    
    /**
     * Sets the ArrayList of type Camera.
     * @param cameras the new ArrayList of type Camera.
     */
    public void setCameras(ArrayList<Camera> cameras) {
        this.cameras = cameras;
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
     * Method to remove a camera from the list.
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
     * Method to return the amount of cameras stored in the list.
     * @return returns the amount of cameras stored in the list as an int.
     */
    public int size(){
        return cameras.size();
    }
    
    /**
     * Method to return the contents of the CameraList as an Object.
     * @return returns the contents of the CameraList as an Object.
     */
    public Object getArray(){
        return cameras.toArray();
    }
    
    /**
     * Method to return a Camera object.
     * @param i the index of the Camera object you want to get.
     * @return returns a Camera object.
     */
    public Camera getCamera(int i){
        return cameras.get(i);
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
            writer.close();
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
