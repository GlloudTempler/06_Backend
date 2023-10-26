package com.example.modelexam.controller.exam05;

import com.example.modelexam.model.Dept;
import com.example.modelexam.model.Member;
import com.example.modelexam.service.exam05.Member05Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

/**
 * packageName : com.example.modelexam.controller.exam01
 * fileName : MemberController
 * author : GGG
 * date : 2023-10-10
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2023-10-10         GGG          최초 생성
 */
@Slf4j
@Controller
@RequestMapping("/exam05")
public class Member05Controller {
    @Autowired
    Member05Service memberService;
    @GetMapping("/member")
    public String getMemberAll(Model model) {
        List<Member> list = memberService.findAll();
        model.addAttribute("list", list);

//        TODO : 로깅
        log.debug(list.toString());

        return "/exam05/member/member_all.jsp";
    }

    @GetMapping("/member/{eno}")
    public String getMemberID(@PathVariable long eno, Model model) {
//        TODO : 멤버서비스 상세호출
        Member member = memberService.findByID(eno);
        model.addAttribute("member", member);
        return "/exam05/member/member_id.jsp";
    }


//    TODO : 새로운 회원 추가 페이지 이동 함수
    @GetMapping("/member/addition")
    public String addMember() {
        return "/exam05/member/add_member.jsp";
    }

//    TODO : db저장 함수
    @PostMapping("/member/add")
//    TODO : jsp 객체 전달 : @ModelAttribute, react 객체 전달 : @RequestBody
    public RedirectView createMember(@ModelAttribute Member member) {
        memberService.save(member);
        return new RedirectView("/exam05/member");
    }

//    TODO : 수정 페이지 열기 :
    @GetMapping("/member/edition/{eno}")
    public String editMember(@PathVariable long eno, Model model) {
//        TODO 1) : 상세 조회
        Member member = memberService.findByID(eno);
        model.addAttribute("member", member);
        return "exam05/member/update_member.jsp";
    }

//    TODO : 수정 저장 : 리다이렉트(강제 이동) : 전체조회페이지로 이동
    @PutMapping("/member/edit/{eno}")
    public RedirectView updateMember(
            @PathVariable long eno,
            @ModelAttribute Member member
    ) {
//        TODO : 수정 저장 함수 호출
        memberService.save(member);
        return new RedirectView("/exam05/member");
    }

}
