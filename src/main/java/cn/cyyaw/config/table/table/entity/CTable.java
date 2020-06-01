package cn.cyyaw.config.table.table.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "c_table")
@org.hibernate.annotations.Table(appliesTo = "c_table", comment = "数据表")
public class CTable implements Serializable {
    private static final long serialVersionUID = 1589122890164236L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic
    @Column(name = "id", unique = true, nullable = false, columnDefinition = "int auto_increment COMMENT ''")
    private Integer id;
    @Basic
    @Column(name = "[database]", columnDefinition = "varchar(255) COMMENT '数据库'")
    private String database;
    @Basic
    @Column(name = "note", columnDefinition = "varchar(255) COMMENT '备注'")
    private String note;
    @Basic
    @Column(name = "pwd", columnDefinition = "varchar(255) COMMENT '连接密码'")
    private String pwd;
    @Basic
    @Column(name = "tablename", columnDefinition = "varchar(255) COMMENT '表名'")
    private String tablename;
    @Basic
    @Column(name = "tabletype", columnDefinition = "varchar(255) COMMENT '类型'")
    private String tabletype;
    @Basic
    @Column(name = "tid", columnDefinition = "varchar(45) COMMENT ''")
    private String tid;
    @Basic
    @Column(name = "url", columnDefinition = "varchar(255) COMMENT '连接url'")
    private String url;
    @Basic
    @Column(name = "user", columnDefinition = "varchar(255) COMMENT '连接用户名'")
    private String user;
}
