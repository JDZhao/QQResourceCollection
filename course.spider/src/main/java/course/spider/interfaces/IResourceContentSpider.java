package course.spider.interfaces;

import course.spider.entity.WebSitesContent;

/**
 * 资源内容解析接口
 * 
 * @author zhaojd
 * @date 2016年10月16日 上午1:13:14
 * @version 1.0
 */
public interface IResourceContentSpider {
	public WebSitesContent getWebSitesContent(String uri);
}
