package springboot.shop.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springboot.shop.domain.Member;
import springboot.shop.domain.MemberAdapter;

@Slf4j
@Controller
@RequestMapping
public class HomeController {

    @GetMapping("/")
    public String home(@AuthenticationPrincipal MemberAdapter memberAdapter, Model model){
        Member member = null;
        if(memberAdapter != null){
            member = memberAdapter.getMember();
            model.addAttribute("member", member);
            return "loginHome";
        }
        log.info("memberAdapter={}", memberAdapter);
        return "home";
    }
}
