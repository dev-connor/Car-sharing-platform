package dev.connor.Carsharingplatform.module.partner.entity;

import dev.connor.Carsharingplatform.common.base.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"partnerAuthorityRoleType", "partner"}, callSuper = false)
@Table(name = "tbl_partner_authority_role", schema = "partner")
@IdClass(PartnerAuthorityRole.ID.class)
public class PartnerAuthorityRole extends BaseEntity {

    @Id
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "partner_authority_role_type_id", updatable = false, nullable = false)
    PartnerAuthorityRoleType partnerAuthorityRoleType;

    @Id
    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumn(name = "partner_id", updatable = false, nullable = false)
    Partner partner;

    @Data
//    @EqualsAndHashCode(of = {"vehicle", "device"})
    public static class ID implements Serializable {
        Integer partnerAuthorityRoleType;
        Long partner;
    }
}
