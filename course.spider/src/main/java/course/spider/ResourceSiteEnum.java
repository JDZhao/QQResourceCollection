package course.spider;

/**
 * ö���� ����������֧�ֵ���Դ��վ
 * @author zhaojd
 * @date 2016��10��15�� ����11:55:15
 * @version 1.0
 */
public enum ResourceSiteEnum {
	GUOJI(1, "qqguoji.com"),
	XIAODAO(2, "xiaodao.la"),
	YEWULEYUAN(3,"qqyewu.com"),
	ZUANSHIHUANGCHAO(4,"txzshc.com"),
	XIAOK(5,"wofl86.com");
	private int id;
	private String url;

	private ResourceSiteEnum(int id, String url) {
		this.id = id;
		this.url = url;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	public static ResourceSiteEnum getEnumById(int id){
		switch(id){
		case 1 : return GUOJI;
		case 2: return XIAODAO;
		case 3: return YEWULEYUAN;
		case 4: return YEWULEYUAN;
		case 5: return XIAOK;
		default : throw new RuntimeException("ID"+id+"�Ǳ�վ��ʱ��֧�ֵ���Դ��վ��");
		}
	}
	
	public static ResourceSiteEnum getEnumByUrl(String url){
		
		try {
			for(ResourceSiteEnum enums :values()){
				if(url.contains(enums.url)){
					return enums;
				}
			}
			new RuntimeException("URL"+url+"�Ǳ�վ��ʱ��֧�ֵ���Դ��վ��");
		} catch (Exception e) {
			throw new RuntimeException("URL"+url+"�Ǳ�վ��ʱ��֧�ֵ���Դ��վ��");
		}
		return null;
	}
}
