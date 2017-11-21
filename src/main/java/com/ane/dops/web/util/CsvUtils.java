package com.ane.dops.web.util;

import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by seven on 17-11-8.
 */
public class CsvUtils {
    /**
     * 菜鸟得分
     * @param lists   数据
     * @param number  生成几天数据
     * @param path    生成文件路径
     * @param name    生成文件名字
     */
    public static String createCSV(List<List<List<String>>> lists,int number,String path ,String name) {
        path = path+"caiNiaoRecord_dailyInput/";
        name = "caiNiaoRecord_dailyInput-"+name+".csv";
        // 表格头
        Object[] head = { "统计日期", "类型", "省区" , "指标", "目标值", "保底值" , "权重", "当日得分", "月累计得分"};
        List<Object> headList = Arrays.asList(head);

        //数据
        List<List<Object>> dataList = new ArrayList<List<Object>>();
        List<Object> rowList = null;
        for(int k = 0 ; k < number ; k++){
            for(int m = 0 ; m < lists.size() ; m++){
                List<List<String>> listList = lists.get(m);

                //全国
                rowList = new ArrayList<Object>();
                //统计日期
                rowList.add(listList.get(0).get(8+k*4));
                if(m == 0){
                    rowList.add("投诉评价");//类型
                    rowList.add(listList.get(18).get(0));//省区
                    rowList.add("虚假签收投诉率");//指标
                }else if(m == 1){
                    rowList.add("信息质量");//类型
                    rowList.add(listList.get(18).get(0));//省区
                    rowList.add("信息完整率");//指标
                }else{
                    rowList.add("信息质量");//类型
                    rowList.add(listList.get(18).get(0));//省区
                    rowList.add("信息及时率");//指标
                }
                rowList.add(saveDouble2(listList.get(18).get(1))+"%");//目标值
                rowList.add(saveDouble2(listList.get(18).get(2))+"%");//保底值
                rowList.add(saveDouble2(listList.get(18).get(3))+"%");//权重
                rowList.add(saveDouble(listList.get(18).get(11+k*4)));//当日得分
                rowList.add(saveDouble(listList.get(18).get(listList.get(18).size()-1)));//月累计得分


                dataList.add(rowList);
                //省区
                for(int i = 3 ; i < 18 ; i++){
                    rowList = new ArrayList<Object>();
                    //统计日期
                    rowList.add(listList.get(0).get(8+k*4));
                    if(m == 0){
                        rowList.add("投诉评价");//类型
                        rowList.add(listList.get(i).get(0));//省区
                        rowList.add("虚假签收投诉率");//指标
                    }else if(m == 1){
                        rowList.add("信息质量");//类型
                        rowList.add(listList.get(i).get(0));//省区
                        rowList.add("信息完整率");//指标
                    }else{
                        rowList.add("信息质量");//类型
                        rowList.add(listList.get(i).get(0));//省区
                        rowList.add("信息及时率");//指标
                    }
                    rowList.add(saveDouble2(listList.get(i).get(1))+"%");//目标值
                    rowList.add(saveDouble2(listList.get(i).get(2))+"%");//保底值
                    rowList.add(saveDouble2(listList.get(i).get(3))+"%");//权重
                    rowList.add(saveDouble(listList.get(i).get(11+k*4)));//当日得分
                    rowList.add(saveDouble(listList.get(i).get(listList.get(i).size()-1)));//月累计得分
                    dataList.add(rowList);
                }
            }
        }
        //写文件
        write(headList,path,name,dataList);
        return path+name;
    }


    /**
     * 网点明细
     * @param lists
     * @param path
     * @param name
     */
    public static String saveSiteRelations(List<List<String>> lists,String path ,String name) {
        path = path+"siteRelations/";
        name = "siteRelations-"+name+".csv";
        // 表格头
        Object[] head = { "网点", "网点编号", "网点性质" , "所属网点", "片区", "分拨" , "大区", "省区"};
        List<Object> headList = Arrays.asList(head);

        //数据
        List<List<Object>> dataList = new ArrayList<List<Object>>();
        List<Object> rowList = null;
        for(List<String> list : lists){
            rowList = new ArrayList<Object>();
            rowList.add(list.get(0));//网点
            rowList.add(conversion(list.get(1)));//网点编号
            rowList.add(list.get(2));//网点性质
            rowList.add(list.get(3));//所属网点
            rowList.add(list.get(4));//片区
            rowList.add(list.get(5));//分拨
            rowList.add(list.get(6));//大区
            rowList.add(list.get(7));//省区
            dataList.add(rowList);
        }
        write(headList,path,name,dataList);
        return path+name;
    }

