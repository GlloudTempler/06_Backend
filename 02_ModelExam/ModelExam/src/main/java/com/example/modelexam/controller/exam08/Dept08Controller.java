package com.example.modelexam.controller.exam08;

import com.example.modelexam.model.Dept;
import com.example.modelexam.service.exam08.Dept08Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.modelexam.Controller.exam07
 * fileName : Dept07Controller
 * author : GGG
 * date : 2023-10-11
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2023-10-11         GGG          최초 생성
 */

@Slf4j
@RestController
@RequestMapping("/exam08")
public class Dept08Controller {
    @Autowired
    Dept08Service deptService;

    /**
     * 전체 조회 함수
     */
    @GetMapping("/dept")
    public ResponseEntity<Object> getDeptAll() {
        try {
            List<Dept> list = deptService.findAll();
            if (!list.isEmpty()) {
//              TODO: 조회 성공
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
//              TODO: 조회 실패 (데이터 없음)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            log.debug(e.getMessage());
//          TODO: INTERNAL_SERVER_ERROR(500번 메시지)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
    * 상세조회(1건)
    * */
    @GetMapping("/dept/{dno}")
    public ResponseEntity<Object> getDeptID(@PathVariable long dno) {
        try {
            Optional<Dept> optionalDept = deptService.findByID(dno);
            if (!optionalDept.isEmpty()) {
//              TODO: 조회 성공
                return new ResponseEntity<>(optionalDept.get(), HttpStatus.OK);
            } else {
//              TODO: 조회 실패 (데이터 없음)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            log.debug(e.getMessage());
//          TODO: INTERNAL_SERVER_ERROR(500번 메시지)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}