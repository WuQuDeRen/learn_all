package com.learn.proxy.cglib.classs;

import org.springframework.cglib.core.ClassGenerator;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.core.DefaultGeneratorStrategy;

/**
 * @desc TODO
 * @author ji_fei
 * @date  2019-07-27 14:59
 */
public class DefaultGeneratorStrategyImpl extends DefaultGeneratorStrategy {

    public byte[] generate(ClassGenerator cg) throws Exception {
        DebuggingClassWriter cw = this.getClassVisitor();
        this.transform(cg).generateClass(cw);
        return this.transform(cw.toByteArray());
    }
}
