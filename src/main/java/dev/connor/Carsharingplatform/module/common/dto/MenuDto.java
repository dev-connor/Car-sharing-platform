package dev.connor.Carsharingplatform.module.common.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MenuDto {

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class FindAll {
        Long menuId;
        String menuName;
        String menuUrl;
        List<SubMenu> subMenus;
        Long orderIndex;
        Character loggingYn;
        Set<MenuByRoleType> menuByRoleTypes;

        public void changeSubMenus(List<SubMenu> subMenus) {
            this.subMenus = subMenus;
        }

        @Setter
        @Getter
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        @FieldDefaults(level = AccessLevel.PRIVATE)
        public static class SubMenu {
            Long menuId;
            String menuName;
            String menuUrl;
            Long orderIndex;
            Character loggingYn;
            Set<MenuByRoleType> menuByRoleTypes;
        }

        @Setter
        @Getter
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        @FieldDefaults(level = AccessLevel.PRIVATE)
        public static class MenuByRoleType {
            Integer authorityRoleTypeId;
            Character readYn;
            Character writeYn;
        }
    }
}
