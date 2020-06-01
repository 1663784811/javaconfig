package cn.cyyaw.config.table.table.entityconst;

/**
 * 表常量
 * 类命名：表名Const
 */
public class CInterfaceConst {

    //允许查询列表
    public final static String selectColumnArr[] = {"id","createtime","name","note","parameter","returnvalue","tid","type","url"};
    //允许查询条件
    public final static String selectWhereArr[] = {"id","createtime","name","note","parameter","returnvalue","tid","type","url"};
    //允许更新字段
    public final static String updateColumnArr[] = {"id","createtime","name","note","parameter","returnvalue","tid","type","url"};
    //允许删除条件
    public final static String deleteColumnArr[] = {"id","createtime","name","note","parameter","returnvalue","tid","type","url"};
    //允许添加字段
    public final static String addColumnArr[] = {"id","createtime","name","note","parameter","returnvalue","tid","type","url"};


    //过滤字段==  查询列表
    public final static String filterselectColumnArr[] = {};
    //过滤字段==  查询条件
    public final static String filterselectWhereArr[] = {};
    //过滤字段==  更新字段
    public final static String filterupdateColumnArr[] = {};
    //过滤字段==  删除条件
    public final static String filterdeleteColumnArr[] = {};
    //过滤字段==  添加字段
    public final static String filteraddColumnArr[] = {};


}
