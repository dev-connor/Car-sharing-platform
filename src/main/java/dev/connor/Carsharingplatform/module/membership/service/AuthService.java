package dev.connor.Carsharingplatform.module.membership.service;

import dev.connor.Carsharingplatform.module.partner.entity.PartnerMember;
import dev.connor.Carsharingplatform.module.partner.repository.PartnerMemberRepository;
import dev.connor.Carsharingplatform.module.partner.repository.PartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AuthService implements UserDetailsService {
    private final PartnerMemberRepository partnerMemberRepository;
    private final PartnerRepository partnerRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String phoneNumber) {
        return partnerMemberRepository.findByPhoneNumber(phoneNumber)
                .map(partnerMember -> createUser(phoneNumber, partnerMember))
                .orElseThrow(() -> new UsernameNotFoundException(phoneNumber + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    private org.springframework.security.core.userdetails.User createUser(String phoneNumber, PartnerMember partnerMember) {
        var partner = partnerRepository.findByPartnerId(partnerMember.getPartnerId());

//        List<GrantedAuthority> grantedAuthorities = partner.getPartnerAuthorityRoles().stream()
//                .map(authority -> new SimpleGrantedAuthority("ROLE_USER"))
//                .collect(Collectors.toList());

        var grantedAuthorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));

        return new org.springframework.security.core.userdetails.User(partnerMember.getPhoneNumber(),
                partnerMember.getRoadAddress(),
                grantedAuthorities);
    }
}