package dev.connor.Carsharingplatform.module.vehicle.repository;

import com.querydsl.core.BooleanBuilder;
import dev.connor.Carsharingplatform.module.vehicle.dto.RepresentativeVehicleDto;
import dev.connor.Carsharingplatform.module.vehicle.entity.QRepresentativeVehicle;
import dev.connor.Carsharingplatform.module.vehicle.entity.RepresentativeVehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
public interface RepresentativeVehicleRepository extends JpaRepository<RepresentativeVehicle, Long>, RepresentativeVehicleCustomRepository {
}


interface RepresentativeVehicleCustomRepository {
    Page<RepresentativeVehicle> getAll(Pageable pageable, RepresentativeVehicleDto.PageRequest request);

}

class RepresentativeVehicleCustomRepositoryImpl extends QuerydslRepositorySupport implements RepresentativeVehicleCustomRepository {
    public RepresentativeVehicleCustomRepositoryImpl() {
        super(RepresentativeVehicle.class);
    }

    @Override
    public Page<RepresentativeVehicle> getAll(Pageable pageable, RepresentativeVehicleDto.PageRequest request) {
        var entity = QRepresentativeVehicle.representativeVehicle;
        BooleanBuilder builder = new BooleanBuilder();

        if (!StringUtils.isEmpty(request.getRepresentativeVehicleName())) {
            builder.and(entity.representativeVehicleName.containsIgnoreCase(request.getRepresentativeVehicleName()));
        }
        if (!StringUtils.isEmpty(request.getManufacturerName())) {
            builder.and(entity.manufacturerName.containsIgnoreCase(request.getManufacturerName()));
        }
        if (request.getUsedYn() != null) {
            builder.and(entity.usedYn.eq(request.getUsedYn()));
        }

        var query = from(entity).where(builder);
        var results = getQuerydsl().applyPagination(pageable, query).fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }
}