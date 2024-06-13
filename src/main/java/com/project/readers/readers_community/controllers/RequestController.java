package com.project.readers.readers_community.controllers;

import com.project.readers.readers_community.DTOs.BookDTO;
import com.project.readers.readers_community.DTOs.BorrowRequestDTO;
import com.project.readers.readers_community.DTOs.MemberApprovalRequestDTO;
import com.project.readers.readers_community.annotations.CurrentUser;
import com.project.readers.readers_community.entities.BorrowRequest;
import com.project.readers.readers_community.entities.MemberApprovalRequest;
import com.project.readers.readers_community.entities.User;
import com.project.readers.readers_community.services.RequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/requests")
public class RequestController
{

    private final RequestService requestService;

    public RequestController(RequestService requestService)
    {
        this.requestService = requestService;
    }

    //endpoint to get all pending MemberApprovalRequests
    @GetMapping("/memberApprovalRequests")
    public Map<String,List<MemberApprovalRequestDTO>> getAllMemberApprovalRequests()
    {
        return requestService.getAllMemberApprovalRequests();
    }

    // endpoint to approve member signup request from the side of reference
    @PostMapping("/memberApprovalRequests/{request_id}/approveFromReference")
    public String approveMemberApprovalRequestFromReference(@PathVariable("request_id") MemberApprovalRequest request, @CurrentUser User currentUser)
    {
        return requestService.approveMemberApprovalRequestFromReference(request, currentUser);
    }

    // endpoint to reject member signup request from the side of reference
    @PostMapping("/memberApprovalRequests/{request_id}/rejectFromReference")
    public String rejectMemberApprovalRequestFromReference(@PathVariable("request_id") MemberApprovalRequest request, @CurrentUser User currentUser)
    {
        return requestService.rejectMemberApprovalRequestFromReference(request, currentUser);
    }

    // endpoint to approve member signup request from the side of admin
    @PostMapping("/memberApprovalRequests/{request_id}/approveFromAdmin")
    public String approveMemberApprovalRequestFromAdmin(@PathVariable("request_id") MemberApprovalRequest request)
    {
        return requestService.approveMemberApprovalRequestFromAdmin(request);
    }

    // endpoint to reject member signup request from the side of admin
    @PostMapping("/memberApprovalRequests/{request_id}/rejectFromAdmin")
    public String rejectMemberApprovalRequestFromAdmin(@PathVariable("request_id") MemberApprovalRequest request)
    {
        return requestService.rejectMemberApprovalRequestFromAdmin(request);
    }

    //endpoint to get all not responded book upload requests
    @GetMapping("/bookUploadRequests")
    public List<BookDTO> getAllBookUploadRequests()
    {
        return requestService.getAllBookUploadRequests();
    }

    //endpoint to approve a book upload request from admin's side
    @PostMapping("/bookUploadRequests/{book_id}/approve")
    public String approveBookUploadRequest(@PathVariable long book_id)
    {
        return requestService.approveBookUploadRequest(book_id);
    }

    //endpoint to reject a book upload request from admin's side
    @PostMapping("/bookUploadRequests/{book_id}/reject")
    public String rejectBookUploadRequest(@PathVariable long book_id)
    {
        return requestService.rejectBookUploadRequest(book_id);
    }

    // endpoint to get all the borrow requests of current user's books
    @GetMapping("/borrowRequests")
    public List<BorrowRequestDTO> getAllBorrowRequests(@CurrentUser User user)
    {
        return requestService.getAllBorrowRequests(user);
    }

    // endpoint to let the book owner approve a borrow request
    @PostMapping("/borrowRequests/{borrow_request_id}/approve")
    public String approveBorrowRequest(@PathVariable("borrow_request_id") int borrowRequestId, @CurrentUser User user)
    {
        return requestService.approveBorrowRequest(borrowRequestId, user);
    }

    // endpoint to let the book owner approve a borrow request
    @PostMapping("/borrowRequests/{borrow_request_id}/reject")
    public String rejectBorrowRequest(@PathVariable("borrow_request_id") int borrowRequestId, @CurrentUser User user)
    {
        return requestService.rejectBorrowRequest(borrowRequestId, user);
    }
    
  
}
