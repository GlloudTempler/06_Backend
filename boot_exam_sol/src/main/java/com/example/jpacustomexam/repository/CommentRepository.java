package com.example.jpacustomexam.repository;

import com.example.jpacustomexam.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName : com.example.jpaexam.repository
 * fileName : CommentRepository
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
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByBid(int bid);
}



















