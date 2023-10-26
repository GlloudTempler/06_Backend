package com.example.modelexam.controller.exam03;

import com.example.modelexam.model.Dept;
import com.example.modelexam.service.exam03.Dept03Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/exam03")
public class Dept03Controller {
//    TODO : MVC의 Model(Service클래스) 객체 가져오기
    @Autowired
    Dept03Service deptService;

    @GetMapping("/dept")
    public String getDeptAll(Model model) {
//        TODO : Service 객체의 전체조회 함수 호출
        List<Dept> list = deptService.findAll();
        model.addAttribute("list", list);

//        TODO : 로그 찍기
        log.debug(list.toString());

        return "exam03/dept/dept_all.jsp";
    }

    @GetMapping("/dept/{dno}")
    public String getDeptID(@PathVariable long dno, Model model) {
//        TODO : 서비스 상세조회 호출
        Dept dept = deptService.findByID(dno);
        model.addAttribute("dept", dept);
        return "exam03/dept/dept_id.jsp";
    }

    @PostMapping("/dept")
//    TODO : ResponseBody : 함수의 결과로 json데이터를 리턴하고자 할 때 사용하는 어노테이션
//           사용법 : 함수 위에 붙이기
    @ResponseBody
    public List<Dept> createDept(
            @RequestBody Dept dept
    ) {
        List<Dept> list = deptService.save(dept);
        return list;
    }

}
