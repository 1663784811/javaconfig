package cn.cyyaw.config.admin.service;


import cn.cyyaw.config.table.table.entity.TAdmin;
import cn.cyyaw.config.table.table.entity.TPower;
import cn.cyyaw.config.table.table.entity.TRole;

import java.util.List;

/**
 * 获取登录信息接口
 */
public interface LoginService {


    /**
     * 获取用户信息
     *
     * @param account
     * @return
     */
    TAdmin getLoignInfo(String account);

    /**
     * 获取角色列表
     *
     * @param tid
     * @return
     */
    List<TRole> getRolesByTAdminTid(String tid);


    /**
     * 获取权限列表
     *
     * @param tid
     * @return
     */
    List<TPower> getTPowerByTAdminTid(String tid);

    /**
     * 注册
     *
     * @param tAdmin
     * @return
     */
    TAdmin register(TAdmin tAdmin);

    /**
     * 修改密码
     *
     * @param password
     * @param newpassword
     * @return
     */
    TAdmin updatePassword(String password, String newpassword);

    /**
     * 修改个人信息
     *
     * @param tAdmin
     * @return
     */
    TAdmin updateUserInfo(TAdmin tAdmin);

}
