package com.example.jpacustomexam.repository.exam04;

import com.example.jpacustomexam.model.exam04.Department;
import com.example.jpacustomexam.model.exam04.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName : com.example.jpacustomexam.repository.exam04
 * fileName : DepartmentRepository
 * author : GGG
 * date : 2023-10-19
 * description : 부서 레포지토리 (CRUD)
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2023-10-19         GGG          최초 생성
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
//    단방향 조인
    @Query(value = "select e from Employee e inner join e.department d ")
    Page<Employee> selectJoinPage2(Pageable pageable);

//    양방향 조인 : fetch join -> Paging 안됨
    @Query(value = "select distinct d from Department d inner join fetch d.employee e ")
    List<Department> selectFetchJoin();
}
