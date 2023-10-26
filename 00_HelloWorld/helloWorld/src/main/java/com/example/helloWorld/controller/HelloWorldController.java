package com.example.helloWorld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * packageName : com.example.helloWorld.controller
 * fileName : HelloWorldController
 * author : GGG
 * date : 2023-10-04
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2023-10-04         GGG          최초 생성
 */
@Controller
public class HelloWorldController {

    @GetMapping("/exam00/hello")
    public String Hello(Model model) {
        
        return "/exam00/hello.jsp";
    }
}
