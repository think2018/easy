/**
 * ID: RandomUtils.java
 * Copyright (c) 2002-2013 Luther Inc.
 * http://xluther.com
 * All rights reserved.
 */
package com.cheng.core.util;

import java.util.Random;

/**
 * 随机数工具集
 *
 * @author			Kama Luther
 * @version			0.1
 * @since           0.1
 * @.createdate     2013-9-21 下午01:47:22
 * @.modifydate     2013-9-21 下午01:47:22
 * <DT><B>修改历史记录</B>
 * <DD>
 * 
 * </DD>
 * </DT>
 */
public class RandomUtils
{

	public static void main(String[] args)
	{
//		System.out.println(RandomUtils.random(6,"abcdef中国人"));
		System.out.println(RandomUtils.random(6,true,true));
//		System.out.println(RandomUtils.nextInt(10));
		
		int min = 1;
		int max = 10;
		int v = new Random().nextInt(max)%(max-min+1) + min;
		System.out.println(v);
	}
	//-----------------------------------------------------------//
	
	
	private static final Random RANDOM = new Random();
	
	
	public static int random(int min, int max)
	{
		return RANDOM.nextInt(max)%(max-min+1) + min;
	}
	
	
    public static String random(int count)
    {
        return random(count, false, false);
    }
	
    public static String randomAscii(int count)
    {
        return random(count, 32, 127, false, false);
    }
	
    public static String randomAlphabetic(int count) 
    {
        return random(count, true, false);
    }
    
    public static String randomAlphanumeric(int count) 
    {
        return random(count, true, true);
    }
	
    public static String randomNumeric(int count) 
    {
        return random(count, false, true);
    }
    
    public static String random(int count, String chars) 
    {
        if (chars == null) 
        {
            return random(count, 0, 0, false, false, null, RANDOM);
        }
        return random(count, chars.toCharArray());
    }
    
    public static String random(int count, char[] chars) 
    {
        if (chars == null) 
        {
            return random(count, 0, 0, false, false, null, RANDOM);
        }
        return random(count, 0, chars.length, false, false, chars, RANDOM);
    }
    
    public static String random(int count, boolean letters, boolean numbers) 
    {
        return random(count, 0, 0, letters, numbers);
    }
	
    public static String random(int count, int start, int end, boolean letters, 
    		boolean numbers)
    {
        return random(count, start, end, letters, numbers, null, RANDOM);
    }
	
    public static String random(int count, int start, int end, boolean letters, 
    		boolean numbers, char[] chars) 
    {
        return random(count, start, end, letters, numbers, chars, RANDOM);
    }
    
	public static String random(int count, int start, int end, boolean letters,
			boolean numbers, char[] chars, Random random)
	{
		if (count == 0)
		{
			return "";
		}
		else if (count < 0)
		{
			throw new IllegalArgumentException(
					"Requested random string length " + count
							+ " is less than 0.");
		}
		if ((start == 0) && (end == 0))
		{
			end = 'z' + 1;
			start = ' ';
			if (!letters && !numbers)
			{
				start = 0;
				end = Integer.MAX_VALUE;
			}
		}

		char[] buffer = new char[count];
		int gap = end - start;

		while (count-- != 0)
		{
			char ch;
			if (chars == null)
			{
				ch = (char) (random.nextInt(gap) + start);
			}
			else
			{
				ch = chars[random.nextInt(gap) + start];
			}
			if ((letters && Character.isLetter(ch))
					|| (numbers && Character.isDigit(ch))
					|| (!letters && !numbers))
			{
				if (ch >= 56320 && ch <= 57343)
				{
					if (count == 0)
					{
						count++;
					}
					else
					{
						// low surrogate, insert high surrogate after putting it
						// in
						buffer[count] = ch;
						count--;
						buffer[count] = (char) (55296 + random.nextInt(128));
					}
				}
				else if (ch >= 55296 && ch <= 56191)
				{
					if (count == 0)
					{
						count++;
					}
					else
					{
						// high surrogate, insert low surrogate before putting
						// it in
						buffer[count] = (char) (56320 + random.nextInt(128));
						count--;
						buffer[count] = ch;
					}
				}
				else if (ch >= 56192 && ch <= 56319)
				{
					// private high surrogate, no effing clue, so skip it
					count++;
				}
				else
				{
					buffer[count] = ch;
				}
			}
			else
			{
				count++;
			}
		}
		return new String(buffer);
	}
	
	public static int nextInt()
	{
		return RANDOM.nextInt(Integer.MAX_VALUE);
	}
	
	public static int nextInt(int n)
	{
		return RANDOM.nextInt(n);
	}
	
	public static long nextLong()
	{
		return RANDOM.nextLong();
	}
	
	public static float nextFloat()
	{
		return RANDOM.nextFloat();
	}
	
	public static double nextDouble()
	{
		return RANDOM.nextDouble();
	}
	
	public static boolean nextBoolean()
	{
		return RANDOM.nextBoolean();
	}

}
