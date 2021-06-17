package kr.ac.pcu.cyber.studentservice.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseData {
    private String studentId;
    private String name;
    private Integer totalNumber;
}
