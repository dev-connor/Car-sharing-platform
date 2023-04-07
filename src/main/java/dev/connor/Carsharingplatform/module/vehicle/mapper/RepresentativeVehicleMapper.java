package dev.connor.Carsharingplatform.module.vehicle.mapper;

import dev.connor.Carsharingplatform.module.vehicle.dto.RepresentativeVehicleDto;
import dev.connor.Carsharingplatform.module.vehicle.entity.RepresentativeVehicle;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RepresentativeVehicleMapper {
    RepresentativeVehicleDto.Response toDto(RepresentativeVehicle representativeVehicle);
}
