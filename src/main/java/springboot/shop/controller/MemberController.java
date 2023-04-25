package springboot.shop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springboot.shop.domain.*;
import springboot.shop.service.MemberService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping
public class MemberController {

    private final MemberService memberService;

    @ModelAttribute("member")
    public Member member(@AuthenticationPrincipal MemberAdaptor memberAdaptor){
        if(memberAdaptor == null){
            return null;
        }
        return memberAdaptor.getMember();
    }

    @GetMapping("/signup")
    public String registerForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "signupForm";
    }

    @PostMapping("/signup")
    public String register(@Valid @ModelAttribute("memberForm") MemberForm memberForm,
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

    @GetMapping("/members")
    public String manageMember(@RequestParam(defaultValue="1" ,required = false) Integer page,
                               Model model){

        SearchCond cond = new SearchCond();
        cond.setPage(page);
        cond.setPageSize(10);
        cond.setKeyword("");
        int count = memberService.count();
        List<Member> memberList = memberService.findAll(cond);

        PageHandlerVO ph = new PageHandlerVO(count, 10, cond);

        model.addAttribute("memberList", memberList);
        model.addAttribute("ph", ph);

        return "memberManage";
    }

    @DeleteMapping("/members/{memberId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteMember(@PathVariable Long memberId){
        memberService.deleteMember(memberId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
