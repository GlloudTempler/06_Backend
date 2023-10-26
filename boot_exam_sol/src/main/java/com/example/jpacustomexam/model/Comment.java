package com.example.jpacustomexam.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * packageName : com.example.modelexam.model
 * fileName : Member
 * author : kangtaegyung
 * date : 2022/10/14
 * description : 회원 정보 테이블, @ManyToOne 단방향, 제일 일반적인 FK 설정 방법(*)
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/14         kangtaegyung          최초 생성
 */
@Entity
@Table(name = "TB_COMMENT")
@SequenceGenerator(
        name = "SQ_COMMENT_GENERATOR"
        , sequenceName = "SQ_COMMENT"
        , initialValue = 1
        , allocationSize = 1
)
@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SQ_COMMENT_GENERATOR"
    )
    @Column(columnDefinition = "NUMBER")
    private Integer cid;
    @Column(columnDefinition = "VARCHAR2(255)")
    private String commentContent;
    @Column(columnDefinition = "VARCHAR2(255)")
    private String commentWriter;
    @Column(columnDefinition = "VARCHAR2(255)")
    private Integer bid;
}
