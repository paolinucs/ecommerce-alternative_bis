package it.paolone.ecommerce.exceptions;

public class UserAndAdminEmailMismatchException extends Exception {

    public UserAndAdminEmailMismatchException() {
        super();
    }

    public UserAndAdminEmailMismatchException(String message) {
        super(message);
    }

}
