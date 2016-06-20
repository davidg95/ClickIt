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
 * Server connection class which handles all communication with the server.
 *
 * @author David
 */
public class ServerConnection {

    private Socket s;
    private BufferedReader in;
    private PrintWriter out;

    /**
     * Blank constructor method for server connection class.
     */
    public ServerConnection() {

    }

    /**
     * Method to open a connection to a server on a specified address and port
     * number. This will close any current connection.
     *
     * @param IP the address of the server to connect to.
     * @param PORT the port number to connect on.
     * @throws IOException if a connection could not be made.
     */
    public void connect(String IP, int PORT) throws IOException {
        s = new Socket();

        s.connect(new InetSocketAddress(IP, PORT), 2000);

        System.out.println("Connected to main server");
        in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        out = new PrintWriter(s.getOutputStream(), true);
    }

    /**
     * Method to add a new camera to the server.
     *
     * @param c the Camera object to be added.
     * @throws clickit.CodeAlreadyExistsException if the camera code already
     * exists.
     */
    public void addCamera(Camera c) throws CodeAlreadyExistsException, Exception {
        try {
            out.println("NEW," + c.getMake() + "," + c.getModel() + "," + c.getMegapixles() + "," + c.getSensor() + "," + c.getStock() + "," + c.getPrice());
            String reply = in.readLine();
            switch (reply) {
                case "FAIL CODE":
                    throw new CodeAlreadyExistsException(c.getCode());
                case "FAIL":
                    throw new Exception("ERROR COMMUNICATING WITH SERVER SERVER");
            }
        } catch (IOException ex) {
        }
    }

    /**
     * Method to purchase a camera.
     *
     * @param code the camera to purchase.
     * @throws OutOfStockException if the camera is out of stock.
     * @throws CameraNotFoundException if the camera is not found.
     */
    public void purchaceCamera(String code) throws OutOfStockException, CameraNotFoundException, Exception {
        out.println("PUR," + code);
        try {
            String reply = in.readLine();
            switch (reply) {
                case "FAIL STOCK":
                    throw new OutOfStockException(code);
                case "FAIL NFOUND":
                    throw new CameraNotFoundException(code);
                case "FAIL":
                    throw new Exception("ERROR COMMUNICATING WITH SERVER");
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
        out.println("GET," + code);

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
        out.println("GETSTOCK," + code);

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
     * Method to increase the stock level of a camera on the server by passing
     * in its product code.
     *
     * @param code the product code of the camera.
     * @param stock the stock to be added.
     * @throws CameraNotFoundException if the camera is not found on the server.
     * @throws Exception if there is any errors with the server.
     */
    public void increaceStock(String code, int stock) throws CameraNotFoundException, Exception {
        out.println("STOCKINC," + code + "," + stock);

        try {
            String reply = in.readLine();
            switch (reply) {
                case "FAIL NFOUND":
                    throw new CameraNotFoundException(code);
                case "FAIL":
                    throw new Exception("ERROR COMMUNICATING WITH SERVER");
            }
        } catch (IOException e) {

        }
    }

    /**
     * Method to delete a camera from the server by passing in its product code.
     *
     * @param code the product code of the camera to delete.
     * @throws CameraNotFoundException if the camera is not found on the server.
     * @throws Exception if there is any errors with the server.
     */
    public void deleteCamera(String code) throws CameraNotFoundException, Exception {
        out.println("DEL," + code);

        try {
            String reply = in.readLine();
            switch (reply) {
                case "FAIL NFOUND":
                    throw new CameraNotFoundException(code);
                case "FAIL":
                    throw new Exception("ERROR COMMUNICATING WITH SERVER");
            }
        } catch (IOException e) {

        }
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
                    out.println("GETINDEX," + i);
                    cameras.add(new Camera(in.readLine()));
                }
            }
        } catch (IOException ex) {
        }
        return cameras;
    }

    /**
     * Method to terminate the connection to the server.
     *
     * @throws java.io.IOException if there was an error closing the connection.
     */
    public void terminateConnection() throws IOException {
        out.println("CONNTERM");
        System.out.println("Closing connection to the server");
        s.close();
    }
}
