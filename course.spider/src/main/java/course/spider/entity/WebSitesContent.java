package course.spider.entity;

import java.io.Serializable;

/**
 * 内容实体类
 * 
 * @author zhaojd
 * @date 2016年10月16日 上午12:11:19
 * @version 1.0
 */
public class WebSitesContent implements Serializable {

	private static final long serialVersionUID = -1101906350019660449L;
	private int id;
	// 标题
	private String title;
	// 内容
	private String content;
	// 下载地址
	private String downloadSite;
	// 网站ID
	private Integer sitesId;

	private String addDate;
	
	private String titleSites;

	
	public String getTitleSites() {
		return titleSites;
	}

	public void setTitleSites(String titlSites) {
		this.titleSites = titlSites;
	}
	
	public String getAddDate() {
		//2016-10-28
		return  addDate.substring(0,10);
	}

	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String contentpagetitle) {
		this.title = contentpagetitle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String contentpagecontent) {
		this.content = contentpagecontent;
	}

	public String getDownloadSite() {
		return downloadSite;
	}

	public void setDownloadSite(String contentpagedown) {
		this.downloadSite = contentpagedown;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getSitesId() {
		return sitesId;
	}

	public void setSitesId(Integer sitesId) {
		this.sitesId = sitesId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addDate == null) ? 0 : addDate.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((downloadSite == null) ? 0 : downloadSite.hashCode());
		result = prime * result + id;
		result = prime * result + ((sitesId == null) ? 0 : sitesId.hashCode());
		result = prime * result + ((titleSites == null) ? 0 : titleSites.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		WebSitesContent other = (WebSitesContent) obj;
		if (addDate == null) {
			if (other.addDate != null)
				return false;
		} else if (!addDate.equals(other.addDate))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (downloadSite == null) {
			if (other.downloadSite != null)
				return false;
		} else if (!downloadSite.equals(other.downloadSite))
			return false;
		if (id != other.id)
			return false;
		if (sitesId == null) {
			if (other.sitesId != null)
				return false;
		} else if (!sitesId.equals(other.sitesId))
			return false;
		if (titleSites == null) {
			if (other.titleSites != null)
				return false;
		} else if (!titleSites.equals(other.titleSites))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WebSitesContent [id=" + id + ", title=" + title + ", content=" + content + ", downloadSite="
				+ downloadSite + ", sitesId=" + sitesId + ", addDate=" + addDate + ", titleSites=" + titleSites + "]";
	}





}
