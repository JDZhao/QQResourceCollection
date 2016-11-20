package course.storage.spider_start;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import course.storage.configuration.ConfigUration;
import course.storage.imp.storages.ImpResourceStorage;

/**
 * @author zhaojd
 * @date 2016年11月7日 上午11:56:31
 * @version 1.0
 */
public class Start {
	private static final  ArrayList<String> URLLIST  = new ArrayList<String>();
	
	static{
		init();
	}
	
	public static void init(){
		SAXReader saxReader = new SAXReader();
		try {
			Document doc = saxReader.read(new File("conf/Url-config.xml"));
			 Element root = doc.getRootElement();
			 @SuppressWarnings("unchecked")
			List<Element> urls = root.elements("url");
			 for(Element url : urls){
				 String textTrim = url.getTextTrim();
				 URLLIST.add(textTrim);
			 }
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ImpResourceStorage impResourceStorage = new ImpResourceStorage();
		ConfigUration configUration = new ConfigUration();
		configUration.setSize(20);
		configUration.setTrytimes(3);
		try {
			for(String uri : URLLIST){
				impResourceStorage.threadCount(uri, configUration);
			}
		} catch (Exception e) {
			
		}
		
		
	}

}
