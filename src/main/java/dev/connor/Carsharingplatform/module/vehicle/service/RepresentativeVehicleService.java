package dev.connor.Carsharingplatform.module.vehicle.service;

import dev.connor.Carsharingplatform.module.vehicle.dto.RepresentativeVehicleDto;
import dev.connor.Carsharingplatform.module.vehicle.entity.RepresentativeVehicle;
import dev.connor.Carsharingplatform.module.vehicle.repository.RepresentativeVehicleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Objects.requireNonNull;

@Transactional
@RequiredArgsConstructor
@Service
public class RepresentativeVehicleService {
    private final ModelMapper mapper;
    private final RepresentativeVehicleRepository representativeVehicleRepository;

    public Page<RepresentativeVehicleDto.Response> getAll(Pageable pageable, RepresentativeVehicleDto.PageRequest request) {
        Page<RepresentativeVehicle> representativeVehicles = representativeVehicleRepository.getAll(pageable, request);

        return representativeVehicles.map(r -> mapper.map(r, RepresentativeVehicleDto.Response.class));
    }

    public RepresentativeVehicleDto.Response create(RepresentativeVehicleDto.SaveRequest request) {
        var representativeVehicle = mapper.map(request, RepresentativeVehicle.class);
        representativeVehicleRepository.save(representativeVehicle);

        return mapper.map(representativeVehicle, RepresentativeVehicleDto.Response.class);
    }

    public RepresentativeVehicleDto.Response update(Long representativeVehicleId, RepresentativeVehicleDto.SaveRequest request) {
        var representativeVehicle = representativeVehicleRepository.findById(representativeVehicleId).orElseThrow();
        mapper.map(request, representativeVehicle);

        return mapper.map(representativeVehicle, RepresentativeVehicleDto.Response.class);
    }
}