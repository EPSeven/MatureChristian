package htmlhelper;

public class HtmlSections {
	public String getDocType() {
		return "<!DOCTYPE html>\r\n";
	}

	public String getOpenHtmlSection() {
		HtmlTagAttributes attributes = new HtmlTagAttributes();

		attributes.addAttribute("lang", "en");

		return HtmlTags.html.getTagLineWithAttributes(attributes);
	}

	public String getCloseHtmlSection() {
		return HtmlTags.html.getCloseTagLine();
	}

	public String getOpenHeadSection() {
		return HtmlTags.head.getTagLine();
	}

	public String getStyleSheetLink() {
		HtmlTagAttributes attributes = new HtmlTagAttributes();

		attributes.addAttribute("rel", "stylesheet");
		attributes.addAttribute("href", "styles.css");

		return HtmlTags.link.getTagLineWithAttributes(attributes);
	}

	public String getCloseHeadSection() {
		return HtmlTags.head.getCloseTagLine();
	}

	public String getTitleLine(String pageTitle) {
		return getIndent() + HtmlTags.title.getTag() + pageTitle + HtmlTags.title.getCloseTagLine();
	}

	public String getOpenBodyByClass(String bodyClassName) {
		return HtmlTags.body.getTagLineWithClass(bodyClassName);
	}

	public String getCloseBodySection() {
		return HtmlTags.body.getCloseTagLine();
	}

	public String getIndent() {
		return "\t";
	}
}
