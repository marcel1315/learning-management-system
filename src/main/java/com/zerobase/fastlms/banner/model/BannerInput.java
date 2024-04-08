package com.zerobase.fastlms.banner.model;

import lombok.Data;

@Data
public class BannerInput {
    Long id;

    String subject;
    String imagePath;
    String link;
    String openTarget;
    String ordering;
    boolean publish;

    String filename;
    String urlFilename;
}
