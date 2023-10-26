package com.example.mybatisexam.dao;

import com.example.mybatisexam.model.common.PageReq;
import com.example.mybatisexam.model.vo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.mybatisexam.dao
 * fileName : EmpDao
 * author : GGG
 * date : 2023-10-12
 * description : 사원 Dao(Mybatis mapper : @Mapper 사용)
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2023-10-12         GGG          최초 생성
 */
@Mapper
public interface EmpDao {
    public List<Emp> findByEnameContaining(@Param("ename") String ename, PageReq pageReq);
    long countByEname(String ename);
    Optional<Emp> findByID(int eno);

    int insert(Emp emp);

    int update(Emp emp);

    int deleteByID(int eno);

    long existByID(int eno);

    /* TODO : dynamic SQL 조회 */
    public List<Emp> findByDynamicContaining(
            @Param("ename") String ename,
            @Param("job") String job,
            @Param("manager") Integer manager,
            PageReq pageReq
    );

    /* TODO : dynamic SQL count 조회 */
    public long countByDynamic(String ename,
                        @Param("job") String job,
                        @Param("manager") Integer manager
    );
}
