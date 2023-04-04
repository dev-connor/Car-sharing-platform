package dev.connor.Carsharingplatform.module.partner.controller;

import dev.connor.Carsharingplatform.module.partner.dto.AdminPartnerMemberDto;
import dev.connor.Carsharingplatform.module.partner.service.PartnerMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping(AdminPartnerMemberController.ROOT_URI)
@RequiredArgsConstructor
public class AdminPartnerMemberController {
    public static final String ROOT_URI = "/api/admin/partners/members";
    private final PartnerMemberService partnerMemberService;

    /**
     * 협력업체직원 등록
     * @param req 등록할 협력업체직원 정보
     * @return
     * @title 협력업체직원 등록
     */
    @PostMapping
//    @Secured({AuthorityRoleType.Roles.SUPER_MANAGER, AuthorityRoleType.Roles.OPERATION_MANAGER, AuthorityRoleType.Roles.CS_MANAGER, AuthorityRoleType.Roles.DELIVERY_MANAGER})
    public AdminPartnerMemberDto.Detail save(@RequestBody AdminPartnerMemberDto.ReqInsert req) {
        return partnerMemberService.save(req);
    }
}
