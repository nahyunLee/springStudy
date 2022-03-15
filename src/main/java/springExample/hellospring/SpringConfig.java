package springExample.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springExample.hellospring.repository.MemberRepository;
import springExample.hellospring.repository.MemoryMemberRepository;
import springExample.hellospring.service.MemberService;

@Configuration
public class SpringConfig {
    //직접 빈 등록

    @Bean
    public MemberService memService(){
    return new MemberService(memberRepository());
    }

    //만약 memoryMemberRepository가 아니라 DBmemberRepository로 바꾼다고 하면
    // 다른거 하나 손댈 필요 없이 MemoryMemberRepository()만 바꿔주면 끝남
    //직접 주입하는 장점
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
