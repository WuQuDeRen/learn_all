package com.learn.system.util;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestMenuUtil {

    private List<MenuDto> menuRecordList = Lists.newArrayList();

    @Before
    public void setUp() {
        MenuDto menuRecord1 = new MenuDto("1", false, "0");
        MenuDto menuRecord11 = new MenuDto("11", false, "1");
        MenuDto menuRecord111 = new MenuDto("111", true, "11");
        MenuDto menuRecord112 = new MenuDto("112", false, "11");
        MenuDto menuRecord1121 = new MenuDto("1121", true, "112");
        MenuDto menuRecord1122 = new MenuDto("1122", true, "112");
        MenuDto menuRecord12 = new MenuDto("12", true, "1");

        MenuDto menuRecord2 = new MenuDto("2", false, "0");
        MenuDto menuRecord21 = new MenuDto("21", true, "2");
        MenuDto menuRecord22 = new MenuDto("22", true, "2");
        MenuDto menuRecord3 = new MenuDto("3", false, "0");
        MenuDto menuRecord31 = new MenuDto("31", false, "3");
        MenuDto menuRecord311 = new MenuDto("311", true, "31");
        MenuDto menuRecord32 = new MenuDto("32", true, "3");


        List<MenuDto> menuRecordList = new ArrayList<MenuDto>();


        menuRecordList.add(menuRecord1);
        menuRecordList.add(menuRecord11);
        menuRecordList.add(menuRecord111);
        menuRecordList.add(menuRecord112);
        menuRecordList.add(menuRecord1121);
        menuRecordList.add(menuRecord1122);
        menuRecordList.add(menuRecord12);

        menuRecordList.add(menuRecord2);
        menuRecordList.add(menuRecord21);
        menuRecordList.add(menuRecord22);
        menuRecordList.add(menuRecord3);
        menuRecordList.add(menuRecord31);
        menuRecordList.add(menuRecord311);
        menuRecordList.add(menuRecord32);

        this.menuRecordList = menuRecordList;
    }
    @Test
    public void testConverter() {
        MenuUtil<MenuDto, MenuDto> menuUtil = MenuUtil.instance(MenuDto.class, (menuDto, children) -> {
            MenuDto resultDto = new MenuDto();
            resultDto.setId(menuDto.getId());
            resultDto.setIsLeaf(menuDto.getIsLeaf());
            resultDto.setParentId(menuDto.getParentId());
            resultDto.setChildList(children);
            return resultDto;
        });
        List<MenuDto> menuDtoList = menuUtil.generateMenu(menuRecordList, "0");

        System.out.println(menuDtoList);
    }


    @Test
    public void test() {
        MenuUtil<MenuDto, MenuDto> menuUtil = MenuUtil.instance(MenuDto.class);
        List<MenuDto> menuDtoList = menuUtil.generateMenu(menuRecordList, "0");

        System.out.println(menuDtoList);

    }
}
