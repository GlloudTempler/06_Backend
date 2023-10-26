package com.example.modelexam.controller.exam01;

import com.example.modelexam.model.Dept;
import com.example.modelexam.service.exam02.Dept02Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * packageName : com.example.modelexam.service.exam01
 * fileName : DeptController
 * author : GGG
 * date : 2023-10-10
 * description : 부서 컨트롤러, @Slf4j, findAll()
 * 요약 :
 *      @Slf4j : 로깅 라이브러리 (롬북) , 인터페이스 : 자식클래스(logback)
 *      사용법 : 예) 1) @Slf4j 클래스 위에 달고
 *                  2) 로깅 하고 싶은 라인에서 log.debug(값.toString());
 *      설치 : Logback 프로퍼티 파일 2개 복사, build.gradle 라이브러리 4개 추가
 *      @Service : 클래스 위에 달고, 달린 클래스는 서버가 가동될 때 자동으로 객체 생성됨
 *      (IOC)
 *      예) @Repository, @Component, @Bean 등
 *      @Autowired : 위에서 생성된 객체를 가져오기 할 때 사용(DI)
 *      @GetMapping : Get 방식으로 오는 요청에 대해 실행되는 어노테이션
 *      (주로 db select 문이 실행됨)
 *      @PostMapping : Post 방식으로 오는 요청에(insert 요청) 대해 실행
 *      @PutMapping : Put 방식으로 오는 요청에(update 요청) 대해 실행
 *      @DeleteMapping : Delete 방식으로 오는 요청에(Delete 요청) 대해 실행
 *      @Controller : return 값 - jsp 페이지명(SSR 프로그램)
 *       사용) jsp / thymeleaf + springboot 연동
 *      @RestController : return 값 - json 객체 데이터
 *       사용) react/vue(SPA) + springboot 연동
 *      ResponseEntity 객체 : 신호 보내느 객체
 *       ex) HTTPStatus.OK(성공 : 200)
 *           HTTPStatus.INTERNAL_SERVER_ERROR(500)
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
@RequestMapping("/exam01")
public class DeptController {
//    TODO : MVC의 Model(Service클래스) 객체 가져오기
    @Autowired
    Dept02Service deptService;

    @GetMapping("/dept")
    public String getDeptAll(Model model) {
//        TODO : Service 객체의 전체조회 함수 호출
        List<Dept> list = deptService.findAll();
        model.addAttribute("list", list);

//        TODO : 로그 찍기
        log.debug(list.toString());

        return "exam01/dept/dept_all.jsp";
    }

}
