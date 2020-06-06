package cn.cyyaw.config.table.table.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "c_page")
@org.hibernate.annotations.Table(appliesTo = "c_page", comment = "页面")
public class CPage implements Serializable {
    private static final long serialVersionUID = 1589122890125451L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic
    @Column(name = "id", unique = true, nullable = false, columnDefinition = "int auto_increment COMMENT 'id'")
    private Integer id;
    @Basic
    @Column(name = "createtime", columnDefinition = "datetime COMMENT '创建时间'")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;
    @Basic
    @Column(name = "name", columnDefinition = "varchar(255) COMMENT '页面名'")
    private String name;
    @Basic
    @Column(name = "note", columnDefinition = "varchar(255) COMMENT '注释'")
    private String note;
    @Basic
    @Column(name = "pageicon", columnDefinition = "varchar(255) COMMENT '图标'")
    private String pageicon;
    @Basic
    @Column(name = "pid", columnDefinition = "varchar(45) COMMENT '父级ID'")
    private String pid;
    @Basic
    @Column(name = "tid", unique = true, columnDefinition = "varchar(45) COMMENT 'tid'")
    private String tid;
}
