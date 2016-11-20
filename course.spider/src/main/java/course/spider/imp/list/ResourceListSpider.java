package course.spider.imp.list;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import course.spider.ResourceSiteEnum;
import course.spider.entity.WebSitesNewList;
import course.spider.imp.ResourceSpider;
import course.spider.interfaces.IResourceListSpider;
import course.spider.util.ResourceSpiderUtil;

/**
 * 资源列表解析
 * @author Zhao
 *
 */
public abstract class ResourceListSpider extends ResourceSpider implements IResourceListSpider {

	/**
	 * 解析HTML
	 * @author zhaojd
	 * @date 2016年10月15日 上午11:12:13
	 * @version 1.0
	 * @param uri
	 * @return
	 */
	public List<WebSitesNewList> getWebSitesNewList(String uri) {

		try {
			String html = super.crawl(uri);
			Document parse = Jsoup.parse(html);
			parse.setBaseUri(uri);
			Elements elements = parse
					.select(ResourceSpiderUtil.getContext(ResourceSiteEnum.getEnumByUrl(uri)).get("new-list-selector"));
			List<WebSitesNewList> list = new ArrayList<WebSitesNewList>();
			for (Element eles : elements) {
				WebSitesNewList sitesContList = new WebSitesNewList();
				sitesContList.setName(eles.text());
				sitesContList.setUrl(eles.absUrl("href"));
				list.add(sitesContList);

			}
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
