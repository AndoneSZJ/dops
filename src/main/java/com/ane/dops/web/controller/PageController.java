package com.ane.dops.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 视图控制器,返回jsp视图给前端
 * Created by hcia on 2017/11/16.
 */
@Controller
public class PageController {

    /**
     * dashboard页
     */
    @RequestMapping("/index2")
    public String index2() {
        return "index2";
    }

    /**
     * dashboard页
     */
    @RequestMapping("/upload")
    public String index3(HttpServletRequest request,
                         HttpServletResponse response) {
        // 获取上传文件的目录
        ServletContext sc = request.getSession().getServletContext();
        // 上传位置
        //String uploadFilePath = sc.getRealPath("I:/upload") + "/"; // 设定文件保存的目录
        String uploadFilePath = "I:/upload"; // 设定文件保存的目录
        // 存储要下载的文件名
        Map<String, String> fileNameMap = new HashMap<String, String>();
        // 递归遍历filepath目录下的所有文件和目录，将文件的文件名存储到map集合中
        listfile(new File(uploadFilePath), fileNameMap);// File既可以代表一个文件也可以代表一个目录
        // 将Map集合发送到listfile.jsp页面进行显示
        request.setAttribute("fileNameMap", fileNameMap);
        return "upload";
    }

    private void listfile(File file, Map<String, String> fileNameMap) {
        File [] lists = file.listFiles();
        if(lists!=null)
        {
            for(int i=0;i<lists.length;i++)
            {
                fileNameMap.put(lists[i].getName(),(new Date(lists[i].lastModified())).toString());
            }
        }
    }

    /**
     * 读取文件创建时间
     */
    public static String getCreateTime(String filePath){
//        String filePath = "C:\\test.txt";
        String strTime = null;
        try {
            Process p = Runtime.getRuntime().exec("cmd /C dir "
                    + filePath
                    + "/tc" );
            InputStream is = p.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while((line = br.readLine()) != null){
                if(line.endsWith(".txt")){
                    strTime = line.substring(0,17);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  strTime;
        //输出：创建时间   2009-08-17  10:21
    }

    /**
     * 读取文件修改时间的方法1
     */
    @SuppressWarnings("deprecation")
    public static void getModifiedTime_1(){
        File f = new File("C:\\test.txt");
        Calendar cal = Calendar.getInstance();
        long time = f.lastModified();
        cal.setTimeInMillis(time);
        //此处toLocalString()方法是不推荐的，但是仍可输出
        System.out.println("修改时间[1] " + cal.getTime().toLocaleString());
        //输出：修改时间[1]    2009-8-17 10:32:38
    }

}
