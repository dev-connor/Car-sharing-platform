package dev.connor.Carsharingplatform.module.membership.service;

import dev.connor.Carsharingplatform.common.util.SecurityUtil;
import dev.connor.Carsharingplatform.module.membership.dto.UserDto;
import dev.connor.Carsharingplatform.module.membership.entity.Authority;
import dev.connor.Carsharingplatform.module.membership.entity.User;
import dev.connor.Carsharingplatform.module.membership.repository.UserRepository;
import javassist.bytecode.DuplicateMemberException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class UserService {
    private final ModelMapper mapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDto signup(UserDto userDto) throws DuplicateMemberException {
        if (userRepository.findOneWithAuthoritiesByUsername(userDto.getUsername()).orElse(null) != null) {
            throw new DuplicateMemberException("이미 가입되어 있는 유저입니다.");
//            throw new DuplicateMemberException();
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        var user = User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .nickname(userDto.getNickname())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        return mapper.map(userRepository.save(user), UserDto.class);
    }

    @Transactional(readOnly = true)
    public UserDto getUserWithAuthorities(String username) {
        var response = userRepository.findOneWithAuthoritiesByUsername(username).orElse(null);
        return mapper.map(response, UserDto.class);
    }

    @Transactional(readOnly = true)
    public UserDto getMyUserWithAuthorities() {
        var response = SecurityUtil.getCurrentUsername()
                .flatMap(userRepository::findOneWithAuthoritiesByUsername)
//                .orElseThrow(() -> new NotFoundMemberException("Member not found"));
                .orElseThrow();
        return mapper.map(response, UserDto.class);
    }
}
