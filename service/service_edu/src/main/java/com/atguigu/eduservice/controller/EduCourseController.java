package com.atguigu.eduservice.controller;
import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-05-23
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin//解决跨域问题
public class EduCourseController {
    @Autowired
    private EduCourseService courseService;
    @Autowired
    private EduChapterService chapterService;
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        //返回添加之后的课程id值
        String id= courseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId",id);
    }
    //根据课程查询课程基本信息
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId){
        CourseInfoVo courseInfoVo = courseService.getCourseInfo(courseId);
        return R.ok().data("courseInfoVo",courseInfoVo);
    }
    //修改课程信息
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){

        courseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }
    //根据章节id查询
    @GetMapping("getChapterInfo/{chapterId}")
    public R getChapterInfo(@PathVariable String chapterId){
        EduChapter eduChapter = chapterService.getById(chapterId);
        return R.ok().data("chapter",eduChapter);
    }
    //修改章节
    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter ){
        chapterService.updateById(eduChapter);
        return R.ok();
    }
    //删除的方法
    @DeleteMapping("{chapterId}")
    public R deleteChapter(@PathVariable String chapterId){
       Boolean flag = chapterService.deleteChapter(chapterId);
       if(flag){
           return R.ok();
       }else {
           return R.error();
       }
    }
}

