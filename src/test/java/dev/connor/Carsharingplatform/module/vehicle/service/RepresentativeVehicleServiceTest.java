package dev.connor.Carsharingplatform.module.vehicle.service;

import dev.connor.Carsharingplatform.module.vehicle.dto.RepresentativeVehicleDto;
import dev.connor.Carsharingplatform.module.vehicle.entity.RepresentativeVehicle;
import dev.connor.Carsharingplatform.module.vehicle.mapper.RepresentativeVehicleMapperImpl;
import dev.connor.Carsharingplatform.module.vehicle.repository.RepresentativeVehicleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class RepresentativeVehicleServiceTest {

    @InjectMocks
    private RepresentativeVehicleService service;

    @Mock
    private RepresentativeVehicleRepository repository;

    @Spy
    private RepresentativeVehicleMapperImpl mapper;

    @Test
    void findAll() {

        // given
        var entities = getPage();

        BDDMockito.given(repository.findAll(any(PageRequest.class), any())).willReturn(entities);

        // when
        var dtos = service.findAll(PageRequest.of(0, 20), new RepresentativeVehicleDto.PageRequest());

        // then
        var entity = entities.getContent().get(0);
        var dto = dtos.getContent().get(0);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(entity.getRepresentativeVehicleId(), dto.getRepresentativeVehicleId());
        Assertions.assertEquals(entity.getRepresentativeVehicleName(), dto.getRepresentativeVehicleName());
        Assertions.assertEquals(entity.getManufacturerName(), dto.getManufacturerName());
        Assertions.assertEquals(entity.getUsedYn(), dto.getUsedYn());
        Assertions.assertEquals(entity.getRepresentativeVehicleCode(), dto.getRepresentativeVehicleCode());
    }

    private static Page<RepresentativeVehicle> getPage() {
        var entity = RepresentativeVehicle.builder()
                .representativeVehicleId(272L)
                .representativeVehicleName("The new K9")
                .manufacturerName("기아")
                .usedYn('Y')
                .representativeVehicleCode("KRJR051")
                .build();

        var page = new PageImpl<>(List.of(entity), PageRequest.of(0, 20), 1);
        return page;
    }
}