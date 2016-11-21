package sites.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import course.spider.entity.WebSitesContent;
import course.spider.entity.WebSitesNewList;
import course.spider.interfaces.IResourceContentSpider;
import course.spider.interfaces.IResourceListSpider;
import course.spider.util.ResourceContentFactory;
import course.spider.util.ResourceListClassFactory;
import course.spider.util.ResourceSpiderUtil;
import sites.web.entity.JSONResponse;
import sites.web.service.CourseService;


/**
 * @author zhaojd
 * @date 2016年10月27日 下午3:14:01
 * @version 1.0
 */
@Controller
public class CourseController {
	@Resource
	private CourseService service;
	static {
		ResourceSpiderUtil.setConfigPath("D:/Spider-Rule.xml");
	}

	@RequestMapping(value = "/getCourseList.do", method = RequestMethod.GET)
	@ResponseBody
	public JSONResponse getCourseList(String uri) {
		IResourceListSpider listSpider = ResourceListClassFactory.getRsourceList(uri);
		List<WebSitesNewList> webSitesNewList = listSpider.getWebSitesNewList(uri);
		return JSONResponse.succeed(webSitesNewList);

	}

	@RequestMapping(value = "/getCourseContent.do", method = RequestMethod.POST)
	@ResponseBody
	public JSONResponse getCourseContent(String uri) {
		IResourceContentSpider contentSpider = ResourceContentFactory.getResourceContent(uri);
		WebSitesContent webSitesContent = contentSpider.getWebSitesContent(uri);
		return JSONResponse.succeed(webSitesContent);
	}
	@RequestMapping(value = "/couresList.do" , method = RequestMethod.POST)
	@ResponseBody
	public JSONResponse getCourseListByDate(){
		return JSONResponse.succeed(service.getCourseListByDate());
		
	}
	
	@RequestMapping(value = "/couresListBySitesId.do" , method = RequestMethod.POST)
	@ResponseBody
	public JSONResponse getCourseListByDate(int sitesId){
		return JSONResponse.succeed(service.getCourseListByDateAndSitesId(sitesId));
		
	}
}
