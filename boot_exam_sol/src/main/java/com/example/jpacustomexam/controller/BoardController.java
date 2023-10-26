package com.example.jpacustomexam.controller;

import com.example.jpacustomexam.model.Board;
import com.example.jpacustomexam.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * packageName : com.example.modelexam.controller
 * fileName : DeptController
 * author : kangtaegyung
 * date : 2022/10/12
 * description : Board 컨트롤러
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/12         kangtaegyung          최초 생성
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class BoardController {

    @Autowired
    BoardService boardService;

    //    URL 매개변수를 각각 속성이름으로 전달
    @GetMapping("/boards/paging")
//    URL 테스트 : http://localhost:8000/api/boards/paging?page=0&size=15
    public ResponseEntity<Object> getByDesc(Pageable pageable) {

        try {
            Page<Board> page = boardService.findAllByOrderByInsertTimeDesc(pageable);

            Map<String, Object> response = new HashMap<>();

            response.put("boards", page.getContent());
            response.put("currentPage", page.getNumber());
            response.put("totalItems", page.getTotalElements());
            response.put("totalPages", page.getTotalPages());

            if (page.isEmpty() == false) {
//                성공
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
//            서버 에러
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    URL 매개변수를 각각 속성이름으로 전달
    @GetMapping("/boards/{bid}")
//    URL 테스트 : http://localhost:8000/api/boards/1
    public ResponseEntity<Object> getBoardId(@PathVariable int bid) {

        try {
            Optional<Board> optionalBoard = boardService.findById(bid);

            if (optionalBoard.isPresent()) {
//                성공
                return new ResponseEntity<>(optionalBoard.get(), HttpStatus.OK);
            } else {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
//            서버 에러
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //  테스트 데이터 : bid 생략 해야 insert 됨
    @PostMapping("/boards")
    public ResponseEntity<Object> createBoard(@RequestBody Board board) {

        try {
            Board board2 = boardService.save(board);

            return new ResponseEntity<>(board2, HttpStatus.OK);
        } catch (Exception e) {
//            DB 에러가 났을경우 : INTERNAL_SERVER_ERROR 프론트엔드로 전송
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //  수정 실행 후 첫페이지로 이동
    @PutMapping("/boards/edit/{bid}")
    public ResponseEntity<Object> updateBoard(@PathVariable int bid, @RequestBody Board board) {

        try {
            Board board2 = boardService.save(board);

            return new ResponseEntity<>(board2, HttpStatus.OK);

        } catch (Exception e) {
//            DB 에러가 났을경우 : INTERNAL_SERVER_ERROR 프론트엔드로 전송
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //  수정 실행 후 첫페이지로 이동
    @DeleteMapping("/boards/delete/{bid}")
    public ResponseEntity<Object> deleteBoard(@PathVariable int bid) {

//        프론트엔드 쪽으로 상태정보를 보내줌
        try {
            boolean bSuccess = boardService.removeById(bid);

            if (bSuccess == true) {
//                delete 문이 성공했을 경우
                return new ResponseEntity<>(HttpStatus.OK);
            }
//            delete 실패했을 경우( 0건 삭제가 될경우 )
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
//            DB 에러가 날경우
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    1) 쿼리메소드 사용하는 방법
    //    쿼리메소드 사용
//   URL 테스트 : http://localhost:8000/api/boards/title/제/paging?page=0&size=15
    @GetMapping("/boards/title/{title}")
    public ResponseEntity<Object> getByBoardTitleContainingDesc(@PathVariable String title
                                                            , Pageable pageable) {
        try {
            Page<Board> page = boardService.findAllByBoardTitleContainingOrderByInsertTimeDesc(title, pageable);

            Map<String, Object> response = new HashMap<>();

            response.put("boards", page.getContent());
            response.put("currentPage", page.getNumber());
            response.put("totalItems", page.getTotalElements());
            response.put("totalPages", page.getTotalPages());

            if (page.isEmpty() == false) {
//                성공
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
//            서버 에러
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

















