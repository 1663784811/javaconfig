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
@Table(name = "w_banner")
@org.hibernate.annotations.Table(appliesTo = "w_banner", comment = "首页banner图")
public class WBanner  implements Serializable {
    private static final long serialVersionUID = 156878262756870L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic
    @Column(name = "id", unique = true, nullable = false, columnDefinition = "int auto_increment COMMENT ''")
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
    @Column(name = "name", columnDefinition = "varchar(255) COMMENT '名称'")
    private String name;
    @Basic
    @Column(name = "url", columnDefinition = "varchar(255) COMMENT '跳转url'")
    private String url;
    @Basic
    @Column(name = "img", columnDefinition = "varchar(255) COMMENT '图片地址'")
    private String img;
    @Basic
    @Column(name = "isshow", length = 10, columnDefinition = "int COMMENT default '0' '是否显示{0:否,1:是}'")
    private Integer isshow;
    @Basic
    @Column(name = "position", length = 10, columnDefinition = "int COMMENT default '0' '显示位置{1:首页}'")
    private Integer position;
}






















