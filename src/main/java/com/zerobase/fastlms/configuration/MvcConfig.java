package com.zerobase.fastlms.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Value("${file.local-file-root}")
    private String LOCAL_FILE_ROOT;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // "/files/**" URL로 들어오는 요청에 대해
        // "/Users/chungchung/my/Temp/files" 로컬 디렉토리에서 찾기
        registry.addResourceHandler(Constants.URL_FILE_ROOT + "**")
                .addResourceLocations("file:" + LOCAL_FILE_ROOT);
    }
}