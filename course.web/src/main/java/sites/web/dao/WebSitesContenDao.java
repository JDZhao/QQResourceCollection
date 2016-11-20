package sites.web.dao;

import java.util.List;

import course.spider.entity.WebSitesContent;

public interface WebSitesContenDao {
	int deleteByPrimaryKey(Integer id);

	int insert(WebSitesContent record);

	int insertSelective(WebSitesContent record);

	WebSitesContent selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(WebSitesContent record);

	int updateByPrimaryKey(WebSitesContent record);

	void batchInsert(List<WebSitesContent> sitesContent);

	List<WebSitesContent> selectByTitle(String title);
	
	List<WebSitesContent> selectByDate();
	List<WebSitesContent> selectByDateAndSitesId(int sitesId);
}