package com.zerobase.fastlms.banner.service;

import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.entity.Banner;
import com.zerobase.fastlms.banner.mapper.BannerMapper;
import com.zerobase.fastlms.banner.model.BannerInput;
import com.zerobase.fastlms.banner.model.BannerParam;
import com.zerobase.fastlms.banner.repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BannerServiceImpl implements BannerService {

    @Value("${file.local-file-root}")
    private String LOCAL_FILE_ROOT;

    private final BannerRepository bannerRepository;

    private final BannerMapper bannerMapper;

    @Override
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

    @Override
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

    @Override
    public BannerDto getById(long id) {
        return bannerRepository.findById(id).map(BannerDto::of).orElse(null);
    }

    @Override
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

    @Override
    public boolean del(String idList) {
        if (idList != null && idList.length() > 0) {
            String[] ids = idList.split(",");
            for (String x : ids) {
                long id = 0L;
                try {
                    id = Long.parseLong(x);
                } catch (Exception e) {
                }

                if (id > 0) {
                    try {
                        Optional<Banner> banner = bannerRepository.findById(id);
                        Path fileStorageLocation = Paths.get(LOCAL_FILE_ROOT).toAbsolutePath().normalize();
                        Path targetLocation = fileStorageLocation.resolve(banner.get().getFilename());
                        Files.deleteIfExists(targetLocation);
                    } catch (Exception e) {
                        log.warn("File deletion fail. NEED INSPECTION");
                    }

                    bannerRepository.deleteById(id);
                }
            }
        }

        return true;
    }
}
