package springboot.shop.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springboot.shop.domain.Member;
import springboot.shop.domain.MemberAdaptor;

@Slf4j
@Controller
@RequestMapping
public class HomeController {

    @GetMapping("/")
    public String home(@AuthenticationPrincipal MemberAdaptor memberAdaptor, Model model){
        if(memberAdaptor != null){
            Member member = memberAdaptor.getMember();
            model.addAttribute("member", member);
            log.info("auth={}", memberAdaptor.getAuthorities());
            return "loginHome";
        }

        return "home";
    }
}
