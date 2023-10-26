package com.example.jpacustomexam.controller;

import com.example.jpacustomexam.model.Comment;
import com.example.jpacustomexam.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.modelexam.controller
 * fileName : DeptController
 * author : kangtaegyung
 * date : 2022/10/12
 * description : 회원 컨트롤러
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/12         kangtaegyung          최초 생성
 */
@Slf4j
@RestController
@RequestMapping("/api/boards")
public class CommentController {

    @Autowired
    CommentService commentService;

    //    URL 매개변수를 각각 속성이름으로 전달
    @GetMapping("/comment")
    public ResponseEntity<Object> getCommentAll() {

        try {
            List<Comment> list = commentService.findAll();

            if (list.isEmpty() == false) {
//                성공
                return new ResponseEntity<>(list, HttpStatus.OK);
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
//    @GetMapping("/comment/{cid}")
////    URL 테스트 : http://localhost:8000/exam05/dept
//    public ResponseEntity<Object> getCommentId(@PathVariable int cid) {
//
//        try {
//            Optional<Comment> optionalComment = commentService.findById(cid);
//
//            if (optionalComment.isPresent()) {
////                성공
//                return new ResponseEntity<>(optionalComment.get(), HttpStatus.OK);
//            } else {
////                데이터 없음
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//        } catch (Exception e) {
////            서버 에러
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }


    //    URL 매개변수를 각각 속성이름으로 전달
    @GetMapping("/comment/{bid}")
    public ResponseEntity<Object> getCommentBid(@PathVariable int bid) {

        try {
            List<Comment> list = commentService.findAllByBid(bid);

            if (list.isEmpty() == false) {
//                성공
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
//            서버 에러
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //  테스트 데이터 : dno 생략 해야 insert 됨
//    {
//        "dname": "Production",
//        "loc": "Pusan"
//    }

    @PostMapping("/comment")
    public ResponseEntity<Object> createComment(@RequestBody Comment comment) {

        try {
            Comment comment2 = commentService.save(comment);

//                ResponseEntity<>(매개변수객체, 상태정보)
            return new ResponseEntity<>(comment2, HttpStatus.CREATED);

        } catch (Exception e) {
//            DB 에러가 났을경우 : INTERNAL_SERVER_ERROR 프론트엔드로 전송
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //  수정 실행 후 첫페이지로 이동
    @PutMapping("/comment/edit/{cid}")
    public ResponseEntity<Object> updateComment(@PathVariable int cid, @RequestBody Comment comment) {

        try {
            Comment comment2 = commentService.save(comment);

            return new ResponseEntity<>(comment2, HttpStatus.OK);
        } catch (Exception e) {
//            DB 에러가 났을경우 : INTERNAL_SERVER_ERROR 프론트엔드로 전송
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //  수정 실행 후 첫페이지로 이동
    @DeleteMapping("/comment/delete/{cid}")
    public ResponseEntity<Object> deleteComment(@PathVariable int cid) {

//        프론트엔드 쪽으로 상태정보를 보내줌
        try {
            boolean bSuccess = commentService.removeById(cid);

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

}

















