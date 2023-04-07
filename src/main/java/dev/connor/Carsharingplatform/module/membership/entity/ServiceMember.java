package dev.connor.Carsharingplatform.module.membership.entity;

import dev.connor.Carsharingplatform.common.base.BaseEntity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

@Entity
@Getter
@Setter
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
//@EqualsAndHashCode(of = {"serviceMemberId", "corporation_id"}, callSuper = false)
@Table(name = "tbl_service_member", schema = "membership")
public class ServiceMember extends BaseEntity {
    @Id
    @Column(name = "service_member_id", length = 22, nullable = false)
    String serviceMemberId;

    @Column(name = "service_id", length = 22, nullable = false)
//    @Convert(converter = ServiceType.TypeConverter.class)
//    ServiceType service;
    String serviceId;

    @Column(name = "service_member_name", length = 512, nullable = false)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    String encryptedServiceMemberName;

    transient String serviceMemberName;

    @Column(name = "service_member_email", length = 512, nullable = false)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    String encryptedServiceMemberEmail;

    transient String serviceMemberEmail;

    @Column(name = "service_member_mobile_phone_number", length = 512, nullable = false)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    String encryptedServiceMemberMobilePhoneNumber;

    transient String serviceMemberMobilePhoneNumber;

    @Column(name = "service_member_birth_date", nullable = false)
    LocalDate serviceMemberBirthDate;

    @Column(name = "driver_license_type_code", length = 10)
    String driverLicenseTypeCode;

    @Column(name = "driver_license_approval_area", length = 4)
    String driverLicenseApprovalArea;

    @Column(name = "driver_license_number", length = 512)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    String encryptedDriverLicenseNumber; // 12자리

    transient String driverLicenseNumber;

    @Column(name = "driver_license_approval_date")
    LocalDate driverLicenseApprovalDate;

    @Column(name = "reception_sms_datetime")
    Instant receptionSmsDatetime;

    @Column(name = "reception_email_datetime")
    Instant receptionEmailDatetime;

    @Column(name = "reception_push_datetime")
    Instant receptionPushDatetime;

    @Column(name = "invite_code", length = 10)
    String inviteCode;

    @Column(name = "join_at", nullable = false)
    Instant joinAt;

    @Column(name = "last_login_at", nullable = false)
    Instant lastLoginAt;

    @Column(name = "cipher_key_id", length = 22)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    String cipherKeyId;

    @Column(name = "service_member_name_sha", length = 512)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    String serviceMemberNameSha;

    @Column(name = "service_member_email_sha", length = 512)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    String serviceMemberEmailSha;

    @Column(name = "service_member_mobile_phone_number_sha", length = 512)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    String serviceMemberMobilePhoneNumberSha;

    @Column(name = "service_member_cert_type", length = 1)
    String serviceMemberCertType;

    @Column(name = "service_member_cert_at")
    Instant serviceMemberCertAt;

    @Column(name = "service_member_gender")
    Character serviceMemberGender;

    @Column(name = "service_member_nation_info", length = 10)
    String serviceMemberNationInfo;

    @Column(name = "service_member_ci", length = 512)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    String encryptedServiceMemberCi;

    transient String serviceMemberCi;

    @Column(name = "service_member_ci_sha", length = 512)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    String serviceMemberCiSha;

    @Column(name = "service_member_di", length = 512)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    String encryptedServiceMemberDi;

    transient String serviceMemberDi;

    @Column(name = "service_member_di_sha", length = 512)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    String serviceMemberDiSha;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "serviceMember", orphanRemoval = true)
//    Set<ServiceMemberAuthorityRole> serviceMemberAuthorityRoles = new HashSet<>();
//
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "serviceMember", orphanRemoval = true)
//    List<MemberAgreement> memberAgreements = new ArrayList<>();
//
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "serviceMember", orphanRemoval = true)
//    @OrderBy("majorYn desc, memberAddressId desc")
//    Set<MemberAddress> memberAddresses = new LinkedHashSet<>();
//
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "serviceMember", orphanRemoval = true)
//    @OrderBy("blacklistMemberId desc")
//    @Setter(AccessLevel.PRIVATE)
//    List<BlacklistMember> blacklistMembers = new ArrayList<>();

//    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
//    @JoinColumn(name = "corporation_id", nullable = true)
//    Corporation corporation;

    @Column(name = "corporation_id")
    Long corporationId;

