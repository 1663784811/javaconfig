package cn.cyyaw.config.code.tools.entity.vue;

import lombok.Data;

import java.util.List;

/**
 * vue JSON 文件实体
 */
@Data
public class VueJson {
    private String key;                 //键值
    private String title;               //名称
    private String type;                //列表类型
    private Integer length;             //长度
    private Boolean isRequire = false;  //是否必须
    private String regStr;              //正则表达式
    private String message;             //提示
    private String controlType;         //控件类型
    private Integer max;                //最大值
    private Integer min;                //最小值
    private String format;              //格式化
    private Boolean isWhere = true;     //是否是查询条件
    private Boolean isShowColumn = true;//是否显示列表
    private String javaWhere;           //java条件
    private String javaType;            //java类型
    private List<Filters> filters;      //过滤
}
