package sites.web.service;

import java.util.List;

import course.spider.entity.WebSitesContent;

/** 
* @author zhaojd
* @date 2016��10��27�� ����9:39:47 
* @version 1.0 
*/
public interface CourseService {
	public List<WebSitesContent> getCourseListByDate();
	public List<WebSitesContent> getCourseListByDateAndSitesId(int sitesId);
}
