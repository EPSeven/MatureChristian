package jsondata;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class JsonDataCharacteristic {
	private JsonDataPageInfo pageInfo;
	private String scripture;
	private List<JsonDataTransformation> transformations;

	public JsonDataPageInfo getPageInfo() {
		return pageInfo;
	}

	public String getScripture() {
		return scripture;
	}

	public List<JsonDataTransformation> getTransformations() {
		return transformations;
	}

	public void setPageInfo(JsonDataPageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public void setScripture(String scripture) {
		this.scripture = scripture;
	}

	public void setTransformations(List<JsonDataTransformation> transformations) {
		this.transformations = transformations;
	}
}
