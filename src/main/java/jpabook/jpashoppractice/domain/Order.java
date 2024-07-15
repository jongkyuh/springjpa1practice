package jpabook.jpashoppractice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "orders") //관례로 바궈준다.
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne  // 다대일
    @JoinColumn(name = "member_id") // 조인 컬럼에 대해 적어준다.
    private Member member;


    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;    // 자바8부터 가능, 주문시간,hibernate가 알아서 어노테이션 없이 맵핑해준다.

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문상태 [ORDER,CALCEL]
}
