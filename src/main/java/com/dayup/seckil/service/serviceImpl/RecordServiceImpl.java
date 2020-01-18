package com.dayup.seckil.service.serviceImpl;

import com.dayup.seckil.entity.dto.RecordDto;
import com.dayup.seckil.entity.dto.SemesterDto;
import com.dayup.seckil.entity.po.Record;
import com.dayup.seckil.entity.po.Student;
import com.dayup.seckil.entity.po.User;
import com.dayup.seckil.entity.vo.Result;
import com.dayup.seckil.entity.vo.ResultCode;
import com.dayup.seckil.repository.RecordRepository;
import com.dayup.seckil.service.RecordService;
import com.dayup.seckil.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname RecordServiceImpl
 * @Description TODO
 * @Date 2020/1/15 9:49
 * @Created by Yinghao.He
 */
@Service
@Slf4j
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordRepository recordRepository;
    @Autowired
    private StudentService studentService;

    @Override
    public Result getRecord(User user) {
        log.info("调用用户成绩的所以成绩");
        Result<Student> studentInfo = studentService.getStudentInfo(user);
        if (studentInfo.getCode().equals(Result.failure().getCode())) {
            return studentInfo;
        }
        Student data = studentInfo.getData();
        String studentNumber = data.getStudentNumber();
        List<Record> records = recordRepository.finAllByStudentNum(studentNumber);
        if (records.isEmpty()) {
            return Result.success();
        }
        List<SemesterDto> semesterDtoList = getSmesterDto(records);
        return Result.success(semesterDtoList);
    }

    @Override
    public Result getRecordByNum(String studentNum) {
        List<Record> records = recordRepository.finAllByStudentNum(studentNum);
        if (records.isEmpty()) {
            return Result.success();
        }
        List<SemesterDto> semesterDtoList = getSmesterDto(records);
        log.info("获取成绩");
        return Result.success(semesterDtoList);
    }

    @Override
    public Result deleteById(Integer id) {
        recordRepository.deleteById(id);
        log.info("删除指定课程");
        return Result.success();
    }

    @Override
    public Result uploadById(Integer id, User user, RecordDto recordDto) {
        recordDto.setCreator(user.getUsername());
        Record record = getRecordByDto(recordDto);
        record.setId(id);
        int i=recordRepository.uploadByRecord(record);
        if (i<0){
            return Result.failure();
        }
        return Result.success();
    }

    @Override
    public Result addRecord(String studentNum, User user, RecordDto recordDto) {
        recordDto.setCreator(user.getUsername());
        Record record = getRecordByDto(recordDto);
        Integer semesterYears = record.getSemesterYears();
        Integer semesterType = record.getSemesterType();
        String courseName = record.getCourseName();
        Record result=recordRepository.findBySemesterYearsAndSemesterTypeAndCourseName(semesterYears,semesterType,courseName);
        if (result!=null){
            return Result.failure(ResultCode.RECORD_IS_CZ);
        }
        Result studentInfoByStudentNum = studentService.getStudentInfoByStudentNum(studentNum);
        if (studentInfoByStudentNum.getCode().equals(500)){
            return studentInfoByStudentNum;
        }
        Student data = (Student) studentInfoByStudentNum.getData();
        String studentName = data.getStudentName();
        record.setStudentNum(studentNum);
        record.setStudentName(studentName);
        Record flush = recordRepository.saveAndFlush(record);
        if (flush==null){
            return Result.failure(ResultCode.UPLOAD_FAIL);
        }
        log.info("新增成绩成功");
        return Result.success();
    }
    private Record getRecordByDto(RecordDto recordDto){
        Record record = new Record();
        BeanUtils.copyProperties(recordDto,record);
        return record;
    }


    private List<SemesterDto> getSmesterDto(List<Record> records) {
        Integer semesterYears = records.get(0).getSemesterYears();
        Integer semesterType = records.get(0).getSemesterType();
        List<SemesterDto> objects = new ArrayList<>();
        AddSmester(records, semesterYears, objects, semesterType);
        return objects;
    }

    private void AddSmester(List<Record> records, Integer semesterYears, List<SemesterDto> objects, Integer semesterType) {
        SemesterDto<Record> objectSemesterDto=new SemesterDto<>();
        int total=0;
        List<Record> recordList=new ArrayList<>();
        List<Record> list=new ArrayList<>();
        list.addAll(records);
        for (Record record :records) {
            if (record.getSemesterYears().equals(semesterYears)&&record.getSemesterType().equals(semesterType)){
                recordList.add(record);
                total=total+record.getActualStudyScore();
                list.remove(record);
            }else{
                AddSmester(list,record.getSemesterYears(),objects,record.getSemesterType());
                break;
            }
        }
        objectSemesterDto.setTotalScore(total);
        objectSemesterDto.setRecordList(recordList);
        if (semesterType==1){
            objectSemesterDto.setSemesterCN(semesterYears+"下学期");
        }else {
            objectSemesterDto.setSemesterCN(semesterYears + "上学期");
        }
        objects.add(objectSemesterDto);
    }
}
