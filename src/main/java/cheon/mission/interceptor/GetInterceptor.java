package cheon.mission.interceptor;

import cheon.mission.auth.Dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
@RequiredArgsConstructor
public class GetInterceptor implements HandlerInterceptor {

    private final HttpSession httpSession;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(request.getMethod().equals(HttpMethod.GET.name())){
            if (user == null) {
                response.sendRedirect("/login_page");
                return false;
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(request.getMethod().equals(HttpMethod.GET.name())){
            modelAndView.addObject("username", user.getName());
            modelAndView.addObject("useremail", user.getEmail());
        }
    }
}
