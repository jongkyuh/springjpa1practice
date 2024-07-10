package jpabook.jpashoppractice;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    @PersistenceContext //영속성에 대한 부분인데, 엔티티 매니저를 주입을 해준다.
    private EntityManager em;

    // 저장로직
    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }

    // 조회로직
    public Member find(Long id){
        return em.find(Member.class,id);
    }
}
