package cn.cyyaw.config.code.tools.entity.java;

import lombok.Data;

import java.util.List;

@Data
public class JavaData {
    private String database;                //数据库
    private String table;                   //数据表
    private String tablenote;               //表注释
    private String tabletype;               //类型
    private List<JavaColumn> javacolumns;   //字段列表
}
