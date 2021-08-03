package kr.ac.pcu.cyber.studentservice.repository;

import kr.ac.pcu.cyber.studentservice.model.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findAllByStudentIdOrderById(Long student_id);
    Integer countByStudentId(Long student_id);
}
