package com.example.jpacustomexam.model.exam04;

import com.example.jpacustomexam.model.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * packageName : com.example.jpacustomexam.model.exam04
 * fileName : Employee
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
@Entity
@Table(name = "TB_EMPLOYEE")
@SequenceGenerator(
        name = "SQ_EMPLOYEE_GENERATOR"
        , sequenceName = "SQ_EMPLOYEE"
        , initialValue = 1
        , allocationSize = 1
)
@Setter
@Getter
@ToString(exclude = "department")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Employee extends BaseTimeEntity {
    //    @Id : Primary Key 에 해당
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SQ_EMPLOYEE_GENERATOR"
    )
    @Column(columnDefinition = "NUMBER")
    private Integer eno;

    @Column(columnDefinition = "VARCHAR2(255)")
    private String ename;

    @Column(columnDefinition = "VARCHAR2(255)")
    private String job;

    @Column(columnDefinition = "NUMBER")
    private Integer manager;

    @Column(columnDefinition = "VARCHAR2(255)")
    private String hiredate;

    @Column(columnDefinition = "NUMBER")
    private Integer salary;

    @Column(columnDefinition = "NUMBER")
    private Integer commission;

//    @Column(columnDefinition = "NUMBER")
//    private Integer dno; // 공통 컬럼(생략)

//  단방향 조인
//  사용법 : @JoinColumn(name="조인컬럼명")
//  양방향 조인 시 : N + 1 문제 발생 - 조인이 안되고 각각 SQL문이 생성되는 현상
//                 1) 즉시 로딩(EAGER) : 기본설정 -> 지연 로딩(LAZY)
//                                       (fetch = FetchType.LAZY)
//                     optional = false -> 조인방법(inner, outerjoin)
//                 2) inner join fetch : 조인 쿼리 생성
//                 3) application.properties :
//                    N+1 sql -> where in (?,?...?) 값 으로 변경하는 옵션
//              spring.jpa.properties.hibernate.default_batch_fetch_size=1000
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="dno")
    @JsonBackReference
    private Department department;
}
