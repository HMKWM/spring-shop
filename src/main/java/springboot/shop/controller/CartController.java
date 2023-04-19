package springboot.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springboot.shop.domain.CartItem;
import springboot.shop.domain.CartItemView;
import springboot.shop.domain.Member;
import springboot.shop.domain.MemberAdaptor;
import springboot.shop.exception.CartItemNotFoundException;
import springboot.shop.repository.ItemRepository;
import springboot.shop.service.CartService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartController {
    /**
     * /carts/** 인증
     * -유저,관리자
     * GET /carts
     * 장바구니 페이지
     *
     * POST /carts
     * 장바구니 신청(아이템 아이디)
     *
     * POST /carts/delete
     * 주문 취소(취소 목록)
     */

    private final CartService cartService;

    @GetMapping
    public String cartList(@AuthenticationPrincipal MemberAdaptor memberAdaptor,
                           Model model){
        Member member = memberAdaptor.getMember();
        Long memberId = member.getMemberId();
        List<CartItemView> cartItemView = cartService.getCartItemList(memberId);
        model.addAttribute("cartItemView", cartItemView);
        model.addAttribute("member", member);

        return "cartList";
    }

    @ResponseBody
    @PostMapping("/{itemId}")
    public ResponseEntity addCartItem(@AuthenticationPrincipal MemberAdaptor memberAdaptor,
                                    @PathVariable Long itemId,
                                    @RequestBody CartItem cartItem){
        Member member = memberAdaptor.getMember();
        cartItem.setMemberId(member.getMemberId());
        cartItem.setItemId(itemId);

        cartService.addCartItem(cartItem);

        return new ResponseEntity(HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/delete")
    public ResponseEntity deleteCartItem(@AuthenticationPrincipal MemberAdaptor memberAdaptor,
                                         @RequestBody List<Long> cartItemIdList){

        Member member = memberAdaptor.getMember();
        Long memberId = member.getMemberId();

        try{
            cartService.removeCartItem(cartItemIdList, memberId);
        } catch (CartItemNotFoundException e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
