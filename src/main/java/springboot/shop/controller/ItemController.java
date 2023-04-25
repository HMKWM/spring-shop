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
import org.springframework.web.multipart.MultipartFile;
import springboot.shop.domain.Item;
import springboot.shop.domain.ItemForm;
import springboot.shop.domain.Member;
import springboot.shop.domain.MemberAdaptor;
import springboot.shop.service.ItemService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @ModelAttribute("member")
    public Member member(@AuthenticationPrincipal MemberAdaptor memberAdaptor){
        if(memberAdaptor == null){
            return null;
        }
        return memberAdaptor.getMember();
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model){
        Item item = itemService.findItem(itemId);

        model.addAttribute("item",item);

        return "item";
    }

    @GetMapping("/add")
    public String itemForm(Model model){
        model.addAttribute("item", new ItemForm());
        return "itemForm";
    }

    @PostMapping("/add")
    public String addItem(@ModelAttribute ItemForm itemForm, BindingResult bindingResult,
                          @RequestPart(value="itemImg") List<MultipartFile> itemImgList){
        if(bindingResult.hasErrors() || itemImgList.get(0) == null){
            return "itemForm";
        }


        Item item = itemService.createItem(itemForm);
        itemService.saveItem(item, itemImgList);


        return "redirect:/";
    }

    @GetMapping("/{itemId}/update")
    public String updateItemPage(@PathVariable Long itemId, Model model){
        Item item = itemService.findItem(itemId);
        model.addAttribute("item",item);

        return "itemUpdateForm";
    }

    @PostMapping("/{itemId}/update")
    public String updateItem(@PathVariable Long itemId, @ModelAttribute @Valid ItemForm itemForm, BindingResult bindingResult,
                             @RequestPart(value="itemImg") List<MultipartFile> itemImgList){
        if(bindingResult.hasErrors()){
            return "itemUpdateForm";
        }
        Item item = itemService.createItem(itemForm);
        item.setItemId(itemId);

        itemService.update(item, itemImgList);

        return "redirect:/";
    }
    
    @DeleteMapping("/{itemId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteItem(@PathVariable Long itemId){

        itemService.delete(itemId);

        return new ResponseEntity(HttpStatus.OK);
    }

}
