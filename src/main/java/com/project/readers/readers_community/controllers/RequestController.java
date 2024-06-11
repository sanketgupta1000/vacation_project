package com.project.readers.readers_community.controllers;

import com.project.readers.readers_community.annotations.CurrentUser;
import com.project.readers.readers_community.entities.BorrowRequest;
import com.project.readers.readers_community.entities.User;
import com.project.readers.readers_community.services.RequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requests")
public class RequestController
{

    private final RequestService requestService;

    public RequestController(RequestService requestService)
    {
        this.requestService = requestService;
    }

    // method to get all the borrow requests of current user's books
    @GetMapping("/borrow-requests")
    public List<BorrowRequest> getBorrowRequests(@CurrentUser User user)
    {
        return requestService.getBorrowRequests(user);
    }

    // endpoint to let the book owner approve a borrow request
    @PostMapping("/borrow-requests/{borrow_request_id}/approve")
    public String approveBorrowRequest(@PathVariable("borrow_request_id") int borrowRequestId, @CurrentUser User user)
    {
        return requestService.approveBorrowRequest(borrowRequestId, user);
    }

    // endpoint to let the book owner approve a borrow request
    @PostMapping("/borrow-requests/{borrow_request_id}/reject")
    public String rejectBorrowRequest(@PathVariable("borrow_request_id") int borrowRequestId, @CurrentUser User user)
    {
        return requestService.rejectBorrowRequest(borrowRequestId, user);
    }

}
