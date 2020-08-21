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
@Table(name = "pr_message")
@org.hibernate.annotations.Table(appliesTo = "pr_message", comment = "临时消息表")
public class PrMessage  implements Serializable {

    private static final long serialVersionUID = 13663011723682985L;

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
    @Column(name = "pruserid",length = 32, columnDefinition = "varchar(32) not null COMMENT '当前用户pr_user表tid'")
    private String pruserid;
    @Basic
    @Column(name = "topruserid",length = 32, columnDefinition = "varchar(32) not null COMMENT '发送给某用户pr_user表tid'")
    private String topruserid;
    @Basic
    @Column(name = "type",length = 10, columnDefinition = "int  not null COMMENT '消息类型{0:文字,1:图片,2:视频}'")
    private Integer type;
    @Basic
    @Column(name = "content", columnDefinition = "text COMMENT '消息内容'")
    private String content;

    @Basic
    @Column(name = "status",length = 10, columnDefinition = "int COMMENT '消息状态{0:未读,1:已读}'")
    private Integer status;

}
