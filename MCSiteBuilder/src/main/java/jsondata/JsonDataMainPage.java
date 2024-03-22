package jsondata;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class JsonDataMainPage {
	private JsonDataPageInfo pageInfo;
	private List<JsonDataCategory> categories;

	public JsonDataPageInfo getPageInfo() {
		return pageInfo;
	}

	public List<JsonDataCategory> getCategories() {
		return categories;
	}

	public void setPageInfo(JsonDataPageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public void setCategories(List<JsonDataCategory> list) {
		this.categories = list;
	}
}
