package springExample.hellospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import springExample.hellospring.service.MemberService;

// 스프링이 뜰 때 이 객체를 생성해서 컨테이너가 들고있음
@Controller
public class MemberController {

//    private final MemberService memberService = new MemberService();
    //new 해서 서비스 생성하면 여러 controller들이 가져다 쓸 수 있게됨 --> 하나만 생성해서 공유로 쓰면됨
    //spring container 에 하나만 등록해서 쓰면 됨

    private final MemberService memberService;

    // 생성자로 service로 연결
    // 스프링 컨테이너가 뜰 때 controller 생성하는데 (이때 생성자 호출)
    // Autowired하면 스프링이 memberSerive(스프링에 있는)연결시켜줌
    // MemberService(아무것도 처리 안했을 때 )--> 순수한 자바 클래스라서 스프링이 알 수 있는 방법이 없음  --> @service넣어주면 됨!
    // Repository도 어노테이션 달면 됨
    // @Autowired하면 memService랑 알아서 둘이 연결해주니 --> DI

    //스프링이 뜰 때 @Controller어 컨트롤러네? 하고 스프링 컨테이너에 등록하면서 생성자 호출하고 이 떄 @Autowired가 있으면 연결해줌
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }
}
