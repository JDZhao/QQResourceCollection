package course.spider.imp.list;

import java.util.List;

import course.spider.entity.WebSitesNewList;

/**
 * 国际网络资源列表解析,删除不uri不带soft的对象
 * 
 * @author zhaojd
 * @date 2016年10月18日 下午2:59:14
 * @version 1.0
 */
public class ImpGJListSpider extends ResourceListSpider {
	public List<WebSitesNewList> getWebSitesNewList(String uri) {
		List<WebSitesNewList> webSitesNewList = super.getWebSitesNewList(uri);

		for (int i = 0; i < webSitesNewList.size(); i++) {
			WebSitesNewList sitesList = webSitesNewList.get(i);
			if (!sitesList.getUrl().contains("oft")) {
				// 如果不包含oft就删除
				webSitesNewList.remove(i);
				i--;
			}
		}
		return webSitesNewList;
	}
}
