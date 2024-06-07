package com.project.readers.readers_community.converters;

import com.project.readers.readers_community.entities.MemberApprovalRequest;
import com.project.readers.readers_community.repositories.MemberApprovalRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class IntToMemberApprovalRequest implements Converter<Integer, MemberApprovalRequest>
{
    private final MemberApprovalRequestRepository memberApprovalRequestRepository;

    @Autowired
    public IntToMemberApprovalRequest(MemberApprovalRequestRepository memberApprovalRequestRepository)
    {
        this.memberApprovalRequestRepository = memberApprovalRequestRepository;
    }

    @Override
    public MemberApprovalRequest convert(Integer source) {
        return memberApprovalRequestRepository.findById(source).orElse(null);
    }
}
