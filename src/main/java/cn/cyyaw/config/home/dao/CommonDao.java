package cn.cyyaw.config.home.dao;

import java.util.List;
import java.util.Map;

public interface CommonDao {


    List<Map<String, Object>> query();

    List<Map<String, Object>> update();

    List<Map<String, Object>> delete();
}
