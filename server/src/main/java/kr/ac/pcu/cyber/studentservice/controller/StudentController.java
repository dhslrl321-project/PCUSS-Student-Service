package kr.ac.pcu.cyber.studentservice.controller;

import kr.ac.pcu.cyber.studentservice.model.dto.*;
import kr.ac.pcu.cyber.studentservice.service.StudentService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/exist/{student_id}", produces = "application/json; charset-utf8")
    @PreAuthorize("isAuthenticated() and hasAnyAuthority('ADMIN')")
    public ResponseEntity<StudentExistResponseData> isStudentExist(@PathVariable("student_id") String studentId) {
        return ResponseEntity.ok(studentService.isStudentExist(studentId));
    }




    @GetMapping(value = "/{grade}", produces = "application/json; charset-utf8")
    public ResponseEntity<Slice<StudentResponseData>> getStudents(@PathVariable("grade") String grade, Pageable pageable) {
        return ResponseEntity.ok(studentService.getStudents(grade, pageable));
    }

    @PostMapping(produces = "application/json; charset-utf8")
    @PreAuthorize("isAuthenticated() and hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> addStudent(@RequestBody List<StudentRequestData> students) {
        studentService.addStudent(students);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /*@PatchMapping(value = "/{student_id}", produces = "application/json; charset-utf8")
    public ResponseEntity<?> updateStudent(
            @PathVariable("student_id") String studentId
//             TODO : DTO 생성
    ) {
        return ResponseEntity.ok(null);
    }*/

    @DeleteMapping(value = "/{student_id}", produces = "application/json; charset-utf8")
    @PreAuthorize("isAuthenticated() and hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> deleteStudent(@PathVariable("student_id") String studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }




    @GetMapping(value = "/activity/{student_id}", produces = "application/json; charset-utf8")
    public ResponseEntity<List<ActivityResponseData>> getActivity(@PathVariable("student_id") String studentId) {
        return ResponseEntity.ok(studentService.getActivity(studentId));
    }

    @PostMapping(value = "/activity", produces = "application/json; charset-utf8")
    @PreAuthorize("isAuthenticated() and hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> addActivity(@RequestBody List<ActivityRequestData> activities) {
        studentService.addActivity(activities);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /*@PatchMapping(value = "/activity/{activity_id}", produces = "application/json; charset-utf8")
    public ResponseEntity<?> updateActivity(
            @PathVariable("activity_id") Long activityId
//            TODO : DTO 생성
    ) {
        return ResponseEntity.ok(null);
    }*/

    @DeleteMapping(value = "/activity/{activity_id}", produces = "application/json; charset-utf8")
    @PreAuthorize("isAuthenticated() and hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> deleteActivity(@PathVariable("activity_id") Long activityId) {
        studentService.deleteActivity(activityId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
