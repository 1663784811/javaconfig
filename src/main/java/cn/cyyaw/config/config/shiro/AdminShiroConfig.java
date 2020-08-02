package cn.cyyaw.config.config.shiro;


import cn.cyyaw.config.config.shiro.filter.LoginShiroFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class AdminShiroConfig {

    @Value("${shiro.hashAlgorithmName}")
    private String hashAlgorithmName;

    @Value("${shiro.hashIterations}")
    private int hashIterations;


    @Bean
    public ShiroFilterFactoryBean shirFilterAdmin(@Qualifier("securityManagerAdmin") SecurityManager securityManager) {
        //自定义拦截器
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();//获取filters
        filters.put("loginFilter", new LoginShiroFilter()); //=============  登录拦截器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //拦截器.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/shiro/admin/log**", "anon");
        filterChainDefinitionMap.put("/shiro/admin/register", "anon");
        filterChainDefinitionMap.put("/admin/check**", "anon");
        filterChainDefinitionMap.put("/admin/check**/**", "anon");
        filterChainDefinitionMap.put("/home/**", "anon");
        filterChainDefinitionMap.put("/web/**", "anon");
        filterChainDefinitionMap.put("/pay/**", "anon");
        filterChainDefinitionMap.put("/notify/order/**", "anon");
        filterChainDefinitionMap.put("/config/**", "anon");
        filterChainDefinitionMap.put("/weixin/**", "anon");
        filterChainDefinitionMap.put("/recharge/**", "loginFilter");
        filterChainDefinitionMap.put("/wx/**", "anon");
        filterChainDefinitionMap.put("/**", "loginFilter");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 安全管理器
     */
    @Bean
    public SecurityManager securityManagerAdmin( MyShiroRealm myShiroRealm, SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        securityManager.setRealm(adminShiroRealm);
        List<Realm> ob = new ArrayList<>();
        ob.add(myShiroRealm);

        securityManager.setRealms(ob);
        securityManager.setSessionManager(sessionManager);// 自定义session管理 使用redis
        return securityManager;
    }


    /**
     * 自定义sessionManager
     */
    @Bean
    public SessionManager sessionManagerAdmin() {
        MySessionManager mySessionManager = new MySessionManager();
        return mySessionManager;
    }


    /**
     * 自己的Realm
     */
    @Bean
    public MyShiroRealm myShiroRealm(@Qualifier("hashedCredentialsMatcherAdmin") HashedCredentialsMatcher hashedCredentialsMatcher) {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return myShiroRealm;
    }

    /**
     * 凭证匹配器
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcherAdmin() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(hashAlgorithmName);//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(hashIterations);//散列的次数，比如散列两次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }


    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreatorAdmin() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }


    /**
     * 配置shiro框架提供的切面类，用于创建代理对象
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisorAdmin(@Qualifier("securityManagerAdmin") SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


}
