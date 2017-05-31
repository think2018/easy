package com.cheng.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cheng.model.Location;
import com.google.gson.JsonParser;

public class GISTools {

	public static enum CoordsSystem {
		WGS_84((byte) 1), MARS((byte) 2), BAIDU((byte) 3);

		private byte value;

		public static GISTools.CoordsSystem valueOf(byte value) {
			switch (value) {
			case 1:
				return WGS_84;
			case 2:
				return MARS;
			case 3:
				return BAIDU;
			default:
				return null;
			}
		}

		private CoordsSystem(byte value) {
			this.value = value;
		}

		public byte value() {
			return this.value;
		}
	}

	private static final double x_pi = 3.14159265358979324 * 3000.0 / 180.0;

	public static double lantitudeLongitudeDist(double lon1, double lat1, double lon2, double lat2) {
		double er = 6378137; // 6378700.0f;
		double radlat1 = Math.PI * lat1 / 180.0f;
		double radlat2 = Math.PI * lat2 / 180.0f;
		// now long.
		double radlong1 = Math.PI * lon1 / 180.0f;
		double radlong2 = Math.PI * lon2 / 180.0f;
		if (radlat1 < 0)
			radlat1 = Math.PI / 2 + Math.abs(radlat1);// south
		if (radlat1 > 0)
			radlat1 = Math.PI / 2 - Math.abs(radlat1);// north
		if (radlong1 < 0)
			radlong1 = Math.PI * 2 - Math.abs(radlong1);// west
		if (radlat2 < 0)
			radlat2 = Math.PI / 2 + Math.abs(radlat2);// south
		if (radlat2 > 0)
			radlat2 = Math.PI / 2 - Math.abs(radlat2);// north
		if (radlong2 < 0)
			radlong2 = Math.PI * 2 - Math.abs(radlong2);// west
		// spherical coordinates x=r*cos(ag)sin(at), y=r*sin(ag)*sin(at),
		// z=r*cos(at)
		// zero ag is up so reverse lat
		double x1 = er * Math.cos(radlong1) * Math.sin(radlat1);
		double y1 = er * Math.sin(radlong1) * Math.sin(radlat1);
		double z1 = er * Math.cos(radlat1);
		double x2 = er * Math.cos(radlong2) * Math.sin(radlat2);
		double y2 = er * Math.sin(radlong2) * Math.sin(radlat2);
		double z2 = er * Math.cos(radlat2);
		double d = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) + (z1 - z2) * (z1 - z2));
		// side, side, side, law of cosines and arccos
		double theta = Math.acos((er * er + er * er - d * d) / (2 * er * er));
		double dist = theta * er;

		return dist;
	}

	/**
	 * 百度经纬度转高德经纬度
	 * 
	 * @param bd_lon
	 * @param bd_lat
	 * @return 0位经度，1位纬度
	 */
	public static double[] bd_decrypt(double bd_lon, double bd_lat) {
		double x = bd_lon - 0.0065, y = bd_lat - 0.006;
		double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
		double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
		double[] result = new double[2];
		result[0] = z * Math.cos(theta); // 经度
		result[1] = z * Math.sin(theta); // 纬度
		return result;
	}

	// -------------------------------------------------------------------------
	//
	// 地球坐标转换为火星坐标(wsg84-->gcj02)
	// 代码: https://on4wp7.codeplex.com/SourceControl/changeset/view/21483#353936
	//
	// -------------------------------------------------------------------------

	final static double pi = 3.14159265358979324;

	//
	// Krasovsky 1940
	//
	// a = 6378245.0, 1/f = 298.3
	// b = a * (1 - f)
	// ee = (a^2 - b^2) / a^2;
	final static double a = 6378245.0;
	final static double ee = 0.00669342162296594323;

	public static boolean outOfChina(double lat, double lon) {
		if (lon < 72.004 || lon > 137.8347)
			return true;
		if (lat < 0.8293 || lat > 55.8271)
			return true;
		return false;
	}

	private static double transformLat(double x, double y) {
		double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y + 0.2 * Math.sqrt(Math.abs(x));
		ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
		ret += (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0;
		ret += (160.0 * Math.sin(y / 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0;
		return ret;
	}

	private static double transformLon(double x, double y) {
		double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1 * Math.sqrt(Math.abs(x));
		ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
		ret += (20.0 * Math.sin(x * pi) + 40.0 * Math.sin(x / 3.0 * pi)) * 2.0 / 3.0;
		ret += (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(x / 30.0 * pi)) * 2.0 / 3.0;
		return ret;
	}

	// World Geodetic System (WGS84) ==> Mars Geodetic System (GCJ02)
	public static double[] transform(double wgLat, double wgLon) {
		double[] d = new double[2];
		if (outOfChina(wgLat, wgLon)) {
			d[0] = wgLat;
			d[1] = wgLon;
			return d;
		}

		double dLat = transformLat(wgLon - 105.0, wgLat - 35.0);
		double dLon = transformLon(wgLon - 105.0, wgLat - 35.0);
		double radLat = wgLat / 180.0 * pi;
		double magic = Math.sin(radLat);
		magic = 1 - ee * magic * magic;
		double sqrtMagic = Math.sqrt(magic);
		dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
		dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
		d[0] = wgLat + dLat;
		d[1] = wgLon + dLon;
		return d;
	}

	// Mars Geodetic System (GCJ02) ==> World Geodetic System (WGS84)
	public static double[] distransform(double mgLat, double mgLon) {
		double[] d = transform(mgLat, mgLon);
		d[0] = mgLat * 2.0 - d[0];
		d[1] = mgLon * 2.0 - d[1];
		return d;
	}

	/**
	 * 经纬度转地理位置 内部实现调用百度api
	 * 
	 * @param lng
	 * @param lat
	 * @return
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	public static String latAndLngToAddress(double lng, double lat, CoordsSystem coordsSystem) throws UnsupportedEncodingException,
			IOException {
		String coordtype = "wgs84ll";
		String location = lat + "," + lng;

		switch (coordsSystem) {
		case WGS_84:
			coordtype = "wgs84ll";
			break;
		case MARS:
			coordtype = "gcj02ll";
			break;
		case BAIDU:
			coordtype = "bd09ll";
			break;
		default:
			throw new IllegalArgumentException("坐标系coordsSystem不支持！");
		}
		String param = "&coordtype=" + coordtype + "&location=" + location;
		HttpURLConnection conn = (HttpURLConnection) new URL(
				"http://api.map.baidu.com/geocoder/v2/?ak=FF368207e8757b529c3fc19e0a9b30ac&output=json&pois=0" + param).openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("GET");

		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
		String result = reader.readLine();
		reader.close();
		return new JsonParser().parse(result).getAsJsonObject().get("result").getAsJsonObject().get("formatted_address").getAsString();
	}

	/**
	 * 经纬度转地理位置 内部实现调用百度api
	 * 
	 * @param lng
	 * @param lat
	 * @param coordsSystem
	 * @return 包含详细位置信息的数据
	 * @throws Exception
	 */
	public static Location latAndLngToLocation(double lng, double lat, CoordsSystem coordsSystem) throws Exception {
		String coordtype = "wgs84ll";
		String loc = lat + "," + lng;

		switch (coordsSystem) {
		case WGS_84:
			coordtype = "wgs84ll";
			break;
		case MARS:
			coordtype = "gcj02ll";
			break;
		case BAIDU:
			coordtype = "bd09ll";
			break;
		default:
			throw new IllegalArgumentException("坐标系coordsSystem不支持！");
		}
		String param = "&coordtype=" + coordtype + "&location=" + loc;
		HttpURLConnection conn = (HttpURLConnection) new URL(
				"http://api.map.baidu.com/geocoder/v2/?ak=FF368207e8757b529c3fc19e0a9b30ac&output=json&pois=0" + param).openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("GET");

		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
		String result = reader.readLine();
		reader.close();

		int state = conn.getResponseCode();
		// logger.debug("state : " + state);
		if (state == 200) {
			// logger.debug("data : " + data);
			if (result != null && result.trim().length() > 0) {
				JSONObject obj = (JSONObject) JSON.parse(result);
				JSONObject resultObj = obj.getJSONObject("result");
				// JsonObject jobj = new
				// JsonParser().parse(result).getAsJsonObject();
				if (resultObj != null) {
					JSONObject addressObj = resultObj.getJSONObject("addressComponent");
					if (addressObj != null) {

						String province = addressObj.getString("province");// addressobj.get("province").getAsString();
						String city = addressObj.getString("city");// .getAsString();
						String district = addressObj.getString("district");// .getAsString();
						String street = addressObj.getString("street");// .getAsString();
						String streetNo = addressObj.getString("street_number");// .getAsString();
						String address = resultObj.getString("formatted_address");// .getAsString();
						Location location = new Location(lat, lng, (byte) 0, province, "", city, "", district, "", street, streetNo,
								address, 0L, 0L);
						return location;
					}

				}
			}
		}

		return null;

	}
	
	public static boolean isRightLogAndLat(double longitude,double latitude)
	{
		if(longitude<70||longitude>137) return false;
		if(latitude<15||latitude>56)return false;
		return true;
	}

	public static void main(String[] agre) {
		try {
			System.out.println(latAndLngToAddress(113.680641, 34.726104, CoordsSystem.WGS_84));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
