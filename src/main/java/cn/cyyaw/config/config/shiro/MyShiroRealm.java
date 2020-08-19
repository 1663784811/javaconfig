package cn.cyyaw.config.config.shiro;

import cn.cyyaw.common.util.WhyException;
import cn.cyyaw.config.admin.service.LoginService;
import cn.cyyaw.config.table.table.entity.tadmin.TAdmin;
import cn.cyyaw.config.table.table.entity.tadmin.TPower;
import cn.cyyaw.config.table.table.entity.tadmin.TRole;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class MyShiroRealm extends AuthorizingRealm {

    @Value("${shiro.hashAlgorithmName}")
    private String hashAlgorithmName;
    @Value("${shiro.hashIterations}")
    private int hashIterations;

    @Autowired
    private LoginService loginService;


    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Session session = SecurityUtils.getSubject().getSession();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //角色集
        simpleAuthorizationInfo.setRoles((Set<String>) session.getAttribute(ShiroEnum.ROLE));
        //权限集
        simpleAuthorizationInfo.setStringPermissions((Set<String>) session.getAttribute(ShiroEnum.POWER));
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户的输入的账号.
        String account = (String) token.getPrincipal();
        TAdmin tAdmin = loginService.getLoignInfo(account);
        Date canlogintime = tAdmin.getCanlogintime();
        if ((tAdmin.getStatus() != null && tAdmin.getStatus() == 10) || (null != canlogintime && canlogintime.getTime() >= new Date().getTime())) {
            if (null != canlogintime) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format = simpleDateFormat.format(canlogintime);
                new WhyException("你的账号已经被锁定,在" + format + "解锁！");
            } else {
                new WhyException("你的账号已经被锁定");
            }
        }
        ByteSource credentialsSalt = ByteSource.Util.bytes(tAdmin.getSalt() + "");
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                tAdmin.getAccount(),  //用户名
                tAdmin.getPassword(), //密码
                credentialsSalt,  //盐
                getName()  //realm name
        );
        Session session = SecurityUtils.getSubject().getSession();
        //缓存用户信息  START
        session.setAttribute(ShiroEnum.USERINFO, tAdmin);
        //角色
        List<TRole> rolesByTAdminTid = loginService.getRolesByTAdminTid(tAdmin.getTid());
        session.setAttribute(ShiroEnum.ROLE, rolesByTAdminTid);
        //权限
        List<TPower> tPowerByTAdminTid = loginService.getTPowerByTAdminTid(tAdmin.getTid());
        session.setAttribute(ShiroEnum.POWER, tPowerByTAdminTid);
        //=========  缓存用户信息 END
        return authenticationInfo;
    }


}
