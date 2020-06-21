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
@Table(name = "pr_user")
@org.hibernate.annotations.Table(appliesTo = "pr_user", comment = "临时用户表")
public class PrUser implements Serializable {
    private static final long serialVersionUID = 13873011723682985L;

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
    @Column(name = "uuserid", columnDefinition = "varchar(32) COMMENT '用户表ID'")
    private String uuserid;
    @Basic
    @Column(name = "truename", length = 32, columnDefinition = "varchar(32) COMMENT '临时真实姓名'")
    private String truename;
    @Basic
    @Column(name = "phone", length = 15, columnDefinition = "varchar(15) COMMENT '临时手机号'")
    private String phone;
    @Basic
    @Column(name = "nickname", length = 32, columnDefinition = "varchar(32) COMMENT '临时昵称'")
    private String nickname;
    @Basic
    @Column(name = "face", length = 255, columnDefinition = "varchar(255) COMMENT '临时用户头像'")
    private String face;
    @Basic
    @Column(name = "ip", length = 60, columnDefinition = "varchar(60) COMMENT '临时最后登录IP'")
    private String ip;
    @Basic
    @Column(name = "lastlogintime", length = 19, columnDefinition = "datetime COMMENT '临时最后登录时间'")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastlogintime;
}
