package com.example.jpacustomexam.model.exam05;

import com.example.jpacustomexam.model.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * packageName : com.example.jpacustomexam.model.exam05
 * fileName : Phone
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
@Table(name="TB_PHONE")
@SequenceGenerator(
        name = "SQ_PHONE_GENERATOR"
        , sequenceName = "SQ_PHONE"
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
public class Phone extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SQ_PHONE_GENERATOR")
    @Column(columnDefinition="NUMBER")
    private Integer pno;

    @Column(columnDefinition="VARCHAR2(255)")
    private String  pname;

    @Column(columnDefinition="VARCHAR2(255)")
    private String  vendor;

//    1: 1 관계
//  사용법 :     @JoinColumn(name="부모공통컬럼")
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="no")
    @JsonBackReference
    private Person person;
}
