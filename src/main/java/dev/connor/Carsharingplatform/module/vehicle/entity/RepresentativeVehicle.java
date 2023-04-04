package dev.connor.Carsharingplatform.module.vehicle.entity;

import dev.connor.Carsharingplatform.common.base.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"representativeVehicleId"}, callSuper = false)
@Entity
@Table(name = "tbl_representative_vehicle", schema = "vehicle")
public class RepresentativeVehicle extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "representative_vehicle_id")
    Long representativeVehicleId; // ID

    @Column(name = "representative_vehicle_code", length = 30)
    String representativeVehicleCode; // 차량 코드

    @Column(name = "representative_vehicle_name", length = 100, nullable = false)
    String representativeVehicleName; // 차량 명

    @Column(name = "manufacturer_name", length = 100, nullable = false)
    String manufacturerName;  // 제조사

    @Column(name = "used_yn", nullable = false)
    Character usedYn; // 활성 상태
}