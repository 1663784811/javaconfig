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
@Table(name = "g_store_goods_sku")
@org.hibernate.annotations.Table(appliesTo = "g_store_goods_sku", comment = "门店商品sku信息")
public class GStoreGoodsSku implements Serializable {

    private static final long serialVersionUID = 156878266734233758L;

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
    @Column(name = "storegoodsid", unique = true, columnDefinition = "varchar(32) not null COMMENT '商品g_store_goods表ID'")
    private String storegoodsid;

    @Basic
    @Column(name = "goodsid", columnDefinition = "varchar(32) not null COMMENT '商品g_goods表ID'")
    private String goodsid;

    @Basic
    @Column(name = "skuid", columnDefinition = "varchar(32) not null COMMENT '所属门店e_storeid表ID'")
    private String skuid;

    @Basic
    @Column(name="price", columnDefinition = "decimal(18,2 ) COMMENT '价格'")
    private BigDecimal price;
    @Basic
    @Column(name="number", columnDefinition = "int not null default '0' COMMENT '虚拟库存数量'")
    private Integer number;

}
