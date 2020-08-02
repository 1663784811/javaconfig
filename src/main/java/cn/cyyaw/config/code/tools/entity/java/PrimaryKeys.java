package cn.cyyaw.config.code.tools.entity.java;

import lombok.Data;

/**
 * 主键
 */
@Data
public class PrimaryKeys {
    private String tableName;   //表名
    private String columnName;  //指向的列
}
