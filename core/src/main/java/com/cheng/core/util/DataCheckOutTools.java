package com.cheng.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 数据校验工具
 * @author yuesiwen
 * @date 2014-3-4 上午9:46:08 
 * @email yswwpp@163.com
 * @version V1.0
 */
public class DataCheckOutTools
{
	public static boolean IsNull(String str)
	{
		return (str == null) || (str.trim().length() == 0);
	}
	public static boolean IsNull(double data)
	{
		return data<0.0000000001;
	}
	/**
	 * 检验字符串是否是合法的密码字符串
	 * 标准：6——20未得数字或字符组成，字母不区分大小写
	 * @param password
	 * @return
	 */
	public static boolean isPassword(String password)
	{
		if(password!=null&&password.length()>=6&&password.length()<=20)
		{
			for(int i=0;i<password.length();i++)
			{
				String subStr=password.substring(i,i+1);
				Pattern pat1 = Pattern.compile("[0-9]");
				Pattern pat2 = Pattern.compile("[A-Za-z]");
				Matcher mat1 = pat1.matcher(subStr);
				Matcher mat2 = pat2.matcher(subStr);
				if(mat1.matches()||mat2.matches()) 
				{
					continue;
				}else
				{
					return false;
				}
			}
			 return true;
	   }else
	   {
		   return false;
	   }
	}
	/**
	 * 车牌号判断规则
	 * 第一位必须是汉字
	 * 【京,浙,津,皖,沪,闽,渝,赣,港,鲁,澳,豫,蒙,鄂,新,湘,宁,粤,藏,琼,桂,川,冀,贵,晋,云,辽,陕,吉,甘,黑,青,苏,台】之一
	 * 第二位必须是A-Z大写字母
	 * 接下来的5位必须是大写字母和数字组合
	 * @param truckNumber
	 * @return
	 */
	public static boolean isTruckNumber(String truckNumber)
	{
		if(truckNumber==null)return false;
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$"); // 正则表达式 
		Matcher m = p.matcher(truckNumber); // 操作的字符串 
		if(m.matches())
		{
			Set<String> truckNONick = new HashSet<String>();
			String defaultValue = "京,浙,津,皖,沪,闽,渝,赣,港,鲁,澳,豫,蒙,鄂,新,湘,宁,粤,藏,琼,桂,川,冀,贵,晋,云,辽,陕,吉,甘,黑,青,苏,台";
			String[] truckNONickArr = defaultValue.split(",");
			for (String tempStr : truckNONickArr)
				truckNONick.add(tempStr);
			return truckNONick.contains(truckNumber.substring(0, 1));
		}
		return false;
	}
    /**
     * 密码校验
     * @param password 明文密码
     * @param md5Password 加密密码
     * @return 校验通过返回true，校验未通过false
     */
	/*public static boolean passwordCheckOut(String password,String md5Password)
	{
		String newMd5String=DigestUtils.md5Hex(DigestUtils.md5Hex(password)+Protocol.PASSWORD_KEY);
		if(md5Password.equals(newMd5String)) return true;
	    else return false;
	}*/
	/**
	 * 生成加密密码
	 * @param password 明文密码
	 * @return 加密密码
	 */
	/*public static String createMD5Password(String password)
	{
		return DigestUtils.md5Hex(DigestUtils.md5Hex(password)+Protocol.PASSWORD_KEY);
	}*/
	
	/**
	 * 生成验证码
	 * 
	 * @param n
	 * @return
	 */
	public static String createTestNumber(int n)
	{
		int k;
		Random ran = new Random();
		String rnumber = "";
		String str = "0123456789";
		char[] chr = str.toCharArray();
		for (int i = 0; i < n; i++)
		{
			k = ran.nextInt(10);
			if (rnumber.indexOf(chr[k]) == -1)
			{
				// 判读验证新生成的验证码是否与前面的重复
				rnumber = rnumber + chr[k];
			} else
			{
				// 如果重复重新生成
				i--;
				continue;
			}
		}
		return rnumber;
	}
	
	/**
	 * 判断手机号格式(第一为为数字1，后面为10位0到9的数字）
	 * @param phone 手机号
	 * @return
	 */
	public static boolean isPhone(String phone){
		if(phone==null) return false;
        Pattern pattern =  Pattern.compile("^1\\d{10}$");
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
	
    public static void checkContainSpecialChar(String str)
    {
    	if(str!=null)
    	{
    		char[] cArray=str.toCharArray();
    		for(int i=0;i<cArray.length;i++)
    		{
    			if((cArray[i]>=0xE001&&cArray[i]<=0xE05A)||
    					(cArray[i]>=0xE101&&cArray[i]<=0xE15A)||
    					(cArray[i]>=0xE201&&cArray[i]<=0xE253)||
    					(cArray[i]>=0xE301&&cArray[i]<=0xE34D)||
    					(cArray[i]>=0xE401&&cArray[i]<=0xE44C)||
    					(cArray[i]>=0xE501&&cArray[i]<=0xE537))
    				throw new IllegalArgumentException("参数中不能含特殊字符！");
    		}
    	}
    }
    
    public static boolean isIDCard(String IDStr) { 
    	if(IDStr==null) return false;
       
        String[] ValCodeArr = { "1", "0", "X", "9", "8", "7", "6", "5", "4",   
                "3", "2" };   
        String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",   
                "9", "10", "5", "8", "4", "2" };   
        String Ai = "";   
        // ================ 号码的长度 15位或18位 ================   
        if (IDStr.length() != 15 && IDStr.length() != 18) {   
            //errorInfo = "身份证号码长度应该为15位或18位。";   
            return false;   
        }   
        // =======================(end)========================   
  
        // ================ 数字 除最后以为都为数字 ================   
        if (IDStr.length() == 18) {   
            Ai = IDStr.substring(0, 17);   
        } else if (IDStr.length() == 15) {   
            Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);   
        }   
        if (isNumeric(Ai) == false) {   
            //errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";  
            return false;   
        }   
        // =======================(end)========================   
  
