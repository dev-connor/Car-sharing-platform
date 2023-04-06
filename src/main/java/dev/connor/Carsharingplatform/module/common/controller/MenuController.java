package dev.connor.Carsharingplatform.module.common.controller;

import dev.connor.Carsharingplatform.module.common.dto.MenuDto;
import dev.connor.Carsharingplatform.module.common.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/common/menus")
@RestController
public class MenuController {
    private final MenuService menuService;

    @GetMapping("/user")
    public List<MenuDto.FindAll> findUserMenus() {
        List<MenuDto.FindAll> response = menuService.findUserMenus();
        return response;
    }
}
