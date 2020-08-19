package cn.cyyaw.config.table.table.entity.config;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "c_field")
@org.hibernate.annotations.Table(appliesTo = "c_field", comment = "字段表")
public class CField implements Serializable{
    private static final long serialVersionUID = 1589122890069706L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic
    @Column(name="id" ,unique = true ,nullable = false, columnDefinition = "int auto_increment COMMENT ''")
    private Integer id;
    @Basic
    @Column(name="columnname", columnDefinition = "varchar(255) COMMENT '字段名'")
    private String columnname;
    @Basic
    @Column(name="controltype", columnDefinition = "varchar(255) COMMENT '控件类型'")
    private String controltype;
    @Basic
    @Column(name="ctableid", columnDefinition = "varchar(255) COMMENT 'ctableid表tid'")
    private String ctableid;
    @Basic
    @Column(name="dbtype", columnDefinition = "varchar(255) COMMENT '数据库字段类型'")
    private String dbtype;
    @Basic
    @Column(name="defaultvalue", columnDefinition = "varchar(255) COMMENT '默认值'")
    private String defaultvalue;
    @Basic
    @Column(name="fkcolumnname", columnDefinition = "varchar(255) COMMENT '外键表列'")
    private String fkcolumnname;
    @Basic
    @Column(name="fktablename", columnDefinition = "varchar(255) COMMENT '外键表名'")
    private String fktablename;
    @Basic
    @Column(name="format", columnDefinition = "varchar(255) COMMENT '格式化'")
    private String format;
    @Basic
    @Column(name="indextype", columnDefinition = "varchar(255) COMMENT '索引类型'")
    private String indextype;
    @Basic
    @Column(name="isautoincrement", columnDefinition = "int COMMENT '自增{0:否,1:是}'")
    private Integer isautoincrement;
    @Basic
    @Column(name="isfk", columnDefinition = "int COMMENT '外键{0:否,1:是}'")
    private Integer isfk;
    @Basic
    @Column(name="isindex", columnDefinition = "int COMMENT '索引{0:否,1:是}'")
    private Integer isindex;
    @Basic
    @Column(name="isnull", columnDefinition = "int COMMENT '为null{0:否,1:是}'")
    private Integer isnull;
    @Basic
    @Column(name="isprimary", columnDefinition = "int COMMENT '主键{0:否,1:是}'")
    private Integer isprimary;
    @Basic
    @Column(name="isrequire", columnDefinition = "int COMMENT '是否必须{0:否,1:是}'")
    private Integer isrequire;
    @Basic
    @Column(name="isshowcolumn", columnDefinition = "int COMMENT '显示列表{0:否,1:是}'")
    private Integer isshowcolumn;
    @Basic
    @Column(name="isunique", columnDefinition = "int COMMENT '唯一{0:否,1:是}'")
    private Integer isunique;
    @Basic
    @Column(name="iswhere", columnDefinition = "int COMMENT '查询条件{0:否,1:是}'")
    private Integer iswhere;
    @Basic
    @Column(name="javatype", columnDefinition = "varchar(255) COMMENT 'java类型'")
    private String javatype;
    @Basic
    @Column(name="javawhere", columnDefinition = "varchar(255) COMMENT 'java条件'")
    private String javawhere;
    @Basic
    @Column(name="[key]", columnDefinition = "varchar(255) COMMENT '键值'")
    private String key;
    @Basic
    @Column(name="length", columnDefinition = "int COMMENT '长度'")
    private Integer length;
    @Basic
    @Column(name="message", columnDefinition = "varchar(255) COMMENT '提示'")
    private String message;
    @Basic
    @Column(name="note", columnDefinition = "varchar(255) COMMENT '注释'")
    private String note;
    @Basic
    @Column(name="pkcolumnname", columnDefinition = "varchar(255) COMMENT '外键主表列'")
    private String pkcolumnname;
    @Basic
    @Column(name="pktablename", columnDefinition = "varchar(255) COMMENT '外键主表'")
    private String pktablename;
    @Basic
    @Column(name="regstr", columnDefinition = "varchar(255) COMMENT '正则表达式'")
    private String regstr;
    @Basic
    @Column(name="tid", unique = true, columnDefinition = "varchar(45) COMMENT 'tid'")
    private String tid;
    @Basic
    @Column(name="title", columnDefinition = "varchar(255) COMMENT '名称'")
    private String title;
    @Basic
    @Column(name="type", columnDefinition = "varchar(255) COMMENT '列表类型'")
    private String type;
}
