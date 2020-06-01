package cn.cyyaw.config.table.controller;

import cn.cyyaw.common.entity.SelectEntity;
import cn.cyyaw.common.util.ResponseUtils;
import cn.cyyaw.common.util.WhyStringUtil;
import cn.cyyaw.config.table.service.CComponentsService;
import cn.cyyaw.config.table.table.entity.CComponents;
import cn.cyyaw.config.table.table.entityconst.CComponentsConst;
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
import java.util.List;
import java.util.Map;
import java.util.Date;

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
public class CComponentsController {

    @Autowired
    private CComponentsService cComponentsService;

    /**
     * 表:c_components ===> 所有:带条件
     */
    @GetMapping(value = "/findAllCComponents")
    public void findAllCComponents(HttpServletResponse response, String jsonStr, SelectEntity selectEntity) {
        List<CComponents> list = cComponentsService.findAll(jsonStr, selectEntity);
        ResponseUtils.responseJsonFilter(response, list, CComponentsConst.filterselectColumnArr);
    }

    /**
     * 分页条件查询
     */
    @GetMapping(value = "/findPageCComponents")
    public void findPageCComponents(HttpServletResponse response,String jsonStr,  SelectEntity selectEntity) {
        PageRequest pageRequest = JpaUtils.getPageRequest(selectEntity);
        Page<CComponents> page = cComponentsService.findPage(jsonStr, pageRequest);
        ResponseUtils.responseJsonFilter(response, PageUtil.pageFormat(page),CComponentsConst.filterselectColumnArr);
    }

    /**
     * 根据ID查询
     */
    @GetMapping(value = "/findIdCComponents")
    public void findIdCComponents(HttpServletResponse response,@RequestParam Integer id) {
        CComponents obj = cComponentsService.findId(id);
        ResponseUtils.responseJsonFilter(response, obj,CComponentsConst.filterselectColumnArr);
    }


    /**
     * 添加或修改
     */
    @PostMapping(value = "/saveCComponents")
    public void saveCComponents(HttpServletResponse response,@RequestBody CComponents cComponents) {
        CComponents obj = null;
        //添加
        Integer id = cComponents.getId();
        if (null == id) {
            //添加
            log.info("添加:{}", cComponents);
            WhyBeanUtils.filterField(cComponents, CComponentsConst.filteraddColumnArr);
            cComponents.setCreatetime(new Date());
            cComponents.setTid(WhyStringUtil.getUUID());
            obj = cComponentsService.save(cComponents);
        } else {
            //修改
            log.info("修改:{}", cComponents);
            CComponents cComponents1 = cComponentsService.findId(id);
            Assert.notNull(cComponents1, "操作失败！");
            WhyBeanUtils.filterField(cComponents, CComponentsConst.filteraddColumnArr);
            obj = cComponentsService.save(cComponents);
        }
        ResponseUtils.responseJsonFilter(response, obj,CComponentsConst.filterselectColumnArr);
    }

    /**
     * 删除
     */
    @PostMapping(value = "/delCComponents")
    public Map delCComponents( @RequestBody Integer idArr[]) {
        cComponentsService.del(idArr);
        return BaseConstants.tableDelSuccess;
    }

}
