package jsondata;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class JsonDataPageInfo {
	private String htmlFileName;
	private String bodyClassName;
	private String pageTitle;
	private String customPageHeading;

	public String getHtmlFileName() {
		return htmlFileName;
	}
	
	public String getBodyClassName() {
		return bodyClassName;
	}
	
	public String getPageTitle() {
		return pageTitle;
	}
	
	public String getCustomPageHeading() {
		return customPageHeading;
	}
	
	public void setHtmlFileName(String htmlFileName) {
		this.htmlFileName = htmlFileName;
	}

	public void setBodyClassName(String bodyClassName) {
		this.bodyClassName = bodyClassName;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public void setCustomPageHeading(String pageHeading) {
		this.customPageHeading = pageHeading;
	}
}
