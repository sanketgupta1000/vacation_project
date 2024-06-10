package com.project.readers.readers_community.controllers;

import com.project.readers.readers_community.services.RequestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/requests")
public class RequestController
{

    private final RequestService requestService;

    public RequestController(RequestService requestService)
    {
        this.requestService = requestService;
    }



}
