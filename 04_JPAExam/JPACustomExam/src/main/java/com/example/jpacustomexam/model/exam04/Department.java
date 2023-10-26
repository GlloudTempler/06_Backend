package com.example.jpacustomexam.model.exam04;

import com.example.jpacustomexam.model.BaseTimeEntity;
import com.example.jpacustomexam.model.Emp;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * packageName : com.example.jpacustomexam.model.exam04
 * fileName : Department
 * author : GGG
 * date : 2023-10-19
 * description :
 * 요약 :
 *    JPA 조인 : @(어노테이션) 을 이용해서 조인함 ( 자동 FK , 컬럼 생성 )
 *       부모(부서) - 자식(사원) 관계 :
 *                1) 1 : N(다) - @OneToMany(부서), @ManyToOne(사원)
 *                2) 1 : 1     - @OneToOne(핸드폰), @OneToOne(사람)
 *                가정: 1사람이 1핸드폰을 가짐
 *                3) N(다) : N(다) - @ManyToMany (x)
 *
 *       추천 : 1) 1:N  :  @OneToMany(부서), @ManyToOne(사원)
 *                 => 양방향 조인(불가피할때 -> 여러가지 문제 발생)
 *             1-1)    : 부서 , @ManyToOne(사원)
 *                 => 단방향 조인(추천)
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2023-10-19         GGG          최초 생성
 */
@Entity
@Table(name="TB_DEPARTMENT")
@SequenceGenerator(
        name = "SQ_DEPARTMENT_GENERATOR"
        , sequenceName = "SQ_DEPARTMENT"
        , initialValue = 1
        , allocationSize = 1
)
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Department extends BaseTimeEntity {
    //    부서넘버
//    @Id : Primary Key 에 해당
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SQ_DEPARTMENT_GENERATOR"
    )
    @Column(columnDefinition="NUMBER")
    private Integer dno;
    //    부서이름
    @Column(columnDefinition="VARCHAR2(255)")
    private String dname;
    //    부서위치
    @Column(columnDefinition="VARCHAR2(255)")
    private String loc;

//    양방향 조인 : 1) 순환참조 문제
//                  => 해결 : @JsonManagedReference(부서)
//                           @JsonBackReference(사원)
//                2) N + 1 문제
//  사용법 : @OneToMany(mappedBy = "사원_연결속성")
    @OneToMany(mappedBy = "department")
    @JsonManagedReference
    private Set<Employee> employee = new HashSet<>(); // 1:n(사원)
}
