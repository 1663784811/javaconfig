package cn.cyyaw.config.table.table.entity.tadmin;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "t_photo")
@org.hibernate.annotations.Table(appliesTo = "t_photo", comment = "图片资源表")
public class TPhoto implements Serializable {
    private static final long serialVersionUID = 1387301173682985L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic
    @Column(name = "id", unique = true, nullable = false, columnDefinition = "int auto_increment COMMENT 'id'")
    private Integer id;
    @Basic
    @Column(name = "tid", unique = true, length = 32, columnDefinition = "varchar(32) not null COMMENT 'tid'")
    private String tid;
    @Basic
    @Column(name = "del", length = 10, columnDefinition = "int COMMENT '是否删除{0:否,1:是}'")
    private Integer del;
    @Basic
    @Column(name = "note", columnDefinition = "varchar(255) COMMENT '备注'")
    private String note;
    @Basic
    @Column(name = "createtime", length = 19, columnDefinition = "datetime COMMENT '创建时间'")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    @Basic
    @Column(name = "path", columnDefinition = "varchar(255) COMMENT '图片路径'")
    private String path;
    @Basic
    @Column(name = "name", columnDefinition = "varchar(255) COMMENT '图片名'")
    private String name;
    @Basic
    @Column(name = "tphotoclassificationid", columnDefinition = "varchar(32) COMMENT '分类表ID'")
    private String tphotoclassificationid;


}
