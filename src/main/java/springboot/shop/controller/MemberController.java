package springboot.shop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springboot.shop.domain.Member;
import springboot.shop.domain.MemberForm;
import springboot.shop.domain.SearchCond;
import springboot.shop.service.MemberService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public String registerForm(Model model){
        model.addAttribute("member", new MemberForm());
        return "signupForm";
    }

    @PostMapping("/signup")
    public String register(@Valid @ModelAttribute("member") MemberForm memberForm,
                           BindingResult bindingResult){
        log.info("===회원가입 시도===");
        if(bindingResult.hasErrors()){
            log.info("binding error={}", bindingResult.getFieldErrors());
            return "signupForm";
        }

        try{
            Member member = memberService.creatMember(memberForm);
            memberService.save(member);
        } catch (IllegalStateException e){
            //가입 실패 처리
            log.info("===회원가입 실패(중복 이메일)===");
            return "signupForm";
        }

        log.info("===회원가입 성공===");
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "loginForm";
    }

    @GetMapping("/manage")
    public String manageMember(@ModelAttribute SearchCond cond, Model model){
        List<Member> memberList = memberService.findAll(cond);
        model.addAttribute("memberList", memberList);

        return "manageMember";
    }
}
