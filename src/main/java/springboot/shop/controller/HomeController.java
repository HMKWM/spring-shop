package springboot.shop.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springboot.shop.domain.*;
import springboot.shop.service.ItemService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping
public class HomeController {

    private final ItemService itemService;

    @ModelAttribute
    public Member member(@AuthenticationPrincipal MemberAdaptor memberAdaptor){
        if(memberAdaptor == null){
            return null;
        }
        return memberAdaptor.getMember();
    }

    @GetMapping("/")
    public String home(@RequestParam(defaultValue="1" ,required = false) Integer page,
                       @RequestParam(required = false) String keyword, Model model){

        int count = itemService.count(keyword);

        SearchCond cond = new SearchCond();
        cond.setPage(page);
        cond.setPageSize(9);
        PageHandlerVO ph = new PageHandlerVO(count, 10, cond);

        List<Item> itemList = itemService.getItemList(ph);

        model.addAttribute("pageHandler", ph);
        model.addAttribute("itemList", itemList);

        return "home";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "loginForm";
    }
}
