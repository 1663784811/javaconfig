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
@Table(name = "g_depository_goods")
@org.hibernate.annotations.Table(appliesTo = "g_depository_goods", comment = "仓库商品表")
public class GDepositoryGoods implements Serializable {
    private static final long serialVersionUID = 13787826273933758L;

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
    @Column(name = "depositoryid", columnDefinition = "varchar(32) COMMENT '所属仓库g_depository表ID'")
    private String depositoryid;
    @Basic
    @Column(name = "goodsid", columnDefinition = "varchar(32) COMMENT '所属商品g_goods表ID'")
    private String goodsid;


    @Basic
    @Column(name = "number",  columnDefinition = "int not null default '0' COMMENT '数量'")
    private Integer number;

    @Basic
    @Column(name = "type",  columnDefinition = "int not null COMMENT '类型{1:入库,2:出库}'")
    private Integer type;

    @Basic
    @Column(name = "describe", columnDefinition = "varchar(255) COMMENT '描述'")
    private String describe;

}
