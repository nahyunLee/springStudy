package springExample.hellospring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springExample.hellospring.domain.Member;
import springExample.hellospring.repository.MemberRepository;
import springExample.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service는 비지니스에 의존적으로 설계 밍
 * 비지니스를 처리하는게 role
 * 그래서 메서드명이 비지니스에 관련된 메서드 네이밍을 해야
 */
@Service
//@Component --> 원래 얘도 되지만 @Service에 들어가있음( Controller, repository 다 같음)
// component로 하면 component스캔 방식
// @Compoent가 있으면 스프링에서 객체 만들어서 컨테이너에 등록시켜둠
//@autowired쓰면 연관관계를 이어주는것
public class MemberService {

    private final MemberRepository memberRepository ;

    //외부에서 의존성 주입해주는거 --> DI
    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가임
     */
    public Long join(Member member){

        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // 같은 이름이 있는 중복 회원 X
        // Optional 해서 널체크 이런식으로 할 수 있어서 좋음.
        // OPtional 리턴하면 보기 별로 안좋음
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m ->{
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
