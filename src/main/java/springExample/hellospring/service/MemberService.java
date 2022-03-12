package springExample.hellospring.service;

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
public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

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
