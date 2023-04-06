package dev.connor.Carsharingplatform.module.common.repository;

import dev.connor.Carsharingplatform.common.util.SecurityUtil;
import dev.connor.Carsharingplatform.module.common.entity.Menu;
import dev.connor.Carsharingplatform.module.common.entity.QMenu;
import dev.connor.Carsharingplatform.module.common.entity.QMenuByRoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long>, MenuCustomRepository {
}

interface MenuCustomRepository {
    List<Menu> findReadableAuthorityMenus();
}

class MenuCustomRepositoryImpl extends QuerydslRepositorySupport implements MenuCustomRepository {
    public MenuCustomRepositoryImpl() {
        super(Menu.class);
    }

    @Override
    public List<Menu> findReadableAuthorityMenus() {
        var authorityRoleTypeId = SecurityUtil.getCurrentUserRole().get();

        QMenu menu = QMenu.menu;
        QMenuByRoleType menuByRoleType = QMenuByRoleType.menuByRoleType;

        List<Menu> entities =
                from(menu)
                        .join(menu.menuByRoleTypes, menuByRoleType)
                        .where(menuByRoleType.authorityRoleTypeId.eq(authorityRoleTypeId)
                                        .and(menu.parentMenu.isNull())
                        )
                        .orderBy(menu.orderIndex.asc())
                        .fetch();

        return entities;
    }
}