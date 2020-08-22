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
@Table(name = "o_discount")
@org.hibernate.annotations.Table(appliesTo = "o_discount", comment = "订单优惠表")
public class ODiscount implements Serializable {
    private static final long serialVersionUID = 156873426273133758L;

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
    @Column(name = "orderid", columnDefinition = "varchar(32) not null COMMENT 'o_order订单表ID'")
    private String orderid;
    @Basic
    @Column(name = "otherid", columnDefinition = "varchar(32) COMMENT '附ID'")
    private String otherid;

    @Basic
    @Column(name = "type", columnDefinition = "int not null default '0' COMMENT '优惠类型{0:优惠金额,1:赠送积分,2:优惠券}'")
    private Integer type;

    @Basic
    @Column(name="money", columnDefinition = "decimal(18,2) not null default '0' COMMENT '优惠额度'")
    private BigDecimal money;

    @Basic
    @Column(name = "description", columnDefinition = "varchar(255) COMMENT '优惠描述'")
    private String description;

}
