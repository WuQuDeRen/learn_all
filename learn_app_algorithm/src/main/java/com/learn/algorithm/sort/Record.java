package com.learn.algorithm.sort;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Record {
    /**
     * 关键字
     */
    private Integer keyWord;

    public Record(){}

    public Record(Integer keyWord) {
        this.keyWord = keyWord;
    }

}