    /**
     * 路由
     * @param lists
     * @param path
     * @param name
     */
    public static String saveRoutePlan(List<List<String>> lists,String path ,String name) {
        path = path+"routePlan/";
        name = "routePlan-"+name+".csv";
        // 表格头
        Object[] head = {"始发省区","路由名称","中转分拨","货区","到达2","到达3","到达4","到达5","目的分拨","走货路由新","第二路由","车线备注","最晚发车时间"};
        List<Object> headList = Arrays.asList(head);

        //数据
        List<List<Object>> dataList = new ArrayList<List<Object>>();
        List<Object> rowList = null;
        for(List<String> list : lists){
            rowList = new ArrayList<Object>();
            rowList.add(list.get(0));//始发省区
            rowList.add((list.get(1)));//路由名称
            rowList.add(list.get(2));//中转分拨
            rowList.add(list.get(3));//货区
            rowList.add(list.get(4));//到达2
            rowList.add(list.get(5));//到达3
            rowList.add(list.get(6));//到达4
            rowList.add(list.get(7));//到达5
            rowList.add(list.get(8));//目的分拨
            rowList.add(toRoutePlan(list.get(2),list.get(3),list.get(4),list.get(5),list.get(6),list.get(7)));//走货路由新
            rowList.add(list.get(9));//第二路由
            rowList.add(list.get(10));//车线备注
            if(list.get(11).length() > 10){//最晚发车时间
                rowList.add(differentDays(list.get(11))+"日"+list.get(11).substring(11,list.get(11).length()));
            }else{
                rowList.add(list.get(11));
            }
            dataList.add(rowList);
        }
        write(headList,path,name,dataList);
        return path+name;
    }

    /**
     * 分拨-分拨群
     * @param lists
     * @param path
     * @param name
     */
    public static String saveTcRelations_tcTcGroup(List<List<String>> lists,String path ,String name) {
        path = path+"tcRelations_tcTcGroup/";
        name = "tcRelations_tcTcGroup-"+name+".csv";
        // 表格头
        Object[] head = { "分拨", "分拨群"};
        List<Object> headList = Arrays.asList(head);

        //数据
        List<List<Object>> dataList = new ArrayList<List<Object>>();
        List<Object> rowList = null;
        for(List<String> list : lists){
            rowList = new ArrayList<Object>();
            rowList.add(list.get(5));//分拨
            rowList.add(list.get(6));//分拨群
            dataList.add(rowList);
        }
        write(headList,path,name,dataList);
        return path+name;
    }

    /**
     * 分拨-大区
     * @param lists
     * @param path
     * @param name
     */
    public static String saveTcRelations_tcBigAreaSpecial(List<List<String>> lists,String path ,String name) {
        path = path+"tcRelations_tcBigAreaSpecial/";
        name = "tcRelations_tcBigAreaSpecial-"+name+".csv";

        // 表格头
        Object[] head = { "分拨", "大区"};
        List<Object> headList = Arrays.asList(head);

        //数据
        List<List<Object>> dataList = new ArrayList<List<Object>>();
        List<Object> rowList = null;
        for(List<String> list : lists){
            if("".equals(list.get(0))){
                break;
            }else{
                rowList = new ArrayList<Object>();
                rowList.add(list.get(0));//分拨
                rowList.add(list.get(1));//大区
                dataList.add(rowList);
            }
        }
        write(headList,path,name,dataList);
        return path+name;
    }

    /**
     * 快递网点交接件时间表
     * @param lists
     * @param path
     * @param name
     */
    public static String saveSitePckupSendTimePlan(List<List<String>> lists,String path ,String name) {
        path = path+"sitePckupSendTimePlan/";
        name = "sitePckupSendTimePlan-"+name+".csv";
        // 表格头
        Object[] head =
                { "省区", "分拨", "网点名称" , "网点编号", "网点距中心距离（km)", "交件时间" , "一派截止扫描时间", "二派截止扫描时间", "接货周期","", "派送时效"};
        List<Object> headList = Arrays.asList(head);
        //数据
        List<List<Object>> dataList = new ArrayList<List<Object>>();
        List<Object> rowList = null;
        for(int i = 2 ; i < lists.size() ; i++){
            List<String> list = lists.get(i);
            rowList = new ArrayList<Object>();
            rowList.add(list.get(0));//省区
            rowList.add(list.get(1));//分拨
            rowList.add(list.get(2));//网点名称
            rowList.add(conversion(list.get(3)));//网点编号
            rowList.add(conversion(list.get(4)));//网点距中心距离（km)
            rowList.add(isFlag(list.get(10)));//交件时间
            rowList.add(isFlag(list.get(13)));//一派截止扫描时间
            rowList.add(isFlag(list.get(16)));//二派截止扫描时间
            rowList.add(list.get(17));//接货周期
            rowList.add(list.get(18));//
            rowList.add(list.get(19));//派送时效
            dataList.add(rowList);
        }
        write(headList,path,name,dataList);
        return path+name;
    }

