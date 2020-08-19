package cn.cyyaw.config.table.table.entity.config;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "c_interface")
@org.hibernate.annotations.Table(appliesTo = "c_interface", comment = "接口")
public class CInterface implements Serializable {
    private static final long serialVersionUID = 1589122890104459L;
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
    @Column(name = "name", columnDefinition = "varchar(255) COMMENT '接口'")
    private String name;
    @Basic
    @Column(name = "note", columnDefinition = "varchar(255) COMMENT '备注'")
    private String note;
    @Basic
    @Column(name = "parameter", columnDefinition = "text COMMENT '参数'")
    private String parameter;
    @Basic
    @Column(name = "returnvalue", columnDefinition = "text COMMENT '返回值'")
    private String returnvalue;
    @Basic
    @Column(name = "tid", unique = true, columnDefinition = "varchar(45) COMMENT 'tid'")
    private String tid;
    @Basic
    @Column(name = "type", columnDefinition = "varchar(45) COMMENT '类型'")
    private String type;
    @Basic
    @Column(name = "url", columnDefinition = "varchar(255) COMMENT 'url'")
    private String url;
}
