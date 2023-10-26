package com.example.controllerexam.controller.exam06;

import com.example.controllerexam.model.Board;
import com.example.controllerexam.model.Dept;
import com.example.controllerexam.model.Member;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName : com.example.controllerexam.controller.exam06
 * fileName : MultiPathRestController
 * author : GGG
 * date : 2023-10-06
 * description : @PathVariable , @RestController
 * 요약 :
 *       @Controller : jsp 개발 시 사용
 *       => return값 : 이동할 jsp 페이지명
 *       @RestController : react / vue 연동 개발 시 사용
 *       => return값 : json 데이터로 출력됨 : {속성 : 값}
 *
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2023-10-06         GGG          최초 생성
 */
@RestController
@RequestMapping("/exam06")
public class MultiPathRestController {
    @GetMapping("/multi-path-rest/id/{id}/name/{name}")
    public Member getMultiPath(
            @PathVariable String id,
            @PathVariable String name
    ) {
//        멤버 객체 생성
        Member member = new Member(id, name);
//        결과를 json데이터로 전송
        return member;
    }
//    TODO : 연습 1) 부서 클래스의 속성에 아래와 같이 데이터를 저장해서 화면에 출력해 보세요
//          결과 :
//              {
//                  "dno": 10,
//               "loc": "Seoul",
//                "dname": "Sales"
//              }
//          Rest client 이용해서 Rest함수 테스트 하기
    @GetMapping("/dept-path-rest/dno/{dno}/loc/{loc}/dname/{dname}")
    public Dept getDept(
            @PathVariable int dno,
            @PathVariable String loc,
            @PathVariable String dname
    ) {
        Dept dept = new Dept(dno, dname, loc);
        return dept;
    }

//    TODO : 연습 2) 게시판(Board) 모델 클래스를 만들어서 아래와 같이 출력
//           결과 :
//               {
//                  "no": 10,
//                  "title": "제목",
//                  "content": "내용",
//                  "count" : "1"
//               }
    @GetMapping("board-rest/no/{no}/title/{title}/content/{content}/count/{count}")
    public Board getBoard(
            @PathVariable int no,
            @PathVariable String title,
            @PathVariable String content,
            @PathVariable int count
    ) {
        Board board = new Board(no, title, content, count);
        return board;
    }

}
