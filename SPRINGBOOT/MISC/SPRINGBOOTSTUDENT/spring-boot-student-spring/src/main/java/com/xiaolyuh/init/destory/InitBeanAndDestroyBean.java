package com.xiaolyuh.init.destory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 测试Bean的三种初始化、销毁方式和执行顺序
 *
 * @author yuhao.wang3
 */
public class InitBeanAndDestroyBean implements InitializingBean, DisposableBean, ApplicationContextAware {
    ApplicationContext applicationContext;

    public String say() {
        return "Hello!" + this.getClass().getName();
    }

    public InitBeanAndDestroyBean() {
        System.out.println("执行InitBeanAndDestroyBean构造方法");
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("接口-执行InitBeanAndDestroyBeanTest：destroy方法");
    }

    /**
     * Bean所有属性设置完(初始化完)之后调用
     *
     * @throws Exception Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("接口-执行InitBeanAndDestroyBeanTest：afterPropertiesSet方法");
    }

    @PostConstruct
    public void postConstructstroy() {
        System.out.println("注解-执行InitBeanAndDestroyBeanTest：preDestroy方法");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("注解--执行InitBeanAndDestroyBeanTest：preDestroy方法");
    }

    /**
     * 真正的Bean初始化方法
     */
    public void initMethod() {
        System.out.println("XML配置-执行InitBeanAndDestroyBeanTest：init-method方法");
    }

    /**
     * 真正的Bean销毁方法
     */
    public void destroyMethod() {
        System.out.println("XML配置-执行InitBeanAndDestroyBeanTest：destroy-method方法");
    }
}