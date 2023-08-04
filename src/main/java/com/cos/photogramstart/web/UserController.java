package com.cos.photogramstart.web;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {

    @GetMapping("/user/{id}")
    public String profile(@PathVariable int id){
        return "user/profile";
    }

    @GetMapping("/user/update/{id}")
    public String update(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails, Model model){
        // 쉽게 찾는법
        System.out.println("세션정보" + principalDetails.getUser());

        /*
        // 힘들게 세션 정보 찾는법...
        Authentication auth = SecurityContextHolder.getContext().getAuthentication(); // Authentication
        PrincipalDetails mPrincipalDetails = (PrincipalDetails) auth.getPrincipal();
        System.out.println("직접 찾은 세션 정보 " + mPrincipalDetails.getUser());
        */

        // security taglibrary 로 대체.
//        model.addAttribute("principal", principalDetails.getUser());
        return "user/update";
    }
}
