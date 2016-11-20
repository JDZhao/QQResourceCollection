package course.spider.imp.content;

import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import course.spider.ResourceSiteEnum;
import course.spider.entity.WebSitesContent;
import course.spider.util.ResourceHtmlParseUtil;
import course.spider.util.ResourceSpiderUtil;

/**
 * С������Դ������
 * 
 * @author zhaojd
 * @date 2016��10��17�� ����2:47:30
 * @version 1.0
 */
public class ImpXDContentSpider extends ResourceContentSpider {
	public WebSitesContent getWebSitesContent(String uri) {
		WebSitesContent webSitesContent = new WebSitesContent();
		Map<String, String> context = ResourceSpiderUtil.getContext(ResourceSiteEnum.getEnumByUrl(uri));
		try {
			String html = super.crawl(uri);
			Document doc = Jsoup.parse(html);
			doc.setBaseUri(uri);
			webSitesContent.setTitle(ResourceHtmlParseUtil.setContentTitle(doc, uri) + "\n");
			webSitesContent.setContent(ResourceHtmlParseUtil.setContent(doc, uri) + "\n");

			String contentDown = context.get("content-page-down");
			String[] strings = contentDown.split("\\,");
			strings = parseSelect(strings);
			Elements contentpagedownSelect = doc.select(strings[0]);
			// �õ�һ�ֱ�ǩ��ȡ����
			if (contentpagedownSelect.size() != 0) {
				String absUrl = contentpagedownSelect.get(Integer.parseInt(strings[1])).absUrl(strings[2]);
				String substring = absUrl.substring(absUrl.indexOf("'") + 1, absUrl.lastIndexOf("'"));
				webSitesContent.setDownloadSite(substring + "\n");
				// ��һ��û�л�ȡ�ɹ�,���Եڶ��ֱ�ǩ��ȡ
			} else if (context.get("content-page-down1") != null) {
				String contentDown1 = context.get("content-page-down1");
				strings = contentDown1.split("\\,");
				strings = parseSelect(strings);
				Elements contentpagedownSelect1 = doc.select(strings[0]);
				if (contentpagedownSelect1.size() > 0) {
					String string = contentpagedownSelect1.get(Integer.parseInt(strings[1])).toString();
					String substring = string.substring(string.indexOf("'") + 10, 129);
					webSitesContent.setDownloadSite(context.get("uri") + "/" + substring + "\n");
				} else {
					webSitesContent.setDownloadSite("���ı���ͼƬ��Դ.�����ص�ַ!/n");
				}
			}
			webSitesContent.setSitesId(ResourceSiteEnum.getEnumByUrl(uri).getId());
			webSitesContent.setTitleSites(uri);
			return webSitesContent;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	private static String[] parseSelect(String[] strings) {
		String[] newSplits = new String[3];
		if (strings.length == 1) {
			newSplits[0] = strings[0];
			newSplits[1] = "0";
			newSplits[2] = "href";
			return newSplits;
		} else {
			return strings;
		}

	}
}
