package com.project.readers.readers_community.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer
{
    private final CurrentUserHandlerMethodArgumentResolver currentUserHandlerMethodArgumentResolver;

    public WebConfig(CurrentUserHandlerMethodArgumentResolver currentUserHandlerMethodArgumentResolver) {
        this.currentUserHandlerMethodArgumentResolver = currentUserHandlerMethodArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
       resolvers.add(currentUserHandlerMethodArgumentResolver);
    }
}
