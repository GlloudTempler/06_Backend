package com.example.jpacustomexam.repository;

import com.example.jpacustomexam.dto.DeptEmpDto;
import com.example.jpacustomexam.dto.DeptGroupDto;
import com.example.jpacustomexam.model.Dept;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName : com.example.jpacustomexam.repository
 * fileName : DeptRepository
 * author : GGG
 * date : 2023-10-17
 * description : 부서 레포지토리 (기본 CRUD 함수)
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2023-10-17         GGG          최초 생성
 */
@Repository
public interface DeptRepository extends JpaRepository<Dept, Integer> {
    //    개발자 직접 sql 작성 하는 기능(JPQL) : 1) 쿼리메소드 : 함수이름으로 sql문 만들기(낙타표기법 비슷)
//                                         예) 컬럼명 사용 : 첫글자는 대문자 , 단어사이는 대문자
//                                       2) @Query 쓰는 방법
//    쿼리메소드 예) findAllByOrderByDnoDesc : findAll - 전체조회
//                                           OrderBy - 정렬(Order by)
//                                           Dno     - 대상 컬럼명
//                                           Desc    - 내림차순 [Asc]
//    ------------------------------------------------
//    쿼리메소드 예제
//    ------------------------------------------------
//    todo: 1) 전체 조회 + 정렬(내림차순)
    List<Dept> findAllByOrderByDnoDesc();

    //    todo: 2) 전체 조회 + 정렬(부서명 내림차순)
    List<Dept> findAllByOrderByDnameDesc();

    //    todo: 3) 전체 조회 + 오름차순(dno) : Asc 생략가능
    List<Dept> findAllByOrderByDno();

    //    todo: 4) dname like 검색(DnameContaining) + dname 내림차순 조회
    List<Dept> findAllByDnameContainingOrderByDnameDesc(String dname);

    //    ---------------------------------------
//    @Query 예제 : 1) 오라클 쿼리 2) 객체 쿼리
//    ---------------------------------------
//  todo: 1) dname like 검색 : 추천 ( 오라클 쿼리 )
    @Query(value = "SELECT TD.* FROM TB_DEPT TD WHERE TD.DNAME LIKE '%' || :dname || '%' ", nativeQuery = true)
    List<Dept> selectByDname(@Param("dname") String dname);

//  todo: 1-1) 위의 꺼 다르게 코딩 : 참고
//    @Query(value = "SELECT TD.* FROM TB_DEPT TD WHERE TD.DNAME LIKE '%' || :dname || '%' ", nativeQuery = true)
//    List<Dept> selectByDname(String dname);
//  todo: 1-2) 위의 꺼 다르게 코딩 : 참고
//    @Query(value = "SELECT TD.* FROM TB_DEPT TD WHERE TD.DNAME LIKE '%' || ?1 || '%' ", nativeQuery = true)
//    List<Dept> selectByDname(String dname);

    //  todo: 2) dname like 검색 : 추천 ( 객체 쿼리 )
//    객체쿼리 만드는 방법 : 테이블 -> 클래스명
//                        컬럼명 -> 속성명
//                        * -> 사용하지 않음 , 클래스명의 별칭을 붙여서 사용
//                        (대소문자를 구분, nativeQuery=true(생략))
//    @Query(value = "SELECT TD FROM Dept TD WHERE TD.dname LIKE '%' || :dname || '%' ")
//    List<Dept> selectByDname(@Param("dname") String dname);

    //    todo: 3) 부서 테이블에서 부서명(dname) 과 위치(loc)를 매개변수로 받아
//       조회하는 함수를 작성하려고 한다.
//       단,  nativeQuery = true 사용하세요
//
    @Query(value = "SELECT D.* FROM TB_DEPT D " +
            "WHERE D.DNAME = :dname " +
            "AND   D.LOC   = :loc "
            , nativeQuery = true)
    List<Dept> selectByDnameAndLoc(@Param("dname") String dname,
                                   @Param("loc") String loc);

    //  todo: 4) 부서테이블의 부서번호를 sum, avg, max, min 값을
//       출력하는 함수를 작성하세요.
//      단,  nativeQuery = true 사용하세요
//      , DeptGroupDto 클래스에 저장하세요(오라클 쿼리 : 인터페이스로 만듬)
    @Query(value = "SELECT SUM(D.DNO) AS sumVar " +
            "     , AVG(D.DNO) AS avgVar " +
            "     , MAX(D.DNO) AS maxVar  " +
            "     , MIN(D.DNO) AS minVar  " +
            "FROM TB_DEPT D",
            nativeQuery = true)
    List<DeptGroupDto> selectByGroupFunc();

    //    todo: 5) SQL 기본 내장 함수를 사용하는 함수를 작성하세요.
//      단,  nativeQuery = true 사용하세요
    @Query(value = "SELECT UPPER(D.DNAME) AS upperDname " +
            "     , LOWER(D.DNAME) AS lowerDname " +
            "     , Trim(' Oracle ') As trimOracle " +
            "     , TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') AS charSysdate " +
            "FROM TB_DEPT D",
            nativeQuery = true)
    List<DeptGroupDto> selectByBasicFunc();

//    todo: 6) case when 사용 쿼리
//     dno < 20 연말 보너스 100%
//     dno > 20   연말 보너스 200%
//     모두 아니면 연말 보너스 없음 이라고 출력하세요
    @Query(value = "SELECT D.DNO, " +
            "       CASE WHEN D.DNO <20 THEN '연말 보너스 100%' " +
            "            WHEN D.DNO >20 THEN '연말 보너스 200%' " +
            "            ELSE '연말 보너스 없음' " +
            "        END AS incentive " +
            "FROM TB_DEPT D ",
            nativeQuery = true)
    List<DeptGroupDto> selectByCase();

//    ----------------------------------------------
//    JPA 페이징 처리 : 요청 페이징 객체 - Pageable (매개변수)
//                    결과 페이징 객체 - Page      (리턴값)
//    -----------------------------------------------
//    1) 쿼리 메소드 : 함수명으로 sql 문 실행하기
//    1-1) dname like 검색
    Page<Dept> findAllByDnameContaining(String dname, Pageable pageable);

//    1-2) 쿼리 메소드 : findAll() (jpa 기본함수)

//    2) @Query : 페이징 처리 (오라클 쿼리 : countQuery 속성 추가)
    @Query(value = "SELECT D.* FROM TB_DEPT D " +
            "WHERE D.DNAME LIKE '%' || :dname || '%'",
            countQuery = "SELECT COUNT(*) FROM TB_DEPT D " +
                    "WHERE D.DNAME LIKE '%' || :dname || '%'",
            nativeQuery = true)
    Page<Dept> selectByDnamePage(@Param("dname") String dname,
                                 Pageable pageable);

//    3) @Query 페이징 + 조인(부서 + 사원) : DeptEmpDto
    @Query(value = "SELECT D.*, E.ENO, E.ENAME, E.SALARY " +
            "FROM TB_DEPT D, TB_EMP E " +
            "WHERE D.DNO = E.DNO ",
            countQuery = "SELECT COUNT(*) " +
                    "FROM TB_DEPT D, TB_EMP E " +
                    "WHERE D.DNO = E.DNO ",
            nativeQuery = true)
    Page<DeptEmpDto> selectNativeJoinPage(Pageable pageable);
}







