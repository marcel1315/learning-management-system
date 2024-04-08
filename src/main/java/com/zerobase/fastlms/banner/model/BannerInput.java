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

    //삭제를 위한
    String idList;

    //ADD
    String filename;
    String urlFilename;
}
