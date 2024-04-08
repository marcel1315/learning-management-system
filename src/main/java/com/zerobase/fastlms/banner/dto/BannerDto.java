package com.zerobase.fastlms.banner.dto;

import com.zerobase.fastlms.banner.entity.Banner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BannerDto {
    Long id;

    String subject;
    String imagePath;
    String link;
    String openTarget;
    String ordering;
    boolean publish;
    LocalDateTime regDt;
    LocalDateTime udtDt;

    //추가컬럼
    long totalCount;
    long seq;

    String filename;
    String urlFilename;

    public static BannerDto of(Banner banner) {
        return BannerDto.builder()
                .id(banner.getId())
                .subject(banner.getSubject())
                .imagePath(banner.getImagePath())
                .link(banner.getLink())
                .openTarget(banner.getOpenTarget())
                .ordering(banner.getOrdering())
                .publish(banner.isPublish())
                .regDt(banner.getRegDt())
                .udtDt(banner.getUdtDt())
                .filename(banner.getFilename())
                .urlFilename(banner.getUrlFilename())
                .build();
    }
}
