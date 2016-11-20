package course.spider.util;

import course.spider.ResourceSiteEnum;
import course.spider.imp.list.ImpGJListSpider;
import course.spider.imp.list.ImpListSpider;
import course.spider.interfaces.IResourceListSpider;

/**
 * 资源列表工厂类
 * 
 * @author zhaojd
 * @date 2016年10月17日 下午11:11:00
 * @version 1.0
 */
public final class ResourceListClassFactory {
	private ResourceListClassFactory() {
	}

	/**
	 * 资料列表工厂方法
	 * 
	 * @author zhaojd
	 * @date 2016年10月17日 下午11:18:24
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
