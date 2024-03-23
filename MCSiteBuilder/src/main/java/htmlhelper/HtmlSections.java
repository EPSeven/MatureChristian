package htmlhelper;

public class HtmlSections {
	public String getDocType() {
		return "<!DOCTYPE html>\r\n<html lang=\"en\">\r\n";
	}

	public String getOpenHtmlSection() {
		return HtmlTags.html.getTagLine();
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
