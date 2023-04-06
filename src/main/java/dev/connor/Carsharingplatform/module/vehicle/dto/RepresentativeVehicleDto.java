package dev.connor.Carsharingplatform.module.vehicle.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.time.LocalDateTime;

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

        @ApiModelProperty(example = "The new K9")
        String representativeVehicleName;

        @ApiModelProperty(example = "기아")
        String manufacturerName;

        @ApiModelProperty(example = "Y")
        Character usedYn;

        @ApiModelProperty(example = "KRJR051")
        String representativeVehicleCode; // 대표차 코드
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Response {
        Long representativeVehicleId;

        @ApiModelProperty(example = "The new K9")
        String representativeVehicleName;

        @ApiModelProperty(example = "기아")
        String manufacturerName;

        @ApiModelProperty(example = "Y")
        Character usedYn;

        @ApiModelProperty(example = "KRJR051")
        String representativeVehicleCode; // 대표차 코드

        LocalDateTime createdAt;
        LocalDateTime updatedAt;

        @ApiModelProperty(example = "ANONYMOUS")
        String createdBy;

        @ApiModelProperty(example = "ANONYMOUS")
        String creatorName;

        @ApiModelProperty(example = "ANONYMOUS")
        String updatedBy;

        @ApiModelProperty(example = "ANONYMOUS")
        String updaterName;

//        public String getCreatorName() {
//            return ServiceMemberUtils.getServiceMemberNameById(createdBy);
//        }
//        public String getUpdaterName() {
//            return ServiceMemberUtils.getServiceMemberNameById(updatedBy);
//        }
    }
}
