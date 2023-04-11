package springboot.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springboot.shop.domain.CartItem;
import springboot.shop.domain.CartItemList;
import springboot.shop.domain.Member;
import springboot.shop.domain.MemberAdaptor;
import springboot.shop.service.CartService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    @GetMapping
    public String cartList(@AuthenticationPrincipal MemberAdaptor memberAdaptor,
                           Model model){
        Member member = memberAdaptor.getMember();
        Long memberId = member.getMemberId();
        List<CartItemList> cartItemList = cartService.getCartItemList(memberId);
        model.addAttribute("cartItemList", cartItemList);
        return "cartList";
    }

    @ResponseBody
    @PostMapping
    public ResponseEntity addCartItem(@RequestBody CartItem cartItem){

        cartService.saveCartItem(cartItem);

        return new ResponseEntity(HttpStatus.OK);
    }
}
