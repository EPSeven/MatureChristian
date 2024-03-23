package htmlhelper;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import builder.PageBuilder;
import jsondata.JsonDataPageInfo;

public abstract class HtmlPageBase implements PageBuilder {
	private static final String HOME_ICON_PNG = "home-icon.png";
	private static final String BACK_ARROW_PNG = "back-arrow.png";

	protected final JsonDataPageInfo pageInfo;

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
			pageFile.write(getEndOfLine());
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

	protected String getIndent() {
		return "  ";
	}

	protected String getEndOfLine() {
		return "\r\n";
	}

	protected String getCommonTitleSection(String pageTitle) {
		String sectionLines = "";

		// Add DocType and head tags.
		sectionLines += getDocType();
		sectionLines += getOpenHtmlSection();

		// Add the head tag and all the contents that go into head.
		sectionLines += getOpenHeadSection();
		sectionLines += getIndent() + getMetaDataCharset();
		sectionLines += getIndent() + getMetaDataViewport();
		sectionLines += getIndent() + getStyleSheetLink();
		sectionLines += getIndent() + getTitleLine(pageTitle);
		sectionLines += getCloseHeadSection();
		sectionLines += getEndOfLine();

		return sectionLines;
	}

	protected String getBodyByClass(String bodyClassName) {
		return HtmlTags.body.getTagLineWithClass(bodyClassName);
	}

	protected String getPageIntroduction() {
		String introSection = "";
		List<String> introLines = pageInfo.getIntroBlock();

		if (introLines != null) {
			introSection += HtmlTags.p.getTagLine();
			for (String introText : introLines) {
				introSection += getIndent() + HtmlTags.div.getTagLineWithClass("intro");
				introSection += getIndent() + getIndent() + introText + getEndOfLine();
				introSection += getIndent() + HtmlTags.div.getCloseTagLine();
			}
			introSection += HtmlTags.p.getCloseTagLine();
		}

		return introSection;
	}

	protected String getPageLinkLine(String pageIdentity, String pageTitle) {
		HtmlTagAttributes attributes = new HtmlTagAttributes();
		String pageLink;

		attributes.addAttribute("href", pageIdentity);
		pageLink = HtmlTags.a.getTagWithAttributes(attributes) + pageTitle + HtmlTags.a.getCloseTag();

		return HtmlTags.h2.getContentTagLine(pageLink);
	}

	protected String getBackArrowNavLink(JsonDataPageInfo pageInfo) {
		return getNavLink(BACK_ARROW_PNG, pageInfo.getHtmlFileName(), "Back to " + pageInfo.getPageTitle());
	}

	protected String getHomePageNavLink() {
		return getNavLink(HOME_ICON_PNG, "index", "Back to Home Page");
	}

	protected String getCloseBodySection() {
		return HtmlTags.body.getCloseTagLine();
	}

	protected String getCloseHtmlSection() {
		return HtmlTags.html.getCloseTagLine();
	}

	private String getDocType() {
		return "<!DOCTYPE html>" + getEndOfLine();
	}

	private String getOpenHtmlSection() {
		HtmlTagAttributes attributes = new HtmlTagAttributes();

		attributes.addAttribute("lang", "en-us");

		return HtmlTags.html.getTagLineWithAttributes(attributes);
	}

	private String getOpenHeadSection() {
		return HtmlTags.head.getTagLine();
	}

	private String getMetaDataCharset() {
		HtmlTagAttributes attributes = new HtmlTagAttributes();

		attributes.addAttribute("charset", "UTF-8");

		return HtmlTags.meta.getTagLineWithAttributes(attributes);
	}

	private String getMetaDataViewport() {
		HtmlTagAttributes attributes = new HtmlTagAttributes();

		attributes.addAttribute("name", "viewport");
		attributes.addAttribute("content", "width=device-width, initial-scale=1.0");

		return HtmlTags.meta.getTagLineWithAttributes(attributes);
	}

	private String getStyleSheetLink() {
		HtmlTagAttributes attributes = new HtmlTagAttributes();

		attributes.addAttribute("rel", "stylesheet");
		attributes.addAttribute("href", "styles.css");

		return HtmlTags.link.getTagLineWithAttributes(attributes);
	}

	private String getTitleLine(String pageTitle) {
		return HtmlTags.title.getTag() + pageTitle + HtmlTags.title.getCloseTagLine();
	}

	private String getCloseHeadSection() {
		return HtmlTags.head.getCloseTagLine();
	}

	private String getPageHeading() {
		String pageHeading;

		pageHeading = pageInfo.getCustomPageHeading();
		if (pageHeading == null) {
			pageHeading = pageInfo.getPageTitle();
		}

		return HtmlTags.h1.getContentTagLine(pageHeading);
	}

	private String getNavIconImg(String iconFileName) {
		HtmlTagAttributes imgAttributes = new HtmlTagAttributes();

		imgAttributes.addAttribute("class", "nav-icon");
		imgAttributes.addAttribute("src", "images/" + iconFileName);

		return HtmlTags.img.getTagWithAttributes(imgAttributes);
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

	private String getCommonClose() {
		return getEndOfLine() + getCloseBodySection() + getCloseHtmlSection();
	}
}
