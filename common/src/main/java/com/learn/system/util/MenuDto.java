package com.learn.system.util;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

@Data
public class MenuDto {

    /**
     * 当前节点的标志
     */
    @MenuColumn(targetFieldName = "id", columnType = ColumnType.CURRENT_ID)
    private String id;
    /**
     * 当前节点是否为叶子
     */
    @MenuColumn(targetFieldName = "isLeaf", columnType = ColumnType.IS_LEAF)
    private Boolean isLeaf;
    /**
     * 当前节点的父节点
     */
    @MenuColumn(targetFieldName = "parentId", columnType = ColumnType.PARENT_ID)
    private String parentId;
    /**
     * 当前节点的子节点
     */
    @MenuColumn(targetFieldName = "childList", columnType = ColumnType.CHILDREN)
    private List<MenuDto> childList = Lists.newArrayList();

    public MenuDto() {
    }

    public MenuDto(String id, Boolean isLeaf, String parentId) {
        this.id = id;
        this.isLeaf = isLeaf;
        this.parentId = parentId;
    }

    public MenuDto(String id, Boolean isLeaf, String parentId, List<MenuDto> childList) {
        this.id = id;
        this.isLeaf = isLeaf;
        this.parentId = parentId;
        this.childList = childList;
    }
}
