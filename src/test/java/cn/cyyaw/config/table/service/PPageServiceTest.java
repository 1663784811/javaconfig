package cn.cyyaw.config.table.service;


import cn.cyyaw.config.home.dao.CommonDao;
import cn.cyyaw.config.table.table.dao.page.PPageDao;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class PPageServiceTest {


    @Autowired
    private PPageDao pPageDao;


    @Autowired
    private CommonDao commonDao;


    @Test
    void test01() {
        JSONObject js = new JSONObject();
        js.put("table", "c_page");
        JSONArray arr = new JSONArray();
        for (int i = 0; i < 10; i++) {
            JSONObject jso = new JSONObject();
            if(i%2== 0){
                jso.put("id", i);
            }

            jso.put("name", "name"+i);
            jso.put("ccsss", "name"+i);
            jso.put("note", "name"+i);
            if(i==0){
                jso.put("id", 54);
                jso.put("name", "测试54  "+i);
            }else if(i==2){
                jso.put("id", 55);
                jso.put("name", "测试55  "+i);
            }
            arr.add(jso);
        }
        js.put("data", arr);
        commonDao.update(js);


    }


}
