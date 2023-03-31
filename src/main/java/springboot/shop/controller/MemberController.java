package springboot.shop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springboot.shop.domain.Member;
import springboot.shop.domain.MemberForm;
import springboot.shop.service.MemberService;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/add")
    public String registerForm(Model model){
        model.addAttribute("member", new MemberForm());
        return "addForm";
    }

    @PostMapping("/add")
    public String register(@Valid @ModelAttribute("member") MemberForm memberForm,
                           BindingResult bindingResult){
        log.info("===회원가입 시도===");
        if(bindingResult.hasErrors()){
            log.info("binding error={}", bindingResult.getFieldErrors());
            return "addForm";
        }

        try{
            Member member = memberService.creatMember(memberForm);
            memberService.save(member);
        } catch (IllegalStateException e){
            //가입 실패 처리
            log.info("===회원가입 실패(중복아이디)===");
            return "addForm";
        }

        log.info("===회원가입 성공===");
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "loginForm";
    }
}
