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
@Table(name = "u_group_user")
@org.hibernate.annotations.Table(appliesTo = "u_group_user", comment = "群用户关联表")
public class UGroupUser implements Serializable {

    private static final long serialVersionUID = 13663052723582985L;

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
    @Column(name = "userid", columnDefinition = "varchar(32) not null COMMENT 'u_user用户表id'")
    private String userid;

    @Basic
    @Column(name = "groupid", columnDefinition = "varchar(32) not null COMMENT 'u_groupid表id'")
    private String groupid;

    @Basic
    @Column(name = "grade", length = 10, columnDefinition = "int not null default '0' COMMENT '等级'")
    private Integer grade;

    @Basic
    @Column(name = "type", length = 10, columnDefinition = "int not null default '0' COMMENT '用户类型{0:普通人,1:群主,2:管理员}'")
    private Integer type;


}
