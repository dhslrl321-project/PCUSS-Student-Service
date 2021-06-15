package kr.ac.pcu.cyber.studentservice.advice;

import kr.ac.pcu.cyber.studentservice.exception.StudentNotFoundException;
import kr.ac.pcu.cyber.studentservice.model.dto.ExceptionResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(StudentNotFoundException.class)
    public ExceptionResponseData handleStudentNotFoundException(StudentNotFoundException e) {
        return new ExceptionResponseData(e.getMessage());
    }

}
