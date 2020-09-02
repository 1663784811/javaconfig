package cn.cyyaw.config.event.thread;

import cn.cyyaw.config.table.table.entity.enterprise.EEnterprise;


/**
 * 企业注册完成后（  初始化企业菜单、  ）
 */
public class RegisterEnterprise implements BaseThread{

    private EEnterprise eEnterprise;
    public RegisterEnterprise(EEnterprise eEnterprise){
        this.eEnterprise = eEnterprise;
    }

    @Override
    public void addPool() {

    }

    @Override
    public void run() {


    }
}
