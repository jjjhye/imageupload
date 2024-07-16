package com.woori.imageupload.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//DTO와 Entity간의 변환
@Configuration
public class AppConfig {
    //새로운 메소드를 등록해서 공용으로 사용
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