//    public ServiceMember addAuthorityRole(AuthorityRoleType type) {
//        for (var serviceMemberAuthorityRole : serviceMemberAuthorityRoles) {
//            if (serviceMemberAuthorityRole.getAuthorityRoleType() == type) {
//                return this;
//            }
//        }
//        serviceMemberAuthorityRoles.add(
//                new ServiceMemberAuthorityRole().setServiceMember(this).getAuthorityRoleType(type)
//        );
//        return this;
//    }
//
//    public ServiceMember clearAuthorityRoles() {
//        serviceMemberAuthorityRoles.clear();
//        return this;
//    }
//
//    public BlacklistMember registerBlacklistMember(String blacklistRegistrationCode, String blacklistRegistrationDetail) {
//        BlacklistMember blacklistMember = new BlacklistMember();
//        blacklistMember.setServiceMember(this);
//        blacklistMember.setBlacklistRegistrationCode(blacklistRegistrationCode);
//        blacklistMember.setBlacklistRegistrationDetail(blacklistRegistrationDetail);
//        blacklistMember.setUsedYn('Y');
//
//        blacklistMembers.add(blacklistMember);
//
//        return blacklistMember;
//    }
//
//    /**
//     * 등록순으로 정렬이 되어있으므로, 첫번째 레코드의 블랙리스트 회원이 활성화되어 있으면 블랙 리스트이다.
//     *
//     * @return 블랙리스트 여부
//     */
//    public boolean isBlacklistMember() {
//        return !blacklistMembers.isEmpty() && blacklistMembers.get(0).getUsedYn() == 'Y';
//    }
//
//    /**
//     * 등록순으로 정렬이 되어있으므로, 첫번째 레코드의 블랙리스트 회원이 활성화되어 있으면 블랙 리스트이다.
//     *
//     * @return 블랙리스트 여부(Y/N)
//     */
//    public Character getBlacklistMemberYn() {
//        return isBlacklistMember() ? Constants.YES : Constants.NO;
//    }
//
//    public ServiceMember cancelBlacklistMember(String cancellationReason) {
//        getBlacklistMembers().stream().filter(t -> t.getUsedYn().equals('Y')).forEach(blacklistMember -> {
//            blacklistMember.setUsedYn('N');
//            blacklistMember.setCancellationDate(Instant.now());
//            blacklistMember.setCancellationReason(cancellationReason);
//        });
//
//        return this;
//    }
//
//    public MemberAgreement addMemberAgreement(Terms terms, Character agreementYn) {
//        if (terms.getAgreementRepeatYn() != 'Y') {
//            for (var agreement : memberAgreements) {
//                if (agreement.getTerms().getTermsId().equals(terms.getTermsId())) {
//                    // 기존 등록된 약관이 있으면 개신한다.
//                    agreement.setAgreementYn(agreementYn);
//                    agreement.setAgreementAt(Instant.now());
//                    return agreement;
//                }
//            }
//        }
//        var memberAgreement = new MemberAgreement();
//        memberAgreement.setServiceMember(this);
//        memberAgreement.setTerms(terms);
//        memberAgreement.setAgreementYn(agreementYn);
//        memberAgreement.setAgreementAt(Instant.now());
//
//        memberAgreements.add(memberAgreement);
//        if (terms.getTermsCategoryCode().equals("TRCG000002")) {
//            addAuthorityRole(AuthorityRoleType.USER);
//        }
//        return memberAgreement;
//    }
//
//    public MemberAddress addMemberAddress(MemberAddress memberAddress) {
//        memberAddress.setServiceMember(this);
//        if (memberAddresses.contains(memberAddress)) {
//            // 동록된 주소가 있는 경우
//            for (var address : memberAddresses) {
//                if (address.equals(memberAddress)) {
//                    if (memberAddress.getMajorYn() == 'Y') {
//                        address.setMajorYn('Y');
//                    }
//                } else {
//                    if (memberAddress.getMajorYn() == 'Y') {
//                        address.setMajorYn('N');
//                    }
//                }
//            }
//        } else {
//            if (memberAddress.getMajorYn() == 'Y') {
//                // 실거주지는 1개만 등록되어 있어야 함
//                memberAddresses.stream().filter(address -> address.getMajorYn() == 'Y').forEach(address -> address.setMajorYn('N'));
//            }
//            memberAddresses.add(memberAddress);
//        }
//
//        return memberAddress;
//    }
//
//    /**
//     * 실제 거주지 주소를 등록했는지 확인 한다. 첫번째 레코드가 실 거주지 주소이다.
//     *
//     * @return 실거주지 주소 보유 여부
//     */
//    public boolean hasResidenceAddress() {
//        for (var address : getMemberAddresses()) {
//            if (address.getMajorYn() == 'Y') {
//                return true;
//            }
//            break;
//        }
//        return false;
//    }
//
//    /**
//     * 실거지주 정보를 반환 한다.
//     *
//     * @return 실거주지 정보
//     */
//    public MemberAddress getResidenceAddress() {
//        for (var address : getMemberAddresses()) {
//            if (address.getMajorYn() == 'Y') {
//                return address;
//            }
//            break;
//        }
//        return null;
//    }
//
//    public void setServiceMemberName(String serviceMemberName) {
//        // https://git-friends.42dot.ai/tapent/kiaflex-operation/-/issues/783
//        // Member.java에 동일 로직 있음
//        if (serviceMemberName.length() > 32) {
//            throw new IllegalArgumentException("Illegal serviceMemberName");
//        }
//        this.serviceMemberName = serviceMemberName;
//        this.serviceMemberNameSha = CipherUtils.sha(serviceMemberName);
//    }
//
//    public void setServiceMemberEmail(String serviceMemberEmail) {
//        if (serviceMemberEmail.length() > 255) {
//            throw new IllegalArgumentException("Illegal serviceMemberEmail");
//        }
//        this.serviceMemberEmail = serviceMemberEmail;
//        this.serviceMemberEmailSha = CipherUtils.sha(serviceMemberEmail);
//    }
//
//    public ServiceMember setServiceMemberMobilePhoneNumber(String serviceMemberMobilePhoneNumber) {
//        Assert.notNull(serviceMemberMobilePhoneNumber, "ServiceMemberMobilePhoneNumber must not be null");
//        if (serviceMemberMobilePhoneNumber.startsWith("+82")) {
//            serviceMemberMobilePhoneNumber = "0" + serviceMemberMobilePhoneNumber.substring(3);
//        } else if (serviceMemberMobilePhoneNumber.startsWith("82")) {
//            serviceMemberMobilePhoneNumber = "0" + serviceMemberMobilePhoneNumber.substring(2);
//        }
//
//        this.serviceMemberMobilePhoneNumber = serviceMemberMobilePhoneNumber;
//        this.serviceMemberMobilePhoneNumberSha = CipherUtils.sha(serviceMemberMobilePhoneNumber);
//        return this;
//    }
//
//    public void setDriverLicenseNumber(String driverLicenseNumber) {
//        if (driverLicenseNumber != null && driverLicenseNumber.length() > 12) {
//            throw new IllegalArgumentException("Illegal driverLicenseNumber");
//        }
//        this.driverLicenseNumber = driverLicenseNumber;
//    }
//
//    public void setServiceMemberCi(String serviceMemberCi) {
//        if (serviceMemberCi != null && serviceMemberCi.length() > 88) {
//            throw new IllegalArgumentException("Illegal serviceMemberCi");
//        }
//        this.serviceMemberCi = serviceMemberCi;
//        this.serviceMemberCiSha = CipherUtils.sha(serviceMemberCi);
//    }
//
//    public void setServiceMemberDi(String serviceMemberDi) {
//        if (serviceMemberDi != null && serviceMemberDi.length() > 64) {
//            throw new IllegalArgumentException("Illegal serviceMemberDi");
//        }
//        this.serviceMemberDi = serviceMemberDi;
//        this.serviceMemberDiSha = CipherUtils.sha(serviceMemberDi);
//    }
//
//    @PrePersist
//    @PreUpdate
//    protected void encrypt() throws Exception {
//
//        //TODO service Id 삽입
//        CipherData encryptedData = CipherUtils.encrypt(serviceId, "membership", "tbl_service_member", serviceMemberName, serviceMemberEmail,
//                serviceMemberMobilePhoneNumber, driverLicenseNumber, serviceMemberCi, serviceMemberDi);
//        this.cipherKeyId = encryptedData.getId();
//        this.encryptedServiceMemberName = encryptedData.getEncryptedData().get(0);
//        this.encryptedServiceMemberEmail = encryptedData.getEncryptedData().get(1);
//        this.encryptedServiceMemberMobilePhoneNumber = encryptedData.getEncryptedData().get(2);
//        this.encryptedDriverLicenseNumber = encryptedData.getEncryptedData().get(3);
//        this.encryptedServiceMemberCi = encryptedData.getEncryptedData().get(4);
//        this.encryptedServiceMemberDi = encryptedData.getEncryptedData().get(5);
//    }
//
//    @PostLoad
//    public void logUserLoad() throws Exception {
//        if (cipherKeyId != null) {
//            List<String> decryptedData = CipherUtils.decrypt(cipherKeyId, encryptedServiceMemberName, encryptedServiceMemberEmail,
//                    encryptedServiceMemberMobilePhoneNumber, encryptedDriverLicenseNumber, encryptedServiceMemberCi, encryptedServiceMemberDi);
//            this.serviceMemberName = decryptedData.get(0);
//            this.serviceMemberEmail = decryptedData.get(1);
//            this.serviceMemberMobilePhoneNumber = decryptedData.get(2);
//            this.driverLicenseNumber = decryptedData.get(3);
//            this.serviceMemberCi = decryptedData.get(4);
//            this.serviceMemberDi = decryptedData.get(5);
//        } else {
//            this.serviceMemberName = encryptedServiceMemberName;
//            this.serviceMemberEmail = encryptedServiceMemberEmail;
//            this.serviceMemberMobilePhoneNumber = encryptedServiceMemberMobilePhoneNumber;
//            this.driverLicenseNumber = encryptedDriverLicenseNumber;
//            this.serviceMemberCi = encryptedServiceMemberCi;
//            this.serviceMemberDi = encryptedServiceMemberDi;
//        }
//    }
}
