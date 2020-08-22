package cn.cyyaw.config.table.table.entity.order;

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
@Table(name = "o_details")
@org.hibernate.annotations.Table(appliesTo = "o_details", comment = "订单详情表")
public class ODetails implements Serializable {
    private static final long serialVersionUID = 156873326273933758L;

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

    //==============
    @Basic
    @Column(name = "orderid", columnDefinition = "varchar(32) not null COMMENT 'o_order订单表ID'")
    private String orderid;

    @Basic
    @Column(name = "goodsid", columnDefinition = "varchar(32) not null COMMENT 'g_goods商品表ID'")
    private String goodsid;

    //==============
    @Basic
    @Column(name = "type", columnDefinition = "int not null default '0' COMMENT '商品类型{0:普通商品,1:赠品}'")
    private Integer type;


    @Basic
    @Column(name = "name",  columnDefinition = "varchar(255) not null COMMENT '商品名称'")
    private String name;
    @Basic
    @Column(name = "photo",  columnDefinition = "varchar(255) COMMENT '商品主图片'")
    private String photo;

    @Basic
    @Column(name="price", columnDefinition = "decimal(18,2) COMMENT '售单价格'")
    private BigDecimal price;

    @Basic
    @Column(name="lastprice", columnDefinition = "decimal(18,2) COMMENT '最后出售单价格'")
    private BigDecimal lastprice;

    @Basic
    @Column(name = "number", columnDefinition = "int COMMENT '商品数量'")
    private Integer number;







}
