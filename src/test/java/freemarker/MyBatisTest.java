package freemarker;


import cn.weeho.freemarker.dao.ArticleMapper;
import cn.weeho.freemarker.entities.Article;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 测试相关的方法
 */
public class MyBatisTest {

    private SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }


    @Test
    public void testGetOneArticle() throws IOException {
        // 1、获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        // 2、获取sqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            // 3、获取接口的实现类对象
            //会为接口自动的创建一个代理对象，代理对象去执行增删改查方法
            ArticleMapper mapper = openSession.getMapper(ArticleMapper.class);
            Article article = mapper.getArticleById(1);
            System.out.println(mapper.getClass());
            System.out.println(article);
        } finally {
            openSession.close();
        }

    }

    @Test
    public void testGetArticleList() throws IOException {
        // 1、获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        // 2、获取sqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession();
        sqlSessionFactory.openSession();

        try {
            // 3、获取接口的实现类对象
            //会为接口自动的创建一个代理对象，代理对象去执行增删改查方法
            ArticleMapper mapper = openSession.getMapper(ArticleMapper.class);
            List<Article> articleList = mapper.getArticleList();
            for (int i = 0; i < articleList.size(); i++) {
                Article article =  articleList.get(i);
                System.out.println(article.toString());

            }
            System.out.println(articleList.size());
            System.out.println(mapper.getClass());

        } finally {
            openSession.close();
        }
    }
}
