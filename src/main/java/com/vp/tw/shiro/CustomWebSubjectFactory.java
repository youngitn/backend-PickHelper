package com.vp.tw.shiro;

import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;
import org.apache.shiro.subject.Subject;
/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: YangTingCheng
 * @Date: 2020/09/24/下午 03:44
 * @Description:
 */
public class CustomWebSubjectFactory extends DefaultWebSubjectFactory {

    @Override
    public Subject createSubject(SubjectContext context) {
        // 禁用session
        context.setSessionCreationEnabled(false);
        return super.createSubject(context);
    }
}
