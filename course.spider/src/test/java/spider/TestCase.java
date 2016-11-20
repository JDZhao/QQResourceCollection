package spider;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import course.spider.ResourceSiteEnum;
import course.spider.entity.WebSitesContent;
import course.spider.entity.WebSitesNewList;
import course.spider.imp.content.ImpContentSpider;
import course.spider.imp.content.ImpXDContentSpider;
import course.spider.imp.list.ImpGJListSpider;
import course.spider.imp.list.ImpListSpider;
import course.spider.interfaces.IResourceContentSpider;
import course.spider.interfaces.IResourceListSpider;
import course.spider.util.ResourceSpiderUtil;

public class TestCase {
	
	
	@Test
	public void getSpiderContext(){
		Map<String, String> context = ResourceSpiderUtil.getContext(ResourceSiteEnum.getEnumByUrl("www.qqguoji.com"));
		System.out.println(context.get("charset"));
	}
	
	
	@Test
	public void getWebSitesListTest(){
		ImpListSpider impListSpider = new ImpListSpider();
		 List<WebSitesNewList> webSitesNewList = impListSpider.getWebSitesNewList("http://www.wofl86.com/index.html");
		for(WebSitesNewList list: webSitesNewList){
			System.out.println(list.getName()+"---"+list.getUrl());
			
		}
	}
	
	@Test
	public void getGJWebSitesListTest(){
		IResourceListSpider impListSpider = new ImpGJListSpider();
		 List<WebSitesNewList> webSitesNewList = impListSpider.getWebSitesNewList("http://www.wofl86.com/");
		for(WebSitesNewList list: webSitesNewList){
			System.out.println(list.getName()+"---"+list.getUrl());
			
		}
	}
	
	@Test
	public void getWebSitesContentTest(){
		ImpContentSpider impContentSpider = new ImpContentSpider();
		WebSitesContent sitesContent = impContentSpider.getWebSitesContent("http://www.wofl86.com/i-wz-976717.html");
		System.out.println(sitesContent);
	}
	
	@Test
	public void getWebSitesXDContentTest(){
		IResourceContentSpider impContentSpider = new ImpXDContentSpider();
		WebSitesContent sitesContent = impContentSpider.getWebSitesContent("http://www.xiaodao.la/i-wz-31041892.html");
		System.out.println(sitesContent);
	}
	

	
	

	
	
}
