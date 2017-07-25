package cn.weeho.freemarker.dao;

import cn.weeho.freemarker.entities.Article;

import java.util.List;

public interface ArticleMapper {

    /**
     * 获取新闻列表
     * @return
     */
    public List<Article> getArticleList();

    /**
     *
     * @param id
     * @return
     */
    public Article getArticleById(Integer id);


}
