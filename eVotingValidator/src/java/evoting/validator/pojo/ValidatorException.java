/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package evoting.validator.pojo;

/**
 *
 * @author defiler
 */
public class ValidatorException extends Exception {
    String msg;

    public ValidatorException(String msg) {
        super(msg);
        this.msg = msg;
    }


}
