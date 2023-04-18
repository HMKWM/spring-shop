package springboot.shop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springboot.shop.domain.Member;
import springboot.shop.domain.MemberAdaptor;
import springboot.shop.domain.Order;
import springboot.shop.domain.OrderItem;
import springboot.shop.service.CartService;
import springboot.shop.service.OrderService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    /**
     * /orders/** 인증
     * -유저,관리자
     * GET /orders
     * 주문 페이지
     *
     * POST /orders
     * 주문 신청(신청 목록)
     *
     * PATCH /orders
     * 주문 취소(주문 번호)
     *
     * -관리자
     * 주문 관리페이지
     * 주문 받기
     * 주문 취소
     * 주문 삭제
     */

    private final OrderService orderService;
    private final CartService cartService;

    @ModelAttribute
    public Member member(@AuthenticationPrincipal MemberAdaptor memberAdaptor){
        if(memberAdaptor == null){
            return null;
        }
        return memberAdaptor.getMember();
    }

    @GetMapping
    public String orderPage(@AuthenticationPrincipal MemberAdaptor memberAdaptor, Model model){
        Member member = memberAdaptor.getMember();

        List<Order> orderList = orderService.getOrderList(member.getMemberId());
        model.addAttribute("orderList", orderList);
        return "orderList";
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity order(@AuthenticationPrincipal MemberAdaptor memberAdaptor,
                                @RequestBody List<OrderItem> orderItemList){
        Member member = memberAdaptor.getMember();

        cartService.removeCartItem(new ArrayList<>(), member.getMemberId());


        try {
            orderService.saveOrder(member.getMemberId(), orderItemList);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e){
            log.error("add order error", e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping
    public String cancelOrder(@AuthenticationPrincipal MemberAdaptor memberAdaptor){
        Member member = memberAdaptor.getMember();


        return null;
    }
}
