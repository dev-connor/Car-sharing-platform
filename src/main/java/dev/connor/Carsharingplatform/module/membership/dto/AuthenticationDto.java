package dev.connor.Carsharingplatform.module.membership.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AuthenticationDto {

    @Getter
    public static class LoginDto {

        @NotNull
        String serviceId; // 서비스 ID는 관리자에게 문의한다.

        @NotNull
        String mobilePhoneNumber; // 휴대전화번호

        @NotNull
        @Size(max = 1024)
        String password; // 비밀번호(RSA로 암호화된 값)
    }

    @Getter
    public static class ChangedPassword extends LoginDto {

        @NotNull
        @Size(max = 1024)
        String newPassword; // 비밀번호(RSA로 암호화된 값)
    }

    @Getter
    public static class LoginDetailDto extends LoginDto {

        String loginIp; // 클라이언트 IP(서버 설정)
        String loginUserAgent; // 클라이언트 User agent (서버 설정)
        String refreshToken; // refresh token
    }

    @Getter
    @NoArgsConstructor
    public static class LoginResponseDto {
        LoginHistoryDto loginHistory; // 최근 접속 기록
        AccessTokenDto accessTokenDetail; // 발급된 토큰 상세 정보
    }

    @Getter
    @Builder
    public static class LoginHistoryDto {
        String recentLoginIp; // 최근에 접속한 ip
        String recentLoginUserAgent; // 최근에 접속한 useragent
        Instant recentLoginAt; // 최근에 로그인한 시간
        Instant recentPasswordChangeTime; // 최근 비밀번호 변경일
    }

    @Getter
    public static class AuthenticationResponseDto {
        AuthenticationSecurityAlertDto securityAlert; // 보안경고사항
        AccessTokenDto accessTokenDetail; // access token

        public void setSecurityAlert(LoginHistoryDto recentHistory, String userAgent, String ip) {
            var securityAlert = new AuthenticationSecurityAlertDto();
            var isUserAgentDifferent = recentHistory.getRecentLoginUserAgent() != null &&
                    !recentHistory.getRecentLoginUserAgent().equals(userAgent);
            var isIpDifferent = recentHistory.getRecentLoginIp() != null && !recentHistory.getRecentLoginIp().equals(ip);
            var showAlert = isUserAgentDifferent || isIpDifferent;
//            securityAlert.setShowAlert(showAlert);
//            if (showAlert) {
//                securityAlert.setMessage("최종 접속 계정(IP, 매체)정보와 상이합니다.");
//            }
            this.securityAlert = securityAlert;
        }
    }

    @Getter
    public static class AuthenticationSecurityAlertDto {
        boolean showAlert; // 보안경고메시지 노출 여부
        String message; // 노출할 보안경고메시지
    }

    @Getter
    @NoArgsConstructor
    public static class AccessTokenDto {
        String success;
        String code;
        String message;

        @JsonAlias("token_type")
        final String tokenType = "bearer"; // 토큰 타입, "bearer"로 고정

        @JsonAlias("access_token")
        String accessToken; // 사용자 액세스 토큰 값

        @JsonAlias("expires_in")
        long expiresIn; // 액세스 토큰 만료 시간(초)

        @JsonAlias("refresh_token")
        String refreshToken; // 사용자 리프레시 토큰 값
        long refreshTokenExpiresIn; // 리프레시 토큰 만료 시간(초)

        @JsonAlias("data")
        UserIdDto data = new UserIdDto();

        @Getter
        @NoArgsConstructor
        public static class UserIdDto {
            String userId;
        }

        public AccessTokenDto(String accessToken, long expiresIn) {
            this.accessToken = accessToken;
            this.expiresIn = expiresIn;
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CcspUserDto {
        String id; // 발급된 사용자 ID
        String email; // 이메일(가입ID)
        String name; // 이름
        String mobileNum; // 휴대폰 번호
        String birthdate; // 생년월일
        String lang; // 언어코드 (ko: 국문, en: 영문, zh: 중문)
        Boolean social; // 소셜 로그인 등록 여부
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CcspUserCertDto {
        String data;
        /**
         * 본인인증 받은 타입
         * 1: Mobile 인증
         * 2: Ipin 인증
         */
        String certType;
        Character gender; // 성별
        String nationInfo; // 국가 정보
        String dateTime; // 본인인증 받은 시간
        String ci; // CI
        String di; // DI

//        /**
//         * @return 본인인증 받은 시간
//         */
//        public Instant getCertDatetime() {
//            try {
//                return DateUtils.parseDate(dateTime, "yyyyMMdd'T'HHmmss.SSS").toInstant();
//            } catch (ParseException e) {
//                return null;
//            }
//        }
    }


    @Getter
    public static class SignOutRequestDto extends Password {
        String outReasonEtcOpinion; // 기타 탈퇴 사requestDto
        /**
         * <p>
         * 탈퇴 사유
         *     <ul>
         *         <li>OTRS000001,서비스 미제공 지역</li>
         *         <li>OTRS000002,다른 카세어링 서비스 이용</li>
         *         <li>OTRS000003,이용 불편 - 시스템</li>
         *         <li>OTRS000004,이용 불편 - 차량배송</li>
         *         <li>OTRS000005,비싼 요금</li>
         *         <li>OTRS000006,자기 차량 구입</li>
         *         <li>OTRS000007,장기 미사용 탈퇴</li>
         *         <li>OTRS000008,기타</li>
         *     </ul>
         * </p>
         */
        @NotNull
        String outReasonCode;
        boolean withCcsp; // ccsp 탈퇴
        boolean withRedMembers; // Red members 탈퇴
    }

    @Getter
    public static class Password {

        @NotNull
        @Size(min = 8, max = 20)
        String password; // 비밀번호
    }

    @Getter
    public static class UserProfile {

        @NotNull
        AuthenticationDto.UserProfile.ChangedPassword password; // 비밀번호

        @Getter
        public static class ChangedPassword {

            @NotNull
            @Size(min = 8, max = 20)
            String current; // 현재 비밀번호

            @NotNull
            @Size(min = 8, max = 20)
            String change; // 변경할 비밀번호
        }
    }

    @Getter
    public static class DriverLicenseDto {
        /**
         * 운전명허타입 코드
         * <p>
         * DVLC000001, 2종보통, 32
         * DVLC000002, 1종소형, 13
         * DVLC000003, 1종보통, 12
         * DVLC000004, 1종대형, 11
         */
        @NotNull
        String driverLicenseTypeCode;
        /**
         * 운전면허발급 지역
         * <p>
         * 11 서울
         * 12 부산
         * 13 경기
         * 13 경기남부
         * 14 강원
         * 15 충북
         * 16 충남
         * 17 전북
         * 18 전남
         * 19 경북
         * 20 경남
         * 21 제주
         * 22 대구
         * 23 인천
         * 24 광주
         * 25 대전
         * 26 울산
         * 28 경기북부
         */
        @NotNull
        String driverLicenseApprovalArea;

        @Size(min = 10, max = 10)
        String driverLicenseNumber; // 운전면허번호(10자리)

        @NotNull
        Instant orderEndDatetime; // 대여기간 종료

        @NotNull
        String serviceMemberId; // 서비스 회원 ID

        @NotNull
        String serviceMemberName; // 서비스 회원 이름
        LocalDate serviceMemberBirthDate; // 생년월일
        /**
         * 운전면허 검증의 실행 타입 코드 (사용자/배치)
         * <p>
         * DLVE000001 혹은 빈값(null),사용자
         * DLVE000002,배치
         */
        String driverLicenseValidExecutionCode;

        @Override
        public String toString() {
//            String maskedDriverLicenseNumber = driverLicenseNumber.replaceAll("(?<=.{4}).\\S{4}", "*****");
            String maskedServiceMemberName = serviceMemberName.replaceAll("(?<=.{1}).", "*");
            return "{"
//                    + "\"driverLicenseTypeCode\":" + "\"" + driverLicenseTypeCode + "\","
//                    + "\"driverLicenseApprovalArea\":" + "\"" + driverLicenseApprovalArea + "\","
//                    + "\"driverLicenseNumber\":" + "\"" + driverLicenseNumber + "\","
                    + "\"orderEndDatetime\":" + "\"" + DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").withZone(ZoneId.of("Asia/Seoul")).format(orderEndDatetime) + "\","
                    + "\"serviceMemberId\":" + "\"" + serviceMemberId + "\","
//                    + "\"serviceMemberName\":" + "\"" + maskedServiceMemberName + "\","
                    + "\"serviceMemberBirthDate\":" + "\"" + DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.of("Asia/Seoul")).format(serviceMemberBirthDate) + "\""
                    + "}";
        }
    }

    @Getter
    public static class RegisterMemberDto {

        @NotNull
        @Size(min = 1)
        String serviceMemberName; // 서비스 회원 이름

        @NotNull
        @Size(min = 3)
        @Email
        String serviceMemberEmail; // 서비스 회원 이메일

        @NotNull
        @Size(min = 10)
        String serviceMemberMobilePhoneNumber; // 서비스 회원 전화번호

        /**
         * 서비스 별 권한
         * <p>
         * TAP Admin 사용자 권한:
         * SUPER_MANAGER(41): 관리자, OPERATION_MANAGER(42): 운영자, CS_MANAGER(43): CS 관리자, REPAIR_SHOP_MANAGER(44): 정비 관리자
         * <p>
         * TAP Now 사용자 권한:
         * DELIVERY_MANAGER(45): 배송 관리자, DELIVERY_MAN(50): 배송 업무 수행자
         */
        List<Integer> roles = new ArrayList<>();
//        /**
//         * TAP Now 사용자 권한
//         */
//        @Setter(AccessLevel.PRIVATE)
//        List<AuthorityRoleType> deliveryRoles = new ArrayList<>();
//        /**
//         * TAP Admin 사용자 권한
//         */
//        @Setter(AccessLevel.PRIVATE)
//        List<AuthorityRoleType> adminRoles = new ArrayList<>();

        /**
         * 권한 정보를 권한의 종류에 따라 저장 한다. 데이터 접근 시 마다 파싱하는 반복문을 피하기 위해 저장 시점에 분류 한다.
         *
         * @param roles 권한 정보
         * @return RegisterMemberDto
         */
        public RegisterMemberDto setRoles(List<Integer> roles) {
            this.roles = roles;

//            for (var role : roles) {
//                var authorityRoleType = AuthorityRoleType.ofCode(role);
//                // delivery app
//                // operation-#611, 배송 메니저도 VSM 로그인 가능하도록 수정.
////                if (authorityRoleType == DELIVERY_MANAGER || authorityRoleType == DELIVERY_MAN) {
////                    deliveryRoles.add(authorityRoleType);
////                } else if (authorityRoleType != USER) {
////                    adminRoles.add(authorityRoleType);
////                }
//                if (authorityRoleType == DELIVERY_MANAGER || authorityRoleType == DELIVERY_MAN) {
//                    deliveryRoles.add(authorityRoleType);
//                }
//                if (authorityRoleType != USER) {
//                    adminRoles.add(authorityRoleType);
//                }
//            }
            return this;
        }
    }

    @Getter
    public static class AuthenticationMemberDto extends RegisterMemberDto {
        String serviceMemberId;
    }

    @Getter
    public static class SubmitOtpRequest {

        @NotNull
        @Size(min = 6, max = 6)
        String otpCode; // 유저가 입력한 OTP Code
    }

    @Getter
    public static class ChanagePasswordRequest {
        /**
         *
         */
        @NotNull
//        @ValidPassword
        String password; // 비밀번호
    }

    @Getter
    public static class SubmitIdAndNameRequest {

        @NotNull
        String serviceId;

        @NotNull
        @Size(min = 11, max = 11)
        String phoneNumber;

        @NotNull
        @Size(min = 1)
        String name;
    }

    @Getter
    public static class CcspUserAuthenticate {
        AccessTokenDto accessTokenDto; // CCSP 발급 Accesstoken

//        /**
//         * CCSP 회원 정보
//         */
//        ServiceMemberDto.CcspServiceMemberDto ccspServiceMemberDto;
    }
}
