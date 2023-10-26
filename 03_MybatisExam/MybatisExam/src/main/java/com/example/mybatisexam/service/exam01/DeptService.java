package com.example.mybatisexam.service.exam01;

import com.example.mybatisexam.dao.DeptDao;
import com.example.mybatisexam.model.common.PageReq;
import com.example.mybatisexam.model.common.PageRes;
import com.example.mybatisexam.model.vo.Dept;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.mybatisexam.service.exam01
 * fileName : DeptService
 * author : GGG
 * date : 2023-10-12
 * description : 부서 서비스 클래스(업무 로직)
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2023-10-12         GGG          최초 생성
 */
@Slf4j
@Service
public class DeptService {
    @Autowired
    DeptDao deptDao; // db crud 함수들이 있는 클래스

    /** dname like 검색 */
    public PageRes<Dept> findByDnameContaining(String dname, PageReq pageReq) {
//        TODO : 전체 조회(like 됨)
        List<Dept> list = deptDao.findByDnameContaining(dname, pageReq);

//        TODO : 페이징 처리 로직
//               1) 총 테이블 개수 :
        long totalCount = deptDao.countByDname(dname);
//        TODO : 생성자 페이지 결과 객체(PageRes)
        PageRes pageRes = new PageRes(
                list, // 검색 결과 배열
                pageReq.getPage(), // 현재 페이지 번호
                totalCount, // 총 테이블 건수
                pageReq.getSize() // 1페이지당 개수
        );

        return pageRes;
    }

    /* 상세 조회 */
    public Optional<Dept> findByID(int dno) {
//        TODO : db 상세조회 호출
        Optional<Dept> optionalDept = deptDao.findByID(dno);
        return optionalDept;
    }

    /* TODO : 저장 함수  : dml (트랜잭션을 동반 : 테이블에 값을 {수정/삭제/넣는} 행위) */
    /*                         트랜잭션(주로 DML - CUD에 해당) : 롤백에 필요
    *                          insert -> commit까지 (트랜잭션 1개)
    *                          트랜잭션에 포함된 데이터 중 하나에서 오류 발생하면 해당 트랜잭션 롤백 - 데이터 일관성 유지 */
    public int save(Dept dept) {
        int queryResult = -1; // 저장된 건수를 위한 변수
        /* TODO : 롤백이나 취소처리가 필요하기 때문에 에러확인이 필요함*/
        try{
//            TODO : 기본키(dno) 없으면 insert
            if(dept.getDno() == null){
                queryResult = deptDao.insert(dept);
            }else {
//                TODO : 기본키(dno) 있으면 update
                queryResult = deptDao.update(dept);
            }

        }catch (Exception e) {
            log.debug(e.getMessage());
        }
        return queryResult;
    }

    /* TODO : 삭제함수 dml함수 -> 에러처리 */
    public boolean removeByID(int dno) {
        try{
            if(deptDao.existByID(dno) > 0) {
                deptDao.deleteByID(dno);
                return true;
            }
        }catch (Exception e){
            log.debug(e.getMessage());
        }
        return false;
    }

    /* TODO : dynamic SQL */
    public PageRes<Dept> findByDynamicContaining(String dname, String loc, PageReq pageReq) {
        //        TODO : dynamic 조회 (like 됨)
        List<Dept> list = deptDao.findByDynamicContaining(dname, loc, pageReq);

//        TODO : 페이징 처리 로직
//               1) 총 테이블 개수 :
        long totalCount = deptDao.countByDynamic(dname, loc);
//        TODO : 생성자 페이지 결과 객체(PageRes)
        PageRes pageRes = new PageRes(
                list, // 검색 결과 배열
                pageReq.getPage(), // 현재 페이지 번호
                totalCount, // 총 테이블 건수
                pageReq.getSize() // 1페이지당 개수
        );

        return pageRes;
    }
}
