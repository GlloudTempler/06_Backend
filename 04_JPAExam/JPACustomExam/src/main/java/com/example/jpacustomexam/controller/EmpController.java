package com.example.jpacustomexam.controller;

import com.example.jpacustomexam.dto.EmpGroupDto;
import com.example.jpacustomexam.model.Dept;
import com.example.jpacustomexam.model.Emp;
import com.example.jpacustomexam.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * packageName : com.example.jpacustomexam.controller
 * fileName : EmpController
 * author : GGG
 * date : 2023-10-17
 * description : 사원 컨트롤러
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2023-10-17         GGG          최초 생성
 */
@Slf4j
@RestController
public class EmpController {

    @Autowired
    EmpService empService; // DI

    /**
     * 전체조회 + 정렬(eno 내림차순)
     */
    @GetMapping("/emp/desc")
    public ResponseEntity<Object> getEmpAllDesc() {
        try {
//         전체 조회 + 정렬(dno 내림차순) 호출
            List<Emp> list = empService.findAllByOrderByEnoDesc();

            if (list.isEmpty() == false) {
//                성공
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 전체조회 + 정렬(ename 오름차순)
     */
    @GetMapping("/emp/ename/asc")
    public ResponseEntity<Object> findAllByOrderByEname() {
        try {
//         전체 조회 + 정렬(dno 내림차순) 호출
            List<Emp> list = empService.findAllByOrderByEname();

            if (list.isEmpty() == false) {
//                성공
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 전체조회 + like(Containing) 검색( ename ) : 쿼리메소드
     */
    @GetMapping("/emp/ename/containing/{ename}")
    public ResponseEntity<Object> findAllByEnameContaining(
            @PathVariable String ename
    ) {
        try {
//         전체 조회 + 정렬(dno 내림차순) 호출
            List<Emp> list
                    = empService.findAllByEnameContaining(ename);

            if (list.isEmpty() == false) {
//                성공
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 연습 4) : 쿼리메소드
     */
    @GetMapping("/emp/dno/{dno}")
    public ResponseEntity<Object> findAllByJobAndDno(
            @PathVariable int dno
    ) {
        try {
//         전체 조회 + 정렬(dno 내림차순) 호출
            List<Emp> list
                    = empService.findAllByJobAndDno("MANAGER", dno);

            if (list.isEmpty() == false) {
//                성공
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 연습 5) : 쿼리메소드
     */
    @GetMapping("/emp/salary/{first}/{last}")
    public ResponseEntity<Object> findAllBySalaryBetween(
            @PathVariable int first,
            @PathVariable int last
    ) {
        try {
//         전체 조회 + 정렬(dno 내림차순) 호출
            List<Emp> list
                    = empService.findAllBySalaryBetween(first, last);

            if (list.isEmpty() == false) {
//                성공
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 연습 6) : 쿼리메소드
     */
    @GetMapping("/emp/ignore/job/{job}")
    public ResponseEntity<Object> findAllByJobIgnoreCase(
            @PathVariable String job
    ) {
        try {
//         전체 조회 + 정렬(dno 내림차순) 호출
            List<Emp> list
                    = empService.findAllByJobIgnoreCase(job);

            if (list.isEmpty() == false) {
//                성공
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 연습 7) : 쿼리메소드
     */
    @GetMapping("/emp/commission")
    public ResponseEntity<Object> findAllByCommissionIsNotNull() {
        try {
//         전체 조회 + 정렬(dno 내림차순) 호출
            List<Emp> list
                    = empService.findAllByCommissionIsNotNull();

            if (list.isEmpty() == false) {
//                성공
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 연습 8) : 쿼리메소드
     */
    @GetMapping("/emp/salary/desc/ename/asc")
    public ResponseEntity<Object> findAllByOrderBySalaryDescEnameAsc() {
        try {
//         전체 조회 + 정렬(dno 내림차순) 호출
            List<Emp> list
                    = empService.findAllByOrderBySalaryDescEnameAsc();

            if (list.isEmpty() == false) {
//                성공
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 연습 9) : 쿼리메소드
     */
    @GetMapping("/emp/or/salary/{first}/{last}")
    public ResponseEntity<Object> findAllBySalaryLessThanOrSalaryGreaterThan(
            @PathVariable int first,
            @PathVariable int last
    ) {
        try {
//         전체 조회 + 정렬(dno 내림차순) 호출
            List<Emp> list
                    = empService
                    .findAllBySalaryLessThanOrSalaryGreaterThan(first, last);

            if (list.isEmpty() == false) {
//                성공
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 연습 10) : 쿼리메소드
     */
    @GetMapping("/emp/commission/{first}/{second}/{third}")
    public ResponseEntity<Object> findAllBySalaryLessThanOrSalaryGreaterThan(
            @PathVariable int first,
            @PathVariable int second,
            @PathVariable int third
    ) {
        try {
//         전체 조회 + 정렬(dno 내림차순) 호출
            List<Emp> list
                    = empService
                    .findAllByCommissionOrCommissionOrCommission(first,
                            second,
                            third);

            if (list.isEmpty() == false) {
//                성공
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    --------------------------------------------------
//    @Query 예제
//    -------------------------------------------------

    /**
     * 전체조회 + ename like 검색 : @Query
     */
    @GetMapping("/emp/ename/{ename}")
    public ResponseEntity<Object> selectByEname(
            @PathVariable String ename
    ) {
        try {
//         전체 조회 + 정렬(dno 오름차순) 호출
            List<Emp> list
                    = empService.selectByEname(ename);

            if (list.isEmpty() == false) {
//                성공
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 연습 2) : @Query
     */
    @GetMapping("/emp/desc/asc")
    public ResponseEntity<Object> selectDesc() {
        try {
//         전체 조회 + 정렬(dno 오름차순) 호출
            List<Emp> list
                    = empService.selectDesc();

            if (list.isEmpty() == false) {
//                성공
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 연습 3) : @Query
     */
    @GetMapping("/emp/salary/{salary}")
    public ResponseEntity<Object> selectDesc(
            @PathVariable int salary
    ) {
        try {
//         전체 조회 + 정렬(dno 오름차순) 호출
            List<Emp> list
                    = empService.selectSalary(salary);

            if (list.isEmpty() == false) {
//                성공
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 연습 4) : @Query
     */
    @GetMapping("/emp/hiredate/{first}/{last}")
    public ResponseEntity<Object> selectDesc(
            @PathVariable String first,
            @PathVariable String last
    ) {
        try {
            List<Emp> list
                    = empService.selectHiredate(first, last);

            if (list.isEmpty() == false) {
//                성공
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 연습 5) : @Query(dto)
     */
    @GetMapping("/emp/group")
    public ResponseEntity<Object> selectGroupDnoJob() {
        try {
            List<EmpGroupDto> list
                    = empService.selectGroupDnoJob();

            if (list.isEmpty() == false) {
//                성공
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 연습 6) : @Query(dto)
     */
    @GetMapping("/emp/dno/group")
    public ResponseEntity<Object> selectGroupDnoTrunc() {
        try {
            List<EmpGroupDto> list
                    = empService.selectGroupDnoTrunc();

            if (list.isEmpty() == false) {
//                성공
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 연습 7) : @Query(dto)
     */
    @GetMapping("/emp/sum/max")
    public ResponseEntity<Object> selectGroupSumMax() {
        try {
            List<EmpGroupDto> list
                    = empService.selectGroupSumMax();

            if (list.isEmpty() == false) {
//                성공
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 연습 8) : @Query(dto)
     */
    @GetMapping("/emp/hiredate/min/max")
    public ResponseEntity<Object> selectGroupHiredate() {
        try {
            List<EmpGroupDto> list
                    = empService.selectGroupHiredate();

            if (list.isEmpty() == false) {
//                성공
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    ----------------------------------
//    연습1) 페이징 처리
//    ---------------------------------
//    1)
    @GetMapping("/emp/ename/{ename}/paging")
    public ResponseEntity<Object> getEmpEnamePage(
            @PathVariable String ename,
            Pageable pageable
    ) {
        try {
            Page<Emp> page
                    = empService.findAllByEnameContaining(ename, pageable);

//          todo: Map 자료구조 정보 저장 : 1) 부서객체, 2) 페이징 정보 (3개)
            Map<String, Object> response = new HashMap<>();
            response.put("dept", page.getContent()); // 부서 객체
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

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    2)
    @GetMapping("/emp/all/paging")
    public ResponseEntity<Object> getEmpAllPage(Pageable pageable) {
        try {
            Page<Emp> page
                    = empService.findAllPage(pageable);

//          todo: Map 자료구조 정보 저장 : 1) 사원객체, 2) 페이징 정보 (3개)
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

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    ------------
//    @Query
//    ------------
    //     페이징 2)
    @GetMapping("/emp/ename/{ename}/paging2")
    public ResponseEntity<Object> selectEnamePage(
            @PathVariable String ename,
            Pageable pageable) {
        try {
            Page<Emp> page
                    = empService.selectEnamePage(ename, pageable);

//          todo: Map 자료구조 정보 저장 : 1) 사원객체, 2) 페이징 정보 (3개)
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

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}










