package jpabook.jpashoppractice.service;

import jpabook.jpashoppractice.domain.Delivery;
import jpabook.jpashoppractice.domain.Member;
import jpabook.jpashoppractice.domain.Order;
import jpabook.jpashoppractice.domain.OrderItem;
import jpabook.jpashoppractice.domain.item.Item;
import jpabook.jpashoppractice.repository.ItemRepository;
import jpabook.jpashoppractice.repository.MemberRepository;
import jpabook.jpashoppractice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문
     */
    public Long order(Long memberId, Long itemId, int count){
        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item,item.getPrice(),count);

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        //주문 저장
        // cascade 옵션떄문에 delivery와 orderitem 모두 persist 된거다.
        // delivery와 orderitem은 order에서만 참조해서 쓰는 프라이밋오너이기 떄문에 그럴때 딱 cascade를 쓴다.
        orderRepository.save(order);
        return order.getId();

    }

    /**
     * 주문 취소
     */

    public void cancelOrder(Long orderId){
        //주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        //주문취소
        order.cancel();
    }

//    /**
//     * 주문검색
//     */
//    public List<Order> findOrders(OrderSearch orderSearch){
//        return orderRepository.findAll(orderSearch);
//    }

}
