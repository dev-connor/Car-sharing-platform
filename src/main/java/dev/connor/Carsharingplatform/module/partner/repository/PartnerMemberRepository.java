package dev.connor.Carsharingplatform.module.partner.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPAExpressions;
import dev.connor.Carsharingplatform.module.partner.dto.AdminPartnerMemberDto;
import dev.connor.Carsharingplatform.module.partner.entity.PartnerMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartnerMemberRepository extends JpaRepository<PartnerMember, Long>, PartnerMemberCustomRepository {
    Optional<PartnerMember> findPartnerMemberByPhoneNumber(String phoneNumber);
}

interface PartnerMemberCustomRepository {
}

class PartnerMemberCustomRepositoryImpl extends QuerydslRepositorySupport implements PartnerMemberCustomRepository {

    public PartnerMemberCustomRepositoryImpl() {
        super(PartnerMember.class);
    }
}