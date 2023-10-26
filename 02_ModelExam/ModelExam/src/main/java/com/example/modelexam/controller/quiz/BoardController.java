package com.example.modelexam.controller.quiz;

import com.example.modelexam.model.Board;
import com.example.modelexam.service.quiz.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.modelexam.controller.quiz
 * fileName : BoardController
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
@Slf4j
@RestController
@RequestMapping("/quiz")
public class BoardController {
// todo: 종합문제 1) 부서 업무 클래스들을 참고하여
//       BoardController 클래스를 만들어서 CRUD 에 해당하는 컨틀롤러 함수를 정의하세요
//       단, @RestController 를 사용하고,
//       예외처리와 ResponseEntity 를 사용해 데이터와 메세지를 같이 전송하세요
//       url : board 이용 (기본키 : id)
    @Autowired
    BoardService boardService;

    @GetMapping("/board")
    public ResponseEntity<Object> getBoardAll() {
        try {
            List<Board> list = boardService.findAll();
            return new ResponseEntity<>(list, HttpStatus.OK);
        }catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/board/{id}")
    public ResponseEntity<Object> getBoardID(@PathVariable int id) {
        try {
            Optional<Board> optionalBoard = boardService.findByID(id);
            if(!optionalBoard.isEmpty()){
                return new ResponseEntity<>(optionalBoard, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        }catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/member")
    public ResponseEntity<Object> updateBoard(@RequestBody Board board) {
        try {
            List<Board> list = boardService.save(board);
                return new ResponseEntity<>(list, HttpStatus.OK);
        }catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/board/edit/{id}")
    public ResponseEntity<Object> updateBoard(
            @PathVariable int id,
            @RequestBody Board board
    ) {
        try {
            List<Board> list = boardService.save(board);
            return new ResponseEntity<>(list, HttpStatus.OK);
        }catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/board/delete/{id}")
    public ResponseEntity<Object> deleteBoard(@PathVariable int id) {
        try {
            Boolean rSuccess = boardService.removeByID(id);
            if(rSuccess){
                return new ResponseEntity<>(HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
