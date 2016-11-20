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
 * ��Դ���������
 * 
 * @author zhaojd
 * @date 2016��10��23�� ����12:19:18
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
					tag = "�ϰ壡�����߳�,�ҵ����ֽ�:" + thread.getName() + " ������������<<" + lists.getUrl() + ">>";
					IResourceContentSpider content = ResourceContentFactory.getResourceContent(lists.getUrl());
					WebSitesContent webSitesContent = content.getWebSitesContent(lists.getUrl());
					int insert = webSitesContenDao.insert(webSitesContent);
				
					if(insert==-1){
						tag += "�ϰ壡����Դ�Ѵ����ˡ�";
						System.out.println(tag);
						tag = "";
						break;
					}
					tag += " �ɹ��ˣ������ҵ�[" + (i + 1) + "/" + times + "]�����ء�";
					System.out.println(tag);
					tag = "";
					break;
				} catch (Exception e) {
					tag += " ʧ����~ ������Ϣ("+sleepTime+"����)��[" + (i + 1) + "/" + times + "]�Ρ�";
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
	// // ʧ�ܳ��Դ���
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
	// tag = "�ϰ壡�����߳�,�ҵ����ֽ�:" + thread.getName() + " ������������<<" + lists.getUrl()
	// + ">>";
	// IResourceContentSpider content =
	// ResourceContentFactory.getResourceContent(lists.getUrl());
	// WebSitesContent webSitesContent =
	// content.getWebSitesContent(lists.getUrl());
	// try {
	// session.insert("insert", webSitesContent);
	// session.commit();
	// count++;
	// tag += " �ɹ��ˣ������ҵ�[" + (i + 1) + "/" + tryTimes + "]�����ء�";
	// System.out.println(tag);
	// tag = "";
	// break;
	// } catch (Exception e) {
	// System.out.println(lists.getUrl() + "�Ѵ���!");
	// break;
	// }
	//
	// } catch (Exception e) {
	// tag += " ʧ����~ ������Ϣ[" + (i + 1) + "/" + tryTimes + "]�Ρ�";
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
