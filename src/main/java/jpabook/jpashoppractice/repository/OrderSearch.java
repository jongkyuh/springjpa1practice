package jpabook.jpashoppractice.repository;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class OrderSearch {

    private String memberName; // 회원이름
    private String orderStatus; // 주문상태 ORDER,CANCEL
}
