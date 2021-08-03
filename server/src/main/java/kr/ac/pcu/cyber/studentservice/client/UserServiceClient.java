package kr.ac.pcu.cyber.studentservice.client;

import kr.ac.pcu.cyber.studentservice.common.Role;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Component
@FeignClient(
        name = "user-service"
)
public interface UserServiceClient {

    @GetMapping(value = "/users/{userId}/roles", consumes = "application/json")
    List<Role> getRoles(@PathVariable("userId") String userId);
}
