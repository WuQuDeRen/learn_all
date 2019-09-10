package com.learn.system.util;

import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.List;
import java.util.function.BiFunction;


/**
 * 字段注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@interface MenuColumn {
    /**
     * 字段名
     * @return
     */
    String targetFieldName();

    /**
     * 列类型
     * @return
     */
    ColumnType columnType();
}

enum ColumnType {

    CURRENT_ID("当前节点ID"), PARENT_ID("父节点"), IS_LEAF("是否为叶子"), CHILDREN("子节点");

    ColumnType(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}

/**
 * 字段解析器
 */
@Data
class FieldParser {
    /**
     * 当前元素标志
     */
    private Field currentIdField = null;
    /**
     * 父元素标志
     */
    private Field parentIdField = null;
    /**
     * 是否为叶子
     */
    private Field isLeafField = null;

    /**
     * 子节点
     */
    private Field childrenField = null;

    public FieldParser() {
    }

    public FieldParser(Field currentIdField, Field parentIdField, Field isLeafField, Field childrenField) {
        this.currentIdField = currentIdField;
        this.parentIdField = parentIdField;
        this.isLeafField = isLeafField;
        this.childrenField = childrenField;
    }

    public <R> R getCurrentId(Object obj) {
        try {
            currentIdField.setAccessible(true);
            R result = (R) currentIdField.get(obj);
            return result;
        } catch (IllegalAccessException e) {
        }
        return null;
    }

    public <R> R getParentId(Object obj) {
        try {
            parentIdField.setAccessible(true);
            R result = (R) parentIdField.get(obj);
            return result;
        } catch (IllegalAccessException e) {
        }
        return null;
    }

    public  Boolean getIsLeaf(Object obj) {
        try {
            isLeafField.setAccessible(true);
            Boolean result = (Boolean) isLeafField.get(obj);
            return result;
        } catch (IllegalAccessException e) {
        }
        return false;
    }

    public <R> R getChildren(Object obj) {
        try {
            childrenField.setAccessible(true);
            R result = (R) childrenField.get(obj);
            return result;
        } catch (IllegalAccessException e) {
        }
        return null;
    }

    public static <S> FieldParser instance(Class<S> clazz) {
        Field[] declaredFields = clazz.getDeclaredFields();
        Field currentIdField = null;
        Field parentIdField = null;
        Field isLeafField = null;
        Field childrenField = null;
        for (Field field : declaredFields) {
            MenuColumn menuColumn = field.getAnnotation(MenuColumn.class);
            if (!ObjectUtils.isEmpty(menuColumn)) {
                switch (menuColumn.columnType()) {
                    case CURRENT_ID: {
                        currentIdField = field;
                        break;
                    }
                    case PARENT_ID: {
                        parentIdField = field;
                        break;
                    }
                    case IS_LEAF: {
                        isLeafField = field;
                        break;
                    }
                    case CHILDREN: {
                        childrenField = field;
                        break;
                    }
                }
            }
        }
        if (currentIdField == null || parentIdField == null || isLeafField == null) {
            throw new RuntimeException("处理失败");
        }
        return new FieldParser(currentIdField, parentIdField, isLeafField, childrenField);
    }

}

/**
 * 递归生成菜单的工具类
 * @author ji_fei
 * @date 2019/
 * @param <S>
 * @param <T>
 */
public class MenuUtil<S,T> {

    private FieldParser parser;

    private BiFunction<S,List<T>,T> converter;

    private Class<S> sourceClass;

    public MenuUtil() {
    }

    private MenuUtil(FieldParser parser, Class<S> sourceClass) {
        this.parser = parser;
        this.sourceClass = sourceClass;
    }

    private MenuUtil(FieldParser parser, BiFunction<S, List<T>, T> converter, Class<S> sourceClass) {
        this.parser = parser;
        this.converter = converter;
        this.sourceClass = sourceClass;
    }