    /**
     *
     * @param headList  头文件
     * @param path      文件路径
     * @param name      文件名称
     * @param dataList  文件内容
     */
    public static void write(List<Object> headList,String path ,String name,List<List<Object>> dataList){
        String fileName = name;//文件名称
        String filePath = path; //文件路径
        File csvFile = null;
        BufferedWriter csvWtriter = null;
        try {
            csvFile = new File(filePath + fileName);
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();
            // UTF-8使正确读取分隔符","
            csvWtriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "UTF-8"), 1024);
            //文件下载，使用如下代码
            //            response.setContentType("application/csv;charset=gb18030");
            //            response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            //            ServletOutputStream out = response.getOutputStream();
            //            csvWtriter = new BufferedWriter(new OutputStreamWriter(out, "GB2312"), 1024);

            int num = headList.size() / 2;
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < num; i++) {
                buffer.append("\t");
            }
            //csvWtriter.write(buffer.toString() + fileName + buffer.toString());
            //csvWtriter.newLine();

            // 写入文件头部
            writeRow(headList, csvWtriter);

            // 写入文件内容
            for (List<Object> row : dataList) {
                writeRow(row, csvWtriter);
            }
            csvWtriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvWtriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 写一行数据
     * @param row 数据列表
     * @param csvWriter
     * @throws IOException
     */
    private static void writeRow(List<Object> row, BufferedWriter csvWriter) throws IOException {
        for (Object data : row) {
            StringBuffer sb = new StringBuffer();
            String rowStr = sb.append("").append(data).append("\t").toString();
            csvWriter.write(rowStr);
        }
        csvWriter.newLine();
    }

    /**
     *
     * @param str  String -->  double   0.00
     * @return
     */
    public static double saveDouble(String str){
        double num = Double.valueOf(str);
        BigDecimal b   =   new   BigDecimal(num);
        return b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     *
     * @param str  String -->  double   0.000
     * @return
     */
    public static double saveDouble2(String str){
        double num = Double.valueOf(str)*100;
        BigDecimal b   =   new   BigDecimal(num);
        return b.setScale(3,   BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static String isFlag(String str){
        if(str.indexOf("1899-12-31")!=-1){
            str = str.substring(11);
            return str.substring(0,str.length()-3);
        }else {
            return str;
        }
    }

    public static String conversion(String str){
        if(str.indexOf(".")!=-1){
            return str.substring(0,str.length()-2);
        }else {
            return str;
        }
    }

    /**
     *
     * @param str
     * @return
     */

    public static int differentDays(String str) {
        str = str.substring(0,10);
        int num = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = sdf.parse("1899-12-31");
            Date date2 = sdf.parse(str);

            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);

            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date2);
            int day1 = cal1.get(Calendar.DAY_OF_YEAR);
            int day2 = cal2.get(Calendar.DAY_OF_YEAR);

            int year1 = cal1.get(Calendar.YEAR);
            int year2 = cal2.get(Calendar.YEAR);
            if (year1 != year2) {   //同一年
                int timeDistance = 0;
                for (int i = year1; i < year2; i++) {
                    if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {    //闰年
                        timeDistance += 366;
                    } else {    //不是闰年
                        timeDistance += 365;
                    }
                }
                num = timeDistance + (day2 - day1);
            } else {
                num = day2 - day1;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return num;
    }

    public static String toRoutePlan(String s1,String s2,String s3,String s4,String s5,String s6){
        String str = "";
        if(!"".equals(s1)){
            str = str + s1;
        }
        if(!"".equals(s2)){
            str = str +"-"+ s2;
        }
        if(!"".equals(s3)){
            str = str +"-"+ s3;
        }
        if(!"".equals(s4)){
            str = str +"-"+ s4;
        }
        if(!"".equals(s5)){
            str = str +"-"+ s5;
        }
        if (!"".equals(s6)){
            str = str +"-"+ s6;
        }
        return str;
    }

    public static void main(String[] args) {
        System.out.println(differentDays("1899-12-31 18:10:00"));
    }
}

