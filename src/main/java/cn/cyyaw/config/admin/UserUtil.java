package cn.cyyaw.config.admin;

import cn.cyyaw.config.config.shiro.ShiroEnum;
import cn.cyyaw.config.table.table.entity.TAdmin;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

public final class UserUtil {

    public static TAdmin getAdminInfo(){
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        Object attribute = session.getAttribute(ShiroEnum.USERINFO);
        if(attribute instanceof TAdmin){
            return (TAdmin) attribute;
        }else{
            return null;
        }
    }

}
