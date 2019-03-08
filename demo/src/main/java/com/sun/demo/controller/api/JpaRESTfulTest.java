package com.sun.demo.controller.api;

import com.sun.demo.base.WebRespone;
import com.sun.demo.dao.StudentInfoDao;
import com.sun.demo.dao.UserDao;
import com.sun.demo.entiy.StudentInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api")
public class JpaRESTfulTest {


    @Autowired
    private StudentInfoDao studentInfoDao;


    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public WebRespone Hello(){
        return WebRespone.success("OJBK", studentInfoDao.findAll());
    }

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public WebRespone postUser(@RequestParam(value = "stuName") String stuName) {
        StudentInfoEntity stu = new StudentInfoEntity();
        stu.setStuName(stuName);
        studentInfoDao.save(stu);
        return WebRespone.success("OJBK", "新增成功");
    }

    @RequestMapping(value = "/student/{stuNum}", method = RequestMethod.GET)
    public WebRespone getUser(@RequestParam(value = "stuNum") long stuNum) {
        return WebRespone.success("OJBK", studentInfoDao.findOne(stuNum));
    }

    @RequestMapping(value = "/student/{stuNum}/{stuName}", method = RequestMethod.PUT)
    public WebRespone putUser(@RequestParam(value = "stuNum") long stuNum,@RequestParam(value = "stuName") String stuName) {
        StudentInfoEntity stu = new StudentInfoEntity();
        stu.setStuNum((int) stuNum);
        stu.setStuName(stuName);
        studentInfoDao.save(stu);
        return WebRespone.success("OJBK", "更新成功");
    }

    @RequestMapping(value = "/student/{stuNum}", method = RequestMethod.DELETE)
    public WebRespone deleteUser(@RequestParam(value = "stuNum")Long stuNum) {
        studentInfoDao.delete(stuNum);
        return WebRespone.success("OJBK", "删除成功");
    }


    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public WebRespone userDao(){
        return WebRespone.success("OJBK", userDao.findAll());
    }


}
