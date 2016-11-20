package course.storage.imp.download;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import course.spider.entity.WebSitesContent;
import course.spider.entity.WebSitesNewList;
import course.spider.interfaces.IResourceContentSpider;
import course.spider.util.ResourceContentFactory;
import course.storage.configuration.ConfigUration;
import course.storage.imp.ResourceProcess;

/**
 * ��Դ���ص�ʵ����
 * @author zhaojd
 * @date 2016��10��24�� ����10:47:54
 * @version 1.0
 */
public class ImpResourceDownloadProcess extends ResourceProcess<String>{
	
	public ImpResourceDownloadProcess(List<WebSitesNewList> webSitesNewList, String path, ConfigUration configUration,String threadname) {
		super(webSitesNewList, path,configUration, threadname);
		
	}

	@Override
	public String call() throws Exception {
		Thread thread = new Thread(threadname);
		try (PrintWriter wit = new PrintWriter(new File(path));) {
			for (WebSitesNewList sitesList : webSitesNewList) {
				IResourceContentSpider content = null;
				WebSitesContent webSitesContent = null;
				if (sitesList.getUrl() != null && !sitesList.getUrl().equals("")) {
					content = ResourceContentFactory.getResourceContent(sitesList.getUrl());
				} else {
					throw new RuntimeException();
				}

				for (int i = 0; i < times; i++) {
					try {
						tag = "���ã������߳�,�ҵ����ֽ�:" + thread.getName() + " ������������<<" + sitesList.getUrl() + ">>";
						webSitesContent = content.getWebSitesContent(sitesList.getUrl());
						if (webSitesContent.getTitle() != null && !webSitesContent.getTitle().equals("")) {
							wit.write(webSitesContent.getTitle());
						}
						if (webSitesContent.getContent() != null && !webSitesContent.getContent().equals("")) {
							wit.write(webSitesContent.getContent());
						}
						if (webSitesContent.getDownloadSite() != null
								&& !webSitesContent.getDownloadSite().equals("")) {
							wit.write(webSitesContent.getDownloadSite());
						}
						if(webSitesContent.getTitleSites() !=null && !" ".equals(webSitesContent.getTitleSites())){
							wit.write(webSitesContent.getTitleSites());
						}
						tag += " �ɹ��ˣ������ҵ�[" + (i + 1) + "/" + times + "]�����ء�";
						System.out.println(tag);
						tag = "";
						break;
					} catch (Exception e) {
						tag += " ʧ����~ ������Ϣ[" + (i + 1) + "/" + times + "]�Ρ�";
						Thread.sleep(30000);
						System.out.println(tag);
						tag = "";
					}

				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return path;
	}

}
