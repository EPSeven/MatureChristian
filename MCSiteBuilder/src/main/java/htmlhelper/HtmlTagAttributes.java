package htmlhelper;

public class HtmlTagAttributes {

	private String attributes = "";

	public void clearAttributes() {
		attributes = "";
	}

	public void addAttribute(String attribName, String attribValue) {
		attributes += " " + attribName + "=\"" + attribValue + "\"";
	}

	public String getAttributes() {
		return attributes;
	}
}
