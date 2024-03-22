package htmlhelper;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import builder.PageBuilder;
import jsondata.JsonDataPageInfo;

public abstract class HtmlPageBase implements PageBuilder {
	private static final String HOME_ICON_PNG = "home-icon.png";
	private static final String BACK_ARROW_PNG = "back-arrow.png";

	protected final JsonDataPageInfo pageInfo;

	private final HtmlSections html = new HtmlSections();
	private final Path filePath;

	public HtmlPageBase(String destFolder, JsonDataPageInfo pageInfoData) {
		filePath = Paths.get(destFolder, pageInfoData.getHtmlFileName() + ".html");
		pageInfo = pageInfoData;
	}

	protected abstract String getCustomBodyContent();

	public String getPageGenerationPath() {
		return getFilePath();
	}

	public void createPage() {
		FileWriter pageFile;

		try {
			// Create the file to be written.
			pageFile = new FileWriter(getFilePath());

			// Write the title section.
			pageFile.write(getCommonTitleSection(pageInfo.getPageTitle()));

			// Add the body and page heading.
			pageFile.write(getBodyByClass(pageInfo.getBodyClassName()));
			pageFile.write(getPageHeading());

			// All the derived classes to add their body content.
			pageFile.write(getCustomBodyContent());

			// Close out the file's last closing tags section.
			pageFile.write(getCommonClose());

			// Close the file which also forces the contents to actually be saved.
			pageFile.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected String getFilePath() {
		return filePath.toString();
	}

	protected String getCommonTitleSection(String pageTitle) {
		// Add the section up to where the body section starts.
		return html.getDocType() + html.getOpenHtmlSection() + html.getOpenHeadSection() + html.getStyleSheetLink()
				+ html.getTitleLine(pageTitle) + html.getCloseHeadSection();
	}

	protected String getBodyByClass(String bodyClassName) {
		return html.getOpenBodyByClass(bodyClassName);
	}

	protected String getPageHeading() {
		String pageHeading;

		pageHeading = pageInfo.getCustomPageHeading();
		if (pageHeading == null) {
			pageHeading = pageInfo.getPageTitle();
		}

		return HtmlTags.h1.getContentTagLine(pageHeading);
	}

	protected String getPageIntroduction(String introText) {
		return HtmlTags.p.getTagLineWithClass("intro") + introText + "\r\n" + HtmlTags.p.getCloseTagLine();
	}

	protected String getPageLinkLine(String pageIdentity, String pageTitle) {
		HtmlTagAttributes attributes = new HtmlTagAttributes();
		String pageLink;

		attributes.addAttribute("href", pageIdentity);
		pageLink = HtmlTags.a.getTagWithAttributes(attributes) + pageTitle + HtmlTags.a.getCloseTag();

		return HtmlTags.h2.getContentTagLine(pageLink);
	}

	protected String getNavIconImg(String iconFileName) {
		HtmlTagAttributes imgAttributes = new HtmlTagAttributes();

		imgAttributes.addAttribute("class", "nav-icon");
		imgAttributes.addAttribute("src", "images/" + iconFileName);

		return HtmlTags.img.getTagWithAttributes(imgAttributes);
	}

	protected String getBackArrowNavLink(JsonDataPageInfo pageInfo) {
		return getNavLink(BACK_ARROW_PNG, pageInfo.getHtmlFileName(), "Back to " + pageInfo.getPageTitle());
	}

	protected String getHomePageNavLink() {
		return getNavLink(HOME_ICON_PNG, "index", "Back to Home Page");
	}

	private String getNavLink(String imgFileName, String pageFileName, String linkText) {
		String pageLink = "";
		String navLinkImg = getNavIconImg(imgFileName);
		HtmlTagAttributes linkAttributes = new HtmlTagAttributes();

		linkAttributes.addAttribute("href", pageFileName + ".html");
		pageLink += HtmlTags.a.getTagWithAttributes(linkAttributes);
		pageLink += navLinkImg;
		pageLink += linkText;
		pageLink += HtmlTags.a.getCloseTag();

		return HtmlTags.h2.getContentTagLine(pageLink);
	}

	protected String getCommonClose() {
		return html.getCloseBodySection() + html.getCloseHtmlSection();
	}
}
