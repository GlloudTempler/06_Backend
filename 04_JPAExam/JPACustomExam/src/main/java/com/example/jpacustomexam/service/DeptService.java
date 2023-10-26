package com.example.jpacustomexam.service;

import com.example.jpacustomexam.dto.DeptEmpDto;
import com.example.jpacustomexam.dto.DeptGroupDto;
import com.example.jpacustomexam.model.Dept;
import com.example.jpacustomexam.repository.DeptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * packageName : com.example.jpacustomexam.service
 * fileName : DeptService
 * author : GGG
 * date : 2023-10-17
 * description : 부서 서비스
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2023-10-17         GGG          최초 생성
 */
@Service
public class DeptService {

    @Autowired
    DeptRepository deptRepository; // DI

    /**
     * 전체조회 + 정렬(dno 내림차순)
     */
//    쿼리메소드 사용한 함수
    public List<Dept> findAllByOrderByDnoDesc() {
        List<Dept> list = deptRepository.findAllByOrderByDnoDesc();

        return list;
    }

    /**
     * 전체조회 + 정렬(dname 내림차순) : 쿼리메소드
     */
    public List<Dept> findAllByOrderByDnameDesc() {
        List<Dept> list = deptRepository.findAllByOrderByDnameDesc();

        return list;
    }

    /**
     * 전체조회 + 정렬(dno 오름차순) : 쿼리메소드
     */
    public List<Dept> findAllByOrderByDno() {
        List<Dept> list = deptRepository.findAllByOrderByDno();

        return list;
    }

    /**
     * 전체조회 + dname like(Containing) 검색 +정렬(dname 내림차순) : 쿼리메소드
     */
    public List<Dept> findAllByDnameContainingOrderByDnameDesc(String dname) {
        List<Dept> list
                = deptRepository.findAllByDnameContainingOrderByDnameDesc(dname);

        return list;
    }

    /**
     * 전체조회 + dname like 검색 : @Query
     */
    public List<Dept> selectByDname(String dname) {
        List<Dept> list
                = deptRepository.selectByDname(dname);

        return list;
    }

    /**
     * 전체조회 + dname like 검색 : @Query
     */
    public List<Dept> selectByDnameAndLoc(String dname, String loc) {
        List<Dept> list
                = deptRepository.selectByDnameAndLoc(dname, loc);

        return list;
    }

    /**
     * 전체조회 그룹 함수 검색(dto) : @Query
     */
    public List<DeptGroupDto> selectByGroupFunc() {
        List<DeptGroupDto> list
                = deptRepository.selectByGroupFunc();

        return list;
    }

    /**
     * 5) 전체조회 기본 함수 검색(dto) : @Query
     */
    public List<DeptGroupDto> selectByBasicFunc() {
        List<DeptGroupDto> list
                = deptRepository.selectByBasicFunc();

        return list;
    }

    /**
     * 6) 전체조회 case when 검색(dto) : @Query
     */
    public List<DeptGroupDto> selectByCase() {
        List<DeptGroupDto> list
                = deptRepository.selectByCase();

        return list;
    }

    //    ---------------------------
//    페이지 예제
//    ---------------------------
//    1)
    public Page<Dept> findAllByDnameContaining(String dname, Pageable pageable) {
        Page<Dept> page
                = deptRepository.findAllByDnameContaining(dname, pageable);

        return page;
    }

//    2) 기본 제공되는 전체 조회 함수 : 페이징처리 기능 추가
    public Page<Dept> findAllPage(Pageable pageable) {
        Page<Dept> page
                = deptRepository.findAll(pageable);

        return page;
    }

//    --------------------------
//    @Query : 페이징 처리
//    --------------------------
    //    2)  : 페이징처리 기능 추가
    public Page<Dept> selectByDnamePage(String dname, Pageable pageable) {
        Page<Dept> page
                = deptRepository.selectByDnamePage(dname, pageable);

        return page;
    }
    //    3)  조인 : 페이징처리 기능 추가
    public Page<DeptEmpDto> selectNativeJoinPage(Pageable pageable) {
        Page<DeptEmpDto> page
                = deptRepository.selectNativeJoinPage(pageable);

        return page;
    }


}








