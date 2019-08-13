package com.learn.system.util.excel;


import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: ExcelUtil
 * @Author: WindPursuer
 * @Date 2018/7/18 上午11:53
 */
public class ExcelUtil {

    public static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);


    /**
     * cellNum或cellName对应标识（true :cellNum false:cellName）
     */
    private TitleTypeEnum titleTypeEnum;

    /**
     * 错误处理标识
     */
    private DealErrorTypeEnum dealErrorTypeEnum;

    /**
     * Excel格式
     */
    private ExcelTypeEnum excelTypeEnum;

    /**
     * sheet块
     */
    private Integer sheet;

    private ExcelUtil(TitleTypeEnum titleTypeEnum, DealErrorTypeEnum dealErrorTypeEnum, ExcelTypeEnum excelTypeEnum, Integer sheet) {
        this.titleTypeEnum = titleTypeEnum;
        this.dealErrorTypeEnum = dealErrorTypeEnum;
        this.excelTypeEnum = excelTypeEnum;
        this.sheet = sheet;
    }


    public static ExcelUtil init() {
        return new ExcelUtil(TitleTypeEnum.CellName, DealErrorTypeEnum.NoDoError, ExcelTypeEnum.XLS, null);
    }


    public static ExcelUtil init(TitleTypeEnum titleTypeEnum, ExcelTypeEnum excelTypeEnum) {
        return new ExcelUtil(titleTypeEnum, DealErrorTypeEnum.NoDoError, excelTypeEnum, null);
    }

    public static ExcelUtil init(TitleTypeEnum titleTypeEnum, ExcelTypeEnum excelTypeEnum, int sheet) {
        return new ExcelUtil(titleTypeEnum, DealErrorTypeEnum.NoDoError, excelTypeEnum, sheet);
    }


    public static ExcelUtil init(TitleTypeEnum titleTypeEnum, ExcelTypeEnum excelTypeEnum, DealErrorTypeEnum dealErrorTypeEnum, int sheet) {
        return new ExcelUtil(titleTypeEnum, dealErrorTypeEnum, excelTypeEnum, sheet);
    }


    /**
     * @Description: 导出单sheet, 写入流中
     * @Author: WindPursuer
     * @Date 2018/7/18 上午11:13
     */
    public <T> void exportExcel(ExcelSheetDto data, Class<T> clazz, OutputStream outputStream) throws IOException {
        Workbook workbook = exportExcel(data, clazz);
        workbook.write(outputStream);
    }

    /**
     * @Description: 导出多sheet的, 写入流中
     * @Author: WindPursuer
     * @Date 2018/7/18 上午11:13
     */
    public <T> void exportExcel(List<ExcelSheetDto> data, Class<T> clazz, OutputStream outputStream) throws IOException {
        Workbook workbook = exportExcel(data, clazz);
        workbook.write(outputStream);
    }

    /**
     * @Description: 导出单sheet
     * @Author: WindPursuer
     * @Date 2018/7/18 上午11:11
     */
    public <T> Workbook exportExcel(ExcelSheetDto data, Class<T> clazz) {
        List<ExcelSheetDto> excelSheetDtoList = new ArrayList<>();
        excelSheetDtoList.add(data);
        return exportExcel(excelSheetDtoList, clazz);
    }


    /**
     * @Description: 导出多sheet的
     * @Author: WindPursuer
     * @Date 2018/7/18 上午11:10
     */
    public <T> Workbook exportExcel(List<ExcelSheetDto> data, Class<T> clazz) {
        logger.info("excelExport Start");
        Workbook workbook = getWorkBook();
        Field[] fields;
        try {
            fields = Class.forName(clazz.getName()).getDeclaredFields();
        } catch (ClassNotFoundException e) {
            logger.error("类找不到，原因:【{}】", e.getMessage());
            return workbook;
        }
        List<String> titleName = new ArrayList<>();

        Row row;
        Cell cell;
        logger.info("excelExport ，创建表头");
        for (ExcelSheetDto<T> excelSheetDto : data) {
            Sheet sheet = workbook.createSheet(excelSheetDto.getSheetName());
            int i = 0;
            int j = 0;
            row = sheet.createRow(i);
            for (Field field : fields) {
                cell = row.createCell(j, Cell.CELL_TYPE_STRING);
                if (!field.isAnnotationPresent(Excel.class)) {
                    continue;
                }
                Excel excel = field.getAnnotation(Excel.class);
                j++;
                switch (titleTypeEnum) {
                    case CellNum:
                        cell.setCellValue(String.valueOf(excel.cellNum()));
                        break;
                    case CellName:
                        cell.setCellValue(excel.cellName());
                        break;
                    default:
                        cell.setCellValue(excel.cellName());
                        break;
                }
            }
            logger.info("excelExport ，循环数据");
            for (T t : excelSheetDto.getData()) {
                i++;
                j = 0;
                row = sheet.createRow(i);
                for (Field field : fields) {
                    if (!field.isAnnotationPresent(Excel.class)) {
                        continue;
                    }
                    cell = row.createCell(j, Cell.CELL_TYPE_STRING);
                    j++;
                    field.setAccessible(true);
                    try {
                        cell.setCellValue(null == field.get(t) ? "" : String.valueOf(field.get(t)));
                    } catch (Exception e) {
                        logger.error("导出Excel异常，原因:【{}】", e.getMessage());
                        if (DealErrorTypeEnum.NoDoError.equals(dealErrorTypeEnum)) {
                            return workbook;
                        }
                    }
                }
            }
            logger.info("excelExport ，循环数据结束");
        }
        logger.info("excelExport End");
        return workbook;
    }


    /**
     * @Description: 解析excel
     * @Author: WindPursuer
     * @Date 2018/7/18 上午11:49
     */
    public <T> List<T> parseExcel(InputStream is, int startRow, Class<T> clazz) {
        return parseExcel(is, startRow, -1, clazz);
    }


    /**
     * @Description: 解析excel
     * @Author: WindPursuer
     * @Date 2018/7/18 上午11:49
     */
    public <T> List<T> parseExcel(InputStream is, int startRow, int endCol, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        try {
            Workbook wb = getWorkBook(is);
            int sheetSize = wb.getNumberOfSheets();
            Sheet sheet;
            if (null == this.sheet) {
                for (int i = 0; i < sheetSize; i++) {
                    sheet = wb.getSheetAt(i);
                    list.addAll(parseExcel(sheet, startRow, endCol, clazz));
                }
            } else {
                sheet = wb.getSheetAt(this.sheet);
                list = parseExcel(sheet, startRow, endCol, clazz);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     * @Description: Excel解析
     * @Author: WindPursuer
     * @Date 2018/7/17 下午3:54
     */
    private <T> List<T> parseExcel(Sheet sheet, int startRow, int endCol, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        Row row;
        Cell cell;
        T obj;
        // 开始行
        startRow = startRow == -1 ? sheet.getFirstRowNum() + 1 : startRow;
        for (int i = startRow; i <= sheet.getLastRowNum(); i++) {
            try {
                obj = clazz.newInstance();
                row = sheet.getRow(i);
                if (null != row) {
                    //结束列
                    endCol = endCol == -1 ? row.getPhysicalNumberOfCells() : endCol;
                    for (int j = 0; j < endCol; j++) {
                        cell = row.getCell(j);
                        setEntity(startRow, obj, cell);
                    }
                }
                list.add(obj);
            } catch (Exception e) {
                logger.error("解析Excel异常，原因:【{}】", e.getMessage());
                if (DealErrorTypeEnum.NoDoError.equals(dealErrorTypeEnum)) {
                    return list;
                }
            }
        }
        return list;
    }


    /**
     * @Description: 将数据存入实体
     * @Author: WindPursuer
     * @Date 2018/7/18 上午9:54
     */
    private void setEntity(int startRow, Object entity, Cell cell) throws Exception {
        logger.info("method start, param -> startRow:【{}】,cell:【{}】", startRow, cell);
        Field[] fields = Class.forName(entity.getClass().getName()).getDeclaredFields();
        if (null == cell) {
            logger.info("cell为null，直接返回");
            return;
        }

        /**
         * 获取该cell的title
         */
        String titleName = cell.getSheet().getRow(startRow - 1).getCell(cell.getColumnIndex()).getStringCellValue();
        /**
         * 获取value 针对日期处理（尽量模版提供以全文本）
         */
        String value;
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC && HSSFDateUtil.isCellDateFormatted(cell)) {
            value = cell.getDateCellValue().getTime() + "";
        } else {
            cell.setCellType(Cell.CELL_TYPE_STRING);
            value = cell.getStringCellValue();
        }
        if (null == value || "".equals(value)) {
            return;
        }
        for (Field field : fields) {
            // 设置属性可以修改
            field.setAccessible(true);
            if (!field.isAnnotationPresent(Excel.class)) {
                continue;
            }
            Excel excel = field.getAnnotation(Excel.class);
            switch (titleTypeEnum) {
                case CellNum:
                    if (cell.getColumnIndex() == excel.cellNum()) {
                        field.set(entity, value);
                    }
                    break;
                case CellName:
                    if (titleName.equals(excel.cellName())) {
                        field.set(entity, value);
                    }
                    break;
                default:
                    if (titleName.equals(excel.cellName())) {
                        field.set(entity, value);
                    }
                    break;
            }

        }
    }


    /**
     * @Description: 获取workbook
     * @Author: WindPursuer
     * @Date 2018/7/18 上午10:29
     */
    private Workbook getWorkBook(InputStream inputStream) throws IOException {
        Workbook workbook;
        switch (excelTypeEnum) {

            case XLSX:
                workbook = new XSSFWorkbook(inputStream);
                break;
            case XLS:
            default:
                workbook = new HSSFWorkbook(inputStream);
                break;
        }
        return workbook;
    }

    private Workbook getWorkBook() {
        Workbook workbook;
        logger.info("获取workBook Start");
        switch (excelTypeEnum) {

            case XLSX:
                workbook = new SXSSFWorkbook(1000);
                logger.info("获取workBook End XLSX");
                break;
            case XLS:
            default:
                workbook = new HSSFWorkbook();
                logger.info("获取workBook End XLS");
                break;
        }
        logger.info("获取workBook End");
        return workbook;
    }




    public TitleTypeEnum getTitleTypeEnum() {
        return titleTypeEnum;
    }

    public void setTitleTypeEnum(TitleTypeEnum titleTypeEnum) {
        this.titleTypeEnum = titleTypeEnum;
    }

    public DealErrorTypeEnum getDealErrorTypeEnum() {
        return dealErrorTypeEnum;
    }

    public void setDealErrorTypeEnum(DealErrorTypeEnum dealErrorTypeEnum) {
        this.dealErrorTypeEnum = dealErrorTypeEnum;
    }

    public ExcelTypeEnum getExcelTypeEnum() {
        return excelTypeEnum;
    }

    public void setExcelTypeEnum(ExcelTypeEnum excelTypeEnum) {
        this.excelTypeEnum = excelTypeEnum;
    }

    public Integer getSheet() {
        return sheet;
    }

    public void setSheet(Integer sheet) {
        this.sheet = sheet;
    }
}
