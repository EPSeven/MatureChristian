package jsondata;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class JsonDataCategory {
	private String categoryName;
	private JsonDataPageInfo pageInfo;
	private List<JsonDataCharacteristic> characteristics;

	public String getCategoryName() {
		return categoryName;
	}

	public JsonDataPageInfo getPageInfo() {
		return pageInfo;
	}

	public List<JsonDataCharacteristic> getCharacteristics() {
		return characteristics;
	}

	public void setCategoryName(String category) {
		this.categoryName = category;
	}

	public void setPageInfo(JsonDataPageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public void setCharacteristics(List<JsonDataCharacteristic> characteristics) {
		this.characteristics = characteristics;
	}
}
