package com.vp.tw.shiro;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: YangTingCheng
 * @Date: 2020/09/24/下午 03:43
 * @Description:
 */
@SpringBootConfiguration
public class ShiroConfig {

    //要用的自定義realm
    @Bean
    public Realm realm() {
        return new CustomRealm();
    }

    //免改
    @Bean
    public DefaultWebSubjectFactory subjectFactory() {
        return new CustomWebSubjectFactory();
    }

    //禁用session 因為是API 故不儲存token在server
    //要驗證時就是拿cookie的JWT,分解出username,
    //再拿這個username+secret組token再做比較
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm());
        securityManager.setSubjectFactory(subjectFactory());
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator sessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        // 禁用 session 存储
        sessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(sessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        // 禁用 rememberMe
        securityManager.setRememberMeManager(null);
        return securityManager;
    }
    /**
     *
     * @Title: shiroFilterFactoryBean
     * @Description: 設定驗證位址 啟動springboot時將一併啟動
     * @param @return    設定檔案
     * @return ShiroFilterFactoryBean    返回型別
     * @throws
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());

        //建立一個HASHMAP
        Map<String, Filter> filterMap = new HashMap<>();
        //把自動過濾器放進來
        filterMap.put("customFilter", new CustomFilter());
        //再放到工廠中
        shiroFilterFactoryBean.setFilters(filterMap);

        //再建個MAP 定義哪個位址要用哪個過濾器 一一放入MAP
        //anon那些都內建的 查就知道啥意思了 anon=誰都可以訪問
        Map<String, String> filterChainDefinitionMap = new HashMap<>();
        filterChainDefinitionMap.put("/tologin", "anon");
        filterChainDefinitionMap.put("/shiroApi/exception", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/shiroApi/error", "anon");
        filterChainDefinitionMap.put("/shiroApi/todenied", "anon");//不驗證
        //下面註解拿掉 就會開始要求驗證 IP/* 的位址
        filterChainDefinitionMap.put("/**", "customFilter");//會由自訂過濾器處理
        //filterChainDefinitionMap.put("/**", "anon");//會由自訂過濾器處理
        filterChainDefinitionMap.put("/kanbanApi/**", "anon");//不驗證
        //filterChainDefinitionMap.put("/swagger-ui.html/", "anon");
 		//filterChainDefinitionMap.put("/**", "anon");
        //把過濾定義MAP放到工廠即可和自定過濾器整合 畢竟已將先放進去了
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        //設定一些預設的東西....
        shiroFilterFactoryBean.setLoginUrl("/tologin");
        shiroFilterFactoryBean.setSuccessUrl("/indexpage");
        shiroFilterFactoryBean.setUnauthorizedUrl("/tologin");
        return shiroFilterFactoryBean;
    }

    @Bean
    //@ConditionalOnMissingBean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultSecurityManager securityManager) {
        // This is to enable Shiro's security annotations
        AuthorizationAttributeSourceAdvisor sourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        sourceAdvisor.setSecurityManager(securityManager);
        return sourceAdvisor;
    }
/**
 * 注意下面的bean必須要添加 否則@RequiresRoles會不生效導致不斷出現404錯誤
 */
    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {

        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);

        return defaultAdvisorAutoProxyCreator;
    }

}
