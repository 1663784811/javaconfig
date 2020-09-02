package cn.cyyaw.config.admin.service.impl;


import cn.cyyaw.common.util.StringUtilWHY;
import cn.cyyaw.common.util.WhyException;
import cn.cyyaw.config.admin.dao.TAdminDaoSystem;
import cn.cyyaw.config.admin.dao.TPowerSystem;
import cn.cyyaw.config.admin.dao.TRoleDaoSystem;
import cn.cyyaw.config.admin.service.LoginService;
import cn.cyyaw.config.config.shiro.ShiroEnum;
import cn.cyyaw.config.table.table.entity.tadmin.TAdmin;
import cn.cyyaw.config.table.table.entity.tadmin.TPower;
import cn.cyyaw.config.table.table.entity.tadmin.TRole;
import cn.cyyaw.jpa.WhyBeanUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    @Value("${shiro.hashAlgorithmName}")
    private String hashAlgorithmName;
    @Value("${shiro.hashIterations}")
    private int hashIterations;

    @Autowired
    private TAdminDaoSystem tAdminDaoSystem;

    @Autowired
    private TRoleDaoSystem tRoleDaoSystem;


    @Autowired
    private TPowerSystem tPowerSystem;



    @Override
    public TAdmin getLoignInfo(String account) {
        List<TAdmin> tAdmins = tAdminDaoSystem.getLoignInfo(account);
        if (tAdmins == null || tAdmins.size() == 0) {
            throw new WhyException("账号不存在");
        } else if (tAdmins.size() > 1) {
            throw new WhyException("账号异常请与管理员联系");
        }
        return tAdmins.get(0);
    }

    @Override
    public List<TRole> getRolesByTAdminTid(String tid) {
        return tRoleDaoSystem.getRolesByTAdminTid(tid);
    }

    @Override
    public List<TPower> getTPowerByTAdminTid(String tid) {
        return tPowerSystem.getTPowerByTAdminTid(tid);
    }

    /**
     * 管理员注册
     *
     * @param tAdmin
     * @return
     */
    @Override
    public TAdmin register(TAdmin tAdmin) {
        List<TAdmin> tAdmins = tAdminDaoSystem.getLoignInfo(tAdmin.getAccount());
        if (tAdmins != null && tAdmins.size() > 0) throw new WhyException("账号已经被注册");
        String tid = StringUtilWHY.getUUID();
        String salt = StringUtilWHY.getRandomString(20);
        String hash = new SimpleHash(hashAlgorithmName, tAdmin.getPassword(), salt, hashIterations).toString();
        TAdmin tAdmin1 = new TAdmin();
        tAdmin1.setAccount(tAdmin.getAccount());
        tAdmin1.setPassword(hash);
        tAdmin1.setTid(tid);
        tAdmin1.setSalt(salt);
        tAdmin1.setEmail(tAdmin.getEmail());
        tAdmin1.setNickname(tAdmin.getAccount());
        tAdmin1.setCanlogintime(new Date());
        tAdmin1.setCreatetime(new Date());
        tAdmin1.setStatus(0);
        return tAdminDaoSystem.save(tAdmin1);
    }

    /**
     * 修改密码
     *
     * @param password
     * @param newpassword
     * @return
     */
    @Override
    public TAdmin updatePassword(String password, String newpassword) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        TAdmin oldeTAdmin = (TAdmin) session.getAttribute(ShiroEnum.USERINFO);
        TAdmin tAdmin = tAdminDaoSystem.findByid(oldeTAdmin.getId());
        String hash = new SimpleHash(hashAlgorithmName, password, tAdmin.getSalt(), hashIterations).toString();
        if (!tAdmin.getPassword().equals(hash)) throw new WhyException("原密码错误");
        String hash1 = new SimpleHash(hashAlgorithmName, newpassword, tAdmin.getSalt(), hashIterations).toString();
        tAdmin.setPassword(hash1);
        return tAdminDaoSystem.save(tAdmin);
    }

    /**
     * 修改个人信息
     *
     * @param tAdmin
     * @return
     */
    @Override
    public TAdmin updateUserInfo(TAdmin tAdmin) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        TAdmin oldeTAdmin = (TAdmin) session.getAttribute(ShiroEnum.USERINFO);
        TAdmin tAdmin1 = tAdminDaoSystem.findByid(oldeTAdmin.getId());
        WhyBeanUtils.copyPropertiesAccess(tAdmin, tAdmin1, LoginConstants.UpdateColumnArr);
        TAdmin save = tAdminDaoSystem.save(tAdmin1);
        session.setAttribute(ShiroEnum.USERINFO, save);
        return save;
    }

}
