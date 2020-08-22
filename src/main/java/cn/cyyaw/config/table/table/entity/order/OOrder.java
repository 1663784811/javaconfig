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
@Table(name = "o_order")
@org.hibernate.annotations.Table(appliesTo = "o_order", comment = "订单表")
public class OOrder implements Serializable {
    private static final long serialVersionUID = 1568712426273933758L;

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
    @Column(name = "userid", columnDefinition = "varchar(32) COMMENT 'u_user用户表(当前用户)id'")
    private String userid;
    @Basic
    @Column(name = "username", columnDefinition = "varchar(32) COMMENT '用户名'")
    private String username;
    @Basic
    @Column(name = "enterpriseid", columnDefinition = "varchar(32) COMMENT '所属企业e_enterprise表ID'")
    private String enterpriseid;
    @Basic
    @Column(name = "enterprisename", columnDefinition = "varchar(32) COMMENT '企业名'")
    private String enterprisename;
    @Basic
    @Column(name = "storeid", columnDefinition = "varchar(32) COMMENT '所属门店e_storeid表ID'")
    private String storeid;
    @Basic
    @Column(name = "storename", columnDefinition = "varchar(32) COMMENT '门店名'")
    private String storename;

    //==============
    @Basic
    @Column(name = "orderno",  columnDefinition = "varchar(32) not null COMMENT '订单号'")
    private String orderno;

    @Basic
    @Column(name = "type", columnDefinition = "int not null default '0' COMMENT '订单类型{0:普通订单}'")
    private Integer type;

    @Basic
    @Column(name = "status", columnDefinition = "varchar(32) COMMENT '订单状态'")
    private String status;

    @Basic
    @Column(name = "addressid", columnDefinition = "varchar(32) COMMENT '地址ID'")
    private String addressid;
    @Basic
    @Column(name = "addressdetail", columnDefinition = "varchar(32) COMMENT '地址详情'")
    private String addressdetail;
    @Basic
    @Column(name = "phone", columnDefinition = "varchar(32) COMMENT '手机号'")
    private String phone;

    @Basic
    @Column(name = "description", columnDefinition = "varchar(255) COMMENT '描述'")
    private String description;

    @Basic
    @Column(name = "number", length = 10, columnDefinition = "int COMMENT '商品总数量'")
    private Integer number;

    @Basic
    @Column(name="amount", columnDefinition = "decimal(18,2) COMMENT '商品总价格(未加其它费用)'")
    private BigDecimal amount;

    @Basic
    @Column(name="expressprice", columnDefinition = "decimal(18,2) COMMENT '快递费用'")
    private BigDecimal expressprice;

    @Basic
    @Column(name="payableamount", columnDefinition = "decimal(18,2) COMMENT '最后实际应付(计算所有优惠和费用后)'")
    private BigDecimal payableamount;

    @Basic
    @Column(name = "paytype",  columnDefinition = "int COMMENT '最后付款方式{0:微信,1:支付宝}'")
    private Integer paytype;






}
