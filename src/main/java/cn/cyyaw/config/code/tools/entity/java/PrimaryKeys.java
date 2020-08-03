package cn.cyyaw.config.code.tools.entity.java;

import lombok.Data;

/**
 * 主键
 */
@Data
public class PrimaryKeys {
    private String tablename;   //表名
    private String columnname;  //指向的列
}
