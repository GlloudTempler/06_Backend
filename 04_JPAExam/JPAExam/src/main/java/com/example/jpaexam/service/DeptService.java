package com.example.jpaexam.service;

import com.example.jpaexam.model.Dept;
import com.example.jpaexam.repository.DeptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.jpaexam.service
 * fileName : DeptService
 * author : GGG
 * date : 2023-10-16
 * description : 부서 업무 서비스
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2023-10-16         GGG          최초 생성
 */
@Service
public class DeptService {

    @Autowired
    DeptRepository deptRepository; // DI 객체 가져오기

    /** 전체조회 */
    public List<Dept> findAll() {
        List<Dept> list = deptRepository.findAll(); // db 전체조회 함수 호출
        return list;
    }

    /** 상세조회(1건조회) */
    public Optional<Dept> findById(int dno) {
        Optional<Dept> optionalDept = deptRepository.findById(dno);

        return optionalDept;
    }

    /** 저장(수정)함수 */
    public Dept save(Dept dept) {
//      todo: jpa 저장함수 호출 ( 기본키 없으면 insert, 있으면 update )
        Dept dept2 = deptRepository.save(dept);

        return dept2; // 저장된 부서객체
    }

    /** 삭제함수 */
    public boolean removeById(int dno) {

//      existsById : jpa 함수 - 리턴값: 있으면 true, 없으면 false
        if(deptRepository.existsById(dno)) {
            deptRepository.deleteById(dno); // DB 삭제(dno)
            return true;
        }
        return false;
    }


}
