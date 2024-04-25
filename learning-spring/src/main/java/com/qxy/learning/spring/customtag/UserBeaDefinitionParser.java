package com.qxy.learning.spring.customtag;


import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

public class UserBeaDefinitionParser extends AbstractSingleBeanDefinitionParser {

    /**
     * Element 对应的类
     * @param element
     * @return
     */
    @Override
    protected Class<?> getBeanClass(Element element) {
        return User.class;
    }


    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        // 解析 element 元素中的属性
        String userName = element.getAttribute("userName");
        String email = element.getAttribute("email");

        // 降提取的数据放入 BeanDefinitionBuilder 中，待倒完成所有 bean的解析后统一注册到 beanFactory 中
        if (StringUtils.hasText(userName)) {
            builder.addPropertyValue("userName", userName);
        }
        if (StringUtils.hasText(email)) {
            builder.addPropertyValue("email", email);
        }
        super.doParse(element, builder);
    }
}
