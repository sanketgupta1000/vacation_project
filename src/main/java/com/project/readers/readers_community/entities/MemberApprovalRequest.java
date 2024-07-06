package com.project.readers.readers_community.entities;

import com.project.readers.readers_community.enums.Approval;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "member_approval_requests")
public class MemberApprovalRequest
{

    // id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // member whose request is this
    @OneToOne
    @JoinColumn(name = "member_id")
    private User member;

    // is it approved by referrer person
    @Column(name = "referrer_approval")
    private Approval referrerApproval;

    // is it approved by admin
    @Column(name = "admin_approval")
    private Approval adminApproval;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "request_date_time")
    private Date requestDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "response_date_time")
    private Date responseDateTime;


    public MemberApprovalRequest() {
    }

    public MemberApprovalRequest(Integer id, User member, Approval referrerApproval, Approval adminApproval, Date requestDateTime, Date responseDateTime) {
        this.id = id;
        this.member = member;
        this.referrerApproval = referrerApproval;
        this.adminApproval = adminApproval;
        this.requestDateTime = requestDateTime;
        this.responseDateTime = responseDateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getMember() {
        return member;
    }

    public void setMember(User member) {
        this.member = member;
    }

    public Approval getReferrerApproval() {
        return referrerApproval;
    }

    public void setReferrerApproval(Approval referrerApproval) {
        this.referrerApproval = referrerApproval;
    }

    public Approval getAdminApproval() {
        return adminApproval;
    }

    public void setAdminApproval(Approval adminApproval) {
        this.adminApproval = adminApproval;
    }

    public Date getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(Date requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    public Date getResponseDateTime() {
        return responseDateTime;
    }

    public void setResponseDateTime(Date responseDateTime) {
        this.responseDateTime = responseDateTime;
    }

    @Override
    public String toString() {
        return "MemberApprovalRequest{" +
                "id=" + id +
                ", member=" + member +
                ", referrerApproval=" + referrerApproval +
                ", adminApproval=" + adminApproval +
                ", requestDateTime=" + requestDateTime +
                ", responseDateTime=" + responseDateTime +
                '}';
    }
}
