package com.ane.dops.web.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by seven on 17-11-16.
 */
public class FileTest {
    //private static ArrayList<String> filelist = new ArrayList<String>();

    /**
     *
     * @param fileNameMap 存储文件名称和路径的map
     * @param path 文件生成的路径  例：/home/seven/read/a/
     * @return
     */
    public static List<String> writeCsv(Map<String, String> fileNameMap,String path){
        ArrayList<String> filelist = new ArrayList<String>();
        for(Map.Entry<String,String> map : fileNameMap.entrySet()){
            filelist.add(map.getValue());
        }
        return print(filelist,path);
    }

    /*
    public static void main(String[] args) throws Exception {

        String filePath = "/home/seven/read/a";
        getFiles(filePath);
        for(String s : filelist){
            System.out.println(s);
        }

        print(filelist,filePath+"/");

        filelist.clear();
    }


    static void getFiles(String filePath){//通过递归得到某一路径下所有的目录及其文件
        File root = new File(filePath);
        File[] files = root.listFiles();
        for(File file:files){
            if(file.isDirectory()){

      //  递归调用


                getFiles(file.getAbsolutePath());
                System.out.println("显示"+filePath+"下所有子目录及其文件"+file.getAbsolutePath());
            }else{
                if(file.getAbsolutePath().indexOf("xlsx")!=-1 || file.getAbsolutePath().indexOf("xlsm")!=-1
                        || file.getAbsolutePath().indexOf("xls")!=-1){
                    filelist.add(file.getAbsolutePath());
                }
                System.out.println("显示"+filePath+"下所有子目录"+file.getAbsolutePath());
            }
        }
    }
    */

    /**
     *
     * @param filelist  选定文件下所有的合法文件
     * @param path      新文件目录   例：/home/seven/read/
     */
    public static List<String> print(ArrayList<String> filelist,String path){
        List<String> strings = new ArrayList<String>();
        List<String> list = new ArrayList<String>();
        for(String s : filelist){
            String str = "";
            strings = toSplit(s);
            if(strings.get(1).indexOf("路由")!=-1){

                str = ExcelUtils.saveByRoutePlan(strings.get(0),path,strings.get(2));
            }
            if(strings.get(1).indexOf("菜鸟指数得分")!=-1){

                String[] splits = strings.get(2).split("\\.");
                str = ExcelUtils.saveCaiNiaoRecord_dailyInput(strings.get(0),path,strings.get(2),Integer.valueOf(splits[splits.length-1]));
            }
            if(strings.get(1).indexOf("网点交接件")!=-1){
                str = ExcelUtils.saveSitePckupSendTimePlan(strings.get(0),path,strings.get(2));
            }
            if(!"".equals(str)){
                list.add(str);
            }
            if(strings.get(1).indexOf("网点资料")!=-1){
                List<String> stringList = ExcelUtils.saveNetworkPoint(strings.get(0),path,strings.get(2));
                if(stringList != null){
                    for(String ss : stringList){
                        list.add(ss);
                    }
                }
            }
        }
        return list;
    }



    public static List<String> toSplit(String str){
        List<String> strings = new ArrayList<String>();
        String[] splits = str.split("/");
        strings.add(str);//文件全路径
        //strings.add(str.substring(0,str.length()-splits[splits.length-1].length()));
        String s = splits[splits.length-1].substring(0,splits[splits.length-1].length()-5);
        strings.add(s);//源文件名
        String[] split = s.split("-");
        strings.add(split[split.length-1]);//日期
        return strings;
    }
}
