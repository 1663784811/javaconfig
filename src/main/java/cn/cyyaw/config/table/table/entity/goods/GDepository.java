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
@Table(name = "g_depository")
@org.hibernate.annotations.Table(appliesTo = "g_depository", comment = "仓库表")
public class GDepository implements Serializable {
    private static final long serialVersionUID = 13687826273933758L;

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
    @Column(name = "enterpriseid", columnDefinition = "varchar(32) COMMENT '所属企业e_enterprise表ID'")
    private String enterpriseid;


    @Basic
    @Column(name = "storeid", columnDefinition = "varchar(32) COMMENT '所属门店e_storeid表ID'")
    private String storeid;

    @Basic
    @Column(name = "name",  columnDefinition = "varchar(255) not null COMMENT '仓库名称'")
    private String name;
    @Basic
    @Column(name = "address",   length = 32, columnDefinition = "varchar(255) COMMENT '仓库地址'")
    private String address;

    @Basic
    @Column(name = "type", columnDefinition = "int default '0' COMMENT '仓库类型{1:正品仓库,2:赠品仓库}'")
    private String type;


}
