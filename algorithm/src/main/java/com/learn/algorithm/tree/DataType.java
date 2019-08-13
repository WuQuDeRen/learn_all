package com.learn.algorithm.tree;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;

@Data
@EqualsAndHashCode
public class DataType {

    private String keyName;

    public DataType() {

    }

    public DataType(String keyName) {
        this.keyName = keyName;
    }


}
