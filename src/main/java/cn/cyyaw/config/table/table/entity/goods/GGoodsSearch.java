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
@Table(name = "g_goods_search")
@org.hibernate.annotations.Table(appliesTo = "g_goods_search", comment = "商品搜索表")
public class GGoodsSearch  implements Serializable {

    private static final long serialVersionUID = 156878262734233758L;

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
    @Column(name = "storegoodsid", columnDefinition = "varchar(32) not null COMMENT '所属商品门店e_storeid表ID'")
    private String storegoodsid;

    @Basic
    @Column(name = "name",  columnDefinition = "varchar(255) not null COMMENT '商品名称'")
    private String name;
    @Basic
    @Column(name = "typecode",   length = 32, columnDefinition = "varchar(32) COMMENT '品类Code'")
    private String typecode;
    @Basic
    @Column(name = "brandcode",   length = 32, columnDefinition = "varchar(32) COMMENT '品牌Code'")
    private String brandcode;

    @Basic
    @Column(name="lowprice", columnDefinition = "decimal(18,2 ) COMMENT '最低价格'")
    private BigDecimal lowprice;
    @Basic
    @Column(name="highprice", columnDefinition = "decimal(18,2 ) COMMENT '最高价格'")
    private BigDecimal highprice;


    @Basic
    @Column(name = "istop", columnDefinition = "int not null default '0' COMMENT '是否置顶{0:否,1:是}'")
    private Integer istop;
    @Basic
    @Column(name = "evaluate", columnDefinition = "int not null default '0' COMMENT '评价{0:0星,1:1星,2:2星,3:3星,4:4星,5:5星}'")
    private Integer evaluate;




}
