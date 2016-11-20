package course.storage.imp;

import java.util.List;

import course.spider.entity.WebSitesNewList;
import course.storage.configuration.ConfigUration;
import course.storage.interfaces.IResourceProcess;

/**
 * ���߳���Դ����ĳ�����
 * 
 * @author zhaojd
 * @date 2016��10��24�� ����11:06:34
 * @version 1.0
 */
public abstract class ResourceProcess<T> implements IResourceProcess<T> {
	protected List<WebSitesNewList> webSitesNewList;
	protected String path;
	protected int times;
	protected String threadname;
	protected String tag;
	protected int sleepTime;

	public ResourceProcess(List<WebSitesNewList> webSitesNewList, String path, ConfigUration configUration,
			String threadname) {
		this.webSitesNewList = webSitesNewList;
		this.path = path;
		this.times = configUration.getTrytimes();
		this.threadname = threadname;
		this.sleepTime = configUration.getSleepTime();

	}

	public ResourceProcess(List<WebSitesNewList> webSitesNewList, ConfigUration configUration, String threadname) {
		this.webSitesNewList = webSitesNewList;
		this.times = configUration.getTrytimes();
		this.threadname = threadname;
		this.sleepTime = configUration.getSleepTime();
	}

}
