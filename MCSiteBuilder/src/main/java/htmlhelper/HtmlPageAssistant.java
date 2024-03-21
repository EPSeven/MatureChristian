package htmlhelper;

import java.nio.file.Path;
import java.nio.file.Paths;

public class HtmlPageAssistant {
	private final HtmlSections html = new HtmlSections();
	private final Path filePath;

	public HtmlPageAssistant(String destFolder, String pageFileName) {
		filePath = Paths.get(destFolder, pageFileName);
	}

	protected String getFilePath() {
		return filePath.toString();
	}
	
	protected String getCommonTitleSection(String pageTitle) {
		// Add the section up to where the body section starts.
		return html.getDocType() +
				html.getOpenHtmlSection() +
				html.getOpenHeadSection() +
				html.getStyleSheetLink() +
				html.getTitleLine(pageTitle) + 
				html.getCloseHeadSection();
	}

	protected String getBodyByClass(String bodyClassName) {
		return html.getOpenBodyByClass(bodyClassName);
	}

	protected String getPageHeading(String pageHeading) {
		return HtmlTags.h1.getContentTagLine(pageHeading);
	}
	
	protected String  getPageIntroduction(String introText) {
		return HtmlTags.p.getTagLineWithClass("intro") +
				introText + "\r\n" +
				HtmlTags.p.getCloseTagLine();
	}

	protected String getPageLinkLine(String pageIdentity, String pageTitle) {
		HtmlTagAttributes attributes = new HtmlTagAttributes();
		String pageLink;
		
		attributes.addAttribute("href", pageIdentity);
		pageLink = HtmlTags.a.getTagWithAttributes(attributes) + pageTitle + HtmlTags.a.getCloseTag();
		
		return HtmlTags.h2.getContentTagLine(pageLink);
	}

	protected String getCommonClose() {
		return html.getCloseBodySection() + html.getCloseHtmlSection();
	}
}
