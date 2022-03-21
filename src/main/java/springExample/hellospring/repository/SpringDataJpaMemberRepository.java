package springExample.hellospring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springExample.hellospring.domain.Member;

import java.util.Optional;

//interface 가 interface를 받을 떄 extends
// JpaRepository를 extend하면 알아서 구현체 만들어서 스프링 빈 등록 해줌
public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>, MemberRepository{
    @Override
    Optional<Member> findByName(String name);
}
