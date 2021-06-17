package kr.ac.pcu.cyber.studentservice.common;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private String userId;
    private RoleType roleType;
}
