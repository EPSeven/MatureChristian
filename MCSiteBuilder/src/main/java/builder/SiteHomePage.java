package builder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
		contentLines += getEndOfLine();

		// Introduction text.
		contentLines += getPageIntroduction();
		contentLines += getEndOfLine();

		// Add the links to all the category pages.
		contentLines += getCategoryPageLinksSection();

		return contentLines;
	}

	private String getCategoryPageLinksSection() {
		return HtmlTags.p.getTagLine() + getLinksToCategoryPages() + HtmlTags.p.getCloseTagLine();
	}

	private String getUnderConstruction() {
		HtmlTagAttributes attributes = new HtmlTagAttributes();
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("eeee, dd-MMM-yyyy 'at' K:mm a");
		String dateTimeStr = currentDateTime.format(dtFormatter);

		attributes.addAttribute("src", "images/under-construction.png");
		attributes.addAttribute("alt", "Under Construction");

		return HtmlTags.center.getTagLine() + HtmlTags.img.getTagWithAttributes(attributes) + HtmlTags.br.getTag()
				+ "Last updated : " + dateTimeStr + HtmlTags.center.getCloseTagLine();
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

				linkLines += getIndent() + getPageLinkLine(htmlFileName, pageInfo.getPageTitle());
			}
		}

		return linkLines;
	}
}
