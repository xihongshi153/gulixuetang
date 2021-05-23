package com.atguigu.demo.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class TestEasyExcel {
    public static void main(String[] args) {


        //实现excel的写操作
        //1 设置地址
        String filename="D:\\BaiduNetdiskDownload\\write.xlsx";
        //2调用写操作
        //路径名称 实体类名称
        /*EasyExcel.write(filename, DemoData.class).sheet("学生列表").doWrite(getData());*/

        //读操作
        EasyExcel.read(filename,DemoData.class,new ExcelListener()).sheet().doRead();
    }

    private static List<DemoData> getData(){
        List<DemoData> list=new ArrayList<>();
        for(int i=0;i<10;i++){
            DemoData data=new DemoData();
            data.setSno(i);
            data.setSname("lucy"+i);
            list.add(data);
        }
        return list;
    }
}
