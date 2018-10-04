package com.ghoject.cloud.helloworld.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContext implements ApplicationContextAware {
    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static ApplicationContext  getApplicationContext(){
        return applicationContext;
    }

    /**
     * 适用于springbean使用注解@Service("XXXService")
     * 获取接口对象 参数传入 XXXService
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName){
        return applicationContext.getBean(beanName);
    }

    /**
     * 适用于springbean使用注解@Service
     * 获取接口对象 参数传入 XXXService.class  不是 XXXServiceImpl.class
     * @param c
     * @return
     */
    public static <T> T getBean(Class c){
        return (T)applicationContext.getBean(c);
    }
}
