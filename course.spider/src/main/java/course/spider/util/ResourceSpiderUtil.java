package course.spider.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import course.spider.ResourceSiteEnum;

/**
 * 资源爬取工具类
 * 
 * @author zhaojd
 * @date 2016年10月15日 上午11:52:16
 * @version 1.0
 */
public final class ResourceSpiderUtil {
	private static final HashMap<ResourceSiteEnum, Map<String, String>> CONTEXT_MAP = new HashMap<ResourceSiteEnum, Map<String, String>>();

	private ResourceSpiderUtil() {
	}

	static {
		init();
	}

	public static void init() {
		SAXReader saxReader = new SAXReader();
		try {
			Document doc = saxReader.read(new File("conf/Spider-Rule.xml"));
			Element root = doc.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> sites = root.elements("site");
			for (Element site : sites) {
				HashMap<String, String> hashMap = new HashMap<String, String>();
				@SuppressWarnings("unchecked")
				List<Element> elements = site.elements();
				for (Element sub : elements) {
					String name = sub.getName();
					String textTrim = sub.getTextTrim();
					hashMap.put(name, textTrim);
				}
				CONTEXT_MAP.put(ResourceSiteEnum.getEnumByUrl(hashMap.get("uri")), hashMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void setConfigPath(String path) {
		SAXReader saxReader = new SAXReader();
		try {
			Document doc = saxReader.read(new File(path));
			Element root = doc.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> sites = root.elements("site");
			for (Element site : sites) {
				HashMap<String, String> hashMap = new HashMap<String, String>();
				@SuppressWarnings("unchecked")
				List<Element> elements = site.elements();
				for (Element sub : elements) {
					String name = sub.getName();
					String textTrim = sub.getTextTrim();
					hashMap.put(name, textTrim);
				}
				CONTEXT_MAP.put(ResourceSiteEnum.getEnumByUrl(hashMap.get("uri")), hashMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Map<String, String> getContext(ResourceSiteEnum resourceSiteEnum) {
		return CONTEXT_MAP.get(resourceSiteEnum);
	}

	/**
	 * 合并文件
	* @author zhaojd
	* @date 2016年10月22日 下午6:48:38 
	* @version 1.0 
	* @param path
	* @param merger
	* @return 
	*/ 
	public static String multiFileMerge(String path, String merger) {
		String mergeToFile = merger == null ? path + "\\merger.txt" : merger;
		//查找path目录下所有文件并根据过滤器条件进行过滤
		File[] files = new File(path).listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				return name.endsWith("txt");
			}
		});
		//按照特定顺序排序
		Arrays.sort(files, new Comparator<File>() {

			@Override
			public int compare(File o1, File o2) {
				// TODO Auto-generated method stub
				int o1name = Integer.parseInt(o1.getName().split("\\-")[0]);
				int o2name = Integer.parseInt(o2.getName().split("\\-")[0]);
				if (o1name > o2name) {
					return 1;
				} else if (o1name < o2name) {
					return -1;
				} else {
					return 0;
				}
			}

		});

		PrintWriter printWriter = null;
		try {
			printWriter = new PrintWriter(new File(mergeToFile),"UTF-8");
			for (File file : files) {
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(new FileInputStream(file), "UTF-8"));
				String line = null;
				while ((line = bufferedReader.readLine()) != null) {
					printWriter.println(line);
				}
				bufferedReader.close();
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			printWriter.close();
		}
		return merger;

	}
	
//	   private static void run(ExecutorService threadPool) {  
//		    for(int i = 1; i < 5; i++) {    
//		            final int taskID = i;    
//		            threadPool.execute(new Runnable() {    
//		                @Override  
//		        public void run() {    
//		                    for(int i = 1; i < 5; i++) {    
//		                        try {    
//		                            Thread.sleep(20);// 为了测试出效果，让每次任务执行都需要一定时间    
//		                        } catch (InterruptedException e) {    
//		                            e.printStackTrace();    
//		                        }    
//		                        System.out.println("第" + taskID + "次任务的第" + i + "次执行");    
//		                    }    
//		                }    
//		            });    
//		        }    
//		        threadPool.shutdown();// 任务执行完毕，关闭线程池    
//		   }  
//		      

}
