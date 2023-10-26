package com.example.controllerexam.controller.exam01;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * packageName : com.example.controllerexam.controller.exam01
 * fileName : HelloController
 * author : GGG
 * date : 2023-10-04
 * description : JSP, 타임리프 : SSR (Server Side Rendering)
 *                  => Springboot 소스가 같이 있음
 *                  => html 파일을 Springboot에서 만들어서 웹브라우저로 전송
 *               React / Vue : CSR (Client Side Rendering)
 *                  => 소스 분리
 *                  => html파일을 최초 1회 전송, 변경되는 부분만 서버에서 받고, 나머지는 웹브라우저에서 자체로 만듬
 *               @GetMapping(url) 함수() {return "jsp페이지명";}
 *               => CRUD => Read 요청할 때 사용하는 어노테이션
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2023-10-04         GGG          최초 생성
 */
@Controller
public class HelloController {
    @GetMapping("/exam01/hello")
    public String Hello(Model model){
        model.addAttribute("greeting", "안녕 Springboot");
        model.addAttribute("greeting2", "Springboot 처음");
        model.addAttribute("greeting3", "ㅅㄱ");
        return "exam01/hello.jsp";
    }

//    TODO : 연습 1) url : /exam01/hello2 => "exam01/example01.jsp"에 "안녕 hello2 페이지" 출력하기
//                                         "Springboot 처음이지"
//                                         "수고해!!"
    @GetMapping("/exam01/hello2")
    public String Hello2(Model model){
        model.addAttribute("greet1", "안녕 hello2 페이지");
        model.addAttribute("greet2", "Springboot 처음");
        model.addAttribute("greet3", "ㅅㄱ ㅅㄱ");
        return "exam01/example01.jsp";
    }

//    TODO : 연습2) url : /exam01/hello3 jsp : "exam01/example02.jsp"
//                 결과 : "안녕 JSP 페이지야" / "JSP에는 JSTL" / "EL 표현식이 있습니다."
    @GetMapping("exam01/hello3")
    public String Hello03(Model model) {
        model.addAttribute("gr1", "안녕 JSP 페이지야");
        model.addAttribute("gr2", "JSP에는 JSTL");
        model.addAttribute("gr3", "EL 표현식이 있습니다.");
        return "exam01/example02.jsp";
    }

//    TODO : 예제 2) JSP페이지로 모든 자료형 보내기
    @GetMapping("exam01/operation")
    public String Operation(Model model) {

        String strNum = "10";
        int iNum = 100;
        String strVal = "a";
        boolean bVal = true;

        model.addAttribute("strNum", strNum);
        model.addAttribute("iNum", iNum);
        model.addAttribute("strVal", strVal);
        model.addAttribute("bVal", bVal);

        return "exam01/operation.jsp";
    }

//    TODO : 연습 3)
//        url         : /exam01/hello4
//        jsp 페이지명 :  exam01/example03.jsp
//        결과        : boolean(bFlag), int(iParam),
//                     long(lParam), double(dParam) 변수 4개를
//                     만들어서 각각 자료형으로 전달해서 jsp 로 출력하세요

    @GetMapping("/exam01/hello4")
    public String Hello4(Model model) {

        boolean bFlag = true;
        int iParam    = 10;
        long lParam   = 15L;
        double dParam = 20.2;

        model.addAttribute("bFlag", bFlag);
        model.addAttribute("iParam", iParam);
        model.addAttribute("lParam", lParam);
        model.addAttribute("dParam", dParam);

//      TODO: Debugging : 기본 : 변수의 값 추적
        System.out.println("bFlag " + bFlag);
        System.out.println("iParam " + iParam);
        System.out.println("lParam " + lParam);
        System.out.println("dParam " + dParam);

        return "exam01/example03.jsp";
    }
}
