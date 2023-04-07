package dev.connor.Carsharingplatform.module.partner.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import dev.connor.Carsharingplatform.module.membership.dto.AdminManagementDto;
import dev.connor.Carsharingplatform.module.membership.entity.QServiceMember;
import dev.connor.Carsharingplatform.module.partner.entity.PartnerMember;
import dev.connor.Carsharingplatform.module.partner.entity.QPartner;
import dev.connor.Carsharingplatform.module.partner.entity.QPartnerMember;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface PartnerMemberRepository extends JpaRepository<PartnerMember, Long>, PartnerMemberCustomRepository {
    Optional<PartnerMember> findByPhoneNumber(String phoneNumber);
}

interface PartnerMemberCustomRepository {
    Page<PartnerMember> findAdminManagementMembers(Pageable pageable, AdminManagementDto.MemberFilter filter);
}

class PartnerMemberCustomRepositoryImpl extends QuerydslRepositorySupport implements PartnerMemberCustomRepository {

    public PartnerMemberCustomRepositoryImpl() {
        super(PartnerMember.class);
    }

    @Override
    public Page<PartnerMember> findAdminManagementMembers(Pageable pageable, AdminManagementDto.MemberFilter filter) {
        BooleanBuilder builder = new BooleanBuilder();

        QServiceMember serviceMember = QServiceMember.serviceMember;
        QPartnerMember partnerMember = QPartnerMember.partnerMember;
        QPartner partner = QPartner.partner;

        var query = from(partnerMember).where(builder);
        var results = getQuerydsl().applyPagination(pageable, query).fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }
}