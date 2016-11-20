package course.storage.imp.storages;

import java.util.List;

import course.spider.entity.WebSitesContent;
import course.spider.entity.WebSitesNewList;
import course.spider.interfaces.IResourceContentSpider;
import course.spider.util.ResourceContentFactory;
import course.storage.configuration.ConfigUration;
import course.storage.dao.WebSitesContenDao;
import course.storage.dao.imp.WebSitesContenImp;
import course.storage.imp.ResourceProcess;

/**
 * 资源保存抽象类
 * 
 * @author zhaojd
 * @date 2016年10月23日 上午12:19:18
 * @version 1.0
 */
public class ImpResourceStorageProcess extends ResourceProcess<String> {

	WebSitesContenDao webSitesContenDao;

	public ImpResourceStorageProcess(List<WebSitesNewList> webSitesNewList, ConfigUration configUration, String threadname) {
		super(webSitesNewList, configUration, threadname);
		webSitesContenDao = new WebSitesContenImp();
	}

	private List<String> failUri;

	@Override
	public String call() throws Exception {

		Thread thread = new Thread(threadname);
		for (WebSitesNewList lists : webSitesNewList) {
			for (int i = 0; i < times; i++) {
				try {
					tag = "老板！我是线程,我的名字叫:" + thread.getName() + " 本次任务下载<<" + lists.getUrl() + ">>";
					IResourceContentSpider content = ResourceContentFactory.getResourceContent(lists.getUrl());
					WebSitesContent webSitesContent = content.getWebSitesContent(lists.getUrl());
					int insert = webSitesContenDao.insert(webSitesContent);
				
					if(insert==-1){
						tag += "老板！其资源已存在了。";
						System.out.println(tag);
						tag = "";
						break;
					}
					tag += " 成功了！这是我第[" + (i + 1) + "/" + times + "]次下载。";
					System.out.println(tag);
					tag = "";
					break;
				} catch (Exception e) {
					tag += " 失败了~ 我先休息("+sleepTime+"毫秒)第[" + (i + 1) + "/" + times + "]次。";
					if (i == times) {
						failUri.add(lists.getUrl());
					}
					System.out.println(tag);
					tag = "";
					Thread.sleep(sleepTime);
				}

			}

		}
		return "1";
	}
	// public String resourceDownload(String uri, ConfigUration confing) {
	//
	// super.resourceDownload(uri, confing);
	// IResourceListSpider rsourceList =
	// ResourceListClassFactory.getRsourceList(uri);
	// List<WebSitesNewList> webSitesNewList =
	// rsourceList.getWebSitesNewList(uri);
	// HashMap<String, List<WebSitesNewList>> listMap = new HashMap<String,
	// List<WebSitesNewList>>();
	// int size = confing.getSize();
	// int maxThreadSize = (int) Math.ceil(webSitesNewList.size() * 1.0 / size);
	// for (int i = 0; i < maxThreadSize; i++) {
	// // 0*10=0
	// // 0*10+10=0
	//
	// // 1*10=10
	// // 1*10+10=20
	//
	// // 2*10=20
	// // 2*10+10=30
	// int fromIndex = i * size;
	// int toIndex = i == maxThreadSize - 1 ? webSitesNewList.size() : i * size
	// + size;
	// listMap.put(fromIndex + "-" + toIndex, webSitesNewList.subList(fromIndex,
	// toIndex));
	// }
	// ArrayList<Future<List<String>>> futureList = new
	// ArrayList<Future<List<String>>>();
	// ExecutorService service = Executors.newFixedThreadPool(maxThreadSize);
	// Set<String> keySet = listMap.keySet();
	// for (String key : keySet) {
	// futureList.add(service.submit(new processCallable(listMap.get(key),
	// confing.getTrytimes(), key)));
	// }
	// service.shutdown();
	// for (Future<List<String>> fu : futureList) {
	// try {
	// List<String> count = fu.get();
	// for (String list : count) {
	// System.out.println(list);
	// }
	// } catch (InterruptedException | ExecutionException e) {
	// e.printStackTrace();
	// }
	// }
	// return null;
	// }

	// class DownLoadCallable implements Callable<List<String>> {
	// private List<WebSitesNewList> webSitesNewList;
	// private List<String> failUri;
	// // 失败尝试次数
	// private int tryTimes;
	// private int count;
	// private String threadName;
	// private String tag;
	//
	// public DownLoadCallable(List<WebSitesNewList> webSitesNewList, int
	// tryTimes, String threadName) {
	// this.webSitesNewList = webSitesNewList;
	// this.tryTimes = tryTimes;
	// this.threadName = threadName;
	// failUri = new ArrayList<String>();
	// }
	//
	// SqlSession session = sqlSession.openSession();
	//
	// @Override
	// public List<String> call() throws Exception {
	// Thread thread = new Thread(threadName);
	// for (WebSitesNewList lists : webSitesNewList) {
	// for (int i = 0; i < tryTimes; i++) {
	// try {
	// tag = "老板！我是线程,我的名字叫:" + thread.getName() + " 本次任务下载<<" + lists.getUrl()
	// + ">>";
	// IResourceContentSpider content =
	// ResourceContentFactory.getResourceContent(lists.getUrl());
	// WebSitesContent webSitesContent =
	// content.getWebSitesContent(lists.getUrl());
	// try {
	// session.insert("insert", webSitesContent);
	// session.commit();
	// count++;
	// tag += " 成功了！这是我第[" + (i + 1) + "/" + tryTimes + "]次下载。";
	// System.out.println(tag);
	// tag = "";
	// break;
	// } catch (Exception e) {
	// System.out.println(lists.getUrl() + "已存在!");
	// break;
	// }
	//
	// } catch (Exception e) {
	// tag += " 失败了~ 我先休息[" + (i + 1) + "/" + tryTimes + "]次。";
	// if (i == tryTimes) {
	// failUri.add(lists.getUrl());
	// }
	// System.out.println(tag);
	// tag = "";
	// Thread.sleep(50000);
	// }
	//
	// }
	//
	// }
	// session.close();
	// return failUri;
	// }
	// }
}
