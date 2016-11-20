package course.storage.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import course.spider.entity.WebSitesNewList;
import course.spider.interfaces.IResourceListSpider;
import course.spider.util.ResourceListClassFactory;
import course.storage.configuration.ConfigUration;
import course.storage.interfaces.IResourceThreadCount;
import course.storage.util.ResourceProcessFactory;

/**
 * 资源线程计算抽象类
 * 
 * @author zhaojd
 * @date 2016年10月17日 下午10:56:59
 * @version 1.0
 */
public abstract class ResourceThreadCount implements IResourceThreadCount {
	public String threadCount(String uri, ConfigUration confing) {
		IResourceListSpider listSpider = ResourceListClassFactory.getRsourceList(uri);
		List<WebSitesNewList> webSitesNewList = listSpider.getWebSitesNewList(uri);
		HashMap<String, List<WebSitesNewList>> sitesListMap = new HashMap<String, List<WebSitesNewList>>();
		// 获得每个线程下载多少个内容
		int size = confing.getSize();
		// 计算共需要多少线程
		int maxThreadSize = (int) Math.ceil(webSitesNewList.size() * 1.0 / size);

		for (int i = 0; i < maxThreadSize; i++) {
			// fromIndex 0*100 = 0
			// toIndex 0*100+100 = 100

			// fromIndex 1*100 = 100
			// toIndex 1*100+100 = 200

			// fromIndex 2*100 = 200
			// toIndex 2*100+100 = 300
			int fromIndex = i * size;
			int toIndex = i == maxThreadSize - 1 ? webSitesNewList.size() : i * size + size;
			sitesListMap.put(fromIndex + "-" + toIndex, webSitesNewList.subList(fromIndex, toIndex));
		}

		// 设置线程池数量
		ExecutorService service = Executors.newFixedThreadPool(maxThreadSize);
		Set<String> keySet = sitesListMap.keySet();

		ArrayList<Future<?>> futureList = new ArrayList<Future<?>>();
		// for (String key : keySet) {
		// futureList.add(service
		// .submit(new DownLoadCallable(sitesListMap.get(key), confing.getPath()
		// + "\\" + key + ".txt",confing.getTrytimes(),key)));
		// }
		for (String key : keySet) {
			String path = confing.getPath() != null ? confing.getPath() + "\\" + key + ".txt" : " ";
			futureList.add(service
					.submit(ResourceProcessFactory.getResourceProcess(sitesListMap.get(key), path, confing, key)));
		}
		service.shutdown();

		for (Future<?> fu : futureList) {
			try {
				System.out.println(fu.get() + ",下载完成!");
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	// class DownLoadCallable implements Callable<String> {
	// private List<WebSitesNewList> webSitesNewList;
	// private String path;
	// private int times;
	// private String threadname;
	// private String tag;
	// public DownLoadCallable(List<WebSitesNewList> webSitesNewList, String
	// path,int times,String threadname) {
	// this.webSitesNewList = webSitesNewList;
	// this.path = path;
	// this.times = times;
	// this.threadname = threadname;
	// }
	//
	// public String call() throws Exception {
	// Thread thread = new Thread(threadname);
	// try (PrintWriter wit = new PrintWriter(new File(path));) {
	// for (WebSitesNewList sitesList : webSitesNewList) {
	// IResourceContentSpider content = null;
	// WebSitesContent webSitesContent = null;
	// if (sitesList.getUrl() != null && !sitesList.getUrl().equals("")) {
	// content = ResourceContentFactory.getResourceContent(sitesList.getUrl());
	// }else{
	// throw new RuntimeException();
	// }
	//
	// for(int i = 0 ;i <times;i++){
	// try {
	// tag = "您好！我是线程,我的名字叫:" + thread.getName()+" 本次任务下载<<"
	// +sitesList.getUrl()+">>";
	// webSitesContent = content.getWebSitesContent(sitesList.getUrl());
	// if (webSitesContent.getTitle() != null
	// && !webSitesContent.getTitle().equals("")) {
	// wit.write(webSitesContent.getTitle());
	// }
	// if (webSitesContent.getContent() != null
	// && !webSitesContent.getContent().equals("")) {
	// wit.write(webSitesContent.getContent());
	// }
	// if (webSitesContent.getDownloadSite() != null
	// && !webSitesContent.getDownloadSite().equals("")) {
	// wit.write(webSitesContent.getDownloadSite());
	// }
	// tag += " 成功了！这是我第[" +(i+1)+ "/" +times+ "]次下载。";
	// System.out.println(tag);
	// tag = "";
	// break;
	// } catch (Exception e) {
	// tag += " 失败了~ 我先休息[" +(i+1)+ "/" +times+ "]次。";
	// Thread.sleep(30000);
	// System.out.println(tag);
	// tag = "";
	// //System.out.println("线程名:" + thread.getName()+"第"+ "(" + (i+1) +")" +
	// "次休息!");
	// //System.out.println("线程名:" + thread.getName()+"尝试第[" +(i+1)+ "/" +times+
	// "]次下载" +sitesList.getList_title_url()+ "失效了!");
	// }
	//
	// }
	// }
	// } catch (IOException e) {
	// throw new RuntimeException(e);
	// }
	// return path;
	// }
	//
	// }
}
