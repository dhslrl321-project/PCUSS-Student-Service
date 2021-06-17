package kr.ac.pcu.cyber.studentservice.service;

import io.jsonwebtoken.Claims;
import kr.ac.pcu.cyber.studentservice.common.TokenType;
import kr.ac.pcu.cyber.studentservice.util.CookieUtil;
import kr.ac.pcu.cyber.studentservice.util.JwtUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.transaction.Transactional;

@Service
@Transactional
public class AuthenticationService {

    private final CookieUtil cookieUtil;
    private final JwtUtil jwtUtil;

    public AuthenticationService(CookieUtil cookieUtil, JwtUtil jwtUtil) {
        this.cookieUtil = cookieUtil;
        this.jwtUtil = jwtUtil;
    }

    public String parseUserIdFromCookies(Cookie[] cookies) {

        String accessToken = cookieUtil.parseTokenFromCookie(cookies, TokenType.ACCESS_TOKEN);

        Claims claims = jwtUtil.parseToken(accessToken);
        return claims.get("userId", String.class);
    }

}
