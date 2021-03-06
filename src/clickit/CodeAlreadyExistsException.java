/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickit;

/**
 * CodeAlreadyExists Exception
 *
 * @author David
 */
public class CodeAlreadyExistsException extends Exception {

    private final String code;

    public CodeAlreadyExistsException(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return code + " already exists";
    }

    @Override
    public String toString() {
        return "Exception: " + code + " already exists";
    }
}
