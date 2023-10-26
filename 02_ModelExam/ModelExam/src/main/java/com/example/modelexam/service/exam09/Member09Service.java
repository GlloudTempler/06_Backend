package com.example.modelexam.service.exam09;

import com.example.modelexam.dao.MemberDao;
import com.example.modelexam.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
public class Member09Service {
    @Autowired
    MemberDao memberDao;

    public List<Member> findAll() {
        List<Member> list = memberDao.selectAll();
        return list;
    }

    public Optional<Member> findByID(long eno) {
        Member member = memberDao.selectById(eno);
        Optional<Member> optionalMember = Optional.ofNullable(member);
        return optionalMember;
    }

    public List<Member> save(Member member) {

        List<Member> list = null;
//        TODO : ui(frontend) -> insert (사원번호가 없으면)
        if(member.getEno() == null) {
//            TODO : 새로운 사원번호 생성
            int count = memberDao.selectAll().size();
            int newEno = (count + 8000);
            member.setEno((newEno));
//          TODO : db 저장
            list= memberDao.insert(member);
        } else {
            memberDao.update(member);
        }
        return list;
    }

    public boolean removeByID(int eno) {
        int iCount = memberDao.deleteById(eno);
        return (iCount > 0)? true:false;
    }

}
