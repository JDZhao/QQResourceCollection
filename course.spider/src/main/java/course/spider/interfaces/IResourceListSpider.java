package course.spider.interfaces;

import java.util.List;

import course.spider.entity.WebSitesNewList;

/**
 * 资源列表解析接口
 * @author Zhao
 *
 */
public interface IResourceListSpider {
	public List<WebSitesNewList> getWebSitesNewList(String uri);
}
