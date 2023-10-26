package com.example.jpaexam.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * packageName : com.example.jpaexam.model
 * fileName : Dept
 * author : GGG
 * date : 2023-10-16
 * description : 부서 모델 클래스 ( 엔티티(entity) )
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2023-10-16         GGG          최초 생성
 */
// todo: @Entity - JPA 기능을 클래스에 부여하는 어노테이션
@Entity
// todo: @Table(name = "생성될테이블명")
@Table(name = "TB_DEPT")
// todo 사용법 : @SequenceGenerator(
//        name = "시퀀스함수이름"
//        , sequenceName = "DB에생성된시퀀스이름"
//        , initialValue = 시작값
//        , allocationSize = jpa에서관리용숫자(성능지표)
//)
@SequenceGenerator(
        name = "SQ_DEPT_GENERATOR"
        , sequenceName = "SQ_DEPT"
        , initialValue = 1
        , allocationSize = 1
)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
// todo: jpa 어노테이션 sql 자동 생성시 null 값 컬럼은 제외하고 생성
//   예) insert into 테이블명(컬럼1, 컬럼2, 컬럼3) values(1, 2, null);
//    => insert into 테이블명(컬럼1, 컬럼2) values(1, 2);
@DynamicInsert
@DynamicUpdate
public class Dept extends BaseTimeEntity {
    @Id
//  todo: @GeneratedValue(strategy = GenerationType.SEQUENCE
//            , generator = "시퀀스함수이름"
//    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SQ_DEPT_GENERATOR"
    )
//  todo: @Column(columnDefinition = "DB컬럼자료형")
    @Column(columnDefinition = "NUMBER")
    private Integer dno; // 부서번호(기본키) - 시퀀스 기능 부여

    @Column(columnDefinition = "VARCHAR2(255)")
    private String dname; // 부서명

    @Column(columnDefinition = "VARCHAR2(255)")
    private String loc;   // 부서위치
}
