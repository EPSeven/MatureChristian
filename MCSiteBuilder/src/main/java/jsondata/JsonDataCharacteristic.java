package jsondata;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class JsonDataCharacteristic {
	private JsonDataPageInfo pageInfo;
	private List<String> additionalScriptures;

	private List<JsonDataTransformation> transformations;

	public JsonDataPageInfo getPageInfo() {
		return pageInfo;
	}

	public List<String> getAdditionalScriptures() {
		return additionalScriptures;
	}

	public List<JsonDataTransformation> getTransformations() {
		return transformations;
	}

	public void setPageInfo(JsonDataPageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public void setAdditionalScriptures(List<String> additionalScriptures) {
		this.additionalScriptures = additionalScriptures;
	}

	public void setTransformations(List<JsonDataTransformation> transformations) {
		this.transformations = transformations;
	}
}
