package com.woori.imageupload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ImageuploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImageuploadApplication.class, args);
    }

}
