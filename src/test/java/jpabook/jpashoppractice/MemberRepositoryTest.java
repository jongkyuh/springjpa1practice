package jpabook.jpashoppractice;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)    //junit에게 spring에 관한 테스트라는걸 알려준다. junit4 꺼
//@ExtendWith(SpringExtension.class) junit5꺼
@SpringBootTest
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void testMember(){
        //given
        Member member = new Member();
        member.setUserName("memberA");

        //when
        Long savedId = memberRepository.save(member);
        Member findMember = memberRepository.find(savedId);

        //then
        Assertions.assertThat(findMember.getUserName()).isEqualTo(member.getUserName());
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());

        // 영속성 캐시에서 다시 가져오기 떄문에 아예 같은 객체이다.
        Assertions.assertThat(findMember).isEqualTo(member);

        System.out.println("findMember = " + findMember);
        System.out.println("member = " + member);
    }
}