package course.storage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import course.spider.entity.WebSitesContent;
import course.storage.configuration.ConfigUration;
import course.storage.dao.WebSitesContenDao;
import course.storage.dao.imp.WebSitesContenImp;
import course.storage.imp.download.ImpResourceDownload;
import course.storage.imp.storages.ImpResourceStorage;
import course.storage.spider_start.Start;

/**
 * @author zhaojd
 * @date 2016年10月23日 上午12:55:01
 * @version 1.0
 */
public class StorageTest {

	@Test
	public void mybatisTest() throws FileNotFoundException {
		/// course.storage/src/main/resources/conf/SqlMapConfig.xml
		SqlSession session = new SqlSessionFactoryBuilder().build(new FileInputStream("conf/SqlMapConfig.xml"))
				.openSession();
		ArrayList<WebSitesContent> arrayList = new ArrayList<WebSitesContent>();
		WebSitesContent webSitesContent = new WebSitesContent();
		webSitesContent.setContent("ceshi");
		webSitesContent.setDownloadSite("www.");
		webSitesContent.setTitle("biaoti");
		webSitesContent.setSitesId(1);
		arrayList.add(webSitesContent);
		session.insert("insert", webSitesContent);
		session.commit();
		session.close();
	}

	@Test
	public void getDownLoadTest() {
		ImpResourceDownload impResourceDownload = new ImpResourceDownload();
		ConfigUration configUration = new ConfigUration();
		configUration.setPath("D:\\ceshi");
		configUration.setSize(20);
		configUration.setTrytimes(3);
		impResourceDownload.threadCount("http://www.txzshc.com/", configUration);
	}

	@Test
	public void getinsertTitle() {
		ImpResourceStorage impResourceStorage = new ImpResourceStorage();
		ConfigUration configUration = new ConfigUration();
		configUration.setSize(20);
		configUration.setTrytimes(3);
		impResourceStorage.threadCount("http://www.wofl86.com", configUration);
	}

	@Test
	public void getStorageTest() {
		WebSitesContenDao sitesContenImp = new WebSitesContenImp();
		sitesContenImp.selectByTitle("秒点腾讯街机之王1级QQ图标显示\n");
	}
	@Test
	public void getSelectByDate() {
		WebSitesContenDao sitesContenImp = new WebSitesContenImp();
		List<WebSitesContent> selectByDate = sitesContenImp.selectByDate();
		System.out.println(selectByDate);
	}
	
	@Test
	public void selectByDateAndSitesId() {
		WebSitesContenDao sitesContenImp = new WebSitesContenImp();
		List<WebSitesContent> selectByDate = sitesContenImp.selectByDateAndSitesId(1);
		System.out.println(selectByDate);
	}
	


}
