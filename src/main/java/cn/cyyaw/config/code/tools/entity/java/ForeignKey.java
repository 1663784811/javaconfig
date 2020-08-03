package cn.cyyaw.config.code.tools.entity.java;

import lombok.Data;

/**
 * 外键
 */
@Data
public class ForeignKey {
    private String fktablenote;  //表名
    private String fkcolumnname;  //表名
    private String pktablename;   //指向的表名
    private String pkcolumnname;  //指向的列
}
