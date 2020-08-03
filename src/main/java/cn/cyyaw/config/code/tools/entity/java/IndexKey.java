package cn.cyyaw.config.code.tools.entity.java;

import lombok.Data;

/**
 * 索引
 */
@Data
public class IndexKey {
    private String tablename;   //表名
    private String columnname;  //指向的列
    private String indextype;   //索引类型
    private Boolean isunique = false; //是否是唯一
}
