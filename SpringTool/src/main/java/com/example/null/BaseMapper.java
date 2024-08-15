package com.example.null;

import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface BaseMapper<T, P> {
    // insert
    Integer insert(@Param("bean") T t);

    // insertOrUpdate
    Integer insertOrUpdate(@Param("bean") T t);

    // insertBatch
    Integer insertBatch(@Param("list") List<T> list);

    // insertOrUpdateBatch
    Integer insertOrUpdateBatch(@Param("list") List<T> list);

    // selectList
    List<T> selectList(@Param("query") P p);

    // selectCount
    Integer selectCount(@Param("query") P p);


}
