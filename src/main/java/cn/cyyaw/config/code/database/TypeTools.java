package cn.cyyaw.config.code.database;


import cn.cyyaw.config.code.entity.java.JavaColumn;
import cn.cyyaw.config.code.entity.vue.Filters;
import cn.cyyaw.config.code.entity.vue.VueJson;

import java.util.ArrayList;
import java.util.List;

/**
 * 类型转换工具
 */
public class TypeTools {


    /**
     * 数据库类型转换成java类型
     *
     * @param type
     * @return
     */
    public static String dbType2JavaType(String type) {
        String objType = null;
        if (type.equals("INT") || type.equals("int")
                || type.equals("TINYINT") || type.equals("tinyint")
                || type.equals("SMALLINT") || type.equals("smallint")
                || type.equals("MEDIUMINT") || type.equals("mediumint")
        ) {
            objType = "Integer";
        } else if (type.equals("BIGINT") || type.equals("bigint")) {
            objType = "BigInteger";
        } else if (type.equals("FLOAT") || type.equals("float")) {
            objType = "Float";
        } else if (type.equals("DOUBLE") || type.equals("double")) {
            objType = "Double";
        } else if (type.equals("DATE") || type.equals("date")
                || type.equals("DATETIME") || type.equals("datetime")
                || type.equals("TIMESTAMP") || type.equals("timestamp")
                || type.equals("TIME") || type.equals("time")
                || type.equals("YEAR") || type.equals("year")
        ) {
            //============= 日期
            objType = "Date";

        } else if (type.equals("VARCHAR") || type.equals("varchar")
                || type.equals("TEXT") || type.equals("text")
                || type.equals("TINYTEXT") || type.equals("tinytext")
                || type.equals("MEDIUMTEXT") || type.equals("mediumtext")
                || type.equals("LONGTEXT") || type.equals("longtext")
        ) {
            //============= 数值字符
            objType = "String";
        } else if (type.equals("DECIMAL") || type.equals("decimal")) {
            //============= 数值字符
            objType = "BigDecimal";
        }
        return objType;
    }


    public static String javaType2CodeType(String type) {
        String objType = "";
        switch (type) {
            case "string":
                objType = "like";
                break;
            case "integer":
                objType = "equals";
                break;
            case "biginteger":
                objType = "equals";
                break;
            case "float":
                objType = "equals";
                break;
            case "double":
                objType = "equals";
                break;
            case "date":
                objType = "equals";
                break;
        }
        return objType;
    }

    /**
     * 数据库类型转换 组件类型
     *
     * @param type
     * @return
     */
    public static String javaType2ControlType(String type) {
        String objType = null;
        if (type.equals("INT") || type.equals("int")
                || type.equals("TINYINT") || type.equals("tinyint")
                || type.equals("SMALLINT") || type.equals("smallint")
                || type.equals("MEDIUMINT") || type.equals("mediumint")
                || type.equals("BIGINT") || type.equals("bigint")
        ) {
            objType = "integer";
        } else if (type.equals("FLOAT") || type.equals("float")
                || type.equals("DOUBLE") || type.equals("double")
        ) {
            objType = "float";
        } else if (type.equals("DATE") || type.equals("date")
        ) {
            //============= 日期
            objType = "date";
        } else if (type.equals("DATETIME") || type.equals("datetime")
                || type.equals("TIMESTAMP") || type.equals("timestamp")
                || type.equals("YEAR") || type.equals("year")
        ) {
            //============= 日期时间
            objType = "datetime";
        } else if (type.equals("TIME") || type.equals("time")) {
            //============= 时间
            objType = "time";
        } else if (type.equals("TEXT") || type.equals("text")
                || type.equals("MEDIUMTEXT") || type.equals("mediumtext")
                || type.equals("LONGTEXT") || type.equals("longtext")
        ) {
            //============= 数值字符
            objType = "textarea";
        } else {
            //============= 数值字符
            objType = "input";
        }
        return objType;
    }

    /**
     * 日期格式化
     *
     * @param dateType
     * @return
     */
    public static String dateType2Format(String dateType) {
        String str = "";
        switch (dateType) {
            case "date":
                str = "yyyy-MM-dd";
                break;
            case "datetime":
                str = "yyyy-MM-dd HH:mm:ss";
                break;
            case "time":
                str = "HH:mm:ss";
                break;
        }
        return str;
    }


    /**
     * @param javaColumnList
     * @return
     */
    public static List<VueJson> javaColumnList2VueJsonList(List<JavaColumn> javaColumnList) {
        ArrayList<VueJson> vueJsonArrayList = new ArrayList<VueJson>();
        if (javaColumnList != null) {
            for (int i = 0; i < javaColumnList.size(); i++) {
                vueJsonArrayList.add(javaColumn2VueJson(javaColumnList.get(i)));
            }
        }
        return vueJsonArrayList;
    }

    /**
     * @param javaColumn
     * @return
     */
    public static VueJson javaColumn2VueJson(JavaColumn javaColumn) {
        VueJson vueJson = new VueJson();
        if (null != javaColumn) {
            vueJson.setKey(javaColumn.getColumnName()); //key

            String note = javaColumn.getNote();
            List filters = new ArrayList<Filters>();
            if (null != note) {
                try {
                    int start = note.indexOf("{");
                    int end = note.indexOf("}");
                    if (start != -1 && end != -1) {
                        String tempstr = note.substring(start + 1, end );
                        String[] splitstr = tempstr.split(",");
                        for (int i = 0; i < splitstr.length; i++) {
                            Filters f = new Filters();
                            System.out.println(splitstr[i]);
                            String[] jsonstr = splitstr[i].split(":");
                            if (jsonstr.length == 2) {
                                f.setValue(jsonstr[0]);
                                f.setLabel(jsonstr[1]);
                                filters.add(f);
                            }
                        }
                        note = note.substring(0, start); //标题
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            vueJson.setFilters(filters);
            vueJson.setTitle(note); //标题
            vueJson.setType(javaColumn.getIsPrimary() ? "selection" : "html"); //类型
            vueJson.setLength(javaColumn.getLength());
            vueJson.setRegStr("");
            vueJson.setIsRequire(false);
            vueJson.setMessage(javaColumn.getNote());
            if (javaColumn.getIsPrimary()) {
                vueJson.setControlType("hidden");
            } else {
                vueJson.setControlType(javaType2ControlType(javaColumn.getDbType()));
            }
            vueJson.setFormat(dateType2Format(javaType2ControlType(javaColumn.getDbType())));

            vueJson.setIsWhere(!javaColumn.getIsPrimary());
            vueJson.setIsShowColumn(true);

            vueJson.setJavaType(dbType2JavaType(javaColumn.getDbType()).toLowerCase());
            vueJson.setJavaWhere(javaType2CodeType(dbType2JavaType(javaColumn.getDbType()).toLowerCase()));

        }
        return vueJson;
    }

}
