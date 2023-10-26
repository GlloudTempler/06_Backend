package com.example.jpacustomexam.model.exam05;

import com.example.jpacustomexam.model.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * packageName : com.example.jpacustomexam.model.exam05
 * fileName : Person
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
@Table(name="TB_PERSON")
@SequenceGenerator(
        name = "SQ_PERSON_GENERATOR"
        , sequenceName = "SQ_PERSON"
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
public class Person extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SQ_PERSON_GENERATOR")
    @Column(columnDefinition="NUMBER")
    private Integer no;   // 기본키

    @Column(columnDefinition="VARCHAR2(255)")
    private String name;

    @Column(columnDefinition="VARCHAR2(255)")
    private String job;

//    1:1 관계 설정 : @OneToOne(사람:부모) , @OneToOne(폰:자식)
//  사용법 : @OneToOne(mappedBy = "자식속성명")
    @OneToOne(mappedBy = "person")
//    순환참조 해결
    @JsonManagedReference
    private Phone phone;
}
