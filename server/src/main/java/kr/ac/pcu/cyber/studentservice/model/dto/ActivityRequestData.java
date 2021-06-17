package kr.ac.pcu.cyber.studentservice.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityRequestData {
    private String studentId;
    private String activityName;
    private String result;
}
