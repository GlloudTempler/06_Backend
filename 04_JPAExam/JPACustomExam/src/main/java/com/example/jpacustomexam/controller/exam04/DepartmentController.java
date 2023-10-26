package com.example.jpacustomexam.controller.exam04;

import com.example.jpacustomexam.dto.DeptEmpDto;
import com.example.jpacustomexam.model.exam04.Department;
import com.example.jpacustomexam.model.exam04.Employee;
import com.example.jpacustomexam.service.exam04.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * packageName : com.example.jpacustomexam.controller.exam04
 * fileName : DepartmentController
 * author : GGG
 * date : 2023-10-19
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2023-10-19         GGG          최초 생성
 */
@Slf4j
@RestController
@RequestMapping("/exam04")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    /** 단방향 조인 예제 */
    //    page=현재페이지번호(0 ~ n), size=전체페이지수
    @GetMapping("/dept/join/paging2")
    public ResponseEntity<Object> selectJoinPage2(Pageable pageable) {
        try {
            Page<Employee> page
                    = departmentService.selectJoinPage2(pageable);

//          todo: Map 자료구조 정보 저장 : 1) 부서객체, 2) 페이징 정보 (3개)
            Map<String, Object> response = new HashMap<>();
            response.put("emp", page.getContent()); // 사원 객체
            response.put("currentPage", page.getNumber()); // 현재페이지번호
            response.put("totalItems", page.getTotalElements()); // 전체테이블건수
            response.put("totalPages", page.getTotalPages()); // 전체 페이지 수

            if (page.isEmpty() == false) {
//                성공
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /** 양방향 조인 예제 */
    //    page=현재페이지번호(0 ~ n), size=전체페이지수
    @GetMapping("/dept/fetch/join")
    public ResponseEntity<Object> selectFetchJoin() {
        try {
            List<Department> page
                    = departmentService.selectFetchJoin();

            if (page.isEmpty() == false) {
//                성공
                return new ResponseEntity<>(page, HttpStatus.OK);
            } else {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
