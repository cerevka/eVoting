package evoting.validator.pojo;

public class ValidatorException extends Exception {
    String msg;

    public ValidatorException(String msg) {
        super(msg);
        this.msg = msg;
    }


}
