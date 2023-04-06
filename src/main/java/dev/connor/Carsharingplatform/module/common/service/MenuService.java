package dev.connor.Carsharingplatform.module.common.service;

import dev.connor.Carsharingplatform.common.util.SecurityUtil;
import dev.connor.Carsharingplatform.module.common.dto.MenuDto;
import dev.connor.Carsharingplatform.module.common.entity.Menu;
import dev.connor.Carsharingplatform.module.common.mapper.MenuMapper;
import dev.connor.Carsharingplatform.module.common.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class MenuService {
    private final MenuMapper mapper;
    private final MenuRepository menuRepository;

    public List<MenuDto.FindAll> findUserMenus() {
        List<Menu> entities = menuRepository.findReadableAuthorityMenus();
        var authorityRoleTypeId = SecurityUtil.getCurrentUserRole().get();
        List<MenuDto.FindAll> dtos = new ArrayList<>();

        for (Menu entity : entities) {
             var dto = mapper.toDto(entity);
            List<MenuDto.FindAll.SubMenu> readableSubMenus = new ArrayList<>();

            for (var subMenu : dto.getSubMenus()) {
                var roleType = subMenu.getMenuByRoleTypes().stream().filter(r -> authorityRoleTypeId.equals(r.getAuthorityRoleTypeId())).findFirst();

                if (roleType.isPresent()) {
                    readableSubMenus.add(subMenu);
                }
            }
            dto.changeSubMenus(readableSubMenus);
            dtos.add(dto);
        }
        return dtos;
    }
}
