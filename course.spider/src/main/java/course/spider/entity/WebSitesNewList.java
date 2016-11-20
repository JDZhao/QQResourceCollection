package course.spider.entity;

import java.io.Serializable;

/**
 * 列表实体类
 * @author Zhao
 *
 */
public class WebSitesNewList  implements Serializable{
	private static final long serialVersionUID = 6502116222013720249L;
	private String name;
	private String url;
	
	public String getName() {
		return name;
	}
	public void setName(String list_title_name) {
		this.name = list_title_name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String list_title_url) {
		this.url = list_title_url;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WebSitesNewList other = (WebSitesNewList) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "WebSitesNewList [name=" + name + ", url=" + url + "]";
	}

	
	
}
