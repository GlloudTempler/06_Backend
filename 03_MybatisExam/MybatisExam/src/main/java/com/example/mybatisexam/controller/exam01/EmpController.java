package com.example.mybatisexam.controller.exam01;

import com.example.mybatisexam.model.common.PageReq;
import com.example.mybatisexam.model.common.PageRes;
import com.example.mybatisexam.model.vo.Emp;
import com.example.mybatisexam.service.exam01.DeptService;
import com.example.mybatisexam.service.exam01.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

/**
 * packageName : com.example.mybatisexam.controller.exam01
 * fileName : EmpController
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
@Controller
@RequestMapping("/exam01")
public class EmpController {
    @Autowired
    EmpService empService;
    /* 전체 조회 : ename like 기능 (+) */
//    TODO : @ReauestParam - url?변수=값&변수2=값2 (쿼리스트링 방식)

    @GetMapping("/emp")
    public String getEmpAll(
            @RequestParam(defaultValue = "") String ename,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            Model model

    ) {
        PageReq pageReq = new PageReq(page, size);

        PageRes<Emp> pageRes = empService.findByEnameContaining(ename,pageReq);
        model.addAttribute("emp", pageRes.getContent());
        model.addAttribute("currentPage", pageRes.getNumber());
        model.addAttribute("totalItems", pageRes.getTotalElements());
        model.addAttribute("totalPages", pageRes.getTotalPages());
        model.addAttribute("startPage", pageRes.getStartPage());
        model.addAttribute("endPage", pageRes.getEndPage());

        log.debug(model.toString());

        return "exam01/emp/emp_all.jsp";
    }

    @GetMapping("/emp/{eno}")
    public String getEmpID(@PathVariable int eno, Model model) {
        Optional<Emp> optionalEmp = empService.findByID(eno);
        model.addAttribute("emp", optionalEmp.get());
        return "exam01/emp/emp_id.jsp";
    }

    @GetMapping("/emp/addition")
    public String addEmp() { return "exam01/emp/add_emp.jsp";}

    @PostMapping("/emp/add")
    public RedirectView createEmp(@ModelAttribute Emp emp) {
        empService.save(emp);
        return new RedirectView("/exam01/emp");
    }

    @GetMapping("/emp/edition/{eno}")
    public String editEmp(@PathVariable int eno, Model model) {
        Optional<Emp> optionalEmp = empService.findByID(eno);
        model.addAttribute("emp", optionalEmp.get());
        return "exam01/emp/update_emp.jsp";
    }

    @PutMapping("/emp/edit/{eno}")
    public RedirectView updateEmp(@PathVariable int eno, @ModelAttribute Emp emp) {
        empService.save(emp);
        return new RedirectView("/exam01/emp");
    }

    @DeleteMapping("emp/delete/{eno}")
    public RedirectView deleteEmp(@PathVariable int eno) {
        empService.removeByID(eno);
        return new RedirectView("/exam01/emp");
    }
}
