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
@Table(name = "a_article")
@org.hibernate.annotations.Table(appliesTo = "a_article", comment = "文章")
public class AArticle implements Serializable{

    private static final long serialVersionUID = 1589123890069706L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic
    @Column(name = "id", unique = true, nullable = false, columnDefinition = "int auto_increment COMMENT 'id'")
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
    @Column(name = "title", columnDefinition = "varchar(255) COMMENT '标题'")
    private String title;
    @Basic
    @Column(name = "author", length = 32, columnDefinition = "varchar(32) COMMENT '作者'")
    private String author;
    @Basic
    @Column(name = "content", columnDefinition = "text COMMENT '内容'")
    private String content;
    @Basic
    @Column(name = "mssagetype", length = 10, columnDefinition = "int COMMENT '消息类型'")
    private Integer mssagetype;
    @Basic
    @Column(name = "orginal", length = 10, columnDefinition = "int COMMENT '是否原创{0:否,1:是}'")
    private Integer orginal;
    @Basic
    @Column(name = "publishtime", columnDefinition = "datetime COMMENT '发布时间'")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishtime;
    @Basic
    @Column(name = "status", length = 10, columnDefinition = "int COMMENT '状态'")
    private Integer status;
    @Basic
    @Column(name = "url", columnDefinition = "varchar(255) COMMENT 'url'")
    private String url;

    @Basic
    @Column(name = "img", columnDefinition = "varchar(255) COMMENT '图片'")
    private String img;





}
