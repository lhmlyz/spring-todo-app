package com.app.todo.webconfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("todo")
public class TodoConfig {

    private String todoApiBaseUrl;
    private String uploadFolder;
}
