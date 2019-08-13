package com.learn.algorithm.tree.bindary.strategy;

public enum TrversalTypeEnum {

    TRL("前序遍历"), LTR("中序遍历"), LRT("后序遍历"), HIERARCHY("按照层级访问");

    private String desc;

    TrversalTypeEnum() {}

    TrversalTypeEnum(String desc) {
       this.desc = desc;
    }
    public String getDesc() {
        return desc;
    }

}
