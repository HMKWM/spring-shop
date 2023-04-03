package springboot.shop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springboot.shop.domain.Item;
import springboot.shop.domain.ItemForm;
import springboot.shop.domain.Member;
import springboot.shop.domain.MemberAdaptor;
import springboot.shop.service.ItemService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @ModelAttribute("member")
    public Member member(@AuthenticationPrincipal MemberAdaptor memberAdaptor){
        return memberAdaptor.getMember();
    }

    @GetMapping("/{itemId}")
    public String item(@AuthenticationPrincipal MemberAdaptor memberAdaptor, @PathVariable Long itemId, Model model){
        Item item = itemService.findItem(itemId);

        model.addAttribute("member", memberAdaptor.getMember());
        model.addAttribute("item",item);
        return "item";
    }

    @GetMapping("/add")
    public String itemForm(Model model){
        model.addAttribute("itemForm", new ItemForm());
        return "itemForm";
    }

    @PostMapping("/add")
    public String addItem(@ModelAttribute ItemForm itemForm, BindingResult bindingResult,
                          @RequestPart(value="itemImg") List<MultipartFile> itemImgList){

        if(bindingResult.hasErrors() || itemImgList.get(0) == null){
            return "itemForm";
        }



        return "redirect:/";
    }

}
