package org.apache.ibatis.calvintest;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

public class CalvinTest {
  public static void main(String[] args) throws IOException {
    String resource = "resources/mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    //获取SqlSessionFactory对象
    //解析文件的每一个信息保存在Configuration中 返回包含Configuration的DefaultSqlSessionFactory
    //   注意MapperStatement
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    SqlSession sqlSession = null;
    try {
      //获取session
       sqlSession = sqlSessionFactory.openSession();
      //Connection connection = sqlSession.getConnection();
      //获取接口的代理对象  会为接口字段创建一个代理对象 代理对象去实现增删改查方法(MapperProxy) org.apache.ibatis.binding.MapperProxy@7f690630
      //getMapper 使用MapperProxyFactory创建一个MapperProxy的代理对象
      //代理对象里面包含DefaultSqlSession(Executor)
      BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
      System.out.println(mapper);
      Blog blog = mapper.selectBlog(1);
      System.out.println(blog.getContent());
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if(sqlSession != null){
        sqlSession.close();
      }
    }
  }
}
