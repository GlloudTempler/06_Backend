package com.example.mybatisexam.controller.exam02;

import com.example.mybatisexam.model.common.PageReq;
import com.example.mybatisexam.model.common.PageRes;
import com.example.mybatisexam.model.vo.Emp;
import com.example.mybatisexam.service.exam01.EmpService;
import lombok.extern.slf4j.Slf4j;
import oracle.ucp.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * packageName : com.example.mybatisexam.controller.exam02
 * fileName : Emp02Controller
 * author : GGG
 * date : 2023-10-16
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2023-10-16         GGG          최초 생성
 */
@Slf4j
@RestController
@RequestMapping("/exam02")
public class Emp02Controller {
    @Autowired
    EmpService empService;

    @GetMapping("/emp")
    public ResponseEntity<Object> getEmpAll(
            @RequestParam(defaultValue = "") String ename,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ){
        try{
            PageReq pageReq = new PageReq(page, size);
            PageRes<Emp> pageRes = empService.findByEnameContaining(ename, pageReq);

            Map<String, Object> response = new HashMap<>();

            response.put("emp", pageRes.getContent());
            response.put("currentPage", pageRes.getNumber());
            response.put("totalItems", pageRes.getTotalElements());
            response.put("totalPages", pageRes.getTotalPages());

            if(pageRes.isEmpty() == false) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
            }
        }catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/emp/{eno}")
    public ResponseEntity<Emp> getEmpID(@PathVariable int eno) {
        try {
            Optional<Emp> optionalEmp = empService.findByID(eno);

            if(optionalEmp.isEmpty() == false) {
                return new ResponseEntity<>(optionalEmp.get(), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/emp")
    public ResponseEntity<Object> createEmp(@RequestBody Emp emp) {
        try {
            int count = empService.save(emp);
            return new ResponseEntity<>(count, HttpStatus.OK);
        }catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/emp/{eno}")
    public ResponseEntity<Object> updateEmp(
            @PathVariable int eno,
            @RequestBody Emp emp
    ) {
        try {
            int count = empService.save(emp);
            return new ResponseEntity<>(count, HttpStatus.OK);
        }catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/emp/delete/{eno}")
    public ResponseEntity<Object> deleteEmp(@PathVariable int eno){
        try{
//            TODO : 삭제 함수 호출
            boolean dSuccess = empService.removeByID(eno);
            if(dSuccess == true) {
//                TODO : 삭제 성공
                return new ResponseEntity<>(HttpStatus.OK);
            }else {
//                TODO : 0건 삭제 (실패)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/emp/dynamic")
    public ResponseEntity<Object> getEmpDynamic(
            @RequestParam(defaultValue = "") String ename,
            @RequestParam(defaultValue = "") String job,
            @RequestParam(defaultValue = "0") int manager,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size

    ) {
        try {
            PageReq pageReq = new PageReq(page, size);
//            TODO : dynamic 조회 함수 호출
            PageRes<Emp> pageRes = empService.findByDynamicContaining(ename, job, manager, pageReq);

//            TODO : 정보 전달 - map함수
            Map<String, Object> response = new HashMap<>();

            response.put("emp", pageRes.getContent());
            response.put("currentPage", pageRes.getNumber());
            response.put("totalItems", pageRes.getTotalElements());
            response.put("totalPages", pageRes.getTotalPages());

            if(pageRes.isEmpty() == false) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
