package course.spider.util;

import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import course.spider.ResourceSiteEnum;

/**
 * 解析标题/内容/下载地址工具类
 * 
 * @author zhaojd
 * @date 2016年10月19日 下午8:26:36
 * @version 1.0
 */
public class ResourceHtmlParseUtil {
	private static String[] strings;

	private ResourceHtmlParseUtil() {
	}

	/**
	 * 解析html的标题
	 * 
	 * @author zhaojd
	 * @date 2016年10月19日 下午9:19:19
	 * @version 1.0
	 * @param doc
	 * @param uri
	 * @return
	 */
	public static String setContentTitle(Document doc, String uri) {
		Map<String, String> context = ResourceSpiderUtil.getContext(ResourceSiteEnum.getEnumByUrl(uri));
		String contentTitle = context.get("content-page-title");
		strings = contentTitle.split("\\,");
		strings = parseSelect(strings);
		if (doc.select(strings[0]).size() > 0) {
			return doc.select(strings[0]).get(Integer.parseInt(strings[1])).text();
		} 
		return "抱歉!其资源没有标题!";
	}

	/**
	 * 解析html的内容
	 * 
	 * @author zhaojd
	 * @date 2016年10月19日 下午9:17:28
	 * @version 1.0
	 * @param doc
	 * @param uri
	 * @return
	 */
	public static String setContent(Document doc, String uri) {
		Map<String, String> context = ResourceSpiderUtil.getContext(ResourceSiteEnum.getEnumByUrl(uri));
		String content = context.get("content-page-content");
		strings = content.split("\\,");
		strings = parseSelect(strings);
		if (doc.select(strings[0]).size() != 0) {
			// 获取文本内容
			String text = doc.select(strings[0]).get(Integer.parseInt(strings[1])).text() + "\n";
			StringBuffer buffer = new StringBuffer();
			// 获取图片
			Elements imgs = doc.select(strings[2]);
			if (imgs.size() > 0) {
				for (Element img : imgs) {
					buffer.append(img.absUrl("href")).append(",").append("\n");
				}
			}
			return buffer + text;
		}
		return "抱歉!其资源没有文本抑或图片内容!";
	}

	/**
	 * 解析html的下载地址
	 * 
	 * @author zhaojd
	 * @date 2016年10月19日 下午9:18:28
	 * @version 1.0
	 * @param doc
	 * @param uri
	 * @return
	 */
	public static String setContentDown(Document doc, String uri) {
		Map<String, String> context = ResourceSpiderUtil.getContext(ResourceSiteEnum.getEnumByUrl(uri));
		String contentDown = context.get("content-page-down");
		strings = contentDown.split("\\,");
		strings = parseSelect(strings);
		Elements contentpagedownSelect = doc.select(strings[0]);
		if (contentpagedownSelect.size() != 0) {
			return contentpagedownSelect.get(Integer.parseInt(strings[1])).absUrl(strings[2]);
		} 
		return "抱歉!其资源没有下载地址!";
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
