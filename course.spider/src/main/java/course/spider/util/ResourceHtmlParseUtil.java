package course.spider.util;

import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import course.spider.ResourceSiteEnum;

/**
 * ��������/����/���ص�ַ������
 * 
 * @author zhaojd
 * @date 2016��10��19�� ����8:26:36
 * @version 1.0
 */
public class ResourceHtmlParseUtil {
	private static String[] strings;

	private ResourceHtmlParseUtil() {
	}

	/**
	 * ����html�ı���
	 * 
	 * @author zhaojd
	 * @date 2016��10��19�� ����9:19:19
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
		return "��Ǹ!����Դû�б���!";
	}

	/**
	 * ����html������
	 * 
	 * @author zhaojd
	 * @date 2016��10��19�� ����9:17:28
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
			// ��ȡ�ı�����
			String text = doc.select(strings[0]).get(Integer.parseInt(strings[1])).text() + "\n";
			StringBuffer buffer = new StringBuffer();
			// ��ȡͼƬ
			Elements imgs = doc.select(strings[2]);
			if (imgs.size() > 0) {
				for (Element img : imgs) {
					buffer.append(img.absUrl("href")).append(",").append("\n");
				}
			}
			return buffer + text;
		}
		return "��Ǹ!����Դû���ı��ֻ�ͼƬ����!";
	}

	/**
	 * ����html�����ص�ַ
	 * 
	 * @author zhaojd
	 * @date 2016��10��19�� ����9:18:28
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
		return "��Ǹ!����Դû�����ص�ַ!";
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
