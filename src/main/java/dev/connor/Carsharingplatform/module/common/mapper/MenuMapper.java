package dev.connor.Carsharingplatform.module.common.mapper;

import dev.connor.Carsharingplatform.module.common.dto.MenuDto;
import dev.connor.Carsharingplatform.module.common.entity.Menu;
import dev.connor.Carsharingplatform.module.vehicle.dto.RepresentativeVehicleDto;
import dev.connor.Carsharingplatform.module.vehicle.entity.RepresentativeVehicle;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
public interface MenuMapper {
    MenuDto.FindAll toDto(Menu menu);
}
