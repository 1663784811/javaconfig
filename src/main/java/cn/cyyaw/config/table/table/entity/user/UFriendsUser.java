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
@Table(name = "u_friends_user")
@org.hibernate.annotations.Table(appliesTo = "u_friends_user", comment = "好友关联表")
public class UFriendsUser implements Serializable {

    private static final long serialVersionUID = 1366301723582985L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic
    @Column(name = "id", unique = true, nullable = false, columnDefinition = "int auto_increment COMMENT 'id'")
    private Integer id;
    @Basic
    @Column(name = "tid", unique = true, length = 32, columnDefinition = "varchar(32) not null COMMENT 'tid'")
    private String tid;
    @Basic
    @Column(name = "del", length = 10, columnDefinition = "int not null COMMENT '是否删除{0:否,1:是}'")
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
    @Column(name = "userid", columnDefinition = "varchar(32) not null COMMENT 'u_user用户表(当前用户)id'")
    private String userid;

    @Basic
    @Column(name = "touserid", columnDefinition = "varchar(32) not null COMMENT 'u_user用户表(好友)id'")
    private String touserid;


    @Basic
    @Column(name = "friendsgroupid", columnDefinition = "varchar(32) COMMENT 'u_friends_group好友分组表id'")
    private String friendsgroupid;


}
