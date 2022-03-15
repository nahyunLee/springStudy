package springExample.hellospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import springExample.hellospring.domain.Member;
import springExample.hellospring.service.MemberService;

import java.util.List;

// 스프링이 뜰 때 이 객체를 생성해서 컨테이너가 들고있음
@Controller
public class MemberController {

//    private final MemberService memberService = new MemberService();
    //new 해서 서비스 생성하면 여러 controller들이 가져다 쓸 수 있게됨 --> 하나만 생성해서 공유로 쓰면됨
    //spring container 에 하나만 등록해서 쓰면 됨

    /**
     * 1(DI방법)
     * @Autowired 붙여서 쓰는 방법 --> 필드 의존성 주입
     * 필드 바꿀 방법이 없어서 별로 안좋음
     */
    private final MemberService memberService;

    // 생성자로 service로 연결
    // 스프링 컨테이너가 뜰 때 controller 생성하는데 (이때 생성자 호출)
    // Autowired하면 스프링이 memberSerive(스프링에 있는)연결시켜줌
    // MemberService(아무것도 처리 안했을 때 )--> 순수한 자바 클래스라서 스프링이 알 수 있는 방법이 없음  --> @service넣어주면 됨!
    // Repository도 어노테이션 달면 됨
    // @Autowired하면 memService랑 알아서 둘이 연결해주니 --> DI

    /**
     * 2.(DI방법)
     * 생성자를 통해 의존성 주입하는 방법
     * 한번 주입하면 바꿀 수 없어서 좋은 방법 --> 애초에 실행중에 의존성이 바뀔 일이 없음
     */
    //스프링이 뜰 때 @Controller어 컨트롤러네? 하고 스프링 컨테이너에 등록하면서 생성자 호출하고 이 떄 @Autowired가 있으면 연결해줌
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    /**
     * 3.(DI방법)
     * setter를 통한 방법
     * 단점 : 누가 호출했을 때 public으로 바꿔져있어야함. public하게 노출되어 있는게 문제
     *          --> 아무개발자나 set할 수 있어서;;;
     *     @Autowired
     *     public void setMemberService(MemberService memberService){
     *         this.memberService= memberService;
     *     }
     */

    //controller는 직접 빈 등록을 못함
    //빈 등록 안되어있으면 @Autowired 동작 안함--> 스프링 컨테이너에 올라와야만 autowired 동작

    //home.html에서 여기로 이
    @GetMapping("members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }
}
