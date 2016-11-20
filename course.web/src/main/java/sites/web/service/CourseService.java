package sites.web.service;

import java.util.List;

import course.spider.entity.WebSitesContent;

/** 
* @author zhaojd
* @date 2016年10月27日 下午9:39:47 
* @version 1.0 
*/
public interface CourseService {
	public List<WebSitesContent> getCourseListByDate();
	public List<WebSitesContent> getCourseListByDateAndSitesId(int sitesId);
}
