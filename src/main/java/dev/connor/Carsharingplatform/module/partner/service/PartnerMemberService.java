package dev.connor.Carsharingplatform.module.partner.service;

import dev.connor.Carsharingplatform.module.partner.dto.AdminPartnerMemberDto;
import dev.connor.Carsharingplatform.module.partner.entity.PartnerMember;
import dev.connor.Carsharingplatform.module.partner.repository.PartnerMemberRepository;
import dev.connor.Carsharingplatform.module.partner.repository.PartnerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PartnerMemberService {
    private final ModelMapper mapper;
    private final PartnerRepository partnerRepository;
    private final PartnerMemberRepository partnerMemberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public AdminPartnerMemberDto.Response save(AdminPartnerMemberDto.Request request) {

        if (partnerMemberRepository.findByPhoneNumber(request.getPhoneNumber()).isPresent()) {
            throw new DuplicateKeyException("이미 가입되어 있는 유저입니다.");
        }

        var partnerMember = mapper.map(request, PartnerMember.class);
        var partner = partnerRepository.findById(request.getPartnerId()).get();

        if(partnerMember.getUsedYn() == null) {
            partnerMember.setUsedYn('N');
        }

        partnerMember.setRoadAddress(passwordEncoder.encode(request.getPassword()));
        partnerMember.setPartner(partner);
        partnerMember = partnerMemberRepository.save(partnerMember);

        return mapper.map(partnerMember, AdminPartnerMemberDto.Response.class);
    }
}