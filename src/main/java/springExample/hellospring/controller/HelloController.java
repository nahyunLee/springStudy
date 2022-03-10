package springExample.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello");
        return "hello";     //hello.html로 매핑해라
        //return 값의 이름을 viewResolver가  templates 디렉토리 밑에 있는 애들중에 찾
    }

    //MVC 방식 --> html내려줌
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    //API 방식 --> data를 그대로 내려줌
    @GetMapping("hello-string")
    @ResponseBody           //응답부에 return 값 직접 넣어주겠다.
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody       // ResponseBody를 붙으면 몇가지 조건 확인 후에 template안찾고 바로 내려줌
    //JSON 기본 정
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;

        //프로퍼티 접근 방식이라고도함
        public String getName(){
            return name;
        }

        public void setName(String name){
            this.name = name;
        }
    }
}
