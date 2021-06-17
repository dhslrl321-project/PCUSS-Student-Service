package kr.ac.pcu.cyber.studentservice.util;

import kr.ac.pcu.cyber.studentservice.common.TokenType;
import kr.ac.pcu.cyber.studentservice.exception.EmptyCookieException;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;

@Component
public class CookieUtil {

    public String parseTokenFromCookie(Cookie[] cookies, TokenType tokenType) {
        String cookieName;

        if (tokenType.equals(TokenType.ACCESS_TOKEN)) {
            cookieName = String.valueOf(TokenType.ACCESS_TOKEN);
        } else if (tokenType.equals(TokenType.REFRESH_TOKEN)) {
            cookieName = String.valueOf(TokenType.REFRESH_TOKEN);
        } else {
            cookieName = "empty";
        }

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                return cookie.getValue();
            }
        }

        throw new EmptyCookieException();
    }
}
