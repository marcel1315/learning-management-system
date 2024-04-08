package com.zerobase.fastlms.banner.model;

import lombok.Data;

@Data
public class BannerInput {
    Long id;

    String subject;
    String link;
    String openTarget;
    int ordering;
    boolean publish;

    //삭제를 위한
    String idList;

    //ADD
    String filename;
    String urlFilename;
}
