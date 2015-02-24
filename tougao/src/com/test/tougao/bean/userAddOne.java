package com.test.tougao.bean;
import java.io.*;
import java.net.URLDecoder;

public class userAddOne {

	public userAddOne(){
		try{
			//String filePath="E:/Tomcat 6.0/webapps/tougao/";//counter.txt计数文件存放的绝对路径
			String filePath=this.getClass().getResource("/").getPath();
			filePath=filePath.substring(1, filePath.indexOf("WEB-INF/classes"));//截取项目的根目录
			filePath=URLDecoder.decode(filePath,"utf-8");//转换路径中出现的空格（不转换空格会显示%20代替）
			//System.out.println(filePath);
			File f=new File(filePath,"counter.txt");
			if(f.exists()){
				FileReader fr=new FileReader(filePath+"counter.txt");
				BufferedReader br = new BufferedReader(fr);
				String s=br.readLine();
				int count = Integer.parseInt(s);
				count++;
				System.out.println("已经有"+count+"位访客");
				br.close();		
				s=Integer.toString(count);
				FileWriter fw=new FileWriter(filePath+"counter.txt");
            	PrintWriter pw=new PrintWriter(fw);
            	pw.print(s);//将新的计数值写回counter.txt
            	pw.close();     	
			}
			else{
				f.createNewFile();
                FileWriter fw=new FileWriter(filePath+"counter.txt");
                BufferedWriter bw=new BufferedWriter(fw);
                bw.write("1");
                bw.flush();
                fw.close();    
			}
		}catch(IOException e){
			System.out.println(e.toString());
		}
	}
}
