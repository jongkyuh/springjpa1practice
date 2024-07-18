package jpabook.jpashoppractice.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashoppractice.domain.Member;
import jpabook.jpashoppractice.repository.MemberRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
//@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired MemberService memberService;
    @Autowired
    EntityManager em;   // insert 쿼리를 보고싶을 떄 사용



    @Test
    public void 회원가입() throws Exception{
        // given
        Member member = new Member();
        member.setName("kim");

        //when
        Long savedId = memberService.join(member);

        // then
        em.flush(); // 이렇게 하면 쿼리에 반영이된다(insert), insert 쿼리를 확인하고 싶으면 이렇게 하면된다.
        assertEquals(member,memberRepository.findOne(savedId));

    }

    @Test(expected = IllegalStateException.class)   // 이렇게 하면 예외테스트에 대해서 try catch 없이 가능하다.
    public void 중복_회원_예외() throws Exception{
        // given

        //같은 이름으로 저장
        Member member1 = new Member();
        member1.setName("kim1");

        Member member2 = new Member();
        member2.setName("kim1");
        //when

        /*

        // 이렇게 하면 테스트가 너무 지저분 해진다
        memberService.join(member1);
        try{
            memberService.join(member2);
        }catch (IllegalStateException e){
            return;
        }

         */

        memberService.join(member1);
        memberService.join(member2);    // 이 부분에서 예외터진다.

        // then
        fail("예외가 발생해야 한다.");   // 이 문장까지 오면 안된다 라는걸 말해줌.

    }
}