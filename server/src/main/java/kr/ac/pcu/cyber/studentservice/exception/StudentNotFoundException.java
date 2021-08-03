package kr.ac.pcu.cyber.studentservice.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String studentId) {
        super(("[") + studentId + "] 는 존재하지 않는 학생입니다.");
    }
}
