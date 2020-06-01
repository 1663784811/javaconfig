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
@Table(name = "c_page_components")
@org.hibernate.annotations.Table(appliesTo = "c_page_components", comment = "页面组件")
public class CPageComponents implements Serializable {
    private static final long serialVersionUID = 158912289014615L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic
    @Column(name = "id", unique = true, nullable = false, columnDefinition = "int auto_increment COMMENT 'id'")
    private Integer id;
    @Basic
    @Column(name = "components", columnDefinition = "text COMMENT '组件'")
    private String components;
    @Basic
    @Column(name = "componentsid", columnDefinition = "varchar(45) COMMENT '组件ID'")
    private String componentsid;
    @Basic
    @Column(name = "computed", columnDefinition = "text COMMENT '计算属性'")
    private String computed;
    @Basic
    @Column(name = "createtime", columnDefinition = "datetime COMMENT '创建时间'")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;
    @Basic
    @Column(name = "data", columnDefinition = "text COMMENT '数据'")
    private String data;
    @Basic
    @Column(name = "icon", columnDefinition = "varchar(255) COMMENT 'icon图标'")
    private String icon;
    @Basic
    @Column(name = "import_js", columnDefinition = "text COMMENT '导入js'")
    private String importJs;
    @Basic
    @Column(name = "methods", columnDefinition = "text COMMENT '方法'")
    private String methods;
    @Basic
    @Column(name = "mounted", columnDefinition = "text COMMENT '初始化方法'")
    private String mounted;
    @Basic
    @Column(name = "name", columnDefinition = "varchar(45) COMMENT '名称'")
    private String name;
    @Basic
    @Column(name = "note", columnDefinition = "varchar(255) COMMENT '注释'")
    private String note;
    @Basic
    @Column(name = "pageid", columnDefinition = "varchar(45) COMMENT '页面ID'")
    private String pageid;
    @Basic
    @Column(name = "sort", unique = true, columnDefinition = "int COMMENT '排序'")
    private Integer sort;
    @Basic
    @Column(name = "style", columnDefinition = "text COMMENT '样式'")
    private String style;
    @Basic
    @Column(name = "tag", columnDefinition = "text COMMENT '标签'")
    private String tag;
    @Basic
    @Column(name = "tid", columnDefinition = "varchar(45) COMMENT 'tid'")
    private String tid;
}
