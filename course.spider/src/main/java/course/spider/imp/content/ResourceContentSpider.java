package course.spider.imp.content;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import course.spider.ResourceSiteEnum;
import course.spider.entity.WebSitesContent;
import course.spider.imp.ResourceSpider;
import course.spider.interfaces.IResourceContentSpider;
import course.spider.util.ResourceHtmlParseUtil;

/**
 * 资源内容解析
 * @author zhaojd
 * @date 2016年10月16日 上午1:19:22
 * @version 1.0
 */
public abstract class ResourceContentSpider extends ResourceSpider implements IResourceContentSpider {

	public WebSitesContent getWebSitesContent(String uri) {
		WebSitesContent webSitesContent = new WebSitesContent();
		try {
			String html = super.crawl(uri);
			Document doc = Jsoup.parse(html);
			doc.setBaseUri(uri);
			webSitesContent.setTitle(ResourceHtmlParseUtil.setContentTitle(doc, uri)+"\n");
			webSitesContent.setContent(ResourceHtmlParseUtil.setContent(doc, uri)+"\n");
			webSitesContent.setDownloadSite(ResourceHtmlParseUtil.setContentDown(doc, uri)+"\n");
			webSitesContent.setSitesId(ResourceSiteEnum.getEnumByUrl(uri).getId());
			webSitesContent.setTitleSites(uri);
			return webSitesContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
