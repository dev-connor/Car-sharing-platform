package dev.connor.Carsharingplatform.module.partner.dto;

import com.google.common.collect.ImmutableMap;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdminPartnerMemberDto {

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Filter {
        String serviceId;
        Long partnerId;
        Long partnerMemberId;
        Long transportationAreaGroupId;
        String name;
        String partnerOrganizationName;
        String partnerJobCode;
        String email;
        String phoneNumber;
        Character educationCompletionYn;
        Character usedYn;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class WorkAvailableFilter {
        Long transportationAssignmentId;
        Long orderProductId;
        Instant taskStartDatetime;
        Instant taskEndDatetime;
        Integer intervalMinutes = 0;

        List<Long> garageIds = new ArrayList<>();

        Long partnerId;
        Long partnerMemberId;
        String partnerName;
        String partnerPhoneNumber;

        String partnerMemberName;
        String partnerMemberPhoneNumber;

    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class FilterInGarage {
        String nameOrPhoneNumber;
    }

    @Data
    public static class Base {
        Long partnerMemberId; // 협력업체 직원 ID
        Partner partner; // 협력업체
        Long transportationAreaGroupId;
        String transportationAreaGroupName;

        /**
         * PRJB000001 : Tap! Now (드라이버회원)
         * PRJB000004 : FMS ADMIN (어드민회원)
         */
        String partnerJobCode; // 업무
        String name; // 이름
        String email; // 이메일
        String phoneNumber; // 전화번호
        Character usedYn; // 상태
        Character educationCompletionYn; // 교육 이수 여부
        String serviceMemberId; //서비스 멤버 ID
        Instant certifiedDate; // 인증일
        LocalDate expiredDate;
        Instant createdAt;
        String createdBy;
        String creatorName;
        Instant updatedAt;
        String updatedBy;
        String updaterName;

//        public String getCreatorName() {
//            return ServiceMemberUtils.getServiceMemberNameById(createdBy);
//        }
//
//        public String getUpdaterName() {
//            return ServiceMemberUtils.getServiceMemberNameById(updatedBy);
//        }
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class Item extends Base {

        Service service;

//        /**
//         * Tap! Now 회원 여부
//         * @return
//         */
//        public Boolean getIsTapNowMember() {
//            if(PartnerJobCode.TAB_NOW.getCode().equals(partnerJobCode) && serviceMemberId != null) {
//                return true;
//            }
//            return false;
//        }

//        /**
//         * 마스킹 처리
//         */
//        public void masking() {
//            name = MaskUtils.maskName(name);
//            phoneNumber = MaskUtils.maskMobilePhoneNumber(phoneNumber);
//        }

        @Data
        public static class Service {
            String serviceId;
            String serviceKorName;
            String serviceEngName;
        }

        AvailableAssignment.Assignment beforeAssignment;
        AvailableAssignment.Assignment afterAssignment;

    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Detail extends Item {
        String locationAddress; // 주소지
        String detailAddress; // 주소 상세
        String roadAddress; // 도로명 주소
        WorkSpace workSpace; // 차고지
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class AvailableAssignment extends Item {

        Assignment beforeAssignment;
        Assignment afterAssignment;

        @Data
        @FieldDefaults(level = AccessLevel.PRIVATE)
        public static class Assignment {
            Long transportationAssignmentId;
            String transportationAssignmentTypeCode;
            String transportationAssignmentStatusCode;
            String transportationManualAssignmentStatusCode;
            Instant taskStartDatetime;
            Instant taskEndDatetime;
            Instant manualAssignmentDatetime;
            Long transportationId;
            Instant transportationAt;
            String sidoCode;
            String transportationSourceLocationAddress;
            String transportationSourceRoadAddress;
            String transportationSourceDetailAddress;
            String transportationDestinationLocationAddress;
            String transportationDestinationRoadAddress;
            String transportationDestinationDetailAddress;
            String transportationWorkTypeCode;
            String transportationTypeCode;
        }
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Partner {
        Long partnerId;
        String organizationName;
        String serviceId;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class WorkSpace {
        Long workSpaceId;
        Garage garage;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Garage {
        Long garageId;
        String garageName;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class ReqInsert {
        Long partnerId;
        Long garageId;
        Long transportationAreaGroupId;
        String partnerJobCode;
        String name;
        String password;
        String email;
        String phoneNumber;
        String locationAddress; // 주소지
        String detailAddress; // 주소 상세
//        String roadAddress; // 도로명 주소
        Character educationCompletionYn;
        Character usedYn;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class ReqUpdate extends ReqInsert {
        Long partnerMemberId;
    }

    @Data
    public static class InviteTapNowRequest {
        Long partnerMemberId;
        String password;
        Character educationCompletionYn;
        Long garageId;

//        /**
//         * 가입할 권한 리스트
//         * DELIVERY_MANAGER(45): 배송 관리자, DELIVERY_MAN(50) : 배송 기사
//         */
//        List<Integer> roles = List.of(DELIVERY_MAN.getCode());

    }

    @Data
    public static class SendTapNowUpdateLinkRequest {
        Long partnerMemberId;
    }
}