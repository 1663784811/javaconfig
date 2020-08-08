package cn.cyyaw.config.code.tools.entity.java;

import lombok.Data;

/**
 * 数据表字段
 */
@Data
public class JavaColumn {
    private String columnname;          //字段名
    private String dbtype;              //数据库字段类型
    private Integer length;             //长度
    private String javatype;            //java 类型
    private String defaultvalue;        //默认值
    private String note;                //注释
    private Boolean isautoincrement;    //是否自增加
    private Boolean isnull = true;      //是否可以为null

    private Boolean isprimary = false;  //是否是主键
    private String pktablename;   //表名
    private String pkcolumnname;  //指向的列

    private Boolean isfk = false;  //是否是外键
    private String fktablename;   //表名
    private String fkcolumnname;  //指向的列

    private Boolean isunique = false;   //是否是唯一
    private Boolean isindex = false;   //是否是索引
    private String indextype;   //索引类型
}
