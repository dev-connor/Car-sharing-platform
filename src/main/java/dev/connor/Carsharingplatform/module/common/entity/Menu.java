package dev.connor.Carsharingplatform.module.common.entity;

import dev.connor.Carsharingplatform.common.base.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@EqualsAndHashCode(of = {"menuId", "menuName"}, callSuper = false)
@Table(name = "tbl_menu", schema = "common")
public class Menu extends BaseEntity {

    private static transient int HIGHEST_PRECEDENCE = Integer.MIN_VALUE;

    private static transient int LOWEST_PRECEDENCE = Integer.MAX_VALUE;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    Integer menuId;

    /**
     * 서비스 ID
     */
    @Column(name = "service_id", length = 22, nullable = false)
    String serviceId;

    @Column(name = "menu_name", length = 500, nullable = false)
    String menuName;

    @Column(name = "menu_url", length = 1000)
    String menuUrl;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<MenuByRoleType> menuByRoleTypes = new HashSet<>();

    @ManyToOne(cascade = {})
    @JoinColumn(name = "parent_menu_id")
    Menu parentMenu;

    @OneToMany(mappedBy = "parentMenu", cascade = {}, orphanRemoval = true)
    @OrderBy("orderIndex")
    List<Menu> subMenus = new ArrayList<>();

    @Column(name = "order_index", nullable = false)
    Integer orderIndex = LOWEST_PRECEDENCE;

    @Column(name = "used_yn", nullable = false)
    Character usedYn;

    @Column(name = "logging_yn", nullable = false)
    Character loggingYn;

    public Menu(String menuName, String menuUrl) {
        this(null, menuName, menuUrl);
    }

    public Menu(Menu parentMenu, String menuName, String menuUrl) {
        if (parentMenu != null) {
            parentMenu.subMenus.add(this);
        }
        this.parentMenu = parentMenu;
        this.menuName = menuName;
        this.menuUrl = menuUrl;
    }

    public Menu setOrderIndex(Integer orderIndex) {
        if (orderIndex == null) {
            this.orderIndex = LOWEST_PRECEDENCE;
        } else {
            this.orderIndex = orderIndex;
        }
        return this;
    }

    public Menu addChildMenu(String name, String uri) {
        return new Menu(this, name, uri);
    }

    public Menu changeRole(Integer roleTypeId, Character readYn, Character writeYn) {
        for (MenuByRoleType menuByRoleType : menuByRoleTypes) {
            if (menuByRoleType.getAuthorityRoleTypeId() == roleTypeId) {
                menuByRoleType.setReadYn(readYn);
                menuByRoleType.setWriteYn(writeYn);
                return this;
            }
        }
        menuByRoleTypes.add(new MenuByRoleType(this, roleTypeId, readYn, writeYn));
        return this;
    }
}
