package course.spider.util;

import course.spider.ResourceSiteEnum;
import course.spider.imp.list.ImpGJListSpider;
import course.spider.imp.list.ImpListSpider;
import course.spider.interfaces.IResourceListSpider;

/**
 * ��Դ�б�����
 * 
 * @author zhaojd
 * @date 2016��10��17�� ����11:11:00
 * @version 1.0
 */
public final class ResourceListClassFactory {
	private ResourceListClassFactory() {
	}

	/**
	 * �����б�������
	 * 
	 * @author zhaojd
	 * @date 2016��10��17�� ����11:18:24
	 * @version 1.0
	 * @param uri
	 * @return IResourceListSpider
	 */
	public static IResourceListSpider getRsourceList(String uri) {
		IResourceListSpider listSpider = null;
		ResourceSiteEnum siteEnum = ResourceSiteEnum.getEnumByUrl(uri);
		switch (siteEnum) {
		case GUOJI:
		case ZUANSHIHUANGCHAO:
			listSpider = new ImpGJListSpider();
			break;
		case YEWULEYUAN:
		case XIAODAO:
		case XIAOK:
			listSpider = new ImpListSpider();
			break;
		}
		return listSpider;
	}
}
