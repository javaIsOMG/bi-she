package com.dayup.seckil.service;

import com.dayup.seckil.entity.po.Student;
import com.dayup.seckil.entity.po.User;
import com.dayup.seckil.entity.vo.PasswordVo;
import com.dayup.seckil.entity.vo.Result;
import com.dayup.seckil.entity.vo.StudentVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * @Classname StudentService
 * @Description TODO
 * @Date 2020/1/2 15:11
 * @Created by Yinghao.He
 */
public interface StudentService {
    Result<Student> getStudentInfo(User user);

    Result uploadFile(MultipartFile multipartFile,User user) throws IOException;

    Result setInfo(Student student, User user,String time) throws ParseException;

    Result getStudentList(int page,int size);
    Result getStudentList(int page,int size,String name);

    Result deleteByStudentNum(String studentNum);

    Result addInfo(StudentVo studentVo) throws ParseException;

    Result getStudentInfoByStudentNum(String studentNum);

    Result deleteByStudentNumList(List<String> numList);
}
