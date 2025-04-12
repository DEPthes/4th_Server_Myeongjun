package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc") //html을 이용한 데이터 전송
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string") // api 를 이용한 웹 방식(데이터를 직접 전송)
    @ResponseBody // html에 직접 넣어주겠다는 애노테이션
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api") //api 로 문자열이 아닌 데이터를 넘기는 방식
    @ResponseBody//JSON 으로 반환하는게 기본
    public Hello helloApi(@RequestParam("name") String name, @RequestParam("age") String age) {
        return new Hello(name, age);
    }

    static class Hello {
        private String name;
        private String age;

        public Hello(String name, String age) {
            this.name = name;
            this.age = age;
        }
    }
}
