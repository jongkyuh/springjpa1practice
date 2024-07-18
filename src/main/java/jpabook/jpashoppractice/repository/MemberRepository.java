package jpabook.jpashoppractice.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashoppractice.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // 스프링 빈으로 등록해준다.
@RequiredArgsConstructor
public class MemberRepository {

    //    @PersistenceContext // EntityManager를 주입받을 수 있다.

   // @PersistenceContext를 사용할 수 있찌만 스프링부트에서 Autowired로 주입할 수 잇게 했다.
    private final EntityManager em;

    // 멤버 저장
    public void save(Member member){
        em.persist(member);
    }

    // 단건 조회
    public Member findOne(Long id){
        return em.find(Member.class,id); // 두번째 파라미터는 PK
    }

    // 전체조회
    public List<Member> findAll(){
        //전체조회는 jpql로 직접 조회해야한다. 첫번째 파라미터는 실행할 sql, 두번재는 반환타입
        // jpql은 테이블이 아닌 엔티티객체를 대상으로 조회한다.
        return em.createQuery("select m from Member m",Member.class).getResultList();
    }

    // 이름으로 조회
    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name",name)
                .getResultList();
    }
}
