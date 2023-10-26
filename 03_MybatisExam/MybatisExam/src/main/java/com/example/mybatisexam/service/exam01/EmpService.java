package com.example.mybatisexam.service.exam01;

import com.example.mybatisexam.dao.EmpDao;
import com.example.mybatisexam.model.common.PageReq;
import com.example.mybatisexam.model.common.PageRes;
import com.example.mybatisexam.model.vo.Emp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.mybatisexam.service.exam01
 * fileName : EmpService
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
@Service
public class EmpService {
    @Autowired
    private EmpDao empDao;

    public PageRes<Emp> findByEnameContaining(String ename, PageReq pageReq) {
        List<Emp> list = empDao.findByEnameContaining(ename, pageReq);
        long totalCount = empDao.countByEname(ename);
        PageRes pageRes = new PageRes(
                list,
                pageReq.getPage(),
                totalCount,
                pageReq.getSize()
        );
        return pageRes;
    }

    public Optional<Emp> findByID(int eno) {
        Optional<Emp> optionalEmp = empDao.findByID(eno);
        return optionalEmp;
    }

    public int save(Emp emp) {
        int queryResult = -1;
        try{
            if(emp.getEno() == null) {
                queryResult = empDao.insert(emp);
            }else {
                queryResult = empDao.update(emp);
            }

        }catch (Exception e) {
            log.debug(e.getMessage());
        }
        return queryResult;
    }

    public boolean removeByID(int eno) {
        try{
            if(empDao.existByID(eno) > 0) {
                empDao.deleteByID(eno);
                return true;
            }
        }catch (Exception e){
            log.debug(e.getMessage());
        }
        return false;
    }

    public PageRes<Emp> findByDynamicContaining(String ename,
                                                String job,
                                                Integer manager,
                                                PageReq pageReq) {
        List<Emp> list = empDao.findByDynamicContaining(ename, job, manager, pageReq);

        long totalCount = empDao.countByDynamic(ename, job, manager);

        PageRes pageRes = new PageRes(
                list,
                pageReq.getPage(),
                totalCount,
                pageReq.getSize()
        );

        return pageRes;
    }
}
