/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickit;

import java.util.Scanner;

/**
 * A class of type Camera which stores details about a camera.
 *
 * @author David
 * @version 23/03/2015
 */
public class Camera {

    private String make;
    private String model;
    private double megapixles;
    private boolean full;
    private int stock;
    private double price;

    /**
     * Constructor method for the Camera class which creates an instance of the
     * camera object.
     *
     * @param make the make of the camera as a String.
     * @param model the model of the camera as a String.
     * @param megapixles the megapixels of the camera as a double.
     * @param ifFull a boolean value to state whether the camera as a full frame
     * sensor or a crop, true for full and false for crop.
     * @param stock the current stock level of the camera as an int.
     * @param price the price of the camera as a double.
     */
    public Camera(String make, String model, double megapixles, boolean ifFull, int stock, double price) {
        this.make = make;
        this.model = model;
        this.megapixles = megapixles;
        this.full = ifFull;
        this.stock = stock;
        this.price = price;
    }

    public Camera(String notepad) {
        Scanner in = new Scanner(notepad);

        in = in.useDelimiter(",");

        this.make = in.next();
        this.model = in.next();
        this.megapixles = Double.parseDouble(in.next());
        String sensor = in.next();
        if (sensor.equals("CROP")) {
            this.full = false;
        } else {
            this.full = true;
        }
        this.stock = in.nextInt();
        this.price = in.nextDouble();

    }

    //Getter Methods
    /**
     * Gets the make of the camera.
     *
     * @return returns the make of the camera as a String.
     */
    public String getMake() {
        return make;
    }

    /**
     * Gets the model of the camera.
     *
     * @return returns the model of the camera as a String.
     */
    public String getModel() {
        return model;
    }

    /**
     * Gets the megapixels of the camera.
     *
     * @return returns the megapixels of the camera as a double.
     */
    public double getMegapixles() {
        return megapixles;
    }

    /**
     * Method to get whether the camera is full frame or crop sensor.
     *
     * @return returns true or false to indicate whether the camera is full
     * frame or crop sensor. returns true for full frame, false for crop.
     */
    public boolean isFull() {
        return full;
    }

    /**
     * Gets the current stock level of the camera.
     *
     * @return returns the current stock level of the camera as an int.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Gets the price of the camera.
     *
     * @return returns the price of the camera as a double.
     */
    public double getPrice() {
        return price;
    }

    //Setter Methods
    /**
     * Sets the make of the camera.
     *
     * @param make the value for the make as a String.
     */
    public void setMake(String make) {
        this.make = make;
    }

    /**
     * Sets the model of the camera.
     *
     * @param model the value for the model as a String.
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Sets the megapixels of the camera.
     *
     * @param megapixles the value for the megapixels as a double.
     */
    public void setMegapixles(double megapixles) {
        this.megapixles = megapixles;
    }

    /**
     * Sets whether the camera is full frame or crop sensor.
     *
     * @param full true or false to indicate whether the camera is full frame or
     * crop sensor. Set true for full or false for crop.
     */
    public void setFull(boolean full) {
        this.full = full;
    }

    /**
     * Sets the stock level of the camera.
     *
     * @param stock the value for the stock level as an int.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Sets the price of the camera.
     *
     * @param price the value for the price of the camera as a double.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Method to format output for saving to a notepad file.
     *
     * @return returns a String formatted for output to a notepad file.
     */
    public String writeToFile() {
        System.out.println("Writing camera to file");
        return this.make + "," + this.model + "," + this.megapixles + "," + (this.full ? "FULL" : "CROP") + "," + this.stock + "," + this.price;
    }

    /**
     * ToString method which outputs details of the camera as a String.
     *
     * @return returns details of the camera as a String.
     */
    @Override
    public String toString() {
        return "Make: " + this.make + "\nModel: " + this.model + "\nMegapixels: " + this.megapixles + "\nSensor: " + (this.full ? "FULL" : "CROP") + "\nStock: " + this.stock + "\nPrice: £" + this.price;
    }

}
