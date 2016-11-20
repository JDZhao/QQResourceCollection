package course.spider.imp;

import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import course.spider.ResourceSiteEnum;
import course.spider.interfaces.IResourceSpider;
import course.spider.util.ResourceSpiderHttpGetUtil;
import course.spider.util.ResourceSpiderUtil;

/**
 * ��Դ��ȡ������
 * 
 * @author zhaojd
 * @date 2016��10��16�� ����1:16:23
 * @version 1.0
 */
public abstract class ResourceSpider implements IResourceSpider {

	/**
	 * ץȡָ��URI��ҳ��Ϣ,������HTML
	 * @author zhaojd
	 * @date 2016��10��15�� ����11:11:24
	 * @version 1.0
	 * @param uri
	 * @return
	 * @throws Exception
	 */
	protected String crawl(String uri) throws Exception {

		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build();
				CloseableHttpResponse execute = httpClient.execute(new ResourceSpiderHttpGetUtil(uri));) {
			StatusLine statusLine = execute.getStatusLine();
			if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
				String string = EntityUtils.toString(execute.getEntity(),
						ResourceSpiderUtil.getContext(ResourceSiteEnum.getEnumByUrl(uri)).get("charset"));
				return string;
			} else {
				throw new RuntimeException();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
