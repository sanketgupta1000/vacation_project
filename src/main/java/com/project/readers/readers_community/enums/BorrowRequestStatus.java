package com.project.readers.readers_community.enums;

public enum BorrowRequestStatus
{

    // denotes unresponded by owner
    UNRESPONDED,
    // denotes rejection
    REJECTED,
    // denotes that owner has approved, but the book is not yet transferred
    APPROVED,
    // denotes that the book is received by the requester
    RECEIVED,
    // denotes that the book is passed on by the requester
    COMPLETED

}
