package dev.connor.Carsharingplatform.module.vehicle.service;

import dev.connor.Carsharingplatform.module.vehicle.dto.RepresentativeVehicleDto;
import dev.connor.Carsharingplatform.module.vehicle.entity.RepresentativeVehicle;
import dev.connor.Carsharingplatform.module.vehicle.mapper.RepresentativeVehicleMapper;
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
    private final ModelMapper modelMapper;
    private final RepresentativeVehicleRepository representativeVehicleRepository;
    private final RepresentativeVehicleMapper mapper;

    public Page<RepresentativeVehicleDto.Response> findAll(Pageable pageable, RepresentativeVehicleDto.PageRequest request) {
        Page<RepresentativeVehicle> representativeVehicles = representativeVehicleRepository.findAll(pageable, request);
        var response = representativeVehicles.map(mapper::toDto);

        return response;
    }

    public RepresentativeVehicleDto.Response create(RepresentativeVehicleDto.SaveRequest request) {
        var representativeVehicle = modelMapper.map(request, RepresentativeVehicle.class);
        representativeVehicleRepository.save(representativeVehicle);

        return modelMapper.map(representativeVehicle, RepresentativeVehicleDto.Response.class);
    }

    public RepresentativeVehicleDto.Response update(Long representativeVehicleId, RepresentativeVehicleDto.SaveRequest request) {
        var representativeVehicle = representativeVehicleRepository.findById(representativeVehicleId).orElseThrow();
        modelMapper.map(request, representativeVehicle);

        return modelMapper.map(representativeVehicle, RepresentativeVehicleDto.Response.class);
    }
}