    /**
     * 递归生成菜单树
     * @param sourceMenuRecordList
     * @param parentId
     * @return
     */
    public  List<T> generateMenu(List<S> sourceMenuRecordList, String parentId) {
        // 1、基础参数校验
        if (StringUtils.isEmpty(parentId) || CollectionUtils.isEmpty(sourceMenuRecordList)) {
            return Lists.newArrayList();
        }
        List<T> targetMenuList = Lists.newArrayList();
        // 2、循环所有节点
        for (S menuRecord : sourceMenuRecordList) {
            String recordParentId = parser.getParentId(menuRecord);
            if (parentId.equals(recordParentId)) {
                List<T> childMenuList = Lists.newArrayList();
                // 2.1 当前节点不是叶子节点，进行递归
                if (!parser.getIsLeaf(menuRecord)) {
                    childMenuList = generateMenu(sourceMenuRecordList, parser.getCurrentId(menuRecord));
                }
                T resultDto = null;
                // 2.2 结果转化
                if (ObjectUtils.isEmpty(converter)) {
                    // 2.2.1 没有提供转换器，则按照原类型进行转换
                    try {
                        resultDto = (T) sourceClass.newInstance();
                        BeanUtils.copyProperties(resultDto, menuRecord);
                    } catch (Exception e) {
                    }
                } else {
                    // 2.2.2 提供了类型转化器，则使用类型转化器进行转化
                    resultDto = converter.apply(menuRecord, childMenuList);
                }
                // 2.3 结果不为 null，则保存该节点
                if (!ObjectUtils.isEmpty(resultDto)) {
                    targetMenuList.add(resultDto);
                }
            }
        }
        // 3、返回结果
        return targetMenuList;
    }

    /**
     * 使用扁平状菜单 数据类型（必须要有子节点集合)  作为  树型菜单的数据类型
     *  举例：
     *     扁平状菜单的数据类型
     *     @Data
     *     public class MenuDto {
     *          @MenuColumn(targetFieldName = "id", columnType = ColumnType.CURRENT_ID)
     *          private String id;
     *
     *          @MenuColumn(targetFieldName = "isLeaf", columnType = ColumnType.IS_LEAF)
     *          private Boolean isLeaf;
     *
     *          @MenuColumn(targetFieldName = "parentId", columnType = ColumnType.PARENT_ID)
     *          private String parentId;
     *
     *          @MenuColumn(targetFieldName = "childList", columnType = ColumnType.CHILDREN)
     *          private List<MenuDto> childList = Lists.newArrayList();
     *    }
     *
     * @param sourceClass 扁平状菜单数据类型
     * @param converter   转换器(将扁平状的 菜单记录 和 子菜单  转换为 树型菜单)
     * @param <S>
     * @param <T>
     * @return
     */
    public static <S,T> MenuUtil<S,T> instance(Class<S> sourceClass, BiFunction<S,List<T>,T> converter) {
        FieldParser fieldParser = FieldParser.instance(sourceClass);
        return new MenuUtil<>(fieldParser, converter, sourceClass);
    }

    /**
     * 使用扁平状菜单 数据类型（必须要有子节点集合)  作为  树型菜单的数据类型
     *  举例：
     *     扁平状菜单的数据类型
     *     @Data
     *     public class MenuDto {
     *          @MenuColumn(targetFieldName = "id", columnType = ColumnType.CURRENT_ID)
     *          private String id;
     *
     *          @MenuColumn(targetFieldName = "isLeaf", columnType = ColumnType.IS_LEAF)
     *          private Boolean isLeaf;
     *
     *          @MenuColumn(targetFieldName = "parentId", columnType = ColumnType.PARENT_ID)
     *          private String parentId;
     *
     *          @MenuColumn(targetFieldName = "childList", columnType = ColumnType.CHILDREN)
     *          private List<MenuDto> childList = Lists.newArrayList();
     *    }
     *
     * @param sourceClass   扁平状菜单数据类型
     * @param <S>
     * @return
     */
    public static <S> MenuUtil<S,S> instance(Class<S> sourceClass) {
        FieldParser fieldParser = FieldParser.instance(sourceClass);
        return new MenuUtil<>(fieldParser, sourceClass);
    }
}

