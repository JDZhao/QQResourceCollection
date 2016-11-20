package course.spider.util;

import course.spider.ResourceSiteEnum;
import course.spider.imp.content.ImpContentSpider;
import course.spider.imp.content.ImpXDContentSpider;
import course.spider.interfaces.IResourceContentSpider;

/** 
 * 资源内容工厂类
* @author zhaojd
* @date 2016年10月17日 下午11:19:31 
* @version 1.0 
*/
public final class ResourceContentFactory {
	private ResourceContentFactory(){};
	
	/**
	 * 资源内容类方法
	* @author zhaojd
	* @date 2016年10月17日 下午11:22:15 
	* @version 1.0 
	* @param uri
	* @return IResourceContentSpider
	*/ 
	public static IResourceContentSpider getResourceContent(String uri){
		IResourceContentSpider contentSpider = null;
		ResourceSiteEnum siteEnum = ResourceSiteEnum.getEnumByUrl(uri);
		switch (siteEnum) {
		case GUOJI:
		case YEWULEYUAN:
		case ZUANSHIHUANGCHAO:
		case XIAOK:
			contentSpider = new ImpContentSpider();
			break;
		case XIAODAO:
			contentSpider = new ImpXDContentSpider();
		}
		return contentSpider;
	}
}
