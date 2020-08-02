package cn.cyyaw.config.code.tools.entity.java;

import lombok.Data;

/**
 * 数据表字段
 */
@Data
public class JavaColumn {
    private String columnName;                //字段名
    private String dbType;              //数据库字段类型
    private Integer length;             //长度
    private String javaType;            //java 类型
    private String defaultValue;        //默认值
    private String note;                //注释
    private Boolean isAutoIncrement;    //是否自增加
    private Boolean isNull = true;      //是否可以为null

    private Boolean isPrimary = false;  //是否是主键
    private String pkTableName;   //表名
    private String pkColumnName;  //指向的列

    private Boolean isFk = false;  //是否是外键
    private String fkTableName;   //表名
    private String fkColumnName;  //指向的列


    private Boolean isUnique = false;   //是否是唯一
    private Boolean isIndex = false;   //是否是索引
    private String indexType;   //索引类型



}
