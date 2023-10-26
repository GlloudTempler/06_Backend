package com.example.modelexam.service.exam02;

import com.example.modelexam.dao.MemberDao;
import com.example.modelexam.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName : com.example.modelexam.service.exam01
 * fileName : MemberService
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
@Service
public class Member02Service {
    @Autowired
    MemberDao memberDao;

    public List<Member> findAll() {
        List<Member> list = memberDao.selectAll();
        return list;
    }

    public Member findByID(long eno) {
        Member member = memberDao.selectById(eno);
        return member;
    }

}
