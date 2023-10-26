package com.example.modelexam.controller.exam04;

import com.example.modelexam.model.Dept;
import com.example.modelexam.service.exam04.Dept04Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

/**
 * packageName : com.example.modelexam.service.exam01
 * fileName : DeptController
 * author : GGG
 * date : 2023-10-10
 * description : 부서 컨트롤러, @Slf4j, findAll()
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2023-10-10         GGG          최초 생성
 */
// TODO : @Slf4j - 롬북, 로깅 라이브러리를 위한 어노테이션(디자인패턴 : 퍼케이드(Facade) 패턴)
//        로깅 라이브러리 : log4j(과거) -> log4j2, logback(최신) || 함수 호출을 일원화시켜주는 기능(@Slf4j)
//        사용법 : 클래스 위에 붙이면 사용가능 : log.debug(값)
//                (간략 정보 : Error -> Info -> Debug -> Warn -> Trace : 상세정보)
//        logback 설치 :
//        1) log4jbc.log4j2.properties, logback-spring.xml 기본 설정 파일 추가
//           - log4dbc.log4jc.properties : sql 로그를 출력하기 위한 설정파일
//           - logback-spring.xml : logback의 다양한 레벨 옵션 설정
@Slf4j
@Controller
@RequestMapping("/exam04")
public class Dept04Controller {
//    TODO : MVC의 Model(Service클래스) 객체 가져오기
    @Autowired
    Dept04Service deptService;

    @GetMapping("/dept")
    public String getDeptAll(Model model) {
//        TODO : Service 객체의 전체조회 함수 호출
        List<Dept> list = deptService.findAll();
        model.addAttribute("list", list);

//        TODO : 로그 찍기
        log.debug(list.toString());

        return "exam04/dept/dept_all.jsp";
    }

    @GetMapping("/dept/{dno}")
    public String getDeptID(@PathVariable long dno, Model model) {
//        TODO : 서비스 상세조회 호출
        Dept dept = deptService.findByID(dno);
        model.addAttribute("dept", dept);
        return "exam04/dept/dept_id.jsp";
    }

//    TODO : 부서 추가 페이지 열기 함수
    @GetMapping("/dept/addition")
    public String addDept() {
        return "/exam04/dept/add_dept.jsp";
    }
//    TODO : 저장 버튼 클릭시 db저장하기 함수
    @PostMapping("/dept/add")
    public RedirectView createDept(@ModelAttribute Dept dept) {
//        TODO : 서비스 저장 함수 호출
        deptService.save(dept);
//        TODO : 저장 후 전체조회 url로 강제 페ㅣ지 이동
//               사용법 : new RedirectView("강제이동될 url주소")
        return new RedirectView("/exam04/dept");
    }
}
