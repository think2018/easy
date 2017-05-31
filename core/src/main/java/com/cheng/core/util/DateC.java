package com.cheng.core.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

//import com.tomtop.system.core.MyLocale;

/**
 * 日期相关类
 */
public class DateC {
	public String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";
	public String dateFormat = "yyyy-MM-dd";

	public Date getCurrentTimeD() {
		SimpleDateFormat df = new SimpleDateFormat(dateTimeFormat);

		Date date = null;
		try {
			date = df.parse(getCurrentTime());
		} catch (Exception e) {
		}

		return date;
	}

	public Date getCurrentShortDateD() {
		SimpleDateFormat df = new SimpleDateFormat("yyMMdd");

		Date date = new Date();
		try {
			date = df.parse(getCurrentTime());
		} catch (Exception e) {
		}

		return date;
	}

	public String getCurrentDigitDateTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyMMddHHmmss");

		return df.format(new Date());
	}

	public String getCurrentDateTime() {
		TimeZone zone = TimeZone.getTimeZone("Asia/Shanghai");// 获取时区
		TimeZone.setDefault(zone);// 设置时区,使得输出时间与现在所处地区时间相符

		SimpleDateFormat df = new SimpleDateFormat(dateTimeFormat);

		return df.format(new Date());
	}

	public String getCurrentFullDateTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

		return df.format(new Date());
	}

	public String toUTCDateString(Date date, String dateTimeFormat) {
		if (null == dateTimeFormat) {
			dateTimeFormat = this.dateTimeFormat;
		}
		SimpleDateFormat df = new SimpleDateFormat(dateTimeFormat);
		df.setTimeZone(TimeZone.getTimeZone("UTC"));

		return df.format(date);
	}

	public String toUTCDateString(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		df.setTimeZone(TimeZone.getTimeZone("UTC"));

		return df.format(date);
	}

	public String getCurrentShortDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyMMdd");

		return df.format(new Date());
	}

	public String getCurrentLongDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		return df.format(new Date());
	}

	public String getCurrentLongStringDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy,MM,dd");

		return df.format(new Date());
	}

	public Date getCurrentDateD() {
		SimpleDateFormat df = new SimpleDateFormat(dateFormat);

		Date date = new Date();
		try {
			date = df.parse(getCurrentDate());
		} catch (Exception e) {
		}

		return date;
	}

	public String getCurrentTime() {
		SimpleDateFormat df = new SimpleDateFormat(dateTimeFormat);

		return df.format(new Date());
	}

	public String getCurrentDate() {
		SimpleDateFormat df = new SimpleDateFormat(dateFormat);

		return df.format(new Date());
	}

	public Date getDateD(int count, String format, String dateStr) {
		SimpleDateFormat df = new SimpleDateFormat(format);

		Date date = new Date();
		try {
			date = df.parse(getDate(count, format, dateStr));
		} catch (Exception e) {
		}

		return date;
	}

	public String getDate(int count, String format, String dateStr) {
		Calendar calDate = Calendar.getInstance();
		if (null != dateStr) {
			calDate.setTime(parseDate(dateStr));
		}
		calDate.add(Calendar.DATE, count);
		SimpleDateFormat sdf = new SimpleDateFormat(format);

		return sdf.format(calDate.getTime());
	}

	public Date parseDate(String dateStr) {
		SimpleDateFormat df = new SimpleDateFormat(dateFormat);

		Date date = null;
		try {
			date = df.parse(dateStr);
		} catch (Exception e) {
		}

		return date;
	}

	public Long parseDateToTime(String dateStr) {
		SimpleDateFormat df = new SimpleDateFormat(dateFormat);

		Long minSecs = 0L;
		try {
			minSecs = df.parse(dateStr).getTime();
		} catch (Exception e) {
		}

		return minSecs;
	}

	public Long parseDateToDateTime(String dateStr) {
		SimpleDateFormat df = new SimpleDateFormat(dateTimeFormat);

		Long minSecs = 0L;
		try {
			minSecs = df.parse(dateStr).getTime();
		} catch (Exception e) {
		}

		return minSecs;
	}

	public Date parseDateTime(String dateTimeStr) {
		SimpleDateFormat df = new SimpleDateFormat(dateTimeFormat);

		Date date = null;
		try {
			date = df.parse(dateTimeStr);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return date;
	}

	// public Long dateTimeToSecs(String dateTimeStr) {
	// SimpleDateFormat df = new SimpleDateFormat(dateTimeFormat);
	// Calendar cal = java.util.Calendar.getInstance();
	//
	// return cal.getTimeInMillis() / 1000;
	// }

	public String getDateTimeAddMin(int mins) {
		SimpleDateFormat df = new SimpleDateFormat(dateTimeFormat);
		Calendar cal = java.util.Calendar.getInstance();
		cal.add(java.util.Calendar.MILLISECOND, 1000 * 60 * mins);

		return df.format(new Date(cal.getTimeInMillis()));
	}

	// public String changeSecToReadable(Long sec) {
	// MyLocale myLocale = new MyLocale();
	// StringBuilder readableTime = new StringBuilder();
	//
	// readableTime.append(sec/(60*60*24));
	// readableTime.append(myLocale.getText("day.s"));
	// readableTime.append(sec%(60*60*24)/(60*60));
	// readableTime.append(myLocale.getText("hours.s"));
	// readableTime.append(sec%(60*60*24)%(60*60)/60);
	// readableTime.append(myLocale.getText("min.s"));
	// readableTime.append(sec%(60*60*24)%(60*60)%60);
	// readableTime.append(myLocale.getText("sec.s"));
	// return readableTime.toString();
	// }
}
