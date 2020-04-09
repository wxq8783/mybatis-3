package org.apache.ibatis.calvinplugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.util.Properties;

/**
 * 完成插件的签名 用来告诉mybatis当前插件来拦截哪个对象的哪个方法
 */
@Intercepts({@Signature(type = StatementHandler.class,method = "parameterize",args =java.sql.Statement.class )})
public class MyFirstPlugin implements Interceptor {
  /**
   * 拦截目标对象的目标方法的执行
   * @param invocation
   * @return
   * @throws Throwable
   */
  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    Object proceed = invocation.proceed();
    return proceed;
  }

  /**
   * 包装目标对象 为目标对象创建一个代理对象
   * @param target
   * @return
   */
  @Override
  public Object plugin(Object target) {
    //我们可以借助Plugin的wrap方法来使用当前Interceptor包装我们目标对象
    Object wrap = Plugin.wrap(target, this);//wrap 就是创建一个代理对象
    //返回当前target创建的动态代理
    return wrap;
  }

  /**
   * 将插件注册时的property属性设置进来
   * @param properties
   */
  @Override
  public void setProperties(Properties properties) {
    System.out.println("插件的配置信息"+properties);
  }
}
