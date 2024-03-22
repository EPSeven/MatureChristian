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
		contentLines += getPageIntroduction();

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
