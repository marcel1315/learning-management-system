package com.zerobase.fastlms.member.model;

import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.member.entity.LoginHistory;
import com.zerobase.fastlms.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LoginHistoryDto {
    private String userId;
    private LocalDateTime loginDt;
    private String ip;
    private String userAgent;

    //추가칼럼
    long seq;

    public LoginHistory toEntity() {
        return LoginHistory.builder()
                .userId(userId)
                .loginDt(loginDt)
                .ip(ip)
                .userAgent(userAgent)
                .build();
    }

    public static LoginHistoryDto of(LoginHistory loginHistory) {
        return LoginHistoryDto.builder()
                .userId(loginHistory.getUserId())
                .loginDt(loginHistory.getLoginDt())
                .ip(loginHistory.getIp())
                .userAgent(loginHistory.getUserAgent())
                .build();
    }

    public String getLoginDtText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        return loginDt != null ? loginDt.format(formatter) : "";
    }
}
