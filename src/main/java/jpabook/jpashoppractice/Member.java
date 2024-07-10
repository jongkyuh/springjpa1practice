package jpabook.jpashoppractice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue // db가 자동으로 생성해준다.
    private Long id;

    private String userName;
}
