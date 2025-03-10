package org.zerock.b01.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CustomServletConfig implements WebMvcConfigurer {
    
    // 정적 파일(/board/list) 경로가 달라졌으므로 WebMvcConfigurer 인터페이스 구현
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);

        // addResourceHandler 재정의
        registry.addResourceHandler("/js/**")  // url patternf
                .addResourceLocations("classpath:/static/js/");  // 실제 하드디스크의 파일 위치

        registry.addResourceHandler("/fonts/**")  // url patternf
                .addResourceLocations("classpath:/static/fonts/");  // 실제 하드디스크의 파일 위치

        registry.addResourceHandler("/css/**")  // url patternf
                .addResourceLocations("classpath:/static/css/");  // 실제 하드디스크의 파일 위치

        registry.addResourceHandler("/assets/**")  // url patternf
                .addResourceLocations("classpath:/static/assets/");  // 실제 하드디스크의 파일 위치

    }
}
