package jpabook.jpashoppractice.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;


    protected Address() {
        // JPA는 기본 생성자가 있어야 한다.
        // 그래도 수정되지 않게 protected로 한다 .
    }

    // 생성자로만 생성 가능하게 만들고 setter는 만들지 않는다.
     public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
