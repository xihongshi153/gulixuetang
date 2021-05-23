package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.controller.vo.TeacherQuery;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduTeacherService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-05-11
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin//解决跨域问题
public class EduTeacherController {

    //注入service
    @Autowired
    private EduTeacherService teacherService;

    //1查询所有
    //rest风格
    //访问地址： http://localhost:8001/eduservice/edu-teacher/findAll
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public R findAllTeacher(){
        //调用service方法
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("items",list);
    }

    //2逻辑删除讲师
    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("{id}")
    public R removeTeacher(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id) {
        boolean flag = teacherService.removeById(id);
        if (flag) {
            return R.ok();
        } else{
            return R.error();
        }

    }
    //3分页查询
    /*
    current 当前页  limit 一页的数量
    * */
    @ApiOperation( value = "分页查询")
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@PathVariable long current,
                             @PathVariable long limit){
        //创建page对象
        Page<EduTeacher> pageTeacher=new Page<>(current,limit);//当前页  一页的数量
        //调用方法实现分页
        //
        teacherService.page(pageTeacher,null);
        long total=pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();//数据list集合

        Map map=new HashMap();
        map.put("total",total);
        map.put("rows",records);

        //return R.ok().data("total",total).data("rows",records);
        return R.ok().data(map);
    }
    //条件查询+分页
    @ApiOperation( value = "分页查询+条件")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current,@PathVariable long limit,
                                    @RequestBody(required = false) TeacherQuery teacherQuery){
        Page<EduTeacher> pageTeacher=new Page<>(current,limit);
        //构建条件
        QueryWrapper<EduTeacher> wrapper=new QueryWrapper<>();
        //多条件组合查询
        //判断条件值是否为空，如果不为空拼接条件
        String name=teacherQuery.getName();
        Integer level=teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if(!StringUtils.isEmpty(name)){
            //构建条件
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)){
            //构建条件
            wrapper.like("level",level);
        }
        if(!StringUtils.isEmpty(begin)){
            //构建条件
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)){
            //构建条件
            wrapper.le("gmt_create",end);
        }
        wrapper.orderByDesc("gmt_create");
        teacherService.page(pageTeacher,wrapper);
        long total=pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();//数据list集合
        Map map=new HashMap();
        map.put("total",total);
        map.put("rows",records);

        //return R.ok().data("total",total).data("rows",records);
        return R.ok().data(map);
    }
    //添加讲师接口的方法
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = teacherService.save(eduTeacher);
        if(save){
        return R.ok();
    }else{
            return R.error();
        }
    }
    //根据讲师id进行查询
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable String id){
        EduTeacher eduTeacher = teacherService.getById(id);
        return R.ok().data("teacher",eduTeacher);
    }
    //讲师修改功能
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean flag = teacherService.updateById(eduTeacher);
        if(flag){
        return R.ok();
    }else {
        return R.error();
        }
    }
}





