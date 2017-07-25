package cn.weeho.freemarker.Services;

import cn.weeho.freemarker.dao.ArticleMapper;
import cn.weeho.freemarker.entities.Article;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 文章业务类（模拟）
 */
public class ArticleService {
    private SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * 所有的文章
     */
    public List<Article> getArticles() throws IOException {
        List<Article> articles;

        // 1、获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        // 2、获取sqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            // 3、获取接口的实现类对象
            //会为接口自动的创建一个代理对象，代理对象去执行增删改查方法
            ArticleMapper mapper = openSession.getMapper(ArticleMapper.class);
            articles = mapper.getArticleList();


        } finally {
            openSession.close();
        }
        return articles;
    }

    /*
     * 获得文章通过文章编号
     */
    public Article getArticle(int id) throws IOException {
        for (Article article : getArticles()) {
            if (article.getId() == id) {
                return article;
            }
        }
        return null;
    }
}
