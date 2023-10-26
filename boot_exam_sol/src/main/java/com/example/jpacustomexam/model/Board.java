package com.example.jpacustomexam.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName : com.example.modelexam.model
 * fileName : Board
 * author : kangtaegyung
 * date : 2022/10/12
 * description : Board 클래스
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/12         kangtaegyung          최초 생성
 */
@Entity
@Table(name="TB_BOARD")
@SequenceGenerator(
        name = "SQ_BOARD_GENERATOR"
        , sequenceName = "SQ_BOARD"
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
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SQ_BOARD_GENERATOR"
    )
    @Column(columnDefinition = "NUMBER")
    private Integer bid;
    @Column(columnDefinition = "VARCHAR2(255)")
    private String boardTitle;
    @Column(columnDefinition = "VARCHAR2(255)")
    private String boardWriter;
    @Column(columnDefinition = "NUMBER")
    private Integer viewCnt;
}





















