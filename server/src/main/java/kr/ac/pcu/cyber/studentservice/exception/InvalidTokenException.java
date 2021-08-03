package kr.ac.pcu.cyber.studentservice.exception;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(String token) {
        super(token);
    }
}
