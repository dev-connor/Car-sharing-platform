package dev.connor.Carsharingplatform.module.membership.controller;

import dev.connor.Carsharingplatform.module.membership.dto.AdminManagementDto;
import dev.connor.Carsharingplatform.module.membership.service.ManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/management/members")
@RequiredArgsConstructor
public class AdminManagementController {
    private final ManagementService managementService;

    /**
     * FMS 관리자 메뉴에서 관리자 리스트를 조회 한다.
     *
     * @param pageable
     * @param filter json serviceMemberName 검색할 이름
     * @return
     * @title 관리자 리스트 조회
     */
    @GetMapping
//    @Secured({AuthorityRoleType.Roles.SUPER_MANAGER})
    public Page<AdminManagementDto.MemberItem> findAdminManagementMembers(Pageable pageable, AdminManagementDto.MemberFilter filter) {

        /**
         * 관리자 중 password가 없는 사용자는 초대에 승인되지 않은 멤버이므로
         * service_member중 pasword is not null 사용자만 노출하도록 한다.
         */
        return managementService.findAdminManagementMembers(pageable,filter);
    }
}
