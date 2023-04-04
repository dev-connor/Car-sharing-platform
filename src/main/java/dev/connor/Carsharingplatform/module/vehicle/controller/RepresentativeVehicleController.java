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
@RestController
public class RepresentativeVehicleController {
    private final RepresentativeVehicleService representativeVehicleService;

    @GetMapping("/vehicles")
//    @Secured({AuthorityRoleType.Roles.SUPER_MANAGER, AuthorityRoleType.Roles.OPERATION_MANAGER, AuthorityRoleType.Roles.CS_MANAGER, AuthorityRoleType.Roles.DELIVERY_MANAGER})
    public Page<RepresentativeVehicleDto.Response> getAll(Pageable pageable, RepresentativeVehicleDto.PageRequest request) {
        return representativeVehicleService.getAll(pageable, request);
    }

    @PostMapping("/vehicles")
//    @Secured({AuthorityRoleType.Roles.SUPER_MANAGER, AuthorityRoleType.Roles.OPERATION_MANAGER})
    public RepresentativeVehicleDto.Response save(@RequestBody @Validated RepresentativeVehicleDto.SaveRequest request) {
        return representativeVehicleService.create(request);
    }

    @PutMapping("/vehicles/{representativeVehicleId}")
//    @Secured({AuthorityRoleType.Roles.SUPER_MANAGER, AuthorityRoleType.Roles.OPERATION_MANAGER})
    public RepresentativeVehicleDto.Response update(@PathVariable("representativeVehicleId") Long representativeVehicleId,
                                                       @RequestBody @Validated RepresentativeVehicleDto.SaveRequest detail) {
        isTrue(representativeVehicleId != null, "invalid id");
        return representativeVehicleService.update(representativeVehicleId, detail);
    }
}