package exception;

import model.Individual;

public class SomethingWentWrongException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String message;

    public SomethingWentWrongException(Individual i) {

        super();
        message = Integer.toString(i.getId());
    }

    public String getMessage() {

        message += "Something went wrong";
        return message;
    }

}
