package cn.cyyaw.config.home.service;

import java.util.List;
import java.util.Map;

public interface CommonService {


    /**
     * 通用查询
     */
    List<Map<String, Object>> query();

    /**
     * 通用更新
     * @return
     */
    List<Map<String, Object>> update();

    /**
     * 通用删除
     */
    List<Map<String, Object>> delete();
}
