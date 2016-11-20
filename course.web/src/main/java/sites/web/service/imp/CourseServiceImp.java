package sites.web.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import course.spider.entity.WebSitesContent;
import sites.web.dao.WebSitesContenDao;
import sites.web.service.CourseService;

/** 
* @author zhaojd
* @date 2016年10月27日 下午9:43:07 
* @version 1.0 
*/
@Service
public class CourseServiceImp implements CourseService {
	@Resource
	private WebSitesContenDao webSitesContenDao;
	@Override
	public List<WebSitesContent> getCourseListByDate() {
		return webSitesContenDao.selectByDate();
	}
	@Override
	public List<WebSitesContent> getCourseListByDateAndSitesId(int sitesId) {
		return webSitesContenDao.selectByDateAndSitesId(sitesId);
	}

}
