package kr.ac.pcu.cyber.studentservice.model.entity;

import lombok.*;
import net.bytebuddy.build.ToStringPlugin;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentId;

    private String name;

    private Integer totalNumber = 0;

    @OneToMany(mappedBy = "student")
    private List<Activity> activities = new ArrayList<>();

    public void updateTotalNumber(int count) {
        this.totalNumber = count;
    }

}


