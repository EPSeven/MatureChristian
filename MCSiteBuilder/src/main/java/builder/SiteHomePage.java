package builder;

import java.util.List;

import htmlhelper.HtmlPageBase;
import htmlhelper.HtmlTagAttributes;
import htmlhelper.HtmlTags;
import jsondata.JsonDataCategory;
import jsondata.JsonDataMainPage;
import jsondata.JsonDataPageInfo;

public class SiteHomePage extends HtmlPageBase {
	private final JsonDataMainPage jsonData;

	private static final String INTRO_TEXT = "    There are a number of characteristics found in the New Testament that paint a picture of a mature Christian. Those characteristics \r\n"
			+ "    have been grouped into the six categories found below. Explore the categories to discover the characteristics and the milestones that \r\n"
			+ "    you are likely to encounter as you mature in Christ. A mature Christian has moved from being spiritually dead and lost, to being a mature \r\n"
			+ "    follower of the Lord Jesus Christ. Blessings as you cooporate with the Holy Spirit at work in forming you into a mature Christian.";

	public SiteHomePage(String destFolder, JsonDataMainPage jsonDataBlock) {
		super(destFolder, jsonDataBlock.getPageInfo());

		jsonData = jsonDataBlock;
	}

	@Override
	protected String getCustomBodyContent() {
		String contentLines = "";

		// Add the "Under Construction" notification. THIS IS TEMPORARY.
		contentLines += getUnderConstruction();

		// Introduction text.
		contentLines += getPageIntroduction(INTRO_TEXT);

		// Add the links to all the category pages.
		contentLines += getCategoryPageLinksSection();

		return contentLines;
	}

	private String getCategoryPageLinksSection() {
		return HtmlTags.p.getTagLine() + getLinksToCategoryPages() + HtmlTags.p.getCloseTagLine();
	}

	private String getUnderConstruction() {
		HtmlTagAttributes attributes = new HtmlTagAttributes();

		attributes.addAttribute("src", "images/under-construction.png");
		attributes.addAttribute("alt", "Under Construction");

		return HtmlTags.center.getTagLine() + HtmlTags.img.getTagWithAttributes(attributes)
				+ HtmlTags.center.getCloseTagLine();
	}

	private String getLinksToCategoryPages() {
		String linkLines = "";
		List<JsonDataCategory> categoryList = jsonData.getCategories();
		JsonDataPageInfo pageInfo;
		String htmlFileName;

		if (categoryList != null) {
			for (JsonDataCategory category : categoryList) {
				pageInfo = category.getPageInfo();

				htmlFileName = pageInfo.getHtmlFileName() + ".html";

				linkLines += getPageLinkLine(htmlFileName, pageInfo.getPageTitle());
			}
		}

		return linkLines;
	}
}
