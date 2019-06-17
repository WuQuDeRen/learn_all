package com.learn.algorithm.tree;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
