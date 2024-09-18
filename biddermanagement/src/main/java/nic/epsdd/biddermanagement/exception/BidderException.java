package nic.epsdd.biddermanagement.exception;

// Custom exception class for handling user-related exceptions
public class BidderException extends Exception {

    // Constructor that accepts a custom error message
    public BidderException(String message) {
        // Pass the error message to the superclass (RuntimeException)
        super(message);
    }

    // Optionally, you can add more constructors if needed, like one that accepts both a message and a cause (exception chain)
    public BidderException(String message, Throwable cause) {
        // Pass the error message and cause to the superclass
        super(message, cause);
    }
}
