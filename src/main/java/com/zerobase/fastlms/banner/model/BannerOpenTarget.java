package com.zerobase.fastlms.banner.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BannerOpenTarget {
    public final String target;
    public final String name;
}