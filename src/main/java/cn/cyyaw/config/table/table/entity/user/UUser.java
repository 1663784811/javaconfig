package cn.cyyaw.config.table.table.entity.user;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "u_user")
@org.hibernate.annotations.Table(appliesTo = "u_user", comment = "用户表")
public class UUser implements Serializable {
    private static final long serialVersionUID = 15873011723682985L;

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
    @Column(name = "account", length = 32, columnDefinition = "varchar(32) COMMENT '账号'")
    private String account;
    @Basic
    @Column(name = "password", length = 32, columnDefinition = "varchar(32) COMMENT '密码'")
    private String password;
    @Basic
    @Column(name = "truename", length = 32, columnDefinition = "varchar(32) COMMENT '真实姓名'")
    private String truename;
    @Basic
    @Column(name = "phone", length = 15, columnDefinition = "varchar(15) COMMENT '手机号'")
    private String phone;
    @Basic
    @Column(name = "nickname", length = 32, columnDefinition = "varchar(32) COMMENT '昵称'")
    private String nickname;
    @Basic
    @Column(name = "face", length = 255, columnDefinition = "varchar(255) COMMENT '用户头像'")
    private String face;

    @Basic
    @Column(name = "canlogintime", length = 19, columnDefinition = "datetime COMMENT '可登录时间'")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date canlogintime;
    @Basic
    @Column(name = "email", columnDefinition = "varchar(255) COMMENT '邮箱'")
    private String email;
    @Basic
    @Column(name = "ip", length = 60, columnDefinition = "varchar(60) COMMENT '最后登录IP'")
    private String ip;
    @Basic
    @Column(name = "lastlogintime", length = 19, columnDefinition = "datetime COMMENT '最后登录时间'")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastlogintime;
    @Basic
    @Column(name = "salt", length = 32, columnDefinition = "varchar(32) COMMENT '加密盐'")
    private String salt;
    @Basic
    @Column(name = "status", length = 10, columnDefinition = "int COMMENT '状态{0:正常,1:暂时锁定,2:永久锁定}'")
    private Integer status;

    @Basic
    @Column(name = "type", length = 10, columnDefinition = "int COMMENT '会员类型{0:普通会员,1:客服}'")
    private Integer type;

    @Basic
    @Column(name = "adminid", unique = true, columnDefinition = "varchar(32) COMMENT '客服t_admin表id'")
    private String adminid;
}
