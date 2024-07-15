package jpabook.jpashoppractice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded    // Address에 @Embeddable만 써도 되지만 딱 봐도 임베디드 타입이라는 것을 알기 위함
    private Address address;

    @OneToMany(mappedBy = "member") // order테이블에 있는 member 필드에 맵핑된거다. 읽기전용이 된거다.
    private List<Order> orders = new ArrayList<>();
}
