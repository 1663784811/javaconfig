package cn.cyyaw.config.table.table.entity.goods;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "g_brand")
@org.hibernate.annotations.Table(appliesTo = "g_brand", comment = "品牌表")
public class GBrand implements Serializable {
    private static final long serialVersionUID = 15687826272233758L;

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
    @Column(name = "pid", length = 32, columnDefinition = "varchar(32) COMMENT '父级ID'")
    private String pid;
    @Basic
    @Column(name = "treecode", length = 10, columnDefinition = "varchar(32) not null default '' COMMENT '树码(一级三位)'")
    private String treecode;

    @Basic
    @Column(name = "name", length = 32, columnDefinition = "varchar(32) COMMENT '品牌名称'")
    private String name;
    @Basic
    @Column(name = "img", columnDefinition = "varchar(255) COMMENT '图标'")
    private String img;

    @Basic
    @Column(name = "status", length = 10, columnDefinition = "int COMMENT '状态{1:显示,0:隐藏}'")
    private Integer status;

    @Basic
    @Column(name = "sort", length = 10, columnDefinition = "int default '1' COMMENT '排序'")
    private Integer sort;

}
