package course.spider.imp.list;

import java.util.List;

import course.spider.entity.WebSitesNewList;

/**
 * ����������Դ�б����,ɾ����uri����soft�Ķ���
 * 
 * @author zhaojd
 * @date 2016��10��18�� ����2:59:14
 * @version 1.0
 */
public class ImpGJListSpider extends ResourceListSpider {
	public List<WebSitesNewList> getWebSitesNewList(String uri) {
		List<WebSitesNewList> webSitesNewList = super.getWebSitesNewList(uri);

		for (int i = 0; i < webSitesNewList.size(); i++) {
			WebSitesNewList sitesList = webSitesNewList.get(i);
			if (!sitesList.getUrl().contains("oft")) {
				// ���������oft��ɾ��
				webSitesNewList.remove(i);
				i--;
			}
		}
		return webSitesNewList;
	}
}
