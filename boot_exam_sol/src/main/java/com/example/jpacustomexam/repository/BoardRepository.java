package com.example.jpacustomexam.repository;

import com.example.jpacustomexam.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName : com.example.jpaexam.repository
 * fileName : BoardRepository
 * author : kangtaegyung
 * date : 2022/10/16
 * description : JPA CRUD 인터페이스 ( DAO 역할 )
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/16         kangtaegyung          최초 생성
 */
@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {

//    전체 검색 최신순
    Page<Board> findAllByOrderByInsertTimeDesc(Pageable pageable);
//    제목 검색
    Page<Board> findAllByBoardTitleContainingOrderByInsertTimeDesc(String boardTitle, Pageable pageable);
}














