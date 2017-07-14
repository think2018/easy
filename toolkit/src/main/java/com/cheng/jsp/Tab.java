package com.cheng.jsp;

/**
 * @author chengyunfei
 * @version 1.0
 * @date : 2017-07-14 17:57:02
 * @Description ch ...
 */
public class Tab {
	private String active;
	private String url;
	private String tabName;

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTabName() {
		return tabName;
	}

	public void setTabName(String tabName) {
		this.tabName = tabName;
	}

	public Tab(String active, String url, String tabName) {
		super();
		this.active = active;
		this.url = url;
		this.tabName = tabName;
	}
}
