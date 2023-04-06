package dev.connor.Carsharingplatform.module.common.entity;

import dev.connor.Carsharingplatform.common.base.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"menu", "authorityRoleTypeId"}, callSuper = false)
@Table(name = "tbl_menu_by_role_type", schema = "common")
@IdClass(MenuByRoleType.ID.class)
public class MenuByRoleType extends BaseEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    Menu menu;

    @Id
    @Column(name = "authority_role_type_id", nullable = false)
    Integer authorityRoleTypeId;

    @Column(name = "read_yn")
    Character readYn;

    @Column(name = "write_yn")
    Character writeYn;

    public MenuByRoleType() {
    }

//    public AuthorityRoleType getAuthorityRoleType() {
//        return AuthorityRoleType.ofCode(authorityRoleTypeId);
//    }
//
//    public void setAuthorityRoleType(AuthorityRoleType authorityRoleType) {
//        this.authorityRoleTypeId = authorityRoleType.getCode();
//    }

    @Data
    public static class ID implements Serializable {

        Integer menu;

        Integer authorityRoleTypeId;

    }
}
