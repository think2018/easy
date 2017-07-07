package com.cheng.utils.file;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class IOTools
{
	public static String streamToString(InputStream instream)
	{
		try
		{
			int inputLength = 1024;
			byte[] buffer = new byte[inputLength];
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			int offset = 0;
			while ((offset = instream.read(buffer, 0, inputLength)) != -1)
			{
				output.write(buffer, 0, offset);
			}
			String result = new String(output.toByteArray(), "UTF-8");
			output.flush();
			output.close();
			instream.close();
			return result;
		}
		catch (IOException e)
		{
			System.out.println(e);
			return null;
		}
	}
}
