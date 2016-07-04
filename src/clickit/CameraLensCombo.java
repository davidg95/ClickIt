/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickit;

/**
 * Class which models a Camera Lens combination.
 *
 * @author David
 */
public class CameraLensCombo {

    private final Camera c;
    private final Lens l;

    /**
     * Constructor which takes in a Camera and a Lens.
     *
     * @param c the Camera.
     * @param l the Lens.
     */
    public CameraLensCombo(Camera c, Lens l) {
        this.c = c;
        this.l = l;
    }

    /**
     * Constructor which takes in only a Camera.
     *
     * @param c the Camera.
     */
    public CameraLensCombo(Camera c) {
        this.c = c;
        this.l = null;
    }

    /**
     * Gets the Camera in this CameraLensCombo.
     *
     * @return Camera object.
     */
    public Camera getCamera() {
        return this.c;
    }

    /**
     * Gets the Lens in the CameraLensCombo.
     *
     * @return Lens object.
     */
    public Lens getLens() {
        return this.l;
    }

    @Override
    public String toString() {
        return this.c.toString() + this.l.toString();
    }
}
