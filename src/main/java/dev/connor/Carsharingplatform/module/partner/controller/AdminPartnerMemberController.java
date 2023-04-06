package dev.connor.Carsharingplatform.module.partner.controller;

import dev.connor.Carsharingplatform.module.partner.dto.AdminPartnerMemberDto;
import dev.connor.Carsharingplatform.module.partner.service.PartnerMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AdminPartnerMemberController.ROOT_URI)
@RequiredArgsConstructor
public class AdminPartnerMemberController {
    public static final String ROOT_URI = "/api/admin/partners/members";
    private final PartnerMemberService partnerMemberService;

    @PostMapping
//    @Secured({AuthorityRoleType.Roles.SUPER_MANAGER, AuthorityRoleType.Roles.OPERATION_MANAGER, AuthorityRoleType.Roles.CS_MANAGER, AuthorityRoleType.Roles.DELIVERY_MANAGER})
    public AdminPartnerMemberDto.Response save(@RequestBody AdminPartnerMemberDto.Request request) {
        return partnerMemberService.save(request);
    }
}
