package com.keyword.automation.base.utils;

import com.keyword.automation.database.domain.Goods;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * 数据库操作工具类
 *
 * @author Amio_
 */
public class DbUtils {
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir") + "/src/mybatis-config.xml");
        String resource = System.getProperty("user.dir") + "/src/mybatis-config.xml";
        InputStream is = DbUtils.class.getClassLoader().getResourceAsStream(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession();
        String statement = "com.keyword.automation.database.mapping.goodsMapper.getGoods";
        Goods goods = session.selectOne(statement, 1);
        System.out.println(goods);
    }
}
