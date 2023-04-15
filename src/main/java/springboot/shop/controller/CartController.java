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

import java.util.ArrayList;
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
        model.addAttribute("member", member);

        return "cartList";
    }

    @ResponseBody
    @PostMapping
    public ResponseEntity addCartItem(@AuthenticationPrincipal MemberAdaptor memberAdaptor,
                                    @RequestBody CartItem cartItem){
        Member member = memberAdaptor.getMember();
        cartItem.setMemberId(member.getMemberId());

        cartService.saveCartItem(cartItem);

        return new ResponseEntity(HttpStatus.OK);
    }

    @ResponseBody
    @PutMapping("/delete")
    public ResponseEntity deleteCartItem(@AuthenticationPrincipal MemberAdaptor memberAdaptor,
                                         @RequestBody List<Long> cartItemIdList){
        // 1개 또는 여러개 또는 전부
        /**
         * 방법 1 : 읽어와서 멤버전용 요청이 맞는지 확인
         * 장점 : 없으면 여기서 컷하면 됨. 오류체크 가능
         * 단점 : 쿼리 두번
         * 방법 2 : delete에 memberId를 넣어버림
         * 장점 : 쿼리 한번
         * 단점 : delete는 삭제된게 없어도 정상처리
         */

        Member member = memberAdaptor.getMember();
        Long memberId = member.getMemberId();

        List<Long> findCartItemIdList = cartService.getListByMemberId(memberId);
        if(!findCartItemIdList.containsAll(cartItemIdList) || cartItemIdList.size()==0){
            //실패, 잘못된 요청
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        cartService.removeCartItem(cartItemIdList);

        return new ResponseEntity(HttpStatus.OK);
    }
}
