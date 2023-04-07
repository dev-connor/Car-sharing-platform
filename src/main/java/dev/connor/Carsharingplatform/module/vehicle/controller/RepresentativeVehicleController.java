package dev.connor.Carsharingplatform.module.vehicle.controller;

import dev.connor.Carsharingplatform.module.vehicle.dto.RepresentativeVehicleDto;
import dev.connor.Carsharingplatform.module.vehicle.service.RepresentativeVehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.util.Assert.isTrue;

@RequiredArgsConstructor
@RequestMapping("/vehicles")
@RestController
public class RepresentativeVehicleController {
    private final RepresentativeVehicleService representativeVehicleService;

    @GetMapping
//    @Secured({AuthorityRoleType.Roles.SUPER_MANAGER, AuthorityRoleType.Roles.OPERATION_MANAGER, AuthorityRoleType.Roles.CS_MANAGER, AuthorityRoleType.Roles.DELIVERY_MANAGER})
    public Page<RepresentativeVehicleDto.Response> findAll(Pageable pageable, RepresentativeVehicleDto.PageRequest request) {
        var response = representativeVehicleService.findAll(pageable, request);
        return response;
    }

    @PostMapping
//    @Secured({AuthorityRoleType.Roles.SUPER_MANAGER, AuthorityRoleType.Roles.OPERATION_MANAGER})
    public RepresentativeVehicleDto.Response create(@RequestBody @Validated RepresentativeVehicleDto.SaveRequest request) {
        return representativeVehicleService.create(request);
    }

    @PutMapping("/{representativeVehicleId}")
//    @Secured({AuthorityRoleType.Roles.SUPER_MANAGER, AuthorityRoleType.Roles.OPERATION_MANAGER})
    public RepresentativeVehicleDto.Response update(@PathVariable("representativeVehicleId") Long representativeVehicleId,
                                                       @RequestBody @Validated RepresentativeVehicleDto.SaveRequest detail) {
        isTrue(representativeVehicleId != null, "invalid id");
        return representativeVehicleService.update(representativeVehicleId, detail);
    }
}