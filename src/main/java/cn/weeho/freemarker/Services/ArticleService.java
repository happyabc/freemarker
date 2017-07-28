package cn.weeho.freemarker.Services;

import cn.weeho.freemarker.dao.ArticleMapper;
import cn.weeho.freemarker.entities.Article;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
    public List<Article> getArticlesByMyBatis() throws IOException {
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

    /**
     * 所有的文章
     */
    public List<Article> getArticles() throws IOException {
        List<Article> articles = new ArrayList<>();
        articles.add(new Article(20170711, "不明真相的美国人被UFO惊呆了 其实是长征7号", "据美国《洛杉矶时报》报道，当地时间周三晚(北京时间周四)，在美国中西部的犹他州、内华达州、加利福利亚州，数千人被划过夜空的神秘火球吓到"));
        articles.add(new Article(20170702, "法国巴黎圣母院为教堂恐袭案遇害神父举行大弥撒", "而据美国战略司令部证实，其实这是中国长征七号火箭重新进入大气层，刚好经过加利福利亚附近。"));
        articles.add(new Article(20170703, "日东京知事候选人小池百合子回击石原：浓妆可以", "然而昨晚的美国人民可不明真相，有些人甚至怀疑这些火球是飞机解体，还有些人猜测是流星雨。"));
        articles.add(new Article(20170704, "日资慰安妇基金在首尔成立 韩国示威者闯入抗议", "美国战略司令部发言人表示，到目前为止还没有任何受损报告，他说类似物体通常在大气中就会消失，这也解释了为何出现一道道光痕，这一切都并未造成什么威胁。"));
//        articles.add(new Article(20170725, "中日关系正处十字路口日应寻求减少与华冲突", "中国长征七号火箭6月25日在海南文昌航天发射中心首次发射，并成功升空进入轨道。有学者指出长征七号第二级火箭一直在地球低轨运行，一个月后重新进入大气层。"));


        return articles;
    }

    /*
     * 获得文章通过文章编号
     */
    public Article getArticle(int id) throws IOException {
        for (Article article : getArticlesByMyBatis()) {
            if (article.getId() == id) {
                return article;
            }
        }
        return null;
    }
}
