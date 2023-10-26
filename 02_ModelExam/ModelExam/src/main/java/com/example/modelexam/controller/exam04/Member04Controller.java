package com.example.modelexam.controller.exam04;

import com.example.modelexam.model.Member;
import com.example.modelexam.service.exam04.Member04Service;
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
@RequestMapping("/exam04")
public class Member04Controller {
    @Autowired
    Member04Service memberService;
    @GetMapping("/member")
    public String getMemberAll(Model model) {
        List<Member> list = memberService.findAll();
        model.addAttribute("list", list);

//        TODO : 로깅
        log.debug(list.toString());

        return "/exam04/member/member_all.jsp";
    }

    @GetMapping("/member/{eno}")
    public String getMemberID(@PathVariable long eno, Model model) {
//        TODO : 멤버서비스 상세호출
        Member member = memberService.findByID(eno);
        model.addAttribute("member", member);
        return "/exam04/member/member_id.jsp";
    }


//    TODO : 새로운 회원 추가 페이지 이동 함수
    @GetMapping("/member/addition")
    public String addMember() {
        return "/exam04/member/add_member.jsp";
    }

//    TODO : db저장 함수
    @PostMapping("/member/add")
//    TODO : jsp 객체 전달 : @ModelAttribute, react 객체 전달 : @RequestBody
    public RedirectView createMember(@ModelAttribute Member member) {
        memberService.save(member);
        return new RedirectView("/exam04/member");
    }


}
