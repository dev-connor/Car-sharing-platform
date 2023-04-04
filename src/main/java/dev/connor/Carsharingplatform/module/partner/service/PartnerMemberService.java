package dev.connor.Carsharingplatform.module.partner.service;

import com.google.common.base.Strings;
import com.querydsl.core.BooleanBuilder;
import dev.connor.Carsharingplatform.module.partner.dto.AdminPartnerMemberDto;
import dev.connor.Carsharingplatform.module.partner.entity.PartnerMember;
import dev.connor.Carsharingplatform.module.partner.repository.PartnerMemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartnerMemberService {
    private final ModelMapper mapper;
    private final PartnerMemberRepository partnerMemberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public AdminPartnerMemberDto.Detail save(AdminPartnerMemberDto.ReqInsert dto) {
        var entity = mapper.map(dto, PartnerMember.class);

        if(entity.getUsedYn() == null) {
            entity.setUsedYn('N');
        }

        entity.setRoadAddress(passwordEncoder.encode(dto.getPassword()));
        entity = partnerMemberRepository.save(entity);

        return mapper.map(entity, AdminPartnerMemberDto.Detail.class);
    }
}