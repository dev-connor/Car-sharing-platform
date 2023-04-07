package dev.connor.Carsharingplatform.module.membership.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AdminManagementDto {

    /**
     * 관리자 (초대) 리스트 검색
     */
    @Data
    public static class MemberFilter {
        String serviceMemberName; // 서비스 회원 이름
        String serviceMemberId; // 서비스 멤버 ID
        String serviceId; // 서비스 ID
    }

    /**
     * 관리자 리스트 결과값
     */
    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @RequiredArgsConstructor
    public static class MemberItem {
        String serviceMemberId; // 관리자ID
        String serviceMemberName; // 이름
        String serviceMemberEmail; // 이메일
        String serviceMemberMobilePhoneNumber; // 전화번호
        String partnerName; // 소속
        Integer authorityRoleTypeId; // 관리자 그룹 ID
        String authorityRoleTypeName; // 관리자 그룹명
        Character usedYn = 'Y'; // 활성(Y) 비활성(N)
        Instant createdAt; // 최초 생성 일시
        Instant updatedAt; // 최종 업데이트 일시
        String updaterName; // 최종 업데이트 관리자
        String updatedBy; // 최종 업데이트 관리자 ID

//        public String getUpdaterName() {
//            return ServiceMemberUtils.getServiceMemberNameById(updatedBy);
//        }
    }

    /**
     * FMS 관리자 초대 리스트
     */
    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @RequiredArgsConstructor
    public static class FmsInvitationItem {
        Long partnerMemberId; // 관리자 ID
        String name; // 이름
        String phoneNumber; // 전화번호
        Integer authorityRoleTypeId; // 관리자 그룹 ID
        String authorityRoleTypeName; // 관리자 그룹명
        Partner partner; // 소속
        String authenticationCode;
        Instant updatedAt; // 초대 일시
        String updaterName; // 초대 관리자
        String createdBy;

//        public String getUpdaterName() {
//            return ServiceMemberUtils.getServiceMemberNameById(createdBy);
//        }

//        public void setInvitationSendHistories(List<InvitationSendHistory> invitationSendHistories) {
//
//            //초대 리스트에 가장 최근 값 조회
//            if(!invitationSendHistories.isEmpty() && invitationSendHistories.get(0).isExpired()) {
//                authenticationCode = "EXPIRED"; //인증기간 만료
//            } else {
//                authenticationCode = "NONE"; //미인증
//            }
//        }
//
//        public void setServiceMemberAuthorityRoles(Set<ServiceMemberAuthorityRole> serviceMemberAuthorityRoles) {
//            if(!serviceMemberAuthorityRoles.isEmpty()) {
//                authorityRoleTypeId = serviceMemberAuthorityRoles.stream().findFirst().get().getAuthorityRoleType().getCode();
//                authorityRoleTypeName = serviceMemberAuthorityRoles.stream().findFirst().get().getAuthorityRoleType().name();
//            }
//        }
    }


    @Data
    public static class RegisterMemberDto {

        @NotNull
        Long partnerMemberId; // 파트너 회원 번호

        /**
         * 파트너 잡 코드
         * PRJB000001 드라이버
         * PRJB000004 FMS 어드민
         */
        @NotNull
        String partnerJobCode;

        String password;

        /**
         * 가입할 권한 리스트
         * SUPER_MANAGER(41): 관리자, OPERATION_MANAGER(42): 운영자, CS_MANAGER(43): CS 관리자, DELIVERY_MANAGER(45): 배송 관리자, DELIVERY_MAN(50): 배송 기사
         */
        @NotNull
        List<Integer> roles = new ArrayList<>();

//        @Setter(AccessLevel.PRIVATE)
//        List<AuthorityRoleType> validRoles = new ArrayList<>();

//        public RegisterMemberDto setRoles(List<Integer> roles) {
//            this.roles = roles;
//
//            for (var role : roles) {
//                var authorityRoleType = AuthorityRoleType.ofCode(role);
//
//                if ("PRJB000001".equals(partnerJobCode) && List.of(DELIVERY_MANAGER, DELIVERY_MAN)
//                        .contains(authorityRoleType)) {
//                    validRoles.add(authorityRoleType);
//                }
//
//                // operation-#611, 배송 메니저도 VSM 로그인 가능하도록 수정.
//                if ("PRJB000004".equals(partnerJobCode) && List.of(SUPER_MANAGER, OPERATION_MANAGER, CS_MANAGER, DELIVERY_MANAGER)
//                        .contains(authorityRoleType)) {
//                    validRoles.add(authorityRoleType);
//                }
//            }
//
//            if(validRoles.isEmpty()) {
//                throw new IllegalArgumentException("invalid roles");
//            }
//            return this;
//        }
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class InvitationMemberRes {
        Long partnerMemberId;
        String name;
        String phoneNumber;
        Partner partner;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @RequiredArgsConstructor
    public static class Partner {
        Long partnerId; // 파트너ID
        String organizationName; // 파트너 기관명
    }
}