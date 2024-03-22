package jsondata;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class JsonDataCharacteristic {
	private JsonDataPageInfo pageInfo;

	public JsonDataPageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(JsonDataPageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
}
