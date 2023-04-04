package dev.connor.Carsharingplatform.module.partner.entity;

import dev.connor.Carsharingplatform.common.base.BaseEntity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"partnerMemberId"}, callSuper = false)
@DynamicInsert
@Table(name = "tbl_partner_member", schema = "partner")
public class PartnerMember extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "partner_member_id")
    Long partnerMemberId;

//    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
//    @JoinColumn(name = "partner_id", nullable = false)
//    Partner partner;

    @Column(name = "partner_id")
    Long partnerId;

    @Column(name = "transportation_area_group_id")
    Long transportationAreaGroupId;

    @Column(name = "service_member_id", length = 22)
    String serviceMemberId;

    @Column(name = "partner_job_code", length = 10, nullable = false)
    String partnerJobCode;

    @Column(name = "name", length = 16)
    String name;

    @Column(name = "email", length = 255)
    String email;

    @Column(name = "phone_number", length = 45)
    String phoneNumber;

    @Column(name = "location_address", length = 255)
    String locationAddress;

    @Column(name = "detail_address", length = 255)
    String detailAddress;

    @Column(name = "education_completion_yn")
    Character educationCompletionYn;

    @Column(name = "certified_date")
    Instant certifiedDate;

    @Column(name = "used_yn", nullable = true)
    Character usedYn;

    @Column(name = "road_address", length = 255)
    String roadAddress;

    @Column(name = "profile_image_file_id", length = 22)
    String profileImageFileId;

    @Column(name = "expired_date")
    LocalDate expiredDate;

//    @OneToMany(cascade = {}, fetch = FetchType.LAZY, mappedBy = "partnerMember")
//    List<WorkSpace> workSpaces = new ArrayList<>();

    @Column(name = "pickup_zone_worker_yn")
    Character pickupZoneWorkerYn;

//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "partner_member_id", insertable = false, updatable = false)
//    @OrderBy("invitationSendHistoryId desc")

//    List<InvitationSendHistory> invitationSendHistories = new ArrayList<>();

//    public boolean isDeliveryMan() {
//        return getPartnerJobCode().equals("PRJB000001");
//    }
//
//    public boolean isNotDeliveryMan() {
//        return !isDeliveryMan();
//    }
}