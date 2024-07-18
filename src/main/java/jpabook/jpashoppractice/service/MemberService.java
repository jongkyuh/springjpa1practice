package jpabook.jpashoppractice.service;

import jpabook.jpashoppractice.domain.Member;
import jpabook.jpashoppractice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
// DB조회를 하기 떄문에 Transcational을 걸어야 한다.
// public 메소드는 기본적으로 트랜젝션에 같이 들어간다. //읽기전용 트랜젝션으로 하면 좀 더 성능 최적화 할 수 있다.
// 멤버서비스에는 읽기가 많으므로 클래스레벨에서는 읽기전용으로 하고 쓰기에는 메소드에 직접 트랜젝셔널 어노테이션을 넣는다.
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 1. 회원가입
     * @param member
     * @return
     */
    @Transactional // 이게 먼저 우선권을 가진다. 그래서 readOnly = false가 된다.
    public Long join(Member member){
        // 중복회원 검증
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 중복회원 검증
     * @param member
     */
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 2. 회원 전체 조회
     * @return
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }


    /**
     * 3. 아이디로 회원 조회
     * @param memberId
     * @return
     */
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

}
