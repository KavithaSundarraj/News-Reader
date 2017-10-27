
import java.io.*;
import java.net.*;
import java.util.*;

public class RSSReader
{

	public static void main(String[] args) 
	{
		System.out.println(readRSS("http://feeds.bbci.co.uk/news/politics/rss.xml"));

	}
	public static String readRSS(String urlAddress)
	{
	try
	{
	URL rssUrl=new URL(urlAddress);
	BufferedReader in = new BufferedReader(new InputStreamReader(rssUrl.openStream())); 
	String sourceCode="";
	String line;
	while((line=in.readLine())!=null)
	{
		if(line.contains("<title><![CDATA["))
		{
			int firstPos=line.indexOf("<title><![CDATA[");
			String temp=line.substring(firstPos);
			temp=temp.replace("<title><![CDATA[", "");
			int lastPos=temp.indexOf("]]></title>");
			temp=temp.substring(0,lastPos);
			sourceCode +=temp+"\n";
		}
	}
	in.close();
	return sourceCode;
	}
	catch (MalformedURLException ue)
	{
		System.out.println("Malformed URL");
	}
	catch (IOException ioe)
	{
		System.out.println("Something went wrong while reading content");
	}
	return null;
	}
	
}

