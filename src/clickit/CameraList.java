/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickit;

import java.util.ArrayList;

/**
 * A class which stores a list of objects of type Camera.
 *
 * @author David
 * @version 23/03/2015
 */
public class CameraList {

    protected ArrayList<Camera> cameras;

    private final ServerConnection sc;

    /**
     * Constructor method for the <code>CameraList</code> class which creates an
     * instance of the CameraList object. It creates an ArrayList of type Camera
     * and then calls the method to read from the file.
     *
     * @param sc the class which handles all the server communication.
     */
    public CameraList(ServerConnection sc) {
        this.sc = sc;
    }

    /**
     * Method to get the full list of cameras from the server.
     */
    public void getListFromServer() {
        cameras = sc.getAllCameras();
    }

    /**
     * Gets the ArrayList which stores all the Camera objects.
     *
     * @return returns an ArrayList of type Camera.
     */
    public ArrayList<Camera> getCameras() {
        return cameras;
    }

    /**
     * Sets the ArrayList of type Camera.
     *
     * @param cameras the new ArrayList of type Camera.
     */
    public void setCameras(ArrayList<Camera> cameras) {
        this.cameras = cameras;
    }

    /**
     * Method to add a new camera to the list.
     *
     * @param c the camera to be added as a Camera.
     */
    public void addCamera(Camera c) {
        cameras.add(c);
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
                System.out.println("Camera removed from list");
            }
        }
    }

    /**
     * Method to remove a camera by passing in its product code.
     *
     * @param code the product code of the camera.
     * @throws CameraNotFoundException if the product code could not be found.
     */
    public void removeCamera(String code) throws CameraNotFoundException {
        for (int i = 0; i < cameras.size(); i++) {
            if (cameras.get(i).getCode().equals(code)) {
                cameras.remove(i);
                return;
            }
        }
        throw new CameraNotFoundException(code);
    }

    /**
     * Method to return the amount of cameras stored in the list.
     *
     * @return returns the amount of cameras stored in the list as an int.
     */
    public int size() {
        return cameras.size();
    }

    /**
     * Method to return the contents of the CameraList as an Object.
     *
     * @return returns the contents of the CameraList as an Object.
     */
    public Object getArray() {
        return cameras.toArray();
    }

    /**
     * Method to return a Camera object.
     *
     * @param i the index of the Camera object you want to get.
     * @return returns a Camera object.
     */
    public Camera getCamera(int i) {
        return cameras.get(i);
    }

    /**
     * Method to return a camera object by its product code.
     *
     * @param code the product code to search for.
     * @return returns a Camera object.
     * @throws CameraNotFoundException if the code could not be found.
     */
    public Camera getCamera(String code) throws CameraNotFoundException {
        for (Camera c : cameras) {
            if (c.getCode().equals(code)) {
                return c;
            }
        }
        throw new CameraNotFoundException(code);
    }
}
