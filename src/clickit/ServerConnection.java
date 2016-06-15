/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class ServerConnection {

    private Socket s;
    private BufferedReader in;
    private PrintWriter out;

    /**
     * Constructor which opens a new connection to the server on the specified
     * port.
     */
    public ServerConnection() {
        try {
            s = new Socket();

            s.connect(new InetSocketAddress(Settings.SERVER_ADDRESS, Settings.PORT));

            System.out.println("Connected to main server");
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(s.getOutputStream(), true);

        } catch (IOException ex) {
            System.out.println("Error connecting to main server");
        }
    }

    /**
     * Method to add a new camera to the server.
     *
     * @param c the Camera object to be added.
     */
    public void addCamera(Camera c) {
        out.println("NEW " + c.getMake() + " " + c.getModel() + " " + c.getMegapixles() + " " + c.getSensor() + " " + c.getStock() + " " + c.getPrice());
    }

    /**
     * Method to purchase a camera.
     *
     * @param code the camera to purchase.
     * @throws OutOfStockException if the camera is out of stock.
     * @throws CameraNotFoundException if the camera is not found.
     */
    public void purchaceCamera(String code) throws OutOfStockException, CameraNotFoundException {
        out.println("PUR " + code);
        try {
            String reply = in.readLine();
            switch (reply) {
                case "FAIL STOCK":
                    throw new OutOfStockException(code);
                case "FAIL NFOUND":
                    throw new CameraNotFoundException(code);
            }
        } catch (IOException ex) {
        }
    }

    /**
     * Method to get a camera by its code.
     *
     * @param code the code to search for.
     * @return new camera object which matches the code.
     * @throws CameraNotFoundException if not camera is found.
     */
    public Camera getCamera(String code) throws CameraNotFoundException {
        out.println("GET " + code);

        try {
            String reply = in.readLine();
            if (reply.equals("FAIL")) {
                throw new CameraNotFoundException(code);
            } else {
                return new Camera(reply);
            }
        } catch (IOException e) {
        }

        throw new CameraNotFoundException(code);
    }

    /**
     * Method to get the stock level of a camera.
     *
     * @param code the code to get the stock level for.
     * @return the stock level as an int.
     * @throws CameraNotFoundException if the camera was not found.
     */
    public int getStock(String code) throws CameraNotFoundException {
        out.println("GETSTOCK " + code);

        try {
            String reply = in.readLine();
            if (reply.equals("FAIL")) {
                throw new CameraNotFoundException(code);
            } else {
                return Integer.parseInt(reply);
            }
        } catch (IOException e) {
        }

        throw new CameraNotFoundException(code);
    }

    /**
     * Method to get all the cameras from the server.
     *
     * @return ArrayList of all the cameras.
     */
    public ArrayList<Camera> getAllCameras() {
        ArrayList<Camera> cameras = new ArrayList<>();
        out.println("GETSIZE");
        try {
            String reply = in.readLine();
            System.out.println(reply);
            if (!reply.equals("FAIL")) {
                int size = Integer.parseInt(reply);
                for (int i = 0; i < size; i++) {
                    out.println("GETINDEX " + i);
                    cameras.add(new Camera(in.readLine()));
                }
            }
        } catch (IOException ex) {
        }
        return cameras;
    }

    /**
     * Method to terminate the connection to the server.
     */
    public void terminateConnection() {
        out.println("CONNTERM");
        try {
            System.out.println("Closing connection to the server");
            s.close();
        } catch (IOException ex) {
        }
    }
}
