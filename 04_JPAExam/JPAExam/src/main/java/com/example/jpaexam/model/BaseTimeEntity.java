package com.example.jpaexam.model;

import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * packageName : com.example.jpaexam.model
 * fileName : BaseTimeEntity
 * author : GGG
 * date : 2023-10-16
 * description : JPA 에서 자동으로 생성일자/수정일자를 만들어 주는 클래스
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2023-10-16         GGG          최초 생성
 */
@Getter
// todo: 자동으로 생성일자/수정일자 컬럼을 sql 문에 추가시키는 어노테이션 2개
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {
//    todo: 공통속성 : yyyy-MM-dd HH:mm:ss 아니고 기본 패턴으로 보임
    private String insertTime;

    private String updateTime;

//    todo: 해당 테이블에 데이터가 만들어 질때(insert 문) 실행되는 이벤트 함수
    @PrePersist
    void OnPrePersist() {
        this.insertTime
                = LocalDateTime.now()
                    .format(DateTimeFormatter
                            .ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

//    todo: 해당 테이블에 데이터가 수정 질때(update 문) 실행되는 이벤트 함수
    @PreUpdate
    void OnPreUpdate() {
        this.updateTime
                = LocalDateTime.now()
                .format(DateTimeFormatter
                        .ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.insertTime = this.updateTime; // 생성일시 == 수정일시 동일하게 처리
    }
}
