package com.dayup.seckil.service.serviceImpl;

import com.dayup.seckil.entity.po.Student;
import com.dayup.seckil.entity.po.User;
import com.dayup.seckil.entity.vo.*;
import com.dayup.seckil.repository.StudentRepository;
import com.dayup.seckil.service.StudentService;
import com.dayup.seckil.service.UserService;
import com.dayup.seckil.util.OSSUtil;
import com.dayup.seckil.util.PageUtil;

import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @Classname StudentServiceImpl
 * @Description TODO
 * @Date 2020/1/2 15:11
 * @Created by Yinghao.He
 */
@Service
@Transactional
@Slf4j
public class StudentServiceImpl implements StudentService {
    @Autowired
    private UserService userService;
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Result<Student> getStudentInfo(User user) {
        @NotBlank(message = "用户名不能为空") String username = user.getUsername();
        Student student = studentRepository.findByUserName(username);
        if (student==null){
            log.info("用户名出问题");
            return Result.failure();
        }
        return Result.success(student);
    }

    @Override
    public Result uploadFile(MultipartFile multipartFile, User user) throws IOException {
        String s = OSSUtil.saveFile(multipartFile);
        if (s==null){
            return Result.failure();
        }
        int i=studentRepository.setByUserName(user.getUsername(),s);
        if (i<=0){
            return Result.failure();
        }
        return Result.success();
    }

    @Override
    public Result setInfo(Student student, User user,String time) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = formatter.parse(time);
        student.setBirthTime(date);
        int i=studentRepository.setInfoByUserName(student,user.getUsername());
        if (i<=0){
            log.info("修改详细信息失败");
            return Result.failure();
        }
        log.info("修改详细信息成功");
        return Result.success();
    }

    @Override
    public Result getStudentList(int page,int size) {
        Pageable pageable=PageRequest.of(page-1,size,Sort.by(Sort.Direction.DESC,"birthTime"));
        Page<Student> studentPage = studentRepository.findAll(pageable);
        PageVo pageVo = PageUtil.StartPage(studentPage, size, page);
        if (pageVo==null){
            log.info("分页失败");
            return Result.failure();
        }
        log.info("分页查询成功");
        return Result.success(pageVo);
    }

    @Override
    public Result getStudentList(int page, int size, String name) {
        Pageable pageable=PageRequest.of(page-1,size,Sort.by(Sort.Direction.DESC,"birthTime"));
        Page<Student> studentPage = studentRepository.findAll(new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate=criteriaBuilder.conjunction();
                if(true){
                    if(StringUtils.isNotEmpty(name)){
                        predicate.getExpressions().add(criteriaBuilder.like(root.get("studentName"), "%"+name+"%"));
                    }
                }
                return predicate;
            }
        }, pageable);
        PageVo pageVo = PageUtil.StartPage(studentPage, size, page);
        if (pageVo==null){
            log.info("模糊查询失败");
            return Result.failure();
        }
        log.info("模糊查询成功");
        return Result.success(pageVo);
    }

    @Override
    public Result deleteByStudentNum(String studentNum) {
        int result=studentRepository.deleteByStudentNumber(studentNum);
        if (result<=0){
            log.info("删除失败");
            return Result.failure(ResultCode.FAIL_DELETE);
        }
        log.info("删除成功");
        return Result.success();
    }

    @Override
    public Result addInfo(StudentVo studentVo) throws ParseException {
        String time = studentVo.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = formatter.parse(time);
        Student student = new Student();
        BeanUtils.copyProperties(studentVo,student);
        student.setBirthTime(date);
        User user = new User();
        user.setUsername(studentVo.getUserName());
        user.setPassword(studentVo.getPassword());
        User registered = userService.registered(user);
        if (registered==null){
            return Result.failure(ResultCode.USERNAME_EXISTED_ERROR);
        }
        Student saveAndFlush = studentRepository.saveAndFlush(student);
        if (saveAndFlush==null){
            return Result.failure(ResultCode.REGISTER_FAIL);
        }
        return Result.success();
    }

    @Override
    public Result getStudentInfoByStudentNum(String studentNum) {
        Student student=studentRepository.findByStudentNumber(studentNum);
        if (student==null){
            return Result.failure();
        }
        return Result.success(student);
    }

    @Override
    public Result deleteByStudentNumList(List<String> numList) {
        if (numList.isEmpty()){
            return Result.failure();
        }
        int list=studentRepository.deleteListByStudentNumber(numList);
        if (list<=0){
            return Result.failure();
        }
        return Result.success(list);
    }


}
