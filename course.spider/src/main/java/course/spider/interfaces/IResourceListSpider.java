package course.spider.interfaces;

import java.util.List;

import course.spider.entity.WebSitesNewList;

/**
 * ��Դ�б�����ӿ�
 * @author Zhao
 *
 */
public interface IResourceListSpider {
	public List<WebSitesNewList> getWebSitesNewList(String uri);
}
