package com.yc.interview.io;

import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * File可以表示一个文件或者文件夹（通过isFlie（）和isDirectory（）区别）
 * 一系列方法可以查询文件信息 略 注意并未涉及到修改文件里面的内容，修改内容就需要使用IO流了
 * 以下为文件夹的专属方法
 * list（） 获取指定文件夹下文件的目录的名称数组
 * listFils（） 获取指定文件夹下的File数组
 *
 * public boolean renameTo(File dest):把文件重命名为指定的文件路劲
 * 操作前，常先用exist（）方法确认是否存在
 *
 * 利用Flie在磁盘中创建和删除文件：
 * boolean createNewFile（）：若文件存在，则不创建返回false
 * boolean delete（）：删除发文件或文件夹（注意java中的删除不走回收站）
 *
 * 利用Flie在磁盘中创建和删除目录
 * mkdir（） ：上层目录不在，就不创建
 * mkdirs（）：上层目录存在，就一并创建
 */
public class FlieTest {

    public static void main(String[] args) {
        test01();
    }

    /**
     * * list（） 获取指定文件夹下文件的目录的名称数组
     *  * listFils（） 获取指定文件夹下的File数组
     */
    public static void test01(){
        File file = new File("D:\\app\\log");
        File[] files = file.listFiles();
        for (File file1 : files) {
            System.out.println(file1.getName());
        }
    }
}
