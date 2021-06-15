package kr.ac.pcu.cyber.studentservice.service;

import kr.ac.pcu.cyber.studentservice.exception.StudentNotFoundException;
import kr.ac.pcu.cyber.studentservice.model.dto.*;
import kr.ac.pcu.cyber.studentservice.model.entity.Activity;
import kr.ac.pcu.cyber.studentservice.model.entity.Student;
import kr.ac.pcu.cyber.studentservice.repository.ActivityRepository;
import kr.ac.pcu.cyber.studentservice.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final ActivityRepository activityRepository;
    private final ModelMapper modelMapper;

    public StudentService(ActivityRepository activityRepository, StudentRepository studentRepository, ModelMapper modelMapper) {
        this.activityRepository = activityRepository;
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    public StudentExistResponseData isStudentExist(String studentId) {
        if (isStudent(studentId)) {
            return StudentExistResponseData.builder().isExist(true).build();
        } else {
            return StudentExistResponseData.builder().isExist(false).build();
        }
    }

    public Slice<StudentResponseData> getStudents(String grade) {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Slice<Student> students = studentRepository.findByStudentIdStartsWithOrderByStudentId(grade, pageRequest);

        return students.map(student -> modelMapper.map(student, StudentResponseData.class));
    }

    public void addStudent(List<StudentRequestData> students) {
        for (StudentRequestData item : students) {
            if (!isStudent(item.getStudentId())) {
                studentRepository.save(modelMapper.map(item, Student.class));
            }
        }
    }

    /* TODO : update Student */

    public void deleteStudent(String studentId) {
        Optional<Student> optionalStudent = studentRepository.findByStudentId(studentId);

        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();

            List<Activity> activities = activityRepository.findAllByStudentIdOrderById(student.getId());

            for (Activity activity : activities) {
                activityRepository.delete(activity);
            }

            studentRepository.delete(student);
        }
    }

    public List<ActivityResponseData> getActivity(String studentId) {
        Optional<Student> optionalStudent = studentRepository.findByStudentId(studentId);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();

            List<Activity> activities = activityRepository.findAllByStudentIdOrderById(student.getId());

            List<ActivityResponseData> response = new ArrayList<>();

            for (Activity item : activities) {
                response.add(modelMapper.map(item, ActivityResponseData.class));
            }

            return response;
        } else {
            throw new StudentNotFoundException(studentId);
        }
    }

    public void addActivity(List<ActivityRequestData> activities) {
        for (ActivityRequestData item : activities) {
            Optional<Student> optionalStudent = studentRepository.findByStudentId(item.getStudentId());

            if (optionalStudent.isPresent()) {
                Student student = optionalStudent.get();

                Activity activity = modelMapper.map(item, Activity.class);
                activity.complete(student);

                activityRepository.save(activity);
                int count = activityRepository.countByStudentId(student.getId());

                student.updateTotalNumber(count);

                studentRepository.save(student);
            }
        }
    }

    /* TODO : update Activity */

    public void deleteActivity(Long activityId) {

    }

    private boolean isStudent(String studentId) {
        Optional<Student> item = studentRepository.findByStudentId(studentId);

        return item.isPresent();
    }
}
