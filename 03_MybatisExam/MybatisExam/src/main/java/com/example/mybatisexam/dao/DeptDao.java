package com.example.mybatisexam.dao;

import com.example.mybatisexam.model.common.PageReq;
import com.example.mybatisexam.model.vo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.mybatisexam.dao
 * fileName : DeptDao
 * author : GGG
 * date : 2023-10-12
 * description : Db 접속 함수들이(CRUD) 있는 클래스(Mybatis mapper(dao) 클래스
 * 요약 :
 *       @Mapper : 인터페이스 위에 달고, Mybatis에서 사용할 인터페이스라는 것을 알림
 *                 서버 시작시 자동으로 객체 생성
 *       @Param("속성명") 변수명 : sql의 매개변수로 전달
 *       xml :
 *       pageReq.size : 1 페이지당 개수
 *       pageReq.page : 현재 페이지 번호
 *       (OFFSET => oracle 12버전 이상만 가능)
 *       OFFSET #{pageReq.page} * #{pageReq.size} ROWS FETCH FIRST #(page.size} ROWS ONLY
 *       ex) 1페이지당 개수(size) = 10, 현재 페이지 번호(page) = 1
 *       0 * 10 = 10 (db 건수 0개 건너뛰고)
 *       10       10 (10개를 화면에 출력)
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2023-10-12         GGG          최초 생성
 */
@Mapper
public interface DeptDao {

    /** 전체 조회 : 부서명 like 기능 있음*/
    public List<Dept> findByDnameContaining(@Param("dname") String dname, PageReq pageReq);
    /* 전체 테이블 개수 세기 함수 */
    long countByDname(String dname);

    /* 상세 조회 : 한 건 조회 */
    Optional<Dept> findByID(int dno);

    /* 저장 함수 */
    int insert(Dept dept);

    /* 수정 함수 */
    int update(Dept dept);

    /* 삭제 함수 */
    int deleteByID(int dno);

    /* 기본키(dno)가 있는지 확인하는 조회함수 */
    long existByID(int dno);

    /* TODO : dynamic SQL 작성 예제 */
    public List<Dept> findByDynamicContaining(
            @Param("dname") String dname,
            @Param("loc") String loc,
            PageReq pageReq
            );

    /* TODO : dynamic SQL 작성 전체 카운트 예제 */
    long countByDynamic(String dname, @Param("loc") String loc); // 매개변수 2개 이상 받을 시 @Param 어노테이션 활용
}
