package kr.ac.pcu.cyber.studentservice.filter;

import kr.ac.pcu.cyber.studentservice.client.UserServiceClient;
import kr.ac.pcu.cyber.studentservice.common.Role;
import kr.ac.pcu.cyber.studentservice.common.RoleType;
import kr.ac.pcu.cyber.studentservice.security.CustomUserAuthentication;
import kr.ac.pcu.cyber.studentservice.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthenticationFilter extends BasicAuthenticationFilter {

    private final AuthenticationService authenticationService;
    private final UserServiceClient userServiceClient;

    public AuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationService authenticationService, UserServiceClient userServiceClient) {
        super(authenticationManager);
        this.authenticationService = authenticationService;
        this.userServiceClient = userServiceClient;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        if (request.getCookies() != null) {

            String userId = authenticationService.parseUserIdFromCookies(request.getCookies());

            Role admin = new Role(userId, RoleType.ADMIN);
            Role user = new Role(userId, RoleType.USER);

            List<Role> roles = new ArrayList<>();
            roles.add(admin);
            roles.add(user);
            /* User-Service 에 OpenFeign 으로 Role 받아오기 */

//            List<Role> roless = userServiceClient.getRoles(userId);


            Authentication customUserAuthentication = new CustomUserAuthentication(
                    userId,
                    roles
            );

            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(customUserAuthentication);
        }

        chain.doFilter(request, response);
    }
}
