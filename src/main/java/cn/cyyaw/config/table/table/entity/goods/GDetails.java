package cn.cyyaw.config.table.table.entity.goods;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "g_details")
@org.hibernate.annotations.Table(appliesTo = "g_details", comment = "商品详情表")
public class GDetails implements Serializable {
    private static final long serialVersionUID = 156837826623933758L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic
    @Column(name = "id", unique = true, nullable = false, columnDefinition = "int auto_increment COMMENT 'id'")
    private Integer id;
    @Basic
    @Column(name = "goodsid", columnDefinition = "varchar(32) not null COMMENT '商品表ID/skuID'")
    private String goodsid;
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
    @Column(name = "details",  columnDefinition = "text COMMENT '商品详情'")
    private String details;
}
