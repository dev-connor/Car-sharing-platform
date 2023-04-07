package dev.connor.Carsharingplatform.module.membership.service;

import dev.connor.Carsharingplatform.module.membership.dto.AdminManagementDto;
import dev.connor.Carsharingplatform.module.partner.repository.PartnerMemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class ManagementService {
    private final PartnerMemberRepository partnerMemberRepository;

    public Page<AdminManagementDto.MemberItem> findAdminManagementMembers(Pageable pageable, AdminManagementDto.MemberFilter filter) {
        var response = partnerMemberRepository.findAdminManagementMembers(pageable, filter);
        return null;
    }
}
