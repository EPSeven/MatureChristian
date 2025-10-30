package htmlhelper;

public class HtmlBibleReferenceScripts extends HtmlCore {
	
	public String getLogosRefTaggerScript() {
		String contentLines = "";
		int indentation = 1;
		
		contentLines += getEndOfLine();
		contentLines += HtmlTags.script.getTagLine();
		
		contentLines += buildLine(indentation, "var refTagger = {");
		indentation++;
		contentLines += buildLine(indentation, "settings: {");
		indentation++;
		contentLines += buildLine(indentation, "bibleVersion: 'NASB95'");
		indentation--;
		contentLines += buildLine(indentation, "}");
		indentation--;
		contentLines += buildLine(indentation, "};"); 

		contentLines += getEndOfLine(); 

		contentLines += buildLine(indentation, "(function(d, t) {");
		indentation++;
		contentLines += buildLine(indentation, "var n=d.querySelector('[nonce]');");
		contentLines += buildLine(indentation, "refTagger.settings.nonce = n && (n.nonce||n.getAttribute('nonce'));");
		contentLines += buildLine(indentation, "var g = d.createElement(t), s = d.getElementsByTagName(t)[0];");
		contentLines += buildLine(indentation, "g.src = 'https://api.reftagger.com/v2/RefTagger.js';");
		contentLines += buildLine(indentation, "g.nonce = refTagger.settings.nonce;");
		contentLines += buildLine(indentation, "s.parentNode.insertBefore(g, s);");
		indentation--;
		contentLines += buildLine(indentation, "}(document, 'script'));");

		contentLines += HtmlTags.script.getCloseTagLine();

		return contentLines;
	}
	
	public String getBibleGatewayScript() {
		String contentLines = "";
		HtmlTagAttributes attributes = new HtmlTagAttributes();

		contentLines += getEndOfLine();

		attributes.clearAttributes();
		attributes.addAttribute("src", "https://www.biblegateway.com/public/link-to-us/tooltips/bglinks.js");
		attributes.addAttribute("type", "text/javascript");
		contentLines += HtmlTags.script.getTagWithAttributes(attributes);
		contentLines += HtmlTags.script.getCloseTagLine();

		attributes.clearAttributes();
		attributes.addAttribute("type", "text/javascript");
		contentLines += HtmlTags.script.getTagLineWithAttributes(attributes);
		contentLines += "BGLinks.version = \"NASB1995\";" + getEndOfLine();
		contentLines += "BGLinks.showTooltips = true;" + getEndOfLine();
		contentLines += "BGLinks.linkVerses();" + getEndOfLine();
		contentLines += HtmlTags.script.getCloseTagLine();

		return contentLines;
	}
	
	private String buildLine(int nbrIndents, String text) {
		String outputLine = "";
		
		for (int i = 0; i < nbrIndents; i++) {
			outputLine += getIndent();
		}
		
		outputLine += text;
		outputLine += getEndOfLine();
		
		return outputLine;
	}
}
