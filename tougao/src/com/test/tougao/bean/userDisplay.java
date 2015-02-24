package com.test.tougao.bean;
import java.io.*;
import java.net.URLDecoder;

public class userDisplay {

	public String[]img=new String[10];
	 public void counter()
	 {
	  try
	  {
	   //String filePath="E:/Tomcat 6.0/webapps/tougao/";
	   String filePath=this.getClass().getResource("/").getPath();
	   filePath=filePath.substring(1, filePath.indexOf("WEB-INF/classes"));//截取项目的根目录
	   filePath=URLDecoder.decode(filePath,"utf-8");//转换路径中出现的空格（不转换空格会显示%20代替）
	   System.out.println(filePath);
	   File f=new File(filePath+"counter.txt");
	   if(f.exists()){
		   FileReader fr=new FileReader(filePath+"counter.txt");
		   BufferedReader br=new BufferedReader(fr);
		   String s=br.readLine();
		   int i=Integer.parseInt(s);
		   int st=10;
		   int j=0;
		   while(j<=6)
		   {
		    img[j]=Integer.toString(i%st);
		    img[j]=img[j]+".gif";
		    img[j]="images/1/"+img[j];
		    img[j]="<img src="+img[j]+">";
		    img[j]=img[j]+"</img>";
		    i/=10;
		    j++;
		   }
	     }
	   else
        {
           System.out.print("counter.txt文件不存在！");
        }

	  }
	  catch(IOException e)
	  {
	   System.out.println(e.toString());
	  }
	 }
}
