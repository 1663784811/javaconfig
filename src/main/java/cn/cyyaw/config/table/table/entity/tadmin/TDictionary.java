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
@Table(name = "t_dictionary")
@org.hibernate.annotations.Table(appliesTo = "t_dictionary", comment = "字典表")
public class TDictionary implements Serializable {
    private static final long serialVersionUID = 1587301175682985L;
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
    @Column(name = "type", length = 10, columnDefinition = "int not null COMMENT '字典类型'")
    private Integer type;

    @Basic
    @Column(name = "name", columnDefinition = "varchar(255) not null COMMENT '名称'")
    private String name;

    @Basic
    @Column(name = "[value]", columnDefinition = "varchar(255) COMMENT '值'")
    private String value;














}
