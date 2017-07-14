package com.cheng.jsp;

import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class CreateJspPage{

	private static String title = "测试标题";
	private static String context = "";
	private static String editer = "";

	public static boolean JspToHtmlFile(String filePath, String HtmlFile) {
		String str = "";
		try {
			String tempStr = "";
			FileInputStream is = new FileInputStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			while ((tempStr = br.readLine()) != null)
				str = str + tempStr;
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		try {

			str = str.replaceAll("###title###", title);

			ArrayList<Tab> list = new ArrayList<>();
			list.add(new Tab("active", "/test/a", "TAB_A"));
			list.add(new Tab("", "/test/b", "TAB_B"));
			list.add(new Tab("", "/test/c", "TAB_C"));
			
			String tabHtml = "<li class=\"active\"><a href=\"url\">tabName</a></li>";
			
			String tab = "";
			
//			for (Tab tab : list) {
//				
//			}
			
			
			str = str.replaceAll("###content###", context);
			str = str.replaceAll("###author###", editer);

			File f = new File(HtmlFile);
			BufferedWriter o = new BufferedWriter(new FileWriter(f));
			o.write(str);
			o.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean JspToHtmlByURL(String u, String path) {
		String str = "";
		try {
			URL url = new URL(u);
			URLConnection uc = url.openConnection();
			InputStream is = uc.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			while (br.ready()) {
				str += br.readLine() + "\n";

			}
			is.close();
			File f = new File(path);
			BufferedWriter o = new BufferedWriter(new FileWriter(f));
			o.write(str);
			o.close();
			str = "";
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static StringBuffer getHtmlTextByURL(String url) {
		StringBuffer sb = new StringBuffer();
		try {
			URL u = new URL(url);
			URLConnection uc = u.openConnection();
			InputStream is = uc.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			while (br.ready()) {
				sb.append(br.readLine() + "\n");
			}
			is.close();
			return sb;
		} catch (Exception e) {
			e.printStackTrace();
			return sb;
		}
	}

	public static void main(String[] arg) {
		String url = "D:\\workspace_neon\\easy\\toolkit\\src\\main\\java\\com\\cheng\\jsp\\template.jsp";// ģ���ļ���ַ
		String savepath = "d:/temp/ok.html";// �����ļ���ַ
		JspToHtmlFile(url, savepath);
	}
}