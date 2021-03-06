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
@Table(name = "g_store_goods")
@org.hibernate.annotations.Table(appliesTo = "g_store_goods", comment = "门店商品")
public class GStoreGoods implements Serializable {

    private static final long serialVersionUID = 156878232734233758L;

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
    @Column(name = "goodsid", unique = true, columnDefinition = "varchar(32) not null COMMENT '商品ID'")
    private String goodsid;

    @Basic
    @Column(name = "storeid", columnDefinition = "varchar(32) not null COMMENT '所属门店e_storeid表ID'")
    private String storeid;

    @Basic
    @Column(name = "istop", columnDefinition = "int not null default '0' COMMENT '是否置顶{0:否,1:是}'")
    private Integer istop;

    @Basic
    @Column(name="lowprice", columnDefinition = "decimal(18,2 ) COMMENT '最低价格'")
    private BigDecimal lowprice;
    @Basic
    @Column(name="highprice", columnDefinition = "decimal(18,2 ) COMMENT '最高价格'")
    private BigDecimal highprice;

    @Basic
    @Column(name="number", columnDefinition = "int not null default '0' COMMENT '虚拟库存数量'")
    private Integer number;



}
