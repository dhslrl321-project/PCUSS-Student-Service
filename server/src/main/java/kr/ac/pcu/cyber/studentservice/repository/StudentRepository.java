package kr.ac.pcu.cyber.studentservice.repository;

import kr.ac.pcu.cyber.studentservice.model.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByStudentId(String studentId);
    Page<Student> findByStudentIdStartsWith(String studentId, Pageable pageable);
}
