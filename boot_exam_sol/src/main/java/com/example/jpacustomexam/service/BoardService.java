package com.example.jpacustomexam.service;

import com.example.jpacustomexam.model.Board;
import com.example.jpacustomexam.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.modelexam.service
 * fileName : BoardService
 * author : kangtaegyung
 * date : 2022/10/12
 * description : Board 업무 서비스 클래스
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/12         kangtaegyung          최초 생성
 */
// springboot 프레임워크에 객체를 생성함 : 싱글톤 유형
@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository; // 샘플데이터 DB에 접근하는 객체

    public Page<Board> findAllByOrderByInsertTimeDesc(Pageable pageable) {
        Page<Board> list = boardRepository.findAllByOrderByInsertTimeDesc(pageable);

        return list;
    }

    public Optional<Board> findById(int dno) {
        Optional<Board> optionalBoard = boardRepository.findById(dno);

        return optionalBoard;
    }

    public Board save(Board board) {

        Board board2 = boardRepository.save(board);

        return board2;
    }

    public boolean removeById(int id) {

        if (boardRepository.existsById(id)) {
            boardRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Page<Board> findAllByBoardTitleContainingOrderByInsertTimeDesc(String boardTitle, Pageable pageable) {

        Page<Board> page = boardRepository.findAllByBoardTitleContainingOrderByInsertTimeDesc(boardTitle, pageable);

        return page;
    }
}






















