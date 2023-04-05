package dev.connor.Carsharingplatform.module.partner.repository;

import dev.connor.Carsharingplatform.module.partner.entity.Partner;
import dev.connor.Carsharingplatform.module.partner.entity.QPartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long>, QuerydslPredicateExecutor<Partner>, PartnerCustomRepository {
    Partner findByPartnerId(Long partnerId);
}

interface PartnerCustomRepository {

}

class PartnerCustomRepositoryImpl extends QuerydslRepositorySupport implements PartnerCustomRepository {
    public PartnerCustomRepositoryImpl() {
        super(Partner.class);
    }

}