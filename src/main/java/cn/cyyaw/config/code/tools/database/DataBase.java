package cn.cyyaw.config.code.tools.database;


import cn.cyyaw.config.code.database.TypeTools;
import cn.cyyaw.config.code.tools.entity.java.*;
import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {

    private Connection connection;

    public DataBase(String driver, String url, String user, String pwd) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        connection = DriverManager.getConnection(url, user, pwd);
    }

    /**
     * 获取数据表信息---- 列表
     */
    public List<JavaData> getTableList(String table) throws SQLException {
        ArrayList<JavaData> javaDataArrayList = new ArrayList<JavaData>();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet tables = metaData.getTables(connection.getCatalog(), "%", "%", null);
        while (tables.next()) {
            //数据表名
            String table_name = tables.getString("TABLE_NAME");
            if (null != table_name && !table_name.equals("")) {
                JavaData javaData = new JavaData();
                javaData.setDatabase(connection.getCatalog());                           //数据库名
                javaData.setTable(table_name);                                           //表名
                javaData.setTablenote(getTableNotes(table_name));                        //表注释
                javaData.setTabletype(tables.getString("TABLE_TYPE"));       //数据表类型  （表 ， 视图，。。。）
                List<JavaColumn> javaColumnList = getColumnList(table_name);
                javaData.setJavacolumns(javaColumnList);                                 //字段
                if(StringUtils.isEmpty(table)){
                    javaDataArrayList.add(javaData);
                }else if(table.equals(table_name)){
                    javaDataArrayList.add(javaData);
                }
            }
        }
        return javaDataArrayList;
    }


    /**
     * 获取表字段信息----- 列表
     *
     * @param tableName 数据表名
     * @throws SQLException
     */
    public List<JavaColumn> getColumnList(String tableName) throws SQLException {

        ArrayList<JavaColumn> javaColumnList = new ArrayList<JavaColumn>();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet columns = metaData.getColumns(connection.getCatalog(), "%", tableName, "%");
        while (columns.next()) {
            JavaColumn javaColumn = new JavaColumn();
            String type = columns.getString("TYPE_NAME");
            //===============================
            javaColumn.setColumnname(columns.getString("COLUMN_NAME"));    //字段名
            javaColumn.setDbtype(type);                                          //数据库字段类型
            javaColumn.setLength(columns.getInt("COLUMN_SIZE"));     //长度
            javaColumn.setJavatype(TypeTools.dbType2JavaType(type));             //java 类型
            javaColumn.setDefaultvalue(columns.getString("COLUMN_DEF"));//默认值
            javaColumn.setNote(columns.getString("REMARKS"));        //注释
            javaColumn.setIsautoincrement(columns.getString("IS_AUTOINCREMENT").equals("YES"));//是否自增加
            javaColumn.setIsnull(columns.getString("NULLABLE").equals("1"));  //是否可以为null
            //===============================
            javaColumnList.add(javaColumn);
        }
        //==== 获取索引
        List<IndexKey> indexKeyList = getIndexList(tableName);
        //==== 获取主键
        List<PrimaryKeys> primaryKeysList = getPrimaryKeys(tableName);
        //==== 获取外键
        List<ForeignKey> foreignKeyList = getForeignKeys(tableName);
        for (int i = 0; i < javaColumnList.size(); i++) {
            JavaColumn javaColumn = javaColumnList.get(i);
            String columnName = javaColumn.getColumnname();
            for (int j = 0; j < indexKeyList.size(); j++) {
                IndexKey indexKey = indexKeyList.get(j);
                if (indexKey.getColumnname().equals(columnName)) {
                    javaColumn.setIsindex(true);
                    javaColumn.setIsunique(indexKey.getIsunique());
                    javaColumn.setIndextype(indexKey.getIndextype());
                }
            }
            for (int j = 0; j < primaryKeysList.size(); j++) {
                PrimaryKeys primaryKeys = primaryKeysList.get(j);
                if (primaryKeys.getColumnname().equals(columnName)) {
                    javaColumn.setIsprimary(true);
                }
            }
            for (int j = 0; j < foreignKeyList.size(); j++) {
                ForeignKey foreignKey = foreignKeyList.get(j);
                if (foreignKey.getFktablenote().equals(tableName) && foreignKey.getFkcolumnname().equals(columnName)) {
                    javaColumn.setPktablename(foreignKey.getPktablename());
                    javaColumn.setPkcolumnname(foreignKey.getPkcolumnname());
                }
            }
        }
        return javaColumnList;
    }


    /**
     * 获取索引
     */
    public List<IndexKey> getIndexList(String tableName) throws SQLException {
        List<IndexKey> list = new ArrayList<IndexKey>();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet indexInfo = metaData.getIndexInfo(connection.getCatalog(), "%", tableName, false, false);
        while (indexInfo.next()) {
            IndexKey indexKey = new IndexKey();
            indexKey.setTablename(indexInfo.getString("TABLE_NAME"));
            indexKey.setColumnname(indexInfo.getString("COLUMN_NAME"));
            indexKey.setIsunique(indexInfo.getString("NON_UNIQUE").equals("0"));
            indexKey.setIndextype(indexInfo.getString("TYPE"));
            list.add(indexKey);
        }
        return list;
    }


    /**
     * 获取主键列表
     *
     * @param tableName 数据表名
     * @throws SQLException
     */
    public List<PrimaryKeys> getPrimaryKeys(String tableName) throws SQLException {
        List<PrimaryKeys> list = new ArrayList<PrimaryKeys>();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet primaryKeys = metaData.getPrimaryKeys(connection.getCatalog(), "%", tableName);
        while (primaryKeys.next()) {
            PrimaryKeys primaryKeysList = new PrimaryKeys();
            primaryKeysList.setTablename(primaryKeys.getString("TABLE_NAME"));
            primaryKeysList.setColumnname(primaryKeys.getString("COLUMN_NAME"));
            list.add(primaryKeysList);
        }
        return list;
    }


    /**
     * 获取外键列表
     */
    public List<ForeignKey> getForeignKeys(String taleName) throws SQLException {
        List<ForeignKey> list = new ArrayList<ForeignKey>();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet foreignKeyResultSet = metaData.getImportedKeys(connection.getCatalog(), "%", taleName);
        while (foreignKeyResultSet.next()) {
            ForeignKey foreignKey = new ForeignKey();
            foreignKey.setFktablenote(foreignKeyResultSet.getString("FKTABLE_NAME"));
            foreignKey.setFkcolumnname(foreignKeyResultSet.getString("FKCOLUMN_NAME"));
            foreignKey.setPktablename(foreignKeyResultSet.getString("PKTABLE_NAME"));
            foreignKey.setPkcolumnname(foreignKeyResultSet.getString("PKCOLUMN_NAME"));
            list.add(foreignKey);
        }
        return list;
    }


    /**
     * 获取表的注释
     *
     * @param tableName
     * @return
     */
    public String getTableNotes(String tableName) throws SQLException {
        String notes = null;
        ResultSet rsBiaoGe = connection.prepareStatement("show table status where Name = '" + tableName + "'").executeQuery();
        if (rsBiaoGe != null && rsBiaoGe.next()) {
            notes = rsBiaoGe.getString("Comment");
        }
        return notes;
    }


}















