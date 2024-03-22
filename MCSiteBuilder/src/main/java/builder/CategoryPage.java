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

		contentLines += getCharacteristicPageLinksSection();
		contentLines += getCategoryNavigationSection();

		return contentLines;
	}

	private String getCharacteristicPageLinksSection() {
		return HtmlTags.p.getTagLine() + getLinksToCharacteristicPages() + HtmlTags.p.getCloseTagLine();
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

				linkLines += getPageLinkLine(htmlFileName, pageInfo.getPageTitle());
			}
		}

		return linkLines;
	}

	private String getCategoryNavigationSection() {
		return HtmlTags.p.getTagLine() + super.getHomePageNavLink() + HtmlTags.p.getCloseTagLine();
	}
}
