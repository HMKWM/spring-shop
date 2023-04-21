package springboot.shop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springboot.shop.domain.*;
import springboot.shop.service.OrderService;

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

    @GetMapping("/manage")
    public String orderManagePage(@AuthenticationPrincipal MemberAdaptor memberAdaptor, Model model){
        Member member = memberAdaptor.getMember();

        if(member.getRole()!= Role.ADMIN){
            return "redirect:/";
        }

        List<Order> orderList = orderService.getAllOrderList();
        model.addAttribute("orderList", orderList);
        return "orderManage";
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity order(@AuthenticationPrincipal MemberAdaptor memberAdaptor,
                                @RequestBody List<OrderItem> orderItemList){
        Member member = memberAdaptor.getMember();

        try {
            orderService.addOrder(member.getMemberId(), orderItemList);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e){
            log.error("add order error", e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{orderId}/cancel")
    public ResponseEntity cancelOrder(@AuthenticationPrincipal MemberAdaptor memberAdaptor,
                              @PathVariable Long orderId){

        Member member = memberAdaptor.getMember();

        Long memberId = member.getMemberId();
        Long orderOwner = orderService.getOrderOwner(orderId);

        if(memberId != orderOwner){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        orderService.changeOrderStatus(orderId, OrderStatus.CANCEL);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/{orderId}/reject")
    public ResponseEntity rejectOrder(@AuthenticationPrincipal MemberAdaptor memberAdaptor,
                              @PathVariable Long orderId){

        Member member = memberAdaptor.getMember();
        Role role = member.getRole();

        if(!role.equals(Role.ADMIN)){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        orderService.changeOrderStatus(orderId, OrderStatus.REJECTED);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity deleteOrder(@AuthenticationPrincipal MemberAdaptor memberAdaptor,
                                      @PathVariable Long orderId){
        Member member = memberAdaptor.getMember();
        Role role = member.getRole();

        if (role == Role.ADMIN) {
            orderService.deleteOrder(orderId);
            return ResponseEntity.ok("Order successfully deleted by ADMIN");
        } else {
            Long orderOwner = orderService.getOrderOwner(orderId);
            Long memberId = member.getMemberId();

            if (orderOwner != memberId) {
                return ResponseEntity.badRequest()
                        .body("Cannot delete order: Not the order owner");
            } else {
                orderService.deleteOrder(orderId);
                return ResponseEntity.ok("Order successfully deleted");
            }
        }
    }
}
