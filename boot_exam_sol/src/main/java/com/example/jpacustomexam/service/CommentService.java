package com.example.jpacustomexam.service;

import com.example.jpacustomexam.model.Comment;
import com.example.jpacustomexam.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.modelexam.service.exam01
 * fileName : MemberService
 * author : kangtaegyung
 * date : 2022/10/14
 * description : Comment 업무 서비스 클래스
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/14         kangtaegyung          최초 생성
 */
@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository; // 샘플데이터 DB에 접근하는 객체

    public List<Comment> findAll() {
        List<Comment> list = commentRepository.findAll();

        return list;
    }

//    public Optional<Comment> findById(int cid) {
//        Optional<Comment> optionalMember = commentRepository.findById(cid);
//
//        return optionalMember;
//    }

    public List<Comment> findAllByBid(int bid) {
        List<Comment> list = commentRepository.findAllByBid(bid);

        return list;
    }

    public Comment save(Comment comment) {

        Comment comment2 = commentRepository.save(comment);

        return comment2;
    }

    public boolean removeById(int id) {

        if(commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
