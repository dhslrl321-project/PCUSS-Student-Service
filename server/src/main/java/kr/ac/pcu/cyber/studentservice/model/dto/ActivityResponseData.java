package kr.ac.pcu.cyber.studentservice.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityResponseData {
    private Long id;
    private String activityName;
    private String result;
}
