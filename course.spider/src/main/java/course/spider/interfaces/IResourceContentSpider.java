package course.spider.interfaces;

import course.spider.entity.WebSitesContent;

/**
 * ��Դ���ݽ����ӿ�
 * 
 * @author zhaojd
 * @date 2016��10��16�� ����1:13:14
 * @version 1.0
 */
public interface IResourceContentSpider {
	public WebSitesContent getWebSitesContent(String uri);
}
