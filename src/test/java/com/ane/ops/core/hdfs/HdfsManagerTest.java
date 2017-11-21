package com.ane.ops.core.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by hcia on 2017/11/21.
 */
public class HdfsManagerTest {



    //创建新文件
        public static void createFile(String dst , byte[] contents) throws IOException {
           Configuration conf = new Configuration();
             FileSystem fs = FileSystem.get(conf);
             Path dstPath = new Path(dst); //目标路径
            //打开一个输出流
            FSDataOutputStream outputStream = fs.create(dstPath);
             outputStream.write(contents);
             outputStream.close();
             fs.close();
            System.out.println("文件创建成功！");
    }



    @Test
    public void test() throws Exception {
       // createFile()
    }











    @org.junit.Before
    public void setUp() throws Exception {
        // 构造一个配置参数对象，设置一个参数：我们要访问的hdfs的URI

        // 从而FileSystem.get()方法就知道应该是去构造一个访问hdfs文件系统的客户端，以及hdfs的访问地址

        // new Configuration();的时候，它就会去加载jar包中的hdfs-default.xml

        // 然后再加载classpath下的hdfs-site.xml


    }

    @org.junit.After
    public void tearDown() throws Exception {

    }
}