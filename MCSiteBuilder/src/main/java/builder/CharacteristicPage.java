package builder;

import htmlhelper.HtmlPageBase;
import htmlhelper.HtmlTags;
import jsondata.JsonDataCategory;
import jsondata.JsonDataCharacteristic;

public class CharacteristicPage extends HtmlPageBase {
	private final JsonDataCategory category;

	public CharacteristicPage(String destFolder, JsonDataCharacteristic characteristicData,
			JsonDataCategory categoryData) {
		super(destFolder, characteristicData.getPageInfo());

		category = categoryData;
	}

	@Override
	protected String getCustomBodyContent() {
		String contentLines = "";

		contentLines += getCharacteristicNavigationSection();

		return contentLines;
	}

	private String getCharacteristicNavigationSection() {
		String sectionLines = "";

		sectionLines += HtmlTags.p.getTagLine();
		sectionLines += super.getBackArrowNavLink(category.getPageInfo());
		sectionLines += super.getHomePageNavLink();
		sectionLines += HtmlTags.p.getCloseTagLine();

		return sectionLines;
	}
}
