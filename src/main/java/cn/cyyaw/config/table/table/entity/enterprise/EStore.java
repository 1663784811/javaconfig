package cn.cyyaw.config.table.table.entity.enterprise;


import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "e_store")
@org.hibernate.annotations.Table(appliesTo = "e_store", comment = "企业门店")
public class EStore implements Serializable {
    private static final long serialVersionUID = 15687262756870L;
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
    @Column(name = "enterpriseid", columnDefinition = "varchar(32) COMMENT '所属企业e_enterprise表ID'")
    private String enterpriseid;

    @Basic
    @Column(name = "code", unique = true, length = 32, columnDefinition = "varchar(32) not null COMMENT '门店编号(企业编号+门店号)'")
    private String code;

    @Basic
    @Column(name = "name", columnDefinition = "varchar(255) COMMENT '名称'")
    private String name;
    @Basic
    @Column(name = "url", columnDefinition = "varchar(255) COMMENT 'url地址'")
    private String url;
    @Basic
    @Column(name = "logo", columnDefinition = "varchar(255) COMMENT 'logo图片'")
    private String logo;

}






















