package com.project.readers.readers_community.DTOs;

public class MemberApprovalRequestDTO
{
    private Integer memberApprovalRequestId;
    private Integer memberId;
    private String memberFullName;
    private String memberEmail;
    private String memberPhoneNumber;
    private Integer memberReferrerId;
    private String memberReferrerFullName;
    private String memberReferrerEmail;
    private String memberReferrerProfilePhotoURL;
    private String referrerApproval;
    private String adminApproval;

    public MemberApprovalRequestDTO(Integer memberApprovalRequestId, Integer memberId, String memberFullName, String memberEmail, String memberPhoneNumber, Integer memberReferrerId, String memberReferrerFullName, String memberReferrerEmail, String memberReferrerProfilePhotoURL, String referrerApproval, String adminApproval) {
        this.memberApprovalRequestId = memberApprovalRequestId;
        this.memberId = memberId;
        this.memberFullName = memberFullName;
        this.memberEmail = memberEmail;
        this.memberPhoneNumber = memberPhoneNumber;
        this.memberReferrerId = memberReferrerId;
        this.memberReferrerFullName = memberReferrerFullName;
        this.memberReferrerEmail = memberReferrerEmail;
        this.memberReferrerProfilePhotoURL = memberReferrerProfilePhotoURL;
        this.referrerApproval = referrerApproval;
        this.adminApproval = adminApproval;
    }

    public Integer getMemberApprovalRequestId() {
        return memberApprovalRequestId;
    }

    public void setMemberApprovalRequestId(Integer memberApprovalRequestId) {
        this.memberApprovalRequestId = memberApprovalRequestId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberFullName() {
        return memberFullName;
    }

    public void setMemberFullName(String memberFullName) {
        this.memberFullName = memberFullName;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getMemberPhoneNumber() {
        return memberPhoneNumber;
    }

    public void setMemberPhoneNumber(String memberPhoneNumber) {
        this.memberPhoneNumber = memberPhoneNumber;
    }

    public Integer getMemberReferrerId() {
        return memberReferrerId;
    }

    public void setMemberReferrerId(Integer memberReferrerId) {
        this.memberReferrerId = memberReferrerId;
    }

    public String getMemberReferrerFullName() {
        return memberReferrerFullName;
    }

    public void setMemberReferrerFullName(String memberReferrerFullName) {
        this.memberReferrerFullName = memberReferrerFullName;
    }

    public String getMemberReferrerEmail() {
        return memberReferrerEmail;
    }

    public void setMemberReferrerEmail(String memberReferrerEmail) {
        this.memberReferrerEmail = memberReferrerEmail;
    }

    public String getMemberReferrerProfilePhotoURL() {
        return memberReferrerProfilePhotoURL;
    }

    public void setMemberReferrerProfilePhotoURL(String memberReferrerProfilePhotoURL) {
        this.memberReferrerProfilePhotoURL = memberReferrerProfilePhotoURL;
    }

    public String getReferrerApproval() {
        return referrerApproval;
    }

    public void setReferrerApproval(String referrerApproval) {
        this.referrerApproval = referrerApproval;
    }

    public String getAdminApproval() {
        return adminApproval;
    }

    public void setAdminApproval(String adminApproval) {
        this.adminApproval = adminApproval;
    }

    @Override
    public String toString() {
        return "MemberApprovalRequestDTO{" +
                "memberApprovalRequestId=" + memberApprovalRequestId +
                ", memberId=" + memberId +
                ", memberFullName='" + memberFullName + '\'' +
                ", memberEmail='" + memberEmail + '\'' +
                ", memberPhoneNumber='" + memberPhoneNumber + '\'' +
                ", memberReferrerId=" + memberReferrerId +
                ", memberReferrerFullName='" + memberReferrerFullName + '\'' +
                ", memberReferrerEmail='" + memberReferrerEmail + '\'' +
                ", memberReferrerProfilePhotoURL='" + memberReferrerProfilePhotoURL + '\'' +
                ", referrerApproval='" + referrerApproval + '\'' +
                ", adminApproval='" + adminApproval + '\'' +
                '}';
    }
}
