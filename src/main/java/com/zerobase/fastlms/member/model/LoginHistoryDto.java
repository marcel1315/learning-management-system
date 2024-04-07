package com.zerobase.fastlms.main.model;

import com.zerobase.fastlms.main.entity.LoginHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LoginHistoryDto {
    private String userId;
    private LocalDateTime loginDt;
    private String ip;
    private String userAgent;

    public LoginHistory toEntity() {
        return LoginHistory.builder()
                .userId(userId)
                .loginDt(loginDt)
                .ip(ip)
                .userAgent(userAgent)
                .build();
    }
}
