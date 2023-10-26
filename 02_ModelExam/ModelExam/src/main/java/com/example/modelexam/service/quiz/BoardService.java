package com.example.modelexam.service.quiz;

import com.example.modelexam.dao.BoardDao;
import com.example.modelexam.model.Board;
import com.example.modelexam.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.modelexam.service.quiz
 * fileName : BoardService
 * author : GGG
 * date : 2023-10-12
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2023-10-12         GGG          최초 생성
 */
@Service
public class BoardService {
    @Autowired
    BoardDao boardDao;

    public List<Board> findAll() {
        List<Board> list = boardDao.selectAll();
        return list;
    }

    public Optional<Board> findByID(int id) {
        Board board = boardDao.selectById(id);
        Optional<Board> optionalBoard = Optional.ofNullable(board);
        return optionalBoard;
    }

    public List<Board> save(Board board) {
        List<Board> list = null;
        if(board.getId() == null){
            int count = boardDao.selectAll().size();
            int newId = (count + 1);
            board.setId(newId);
            list = boardDao.insert(board);
        }else{
            boardDao.update(board);
        }
        return list;
    }

    public boolean removeByID(int id) {
        int iCount = boardDao.deleteById(id);
        return (iCount > 0)? true:false;
    }
}
