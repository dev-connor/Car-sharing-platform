package dev.connor.Carsharingplatform.module.vehicle.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

public class RepresentativeVehicleDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class PageRequest {
        String representativeVehicleName; // 차량 명
        String manufacturerName; // 제조사
        Character usedYn; // 활성 상태
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class SaveRequest {
        String representativeVehicleName;
        String manufacturerName;
        Character usedYn;
        String representativeVehicleCode; // 대표차 코드
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Response {

        Long representativeVehicleId;
        String representativeVehicleName;
        String manufacturerName;
        Character usedYn;
        String representativeVehicleCode; // 대표차 코드

        Instant createdAt;
        Instant updatedAt;
        String createdBy;
        String creatorName;
        String updatedBy;
        String updaterName;

//        public String getCreatorName() {
//            return ServiceMemberUtils.getServiceMemberNameById(createdBy);
//        }
//        public String getUpdaterName() {
//            return ServiceMemberUtils.getServiceMemberNameById(updatedBy);
//        }
    }
}
