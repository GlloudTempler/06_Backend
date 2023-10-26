package com.example.controllerexam.controller.exam10;

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
 * packageName : com.example.controllerexam.controller.exam10
 * fileName : ObjectResponseEntity3Controller
 * author : GGG
 * date : 2023-10-06
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2023-10-06         GGG          최초 생성
 */
@RestController
@RequestMapping("/exam10")
public class ObjectResponseEntity3Controller {
    @PostMapping("/object-body")
    public ResponseEntity<List<Member>> getObjectRequestBody(
            @RequestBody Member member
    ) {
        try{
            List<Member> list = new ArrayList<>();
            list.add(member);
            if(member != null) {
//                 TODO : 성공 : OK(200)
                return ResponseEntity.ok().body(list);
            }else {
//                TODO : 0건 insert : NO_CONTENT(204)
                return ResponseEntity.noContent().build();
            }
        }catch (Exception e) {
//            TODO : BAD_REQUEST(400)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
