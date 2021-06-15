package kr.ac.pcu.cyber.studentservice.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequestData {
    private String studentId;
    private String name;
}
