package freemarker;

import cn.weeho.freemarker.Services.ArticleService;
import cn.weeho.freemarker.entities.Article;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaGenerate {
    /**
     * 脱离Web容器进行测试。
     *
     * @throws Exception
     */
    @Test
    public void generate() throws Exception {
        {
            // 设置编码格式与MIME类型
            String basePath = "F:/freemarker/";
            // 首页新闻列表路径
            String indexPath = basePath + "index.html";

            // 文件是否存在
            File file = new File(indexPath);
            //创建子目录
            File news = new File(basePath + "news");
            if (!news.exists()) {
                news.mkdir();
            }
            System.out.println(news.getPath());
            // 创建一个freemarker.template.Configuration实例，它是存储 FreeMarker
            // 应用级设置的核心部分
            // 指定版本号
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
            // 获得模板文件路径
            String templatePath = this.getClass().getClassLoader().getResource("templates").getPath();
            // 设置模板目录
            cfg.setDirectoryForTemplateLoading(new File(templatePath));
            // 设置默认编码格式
            cfg.setDefaultEncoding("UTF-8");

            // 数据
            ArticleService articleService = new ArticleService();
            Map<String, Object> articleData = new HashMap<>();
            List<Article> articles = articleService.getArticlesByMyBatis();
            List<String> userNameList = new ArrayList<String>();
            userNameList.add(0, "孙大圣");
            userNameList.add(1, "猪八戒");
            userNameList.add(2, "唐凡凡");
            userNameList.add(3, "沙晶晶");

            articleData.put("articles", articles);
            articleData.put("userNames", userNameList);

            // 从设置的目录中获得模板
            Template template = cfg.getTemplate("newsList2.ftl");

            // 合并模板和数据模型
            try {
                // 将数据与模板渲染的结果写入文件中
                //1:生成新闻列表
                Writer writer = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
                template.process(articleData, writer);
                writer.flush();
                articleData.clear();


                //2:生成新闻详情
                template = cfg.getTemplate("news.ftl");
                // 生成单个新闻文件
                for (Article article : articles) {
                    articleData.put("article", article);
                    // 单个新闻文件
                    file = new File((news.getPath() + "/" + article.getId() + ".html"));
                    // 文件输出流写入器
                    writer = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
                    // 将模板+数据生成的结果写入文件中，得到一个静态文件
                    template.process(articleData, writer);
                    writer.flush();
                }
                writer.close();
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 生成主页测试
     *
     * @throws Exception
     */
    @Test
    public void generateIndex() throws Exception {
        {
            // 设置编码格式与MIME类型
            String basePath = "F:/freemarker/";
            // 首页新闻列表路径
            String indexPath = basePath + "index.html";

            // 文件是否存在
            File file = new File(indexPath);

            // 数据
            ArticleService articleService = new ArticleService();
            Map<String, Object> articleData = new HashMap<>();
            List<Article> articles = new ArrayList<>();
//            articles = articleService.getArticlesByMyBatis();
            articles = articleService.getArticles();
            List<String> userNameList = new ArrayList<String>();
            userNameList.add(0, "孙大圣");
            userNameList.add(1, "猪八戒");
            userNameList.add(2, "唐凡凡");
            userNameList.add(3, "沙晶晶");

            articleData.put("articles", articles);
            articleData.put("userNames", userNameList);
            String templatePath = this.getClass().getClassLoader().getResource("templates").getPath();
            String templateName = "newsList2.ftl";
            FreeMarkerUtils.generateHtml(file, articleData, templatePath, templateName);
        }
    }


}
