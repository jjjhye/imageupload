package com.woori.imageupload.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//implements 현재 클래스에 지정한 클래스의 뼈대(메소드명만 이용)
//extends 현재 클래스에 지정한 클래스의 추가(프로그램 추가)
public class WebMvcConfig implements WebMvcConfigurer {
    //application.properties에 선언된 uploadPath에 저장된 값을 읽어온다
    @Value("${uploadPath}")
    String uploadPath;//읽어온 값을 저장 uploadPath = "File///c:/product/"

    //기존의 메소드의 내용을 변경(덮어쓰기)-메소드명은 변경불가, 내용은 변경가능
    //resources 폴더에 자원을 추가
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations(uploadPath);
    }
    //images/**(무시)로 접속시 file:///c:/product/폴더에서 찾는다
}
