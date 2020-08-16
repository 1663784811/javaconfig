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
@Table(name = "u_group")
@org.hibernate.annotations.Table(appliesTo = "u_group", comment = "用户群")
public class UGroup  implements Serializable {

    private static final long serialVersionUID = 13663012723682985L;

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
    @Column(name = "name", columnDefinition = "varchar(255) not null COMMENT '群名称'")
    private String name;

    @Basic
    @Column(name = "logo", columnDefinition = "varchar(255) default '' COMMENT '群logo'")
    private String logo;

    @Basic
    @Column(name = "introduce", columnDefinition = "varchar(255) default '' COMMENT '群简介'")
    private String introduce;

    @Basic
    @Column(name = "address", columnDefinition = "varchar(255) default '' COMMENT '地址'")
    private String address;

    @Basic
    @Column(name = "type", length = 10, columnDefinition = "int not null default '0' COMMENT '群类型{0:聊天}'")
    private Integer type;


}
