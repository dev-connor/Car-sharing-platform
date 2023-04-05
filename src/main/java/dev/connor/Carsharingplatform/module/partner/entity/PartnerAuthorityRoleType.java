package dev.connor.Carsharingplatform.module.partner.entity;

import dev.connor.Carsharingplatform.common.base.BaseEntity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"partnerAuthorityRoleTypeId"}, callSuper = false)
@Table(name = "tbl_partner_authority_role_type", schema = "partner")
public class PartnerAuthorityRoleType extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "partner_authority_role_type_id", nullable = false)
    Integer partnerAuthorityRoleTypeId;

    @Column(name = "partner_authority_role_type_name", nullable = false)
    String partnerAuthorityRoleTypeName;

}