        // ================ 出生年月是否有效 ================   
        String strYear = Ai.substring(6, 10);// 年份   
        String strMonth = Ai.substring(10, 12);// 月份   
        String strDay = Ai.substring(12, 14);// 月份   
        if (isDataFormat(strYear + "-" + strMonth + "-" + strDay) == false) {   
            //errorInfo = "身份证生日无效。";   
            return false;   
        }   
        GregorianCalendar gc = new GregorianCalendar();   
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");   
        try
		{
			if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150  
			        || (gc.getTime().getTime() - s.parse(   
			                strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {   
			   // errorInfo = "身份证生日不在有效范围。";   
			    return false;   
			}
		}
		catch (ParseException e)
		{
			e.printStackTrace();
			return false;
		}   
        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {   
           // errorInfo = "身份证月份无效";   
            return false;   
        }   
        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {   
            //errorInfo = "身份证日期无效";   
            return false;   
        }   
        // =====================(end)=====================   
  
        // ================ 地区码时候有效 ================   
        Hashtable<String,String> h = GetAreaCode();   
        if (h.get(Ai.substring(0, 2)) == null) {   
           // errorInfo = "身份证地区编码错误。";   
            return false;   
        }   
        // ==============================================   
  
        // ================ 判断最后一位的值 ================   
        int TotalmulAiWi = 0;   
        for (int i = 0; i < 17; i++) {   
            TotalmulAiWi = TotalmulAiWi   
                    + Integer.parseInt(String.valueOf(Ai.charAt(i)))   
                    * Integer.parseInt(Wi[i]);   
        }   
        int modValue = TotalmulAiWi % 11;   
        String strVerifyCode = ValCodeArr[modValue];   
        Ai = Ai + strVerifyCode;   
  
        if (IDStr.length() == 18) {   
             if (Ai.equals(IDStr) == false) {   
                // errorInfo = "身份证无效，不是合法的身份证号码";   
                 return false;   
             }   
         } else {   
             return true;   
         }   
         // =====================(end)=====================   
         return true;   
     }
    
    /**  
      * 功能：判断字符串是否为数字  
      * @param str  
      * @return  
      */  
     private static boolean isNumeric(String str) {   
         Pattern pattern = Pattern.compile("[0-9]*");   
         Matcher isNum = pattern.matcher(str);   
         if (isNum.matches()) {   
             return true;   
         } else {   
             return false;   
         }   
     }
     
     /**  
      * 功能：设置地区编码  
      * @return Hashtable 对象  
      */  
     private static Hashtable<String,String> GetAreaCode() {   
         Hashtable<String,String> hashtable = new Hashtable<String,String>();   
         hashtable.put("11", "北京");   
         hashtable.put("12", "天津");   
         hashtable.put("13", "河北");   
         hashtable.put("14", "山西");   
         hashtable.put("15", "内蒙古");   
         hashtable.put("21", "辽宁");   
         hashtable.put("22", "吉林");   
         hashtable.put("23", "黑龙江");   
         hashtable.put("31", "上海");   
         hashtable.put("32", "江苏");   
         hashtable.put("33", "浙江");   
         hashtable.put("34", "安徽");   
         hashtable.put("35", "福建");   
         hashtable.put("36", "江西");   
         hashtable.put("37", "山东");   
         hashtable.put("41", "河南");   
         hashtable.put("42", "湖北");   
         hashtable.put("43", "湖南");   
         hashtable.put("44", "广东");   
         hashtable.put("45", "广西");   
         hashtable.put("46", "海南");   
         hashtable.put("50", "重庆");   
         hashtable.put("51", "四川");   
         hashtable.put("52", "贵州");   
         hashtable.put("53", "云南");   
         hashtable.put("54", "西藏");   
         hashtable.put("61", "陕西");   
         hashtable.put("62", "甘肃");   
         hashtable.put("63", "青海");   
         hashtable.put("64", "宁夏");   
         hashtable.put("65", "新疆");   
         hashtable.put("71", "台湾");   
         hashtable.put("81", "香港");   
         hashtable.put("82", "澳门");   
         hashtable.put("91", "国外");   
         return hashtable;   
     }  

/**验证日期字符串是否是YYYY-MM-DD格式
  * @param str
  * @return
  */
 public static boolean isDataFormat(String str){
  boolean flag=false;
  //String regxStr="[1-9][0-9]{3}-[0-1][0-2]-((0[1-9])|([12][0-9])|(3[01]))";
  String regxStr="^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
  Pattern pattern1=Pattern.compile(regxStr);
  Matcher isNo=pattern1.matcher(str);
  if(isNo.matches()){
   flag=true;
  }
  return flag;
 }

 /**  
      * 功能：判断字符串是否为数字  
      * @param str  
      * @return  
      */  
	/*
	 * private static boolean isNumeric(String str) { Pattern pattern =
	 * Pattern.compile("[0-9]*"); Matcher isNum = pattern.matcher(str); if
	 * (isNum.matches()) { return true; } else { return false; } }
	 */
 
 public static void main(String[] agre)
 {
	 System.out.println(isIDCard("420107197703152032"));
	 //System.out.println(isTruckNumber("鄂AB2358"));
 }
   
}
