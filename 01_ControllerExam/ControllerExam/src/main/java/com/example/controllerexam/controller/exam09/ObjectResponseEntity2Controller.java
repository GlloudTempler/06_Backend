package com.example.controllerexam.controller.exam09;

import com.example.controllerexam.model.Dept;
import com.example.controllerexam.model.Member;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName : com.example.controllerexam.controller.exam09
 * fileName : ObjectResponseEntity2Controller
 * author : GGG
 * date : 2023-10-06
 * description : ResposeEntity 최종 버전
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2023-10-06         GGG          최초 생성
 */
@RestController
@RequestMapping("/exam09")
public class ObjectResponseEntity2Controller {
    @PostMapping("/object-body")
    public ResponseEntity<Object> getObjectRequestBody(
            @RequestBody Member member
            ) {
        try{
            List<Member> list = new ArrayList<>();
            list.add(member);
            if(member != null) {
//                 TODO : 성공 : OK(200)
                return new ResponseEntity<>(list, HttpStatus.OK);
            }else {
//                TODO : 0건 insert : NO_CONTENT(204)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e) {
//            TODO : BAD_REQUEST(400)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

//    TODO : 연습 1) 부서클래스의 정보를 post방식으로 제공받고 List에 넣어 RestClient 출력
//           단, 예외 처리 코딩, List가 null이 아닐 경우 상태메세지 FOUND(302),
//           실패메세지 NO_CONTENT(204),
//           프로그램 에러 INTERNAL_SERVER_ERROR(500) 코딩
    @PostMapping("/example01")
    public ResponseEntity<Object> getExample01(
            @RequestBody Dept dept
            ) {
        try{
            List<Dept> list = new ArrayList<>();
            list.add(dept);
            if (dept != null) {
                return new ResponseEntity<>(list, HttpStatus.FOUND);
            }else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
