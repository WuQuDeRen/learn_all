package com.learn.system.util.excel;

import java.util.List;

/**
 * @Description: Excel导出实体
 * @Author: WindPursuer
 * @Date 2018/7/17 下午4:53
 */
public class ExcelSheetDto <T> {

    private String sheetName = "default";

    private List<T> data;


    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
