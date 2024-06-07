package com.project.readers.readers_community.configs;

import com.project.readers.readers_community.annotations.CurrentUser;
import com.project.readers.readers_community.entities.User;
import com.project.readers.readers_community.services.CustomUserDetailsService;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.security.Principal;

@Component
public class CurrentUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver
{
    private final CustomUserDetailsService customUserDetailsService;

    public CurrentUserHandlerMethodArgumentResolver(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(CurrentUser.class) != null
                &&
                parameter.getParameterType().equals(User.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // getting principal
        Principal principal = webRequest.getUserPrincipal();

        if( principal==null )
        {
            throw  new UsernameNotFoundException("User is not authenticated");
        }

        // return the User
        return ((SecurityUser)customUserDetailsService.loadUserByUsername(principal.getName())).getUser();
    }
}
