package springExample.hellospring.repository;

import springExample.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    // jpa를 사용하려면 entitymanager 주입받아야함 --> 내부적으로 다 알아서 해줌
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em){
        this.em = em;
    }
    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.of(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        //jpql
        // 객체를 대상으로 쿼리를 날리면 알아서 번역이됨
        // select 하면 member 엔티티 자체로 검색하는 것 (*, id 이런거 설정 안해도됨)
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

}
