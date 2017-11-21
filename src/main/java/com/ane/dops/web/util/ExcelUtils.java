package com.ane.dops.web.util;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by seven on 2017/4/28.
 * 导入Excel表格并解析
 */
public class ExcelUtils {
    private static final Logger LOG = LoggerFactory.getLogger(ExcelUtils.class);

    /**
     * 解析Excel
     * 封装表格对象,返回一个List<List<String>>类型的集合
     *
     * @param input 导入表格对象流
     * @param start 所选定的开始sheet
     * @param end   所选定的结束sheet
     * @return 数据集合
     */
    public static List<List<String>> readExcel(InputStream input,int start,int end) {
        List<List<String>> lists = new ArrayList<List<String>>();

        //boolean isE2007 = false;
        // 判断是否是excel2007格式
        //if (path.endsWith("xlsx")) {
        //    isE2007 = true;
        //}

        // 准备workbook
        // 同时支持Excel 2003、2007
        Workbook workbook = null;

        try {
            // 文件流
            // FileInputStream input = new FileInputStream(path);
            // 根据文件格式(2003或者2007)来初始化
            // if (isE2007) {
            workbook = new XSSFWorkbook(input);
            //} else {
            //  workbook = new HSSFWorkbook(input);
            //}
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // 循环工作表
        for (int numSheet = start; numSheet <= end; numSheet++) {
            Sheet sheet = workbook.getSheetAt(numSheet);
            if (sheet == null) {
                continue;
            }
            // 循环行
            for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row == null) {
                    continue;
                }

                // 将单元格中的内容存入集合
                List<String> list = new ArrayList<String>();

                Cell cell;
                //循环列
                for (int i = 0; i < row.getLastCellNum(); i++) {
                    cell = row.getCell(i);
                    if (isMergedRegion(sheet, rowNum, i)) {
                        list.add(getMergedRegionValue(sheet, rowNum, i));
                    } else {
                        list.add(getCellValue(cell));
                    }
                }
                lists.add(list);
            }
        }
        return lists;
    }


    public static List<List<String>> readExcelByRoutePlan(InputStream input,int start,int end) {
        List<List<String>> lists = new ArrayList<List<String>>();

        //boolean isE2007 = false;
        // 判断是否是excel2007格式
        //if (path.endsWith("xlsx")) {
        //    isE2007 = true;
        //}

        // 准备workbook
        // 同时支持Excel 2003、2007
        Workbook workbook = null;

        try {
            // 文件流
            // FileInputStream input = new FileInputStream(path);
            // 根据文件格式(2003或者2007)来初始化
            // if (isE2007) {
            workbook = new XSSFWorkbook(input);
            //} else {
            //  workbook = new HSSFWorkbook(input);
            //}
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // 循环工作表
        for (int numSheet = start; numSheet <= end; numSheet++) {
            Sheet sheet = workbook.getSheetAt(numSheet);
            if (sheet == null) {
                continue;
            }
            // 循环行
            for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row == null) {
                    continue;
                }

                // 将单元格中的内容存入集合
                List<String> list = new ArrayList<String>();

                Cell cell;
                //循环列
                for (int i = 0; i < 9; i++) {
                    cell = row.getCell(i);
                    if (isMergedRegion(sheet, rowNum, i)) {
                        list.add(getMergedRegionValue(sheet, rowNum, i));
                    } else {
                        list.add(getCellValue(cell));
                    }
                }
                for (int i = 10; i < row.getLastCellNum(); i++) {
                    cell = row.getCell(i);
                    if (isMergedRegion(sheet, rowNum, i)) {
                        list.add(getMergedRegionValue(sheet, rowNum, i));
                    } else {
                        list.add(getCellValue(cell));
                    }
                }
                lists.add(list);
            }
        }
        return lists;
    }


    /**
     * 菜鸟得分
     * @param input
     * @param start sheet开始列
     * @param end   sheet结束列
     * @return
     */
    public static List<List<List<String>>> readExcelArrayList(InputStream input,int start,int end) {
        List<List<List<String>>> arrayList = new ArrayList<List<List<String>>>();
        end = end*4 + 8;

        //boolean isE2007 = false;
        // 判断是否是excel2007格式
        //if (path.endsWith("xlsx")) {
        //    isE2007 = true;
        //}

        // 准备workbook
        // 同时支持Excel 2003、2007
        Workbook workbook = null;

        try {
            // 文件流
            // FileInputStream input = new FileInputStream(path);
            // 根据文件格式(2003或者2007)来初始化
            // if (isE2007) {
            workbook = new XSSFWorkbook(input);
            //} else {
            //  workbook = new HSSFWorkbook(input);
            //}
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // 循环工作表   从9-11三个sheet
        for (int numSheet = 9; numSheet < 12; numSheet++) {
            List<List<String>> lists = new ArrayList<List<String>>();
            Sheet sheet = workbook.getSheetAt(numSheet);

            if (sheet == null) {
                continue;
            }
            // 循环行
            for (int rowNum = 2; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row == null) {
                    continue;
                }

                // 将单元格中的内容存入集合
                List<String> list = new ArrayList<String>();

                Cell cell;
                //循环列
                for (int i = start; i < end; i++) {
                    cell = row.getCell(i);
                    if (isMergedRegion(sheet, rowNum, i)) {
                        list.add(getMergedRegionValue(sheet, rowNum, i));
                    } else {
                        list.add(getCellValue(cell));
                    }
                }

                cell = row.getCell(row.getLastCellNum()-1);
                if (isMergedRegion(sheet, rowNum, row.getLastCellNum()-1)) {
                    list.add(getMergedRegionValue(sheet, rowNum, row.getLastCellNum()-1));
                } else {
                    list.add(getCellValue(cell));
                }

                lists.add(list);
            }
            arrayList.add(lists);
        }
        return arrayList;
    }


    /**
     * 获取合并单元格的值
     *
     * @param sheet
     * @param row
     * @param column
     * @return
     */
    public static String getMergedRegionValue(Sheet sheet, int row, int column) {

        int sheetMergeCount = sheet.getNumMergedRegions();

        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();

            if (row >= firstRow && row <= lastRow) {
                if (column >= firstColumn && column <= lastColumn) {
                    Row fRow = sheet.getRow(firstRow);
                    Cell fCell = fRow.getCell(firstColumn);
                    return getCellValue(fCell);
                }
            }
        }
        return null;
    }

    /**
     * 判断指定的单元格是否是合并单元格
     *
     * @param sheet
     * @param row
     * @param column
     * @return
     */
    public static boolean isMergedRegion(Sheet sheet, int row, int column) {

        int sheetMergeCount = sheet.getNumMergedRegions();

        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();

            if (row >= firstRow && row <= lastRow) {
                if (column >= firstColumn && column <= lastColumn) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取单元格的值
     *
     * @param cell
     * @return
     */
    public static String getCellValue(Cell cell) {

        if (cell == null) return "";

        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {

            return cell.getStringCellValue();

        } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {

            return String.valueOf(cell.getBooleanCellValue());

        } else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {

            return String.valueOf(cell.getNumericCellValue());

        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            String result = null;
            if (HSSFDateUtil.isCellDateFormatted(cell) || cell.getCellStyle().getDataFormat() == 179) {// 处理日期格式、时间格式
                SimpleDateFormat sdf = null;
                if (cell.getCellStyle().getDataFormat() == HSSFDataFormat
                        .getBuiltinFormat("h:mm")) {
                    sdf = new SimpleDateFormat("HH:mm");
                } else {// 日期
                    sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                }
                Date date = cell.getDateCellValue();
                result = sdf.format(date);
            } else if (cell.getCellStyle().getDataFormat() == 58) {
                // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                double value = cell.getNumericCellValue();
                Date date = DateUtil
                        .getJavaDate(value);
                result = sdf.format(date);
            } else {
                double value = cell.getNumericCellValue();
//                CellStyle style = cell.getCellStyle();
//                DecimalFormat format = new DecimalFormat();
//                String temp = style.getDataFormatString();
//                // 单元格设置成常规
//                if (temp.equals("General")) {
//                    format.applyPattern("#");
//                }
//                result = format.format(value);
                result = String.valueOf(cell.getNumericCellValue());
            }
            return result;
            //return String.valueOf(cell.getNumericCellValue());
        }
        return "";
    }

    /**
     * 处理菜鸟得分
     * @param sourcePath  源文件路径    /home/seven/read/ceshi.xlsx
     * @param path  生成新文件路径       /home/seven/read/
     * @param name  新文件名称           test
     * @param num  获取文件天数
     */
    public static String saveCaiNiaoRecord_dailyInput(String sourcePath,String path,String name,int num){
        try {
            InputStream is = new FileInputStream(new File(sourcePath).getAbsolutePath());

            List<List<List<String>>> lists = readExcelArrayList(is,0,num);
            String str = CsvUtils.createCSV(lists,num,path,name);

            LOG.info("caiNiaoRecord_dailyInput is SUCCESS");
            System.out.println("caiNiaoRecord_dailyInput is SUCCESS");
            return str;
        } catch (FileNotFoundException e) {
            LOG.info(e.toString());
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 网点明细、分拨-分拨群、分拨-大区
     * @param sourcePath  源文件全路径
     * @param path        新文件路径
     * @param name        文件名
     */
    public static List<String> saveNetworkPoint(String sourcePath,String path,String name){
        List<String> stringList = new ArrayList<String>();
        List<List<String>> listList = null;
        List<List<String>> list = null;
        try {
            listList = ExcelUtils.readExcel(new FileInputStream(sourcePath),3,3);
            list = ExcelUtils.readExcel(new FileInputStream(sourcePath),6,6);

            //网点明细
            String str1 = CsvUtils.saveSiteRelations(listList,path,name);
            LOG.info("siteRelations is SUCCESS");
            System.out.println("siteRelations is SUCCESS");
            //分拨-大区
            String str2 = CsvUtils.saveTcRelations_tcBigAreaSpecial(list,path,name);
            LOG.info("tcRelations_tcBigAreaSpecial is SUCCESS");
            System.out.println("tcRelations_tcBigAreaSpecial is SUCCESS");
            //分拨-分拨群
            String str3 = CsvUtils.saveTcRelations_tcTcGroup(list,path,name);
            stringList.add(str1);
            stringList.add(str2);
            stringList.add(str3);
            LOG.info("tcRelations_tcTcGroup is SUCCESS");
            System.out.println("tcRelations_tcTcGroup is SUCCESS");
            return stringList;
        } catch (FileNotFoundException e) {
            LOG.info(e.toString());
            e.printStackTrace();
            return null;
        }


    }

    /**
     * 网点交接件时间表
     * @param sourcePath  源文件全路径
     * @param path        新文件路径
     * @param name        文件名
     */
    public static String saveSitePckupSendTimePlan(String sourcePath,String path,String name){
        try {
            List<List<String>> listList = ExcelUtils.readExcel(new FileInputStream(sourcePath),4,4);
            String str = CsvUtils.saveSitePckupSendTimePlan(listList,path,name);
            LOG.info("sitePckupSendTimePlan is SUCCESS");
            System.out.println("sitePckupSendTimePlan is SUCCESS");
            return str;
        } catch (FileNotFoundException e) {
            LOG.info(e.toString());
            e.printStackTrace();
            return "";
        }
    }


    /**
     * 路由表
     * @param sourcePath  源文件全路径
     * @param path        新文件路径
     * @param name        文件名
     */
    public static String saveByRoutePlan(String sourcePath,String path,String name){
        try {
            List<List<String>> listList = ExcelUtils.readExcelByRoutePlan(new FileInputStream(sourcePath),1,1);
            String str = CsvUtils.saveRoutePlan(listList,path,name);
            LOG.info("routePlan is SUCCESS");
            System.out.println("routePlan is SUCCESS");
            return str;
        } catch (FileNotFoundException e) {
            LOG.info(e.toString());
            e.printStackTrace();
            return "";
        }
    }

    public static void main(String[] args) {
        //saveCaiNiaoRecord_dailyInput("/home/seven/read/ceshi.xlsx","/home/seven/read/","test",7);
         //saveNetworkPoint("/home/seven/read/test/11.14快递网点资料.xlsx","/home/seven/read/test/","11-15-test");
       // saveSitePckupSendTimePlan("/home/seven/read/全国快递网点交接件时间区域表11.3.xlsx","/home/seven/read/test/","11-15-test");
    }
}
