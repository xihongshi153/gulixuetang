package com.atguigu.eduservice.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChapterVo {
    private String title;
    private String id;
    //表示小结
    private List<VideoVo> children=new ArrayList<>();
}
