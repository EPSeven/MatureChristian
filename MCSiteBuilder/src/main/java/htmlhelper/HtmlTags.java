package htmlhelper;

public enum HtmlTags {
	html, head, meta, link, script, title, body, h1, h2, h3, table, tr, td, div, span, p, br, ul, li, a, img, center;

	public String getTagLineWithClass(String tagClassName) {
		HtmlTagAttributes attributes = new HtmlTagAttributes();

		attributes.addAttribute("class", tagClassName);

		return getTagLineWithAttributes(attributes);
	}

	public String getTagWithClass(String tagClassName) {
		HtmlTagAttributes attributes = new HtmlTagAttributes();

		attributes.addAttribute("class", tagClassName);

		return getTagWithAttributes(attributes);
	}

	public String getTagLineWithAttributes(HtmlTagAttributes attributes) {
		return getTagLine(this.toString() + attributes.getAttributes());
	}

	public String getContentTagLine(String tagContent) {
		return getTag(this.toString()) + tagContent + getCloseTagLine();
	}

	public String getTagLine() {
		return getTagLine(this.toString());
	}

	private String getTagLine(String tagName) {
		return formatAsLine(getTag(tagName));
	}

	public String getTagWithAttributes(HtmlTagAttributes attributes) {
		return getTag(this.toString() + attributes.getAttributes());
	}

	public String getTag() {
		return String.format("<%s>", this.toString());
	}

	private String getTag(String tagName) {
		return String.format("<%s>", tagName);
	}

	public String getCloseTag() {
		return getTag("/" + this.toString());
	}

	public String getCloseTagLine() {
		return formatAsLine(getCloseTag());
	}

	private String formatAsLine(String tagName) {
		return String.format("%s\r\n", tagName);
	}
}