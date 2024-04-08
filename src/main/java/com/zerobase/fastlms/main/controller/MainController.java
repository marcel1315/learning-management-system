package com.zerobase.fastlms.main.controller;


import com.zerobase.fastlms.banner.service.BannerService;
import com.zerobase.fastlms.member.model.LoginHistoryDto;
import com.zerobase.fastlms.member.service.MemberService;
import com.zerobase.fastlms.util.RequestUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Controller
@Slf4j
public class MainController {

    private final MemberService memberService;

    private final BannerService bannerService;
    
    @RequestMapping("/")
    public String index(Model model, HttpServletRequest request, @AuthenticationPrincipal User user) {

        if (user != null) {
            LoginHistoryDto loginHistoryDto = LoginHistoryDto.builder()
                    .userId(user.getUsername())
                    .loginDt(LocalDateTime.now())
                    .ip(RequestUtils.getClientIp(request))
                    .userAgent(RequestUtils.getUserAgent(request))
                    .build();
            memberService.saveLoginHistory(loginHistoryDto);
        }

        model.addAttribute("banners",bannerService.listOnlyPublish());

        return "index";
    }
    
    @RequestMapping("/error/denied")
    public String errorDenied() {
        
        return "error/denied";
    }
    
    
    
}
