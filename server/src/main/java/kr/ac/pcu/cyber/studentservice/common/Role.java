package kr.ac.pcu.cyber.studentservice.common;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Role {

    private String userId;
    @Getter
    private RoleType roleType;

    public Role(String userId, RoleType roleType) {
        this.userId = userId;
        this.roleType = roleType;
    }

    public Role(RoleType roleType) {
        this(null, roleType);
    }
}
