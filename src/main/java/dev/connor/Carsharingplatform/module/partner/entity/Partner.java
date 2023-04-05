package dev.connor.Carsharingplatform.module.partner.entity;

import dev.connor.Carsharingplatform.common.base.BaseEntity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"partnerId"}, callSuper = false)
@Table(name = "tbl_partner", schema = "partner")
public class Partner extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "partner_id")
    Long partnerId;

    @Column(name = "service_id", length = 22, nullable = false)
    String serviceId;

    @Column(name = "organization_name", length = 100, nullable = false)
    String organizationName;

    @Column(name = "business_number", length = 10, nullable = false)
    String businessNumber;

    @Column(name = "industry_type_code", length = 10, nullable = false)
    String industryTypeCode;

    @Column(name = "business_type_code", length = 10, nullable = false)
    String businessTypeCode;

    @Column(name = "partner_email", length = 255)
    String partnerEmail;

    @Column(name = "tax_bill_email", length = 255)
    String taxBillEmail;

    @Column(name = "partner_phone_number", length = 45)
    String partnerPhoneNumber;

    @Column(name = "sales_number", length = 50)
    String salesNumber;

    @Column(name = "location_address", length = 255)
    String locationAddress;

    @Column(name = "detail_address", length = 255)
    String detailAddress;

    @Column(name = "representative_name", length = 100, nullable = false)
    String representativeName;

    @Column(name = "representative_phone_number", length = 45, nullable = false)
    String representativePhoneNumber;

    @Column(name = "account_bank_code", length = 10)
    String accountBankCode;

    @Column(name = "account_name", length = 100)
    String accountName;

    @Column(name = "account_number", length = 30)
    String accountNumber;

    @Column(name = "business_license_file_id", length = 22)
    String businessLicenseFileId;

    @Column(name = "bank_book_file_id", length = 22)
    String bankBookFileId;

    @Column(name = "partnership_agreement_file_id", length = 22)
    String partnershipAgreementFileId;

    @Column(name = "communication_attestation_file_id", length = 22)
    String communicationAttestationFileId;

    @Column(name = "used_yn", nullable = false)
    Character usedYn;

    @Column(name = "road_address", length = 255)
    String roadAddress;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "partner")
    List<PartnerAuthorityRole> partnerAuthorityRoles = new ArrayList<>();
}
