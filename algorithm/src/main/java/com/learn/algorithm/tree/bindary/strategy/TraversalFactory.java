package com.learn.algorithm.tree.bindary.strategy;

import com.learn.system.enums.ExceptionMsgEnum;
import com.learn.system.exception.ServerException;

/**
 * 简单工厂
 */
public class TraversalFactory {

    public static TraversalStrategy getTraversal(TrversalTypeEnum trversalTypeEnum) {

        TraversalStrategy traversalStrategy = null;
        switch (trversalTypeEnum) {
            case TRL: {
                traversalStrategy = new PreOrderSearch();
                break;
            }
            case LTR: {
                traversalStrategy = new MiddleOrderSearch();
                break;
            }
            case LRT: {
                traversalStrategy = new PostOrderSearch();
                break;
            }
            case HIERARCHY: {
                traversalStrategy = new HierarchySearch();
                break;
            }
            default: {
                throw new ServerException(ExceptionMsgEnum.DATA_NOT_MATCH);
            }
        }
        return traversalStrategy;
    }
}
