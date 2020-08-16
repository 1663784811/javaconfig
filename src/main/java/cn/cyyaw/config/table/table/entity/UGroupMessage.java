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
@Table(name = "u_group_message")
@org.hibernate.annotations.Table(appliesTo = "u_group_message", comment = "群消息")
public class UGroupMessage implements Serializable {

    private static final long serialVersionUID = 13663012723582985L;

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
    @Column(name = "groupid", columnDefinition = "varchar(32) not null COMMENT 'u_groupid群表id'")
    private String groupid;

    @Basic
    @Column(name = "userid", columnDefinition = "varchar(32) not null COMMENT 'u_user用户表id'")
    private String userid;

    @Basic
    @Column(name = "username", columnDefinition = "varchar(32) not null COMMENT '用户名'")
    private String username;

    @Basic
    @Column(name = "face", columnDefinition = "varchar(255) COMMENT '用户头像'")
    private String face;

    @Basic
    @Column(name = "type",length = 10, columnDefinition = "int  not null COMMENT '消息类型{0:文字,1:图片,2:视频}'")
    private Integer type;

    @Basic
    @Column(name = "content", columnDefinition = "text COMMENT '消息内容'")
    private String content;

}
