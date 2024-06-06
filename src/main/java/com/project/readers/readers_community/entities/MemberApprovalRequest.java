package com.project.readers.readers_community.entities;

import com.project.readers.readers_community.enums.Approval;
import jakarta.persistence.*;

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

    public MemberApprovalRequest(Integer id, User member, Approval referrerApproval, Approval adminApproval) {
        this.id = id;
        this.member = member;
        this.referrerApproval = referrerApproval;
        this.adminApproval = adminApproval;
    }

    public MemberApprovalRequest() {}

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

    @Override
    public String toString() {
        return "MemberApprovalRequest{" +
                "id=" + id +
                ", member=" + member +
                ", referrerApproval=" + referrerApproval +
                ", adminApproval=" + adminApproval +
                '}';
    }
}
