package dev.connor.Carsharingplatform.module.partner.repository;

import dev.connor.Carsharingplatform.module.partner.entity.PartnerMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartnerMemberRepository extends JpaRepository<PartnerMember, Long>, PartnerMemberCustomRepository {
    Optional<PartnerMember> findByPhoneNumber(String phoneNumber);
}

interface PartnerMemberCustomRepository {
}

class PartnerMemberCustomRepositoryImpl extends QuerydslRepositorySupport implements PartnerMemberCustomRepository {

    public PartnerMemberCustomRepositoryImpl() {
        super(PartnerMember.class);
    }
}