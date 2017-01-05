package es.usc.rai.coego.martin.demiurgo.web.beans;

import java.util.regex.Pattern;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;

@Component
@Scope(value = "singleton")
public class ActionFormatter {
	private static Pattern imgPat = Pattern.compile("\\[img\\](.*?)\\[\\/img\\]",
			Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	private static Pattern bPat = Pattern.compile("\\[b\\](.*?)\\[\\/b\\]",
			Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	private static Pattern iPat = Pattern.compile("\\[i\\](.*?)\\[\\/i\\]",
			Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	private static Pattern sPat = Pattern.compile("\\[s\\](.*?)\\[\\/s\\]",
			Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	private static Pattern urlPat = Pattern.compile("\\[url\\](.*?)\\[\\/url\\]",
			Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	private static Pattern urlLongPat = Pattern.compile("\\[url=([^\\]]*?)\\](.*?)\\[\\/url\\]",
			Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	private static Pattern listPat = Pattern.compile("\\[list\\](.*?)\\[\\/list\\]",
			Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	private static Pattern itemPat = Pattern.compile("\\[li\\](.*?)\\[\\/li\\]",
			Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	private static Pattern youtubePat = Pattern.compile("\\[youtube\\](.*?)\\[\\/youtube\\]",
			Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	private static Pattern comPat = Pattern.compile("\\[com\\](.*?)\\[\\/com\\]",
			Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	private static Pattern hidPat = Pattern.compile("\\[o=([\\s\\w]*)\\](.*?)\\[\\/o\\]",
			Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	
	public static String BBCodeToHtml(String code) {
		String html = HtmlUtils.htmlEscape(code);
		html = imgPat.matcher(html).replaceAll("<img src='$1' />");
		html = bPat.matcher(html).replaceAll("<strong>$1</strong>");
		html = iPat.matcher(html).replaceAll("<em>$1</em>");
		html = sPat.matcher(html).replaceAll("<del>$1</del>");
		html = urlPat.matcher(html).replaceAll("<a href='$1' target='_blank'>$1</a>");
		html = urlLongPat.matcher(html).replaceAll("<a href='$1' target='_blank'>$2</a>");
		html = listPat.matcher(html).replaceAll("<ul>$1</ul>");
		html = itemPat.matcher(html).replaceAll("<li>$1</li>");
		html = hidPat.matcher(html).replaceAll("$2");
		html = comPat.matcher(html).replaceAll("<details class='comment'><summary>GM Comment</summary>$1</details>");
		html = youtubePat.matcher(html).replaceAll("<iframe width='560' height='315' src='https://www.youtube.com/embed/$1' frameborder='0' allowfullscreen></iframe>");
		html = html.replace("\n", "<br />");
		return html;
	}
}
