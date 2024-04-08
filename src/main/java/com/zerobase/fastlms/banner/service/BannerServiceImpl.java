package com.zerobase.fastlms.banner.service;

import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.entity.Banner;
import com.zerobase.fastlms.banner.mapper.BannerMapper;
import com.zerobase.fastlms.banner.model.BannerInput;
import com.zerobase.fastlms.banner.model.BannerParam;
import com.zerobase.fastlms.banner.repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BannerServiceImpl {

    private final BannerRepository bannerRepository;

    private final BannerMapper bannerMapper;

    public boolean add(BannerInput parameter) {

        Banner banner = Banner.builder()
                .link(parameter.getLink())
                .subject(parameter.getSubject())
                .ordering(parameter.getOrdering())
                .imagePath(parameter.getImagePath())
                .openTarget(parameter.getOpenTarget())
                .publish(parameter.isPublish())
                .regDt(LocalDateTime.now())
                .filename(parameter.getFilename())
                .urlFilename(parameter.getUrlFilename())
                .build();
        bannerRepository.save(banner);

        return true;
    }

    public boolean set(BannerInput parameter) {

        Optional<Banner> optionalBanner = bannerRepository.findById(parameter.getId());
        if (!optionalBanner.isPresent()) {
            return false;
        }

        Banner banner = optionalBanner.get();
        banner.setSubject(parameter.getSubject());
        banner.setLink(parameter.getLink());
        banner.setOpenTarget(parameter.getOpenTarget());
        banner.setOrdering(parameter.getOrdering());
        banner.setPublish(parameter.isPublish());
        banner.setUdtDt(LocalDateTime.now());

        if (!parameter.getFilename().isEmpty()) {
            banner.setFilename(parameter.getFilename());
            banner.setUrlFilename(parameter.getUrlFilename());
        }

        bannerRepository.save(banner);

        return true;
    }

    public BannerDto getById(long id) {
        return bannerRepository.findById(id).map(BannerDto::of).orElse(null);
    }

    public List<BannerDto> list(BannerParam parameter) {

        long totalCount = bannerMapper.selectListCount(parameter);

        List<BannerDto> list = bannerMapper.selectList(parameter);
        if (!CollectionUtils.isEmpty(list)) {
            int i = 0;
            for (BannerDto x : list) {
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }

        return list;
    }
}
