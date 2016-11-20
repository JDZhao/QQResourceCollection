package course.storage.util;

import java.util.List;

import course.spider.entity.WebSitesNewList;
import course.storage.configuration.ConfigUration;
import course.storage.imp.download.ImpResourceDownloadProcess;
import course.storage.imp.storages.ImpResourceStorageProcess;
import course.storage.interfaces.IResourceProcess;


/**
 * @author zhaojd
 * @date 2016年10月24日 下午11:29:20
 * @version 1.0
 */
public class ResourceProcessFactory {

	private ResourceProcessFactory() {

	}

	public static IResourceProcess<?> getResourceProcess(List<WebSitesNewList> webSitesNewList, String path, ConfigUration configUration,
			String threadname) {
		IResourceProcess<?> impResourceProcess = null;
		if (path != null && !" ".equals(path)) {
			impResourceProcess = new ImpResourceDownloadProcess(webSitesNewList, path, configUration, threadname);
		} else {
			impResourceProcess = new ImpResourceStorageProcess(webSitesNewList, configUration, threadname);
		}
		return impResourceProcess;
	}
}
