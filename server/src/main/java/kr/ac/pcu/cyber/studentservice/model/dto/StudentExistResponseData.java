package kr.ac.pcu.cyber.studentservice.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentExistResponseData {
    private Boolean isExist;
}
