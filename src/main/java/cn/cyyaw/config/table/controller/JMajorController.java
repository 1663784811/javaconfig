package cn.cyyaw.config.table.controller;


import cn.cyyaw.common.entity.SelectEntity;
import cn.cyyaw.common.util.ResponseUtils;
import cn.cyyaw.common.util.WhyStringUtil;
import cn.cyyaw.config.table.service.JMajorService;
import cn.cyyaw.config.table.table.entity.JMajor;
import cn.cyyaw.config.table.table.entityconst.JMajorConst;
import cn.cyyaw.jpa.BaseConstants;
import cn.cyyaw.jpa.JpaUtils;
import cn.cyyaw.jpa.PageUtil;
import cn.cyyaw.jpa.WhyBeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *
 * =========================  声名 START
 * 所有实体类： 属性都是小写
 * =========================  声名 END
 *
 * jsonChaXun - 查询json
 *      例：like_string_cjjlYhmc 对应 (1)_(2)_(3)
 *      (1) - 本条件进行查询时与值的对比关系
 *          like : where [字符串] like '%YYYY%'
 *          likeleft : where [字符串] like 'YYYY%'
 *          likeright : where [字符串] like '%YYYY'
 *          equals : where [字符串/数/日期] = YYY
 *          notequals : where [字符串/数/日期] != YYY
 *          ge : where [数/日期] >= YYY
 *          gt : where [数/日期] > YYY
 *          le : where [数/日期] <= YYY
 *          lt : where [数/日期] < YYY
 *      (2) - 本条件字段的数据类型
 *          string integer float double date
 *      (3) - 本条件对应字段的实体类（不区分大小写）
 *
 *
 * 简单查询条件  and or
 * json 格式：  {like_string_adminid:"abcdefg",like_string_username:"WHY"}
 * 对应的sql 语句：　　where  adminid like '%abcdefg%'  and username like '%WHY%'
 *
 * 复杂查询条件
 * json 格式
 * {or:{like_string_adminid:"abcdefg",like_string_username:"WHY"},like_string_username:"WHY"}
 * 对应的sql 语句： where ( adminid like '%abcdefg%' and username like '%WHY%' ) or username like '%WHY%'
 *
 *  jsonStr格式：      {like_string_adminid:"abcdefg",like_string_username:"WHY"}    传参： http://localhost:8080/admin?jsonStr={like_string_adminid:"abcdefg",like_string_username:"WHY"}
 *  sort格式：         admin_desc,adminname_asc                                      传参： http://localhost:8080/admin?sort=admin_desc,adminname_asc
 *  page格式：         1                                                             传参： http://localhost:8080/admin?page=1
 *  size格式：         30                                                            传参： http://localhost:8080/admin?size=30
 *  id格式:            abcddeffdfd                                                   传参： http://localhost:8080/admin?id=abcddeffdfd
 *  对象格式:          对象                                                           传参： http://localhost:8080/admin? 对象
 *  id数组格式:                                                                       传参： http://localhost:8080/admin? id数组
 *
 * 1.  查询所有         findAll表名     有参      jsonStr    sort
 *     分页条件查询     findPage表名    有参       jsonStr    sort    page    size
 *     根据ID查询      findId表名       有参      id
 *
 * 2.  添加           add表名          有参       对象
 *
 * 3.  修改           update表名       有参       对象
 *
 * 4.  删除           del表名          有参       id数组
 *
 *  </pre>
 */
@Slf4j
@RequestMapping("/admin")
@RestController
public class JMajorController {

    @Autowired
    private JMajorService jMajorService;

    /**
     * 表:j_major ===> 所有:带条件
     */
    @GetMapping(value = "/findAllJMajor")
    public void findAllJMajor(HttpServletResponse response, String jsonStr, SelectEntity selectEntity) {
        List<JMajor> list = jMajorService.findAll(jsonStr, selectEntity);
        ResponseUtils.responseJsonFilter(response, list, JMajorConst.filterselectColumnArr);
    }

    /**
     * 分页条件查询
     */
    @GetMapping(value = "/findPageJMajor")
    public void findPageJMajor(HttpServletResponse response,String jsonStr,  SelectEntity selectEntity) {
        PageRequest pageRequest = JpaUtils.getPageRequest(selectEntity);
        Page<JMajor> page = jMajorService.findPage(jsonStr, pageRequest);
        ResponseUtils.responseJsonFilter(response, PageUtil.pageFormat(page),JMajorConst.filterselectColumnArr);
    }

    /**
     * 根据ID查询
     */
    @GetMapping(value = "/findIdJMajor")
    public void findIdJMajor(HttpServletResponse response,@RequestParam Integer id) {
        JMajor obj = jMajorService.findId(id);
        ResponseUtils.responseJsonFilter(response, obj,JMajorConst.filterselectColumnArr);
    }


    /**
     * 添加或修改
     */
    @PostMapping(value = "/saveJMajor")
    public void saveJMajor(HttpServletResponse response,@RequestBody JMajor jMajor) {
        JMajor obj = null;
        //添加
        Integer id = jMajor.getId();
        if (null == id) {
            //添加
            log.info("添加:{}", jMajor);
            WhyBeanUtils.filterField(jMajor, JMajorConst.filteraddColumnArr);
            jMajor.setCreatetime(new Date());
            jMajor.setTid(WhyStringUtil.getUUID());
            obj = jMajorService.save(jMajor);
        } else {
            //修改
            log.info("修改:{}", jMajor);
            JMajor jMajor1 = jMajorService.findId(id);
            Assert.notNull(jMajor1, "操作失败！");
            WhyBeanUtils.filterField(jMajor, JMajorConst.filteraddColumnArr);
            obj = jMajorService.save(jMajor);
        }
        ResponseUtils.responseJsonFilter(response, obj,JMajorConst.filterselectColumnArr);
    }

    /**
     * 删除
     */
    @PostMapping(value = "/delJMajor")
    public Map delJMajor(@RequestBody Integer idArr[]) {
        jMajorService.del(idArr);
        return BaseConstants.tableDelSuccess;
    }

}
