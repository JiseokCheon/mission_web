package cheon.mission.interceptor;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {

    private static final List<String> getURL = Arrays.asList("/**");
    //private static final List<String> postURL = Arrays.asList();
    private final GetInterceptor getInterceptor;
    private final PostInterceptor postInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getInterceptor).addPathPatterns(getURL);
        //registry.addInterceptor(postInterceptor).addPathPatterns(postURL);
    }
}
