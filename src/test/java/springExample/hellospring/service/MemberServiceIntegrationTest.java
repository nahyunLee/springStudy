package springExample.hellospring.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import springExample.hellospring.domain.Member;
import springExample.hellospring.repository.MemberRepository;
import springExample.hellospring.repository.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
/**
 * DB의 트랜잭션 --> insert하고 commit하기 전에 반영이 안됨
 * DB에 insert등을 하고 commit을 해야 DB에 반영을 해야함
 * auto Commit모드가 있긴 함
 * test 하고 rollback해버리면 반영이 안됨 --> @Transcational
 * 이걸 테스트 클래스에 넣으면 실행하고 알아서 롤백함 --> 테스트 아니면 롤백 안함
 *
 * 순수한 단위테스트가 좋은 테스트 케이스일 확률이 높음
 * 어쩔 수 없이 컨테이너까지 올려야 한다면 테스트 케이스가 잘못됐을 가능성도 있음
 */
public class MemberServiceIntegrationTest {
    /**
     * 통합테스트
     * 스프링까지 다올리고 DB까지 연결 함
     * --> 이제는 스프링 컨테이너에 내놓으라고 해야함
     * 테스트코드에는 가장 편한 방법으로 해도됨 --> Autowired써도 무방 ~
     */

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;


//    //@Treasnscational 때문에 필요 없음
//    @AfterEach
//    public void afterEach(){
//        memberRepository.clearStore();
//    }


    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("hello1");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
//        try {
//            memberService.join(member2);
//            fail();
//        }catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //then

    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}
