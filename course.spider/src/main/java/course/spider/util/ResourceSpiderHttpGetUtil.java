package course.spider.util;

import java.net.URI;

import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;

/**
 * �������ӹ�����
 * 
 * @author zhaojd
 * @date 2016��10��18�� ����10:29:38
 * @version 1.0
 */
public class ResourceSpiderHttpGetUtil extends HttpGet {
//	private RequestConfig config = RequestConfig.custom().setConnectTimeout(6000).setSocketTimeout(6000)
//			.setCookieSpec(CookieSpecs.STANDARD).build();
	public ResourceSpiderHttpGetUtil() {
		// TODO Auto-generated constructor stub
	}

	public ResourceSpiderHttpGetUtil(URI uri) {
		super(uri);
		setDefaultConfig();
		// TODO Auto-generated constructor stub
	}

	public ResourceSpiderHttpGetUtil(String uri) {
		super(uri);
		setDefaultConfig();
		// TODO Auto-generated constructor stub
	}

	private void setDefaultConfig() {
		this.setConfig(RequestConfig.custom().setSocketTimeout(2_000)// ���ô��䳬ʱʱ��
				.setConnectTimeout(10_000)// �������ӳ�ʱʱ��
				.setConnectionRequestTimeout(10_000)// ������������ʱ��
				.setCookieSpec(CookieSpecs.STANDARD).build());
		this.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");// ��������ͷ
		//this.setConfig(config);
	}

}
