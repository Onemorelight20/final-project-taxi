package ua.boretskyi.webtask.logic;

import org.apache.log4j.Logger;

public class UrlParseHelper {
	private String url;
	private static final Logger log = Logger.getLogger(UrlParseHelper.class);

	public UrlParseHelper(String fullUrl) {
		this.url = fullUrl;
	}

	public UrlParseHelper removeParamFromUrl(String param) {

		log.info("Trying to remove param: " + param);
		log.info("URL before removal: " + url);
		url = url.replaceAll("(?<=[?&;])" + param + "=.*?($|[&;])", "");
		log.info("URL after removal: " + url);
		return this;
	}

	public String getUrl() {
		return url;
	}
}
