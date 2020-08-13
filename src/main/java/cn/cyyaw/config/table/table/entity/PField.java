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
@Table(name = "p_field")
@org.hibernate.annotations.Table(appliesTo = "p_field", comment = "页面字段定义表")
public class PField implements Serializable {
    private static final long serialVersionUID = 1589123585442325L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic
    @Column(name = "id", unique = true, nullable = false, columnDefinition = "int auto_increment COMMENT ''")
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
    @Column(name = "pageid", columnDefinition = "varchar(32) COMMENT '页面表p_page_td'")
    private String pageid;
    @Basic
    @Column(name = "componentsid", columnDefinition = "varchar(32) COMMENT '组件表p_page_components'")
    private String componentsid;
    @Basic
    @Column(name = "[key]", columnDefinition = "varchar(20) COMMENT 'key'")
    private String key;
    @Basic
    @Column(name = "width", columnDefinition = "int COMMENT '宽度'")
    private Integer width;
    @Basic
    @Column(name = "title", columnDefinition = "varchar(32) COMMENT '标题'")
    private String title;
    @Basic
    @Column(name = "type", columnDefinition = "varchar(15) COMMENT '组件类型'")
    private String type;
    @Basic
    @Column(name = "length", columnDefinition = "int COMMENT '长度'")
    private Integer length;
    @Basic
    @Column(name = "isrequire", columnDefinition = "int default '0' COMMENT '是否必填{0:否,1:是}'")
    private Integer isrequire;
    @Basic
    @Column(name = "regstr", columnDefinition = "varchar(20) COMMENT '验证正则'")
    private String regstr;
    @Basic
    @Column(name = "message", columnDefinition = "varchar(32) COMMENT '提示'")
    private String message;
    @Basic
    @Column(name = "controltype", columnDefinition = "varchar(20) COMMENT '控件类型'")
    private String controltype;
    @Basic
    @Column(name = "controldata", columnDefinition = "varchar(255) COMMENT '控件类型'")
    private String controldata;
    @Basic
    @Column(name = "max", columnDefinition = "int COMMENT '最大值'")
    private Integer max;
    @Basic
    @Column(name = "min", columnDefinition = "int COMMENT '最小值'")
    private Integer min;
    @Basic
    @Column(name = "format", columnDefinition = "varchar(20) COMMENT '格式化'")
    private String format;
    @Basic
    @Column(name = "isshowcolumn", columnDefinition = "int default '1' COMMENT '是否显示字段{0:否,1:是}'")
    private Integer isshowcolumn;
    @Basic
    @Column(name = "iswhere", columnDefinition = "int default '1' COMMENT '是否显示条件{0:否,1:是}'")
    private Integer iswhere;
    @Basic
    @Column(name = "javawhere", columnDefinition = "varchar(20) COMMENT '条件类型{=:equals,%:like}'")
    private String javawhere;
    @Basic
    @Column(name = "javatype", columnDefinition = "varchar(20) COMMENT 'java类型{integer:integer,string:string}'")
    private String javatype;
    @Basic
    @Column(name = "selectarr", columnDefinition = "varchar(255) COMMENT '[{key:1,title:菜单}]'")
    private String selectarr;
    @Basic
    @Column(name = "sort", columnDefinition = "int COMMENT '排序：从小到大'")
    private Integer sort;


}
