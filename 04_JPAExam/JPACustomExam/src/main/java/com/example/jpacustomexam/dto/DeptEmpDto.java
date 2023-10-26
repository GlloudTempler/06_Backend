package com.example.jpacustomexam.dto;

/**
 * packageName : com.example.jpacustomexam.dto
 * fileName : DeptEmpDto
 * author : GGG
 * date : 2023-10-18
 * description : 부서 + 사원 조인용 DTO
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2023-10-18         GGG          최초 생성
 */
public interface DeptEmpDto {
//    부서번호
    Integer getDno();
//    부서명
    String getDname();
//    부서위치
    String getLoc();
//    사원번호
    Integer getEno();
//    사원명
    String getEname();
//    월급
    Integer getSalary();
}
