package com.example.jpacustomexam.service.exam04;

import com.example.jpacustomexam.model.exam04.Department;
import com.example.jpacustomexam.model.exam04.Employee;
import com.example.jpacustomexam.repository.exam04.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName : com.example.jpacustomexam.service.exam04
 * fileName : DepartmentService
 * author : GGG
 * date : 2023-10-19
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2023-10-19         GGG          최초 생성
 */
@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

//    단방향 조인 예제
    public Page<Employee> selectJoinPage2(Pageable pageable) {
        Page<Employee> page = departmentRepository.selectJoinPage2(pageable);

        return page;
    }

//    양방향 조인 예제 : fetch join
    public List<Department> selectFetchJoin() {
        List<Department> page = departmentRepository.selectFetchJoin();

        return page;
    }
}
