package builder;

import java.util.List;

import htmlhelper.HtmlPageBase;
import htmlhelper.HtmlTags;
import jsondata.JsonDataCategory;
import jsondata.JsonDataCharacteristic;
import jsondata.JsonDataPageInfo;

public class CategoryPage extends HtmlPageBase {
	private final JsonDataCategory category;

	public CategoryPage(String destFolder, JsonDataCategory categoryData) {
		super(destFolder, categoryData.getPageInfo());

		category = categoryData;
	}

	@Override
	protected String getCustomBodyContent() {
		String contentLines = "";

		// Introduction block.
		contentLines += getPageIntroduction();

		// Block containing the links to the characteristic pages.
		contentLines += getCharacteristicPageLinksSection();

		// Page navigation block.
		contentLines += getCategoryNavigationSection();

		return contentLines;
	}

	private String getCharacteristicPageLinksSection() {
		return HtmlTags.p.getTagLine() + getLinksToCharacteristicPages() + HtmlTags.p.getCloseTagLine()
				+ getEndOfLine();
	}

	private String getLinksToCharacteristicPages() {
		String linkLines = "";
		List<JsonDataCharacteristic> characteristicsList = category.getCharacteristics();
		JsonDataPageInfo pageInfo;
		String htmlFileName;

		if (characteristicsList != null) {
			for (JsonDataCharacteristic characteristic : characteristicsList) {
				pageInfo = characteristic.getPageInfo();

				pageInfo.setBodyClassName(category.getPageInfo().getBodyClassName());
				htmlFileName = pageInfo.getHtmlFileName() + ".html";

				linkLines += getIndent() + getPageLinkLine(htmlFileName, pageInfo.getPageTitle());
			}
		}

		return linkLines;
	}

	private String getCategoryNavigationSection() {
		String sectionLines = "";

		sectionLines += HtmlTags.p.getTagLine();
		sectionLines += getIndent() + getHomePageNavLink();
		sectionLines += HtmlTags.p.getCloseTagLine();

		return sectionLines;
	}
}
