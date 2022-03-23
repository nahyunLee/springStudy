package springExample.hellospring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import springExample.hellospring.repository.JdbcMemberRepository;
import springExample.hellospring.aop.TimeTraceAop;
import springExample.hellospring.repository.JdbcTemplateMemberRepository;
import springExample.hellospring.repository.JpaMemberRepository;
import springExample.hellospring.repository.MemberRepository;
import springExample.hellospring.repository.MemoryMemberRepository;
import springExample.hellospring.service.MemberService;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    public DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource){
//        this.dataSource = dataSource;
//    }
//    public EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em){
//        this.em = em;
//    }

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
        //jpaRepository extend하면 알아서 빈 등록해줘서 의존성 받을 수 있음
    }

    //직접 빈 등록

    @Bean
//    public MemberService memService(){
//    return new MemberService(memberRepository());
//    }
    public MemberService memService(){
        return new MemberService(memberRepository);
    }


    //만약 memoryMemberRepository가 아니라 DBmemberRepository로 바꾼다고 하면
    // 다른거 하나 손댈 필요 없이 MemoryMemberRepository()만 바꿔주면 끝남
    //직접 주입하는 장점
//    @Bean
//    public MemberRepository memberRepository(){
////        return new JdbcMemberRepository();
////        return new MemoryMemberRepository();
//        //다형성
////        return new JdbcTemplateMemberRepository(dataSource);
////        return new JpaMemberRepository(em);
//    }

//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }
}
