package cn.cyyaw.config.table.table.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "p_page")
@org.hibernate.annotations.Table(appliesTo = "p_page", comment = "页面配置表")
public class PPage implements Serializable {
    private static final long serialVersionUID = 1589122890183865L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic
    @Column(name = "id", unique = true, columnDefinition = "int auto_increment COMMENT ''")
    private Integer id;
    @Basic
    @Column(name = "tid", unique = true, nullable = false, columnDefinition = "varchar(32) COMMENT 'tid'")
    private String tid;
    @Basic
    @Column(name = "createtime", length = 19, columnDefinition = "datetime COMMENT '创建时间'")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;
    @Basic
    @Column(name = "del", length = 10, columnDefinition = "int COMMENT '是否删除{0:否,1:是}'")
    private Integer del;
    @Basic
    @Column(name = "note", columnDefinition = "varchar(255) COMMENT '备注'")
    private String note;
    @Basic
    @Column(name = "page", columnDefinition = "varchar(32) COMMENT '页面名'")
    private String page;
    @Basic
    @Column(name = "title", columnDefinition = "varchar(32) COMMENT '标题'")
    private String title;
    @Basic
    @Column(name="sort", columnDefinition = "int COMMENT '排序'")
    private Integer sort;
}
