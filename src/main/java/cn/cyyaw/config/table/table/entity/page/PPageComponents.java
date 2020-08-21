package cn.cyyaw.config.table.table.entity.page;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "p_page_components")
@org.hibernate.annotations.Table(appliesTo = "p_page_components", comment = "")
public class PPageComponents implements Serializable{
    private static final long serialVersionUID = 1589474778795994L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic
    @Column(name = "id", unique = true, nullable = false, columnDefinition = "int auto_increment COMMENT 'id'")
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
    @Column(name="name", columnDefinition = "varchar(32) COMMENT '名称'")
    private String name;
    @Basic
    @Column(name="sort", columnDefinition = "int COMMENT '排序'")
    private Integer sort;
    @Basic
    @Column(name="pageid", columnDefinition = "varchar(32) COMMENT '页面ID'")
    private String pageid;

    @Basic
    @Column(name = "type", columnDefinition = "varchar(15) COMMENT '组件类型{1:表格,2:输入框,3:下拉框}'")
    private String type;

    @Basic
    @Column(name = "[table]", columnDefinition = "varchar(32) COMMENT '数据表'")
    private String table;
}
