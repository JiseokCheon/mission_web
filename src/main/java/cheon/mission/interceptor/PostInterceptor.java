package cheon.mission.interceptor;

import cheon.mission.auth.Dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
@RequiredArgsConstructor
public class PostInterceptor implements HandlerInterceptor {
    private final HttpSession httpSession;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(request.getMethod().equals(HttpMethod.POST.name())){
            if (user == null) {
                response.sendRedirect("/login_page");
                return false;
            }
        }

        return true;
    }
}
