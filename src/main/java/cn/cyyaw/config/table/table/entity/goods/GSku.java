package cn.cyyaw.config.table.table.entity.goods;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "g_sku")
@org.hibernate.annotations.Table(appliesTo = "g_sku", comment = "商品sku表")
public class GSku implements Serializable {
    private static final long serialVersionUID = 15127826273933758L;

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
    @Column(name = "goodsid", columnDefinition = "varchar(32) not null COMMENT '商品表ID'")
    private String goodsid;

    @Basic
    @Column(name="price", columnDefinition = "decimal(18,2) COMMENT '价格'")
    private BigDecimal price;
    @Basic
    @Column(name = "photo",  columnDefinition = "varchar(255) COMMENT '商品图片'")
    private String photo;
    @Basic
    @Column(name = "attribute",  columnDefinition = "varchar(255) COMMENT 'json商品属性'")
    private String attribute;


}
