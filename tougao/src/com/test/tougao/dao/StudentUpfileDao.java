package com.test.tougao.dao;

import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.jstl.sql.Result;

import com.test.tougao.entity.StudentUpfile;

public class StudentUpfileDao {
	//添加论文
	public int add(StudentUpfile studentupfile) throws SQLException, ClassNotFoundException{
		int n=0;
		String sql="insert into tg_supfile(disk_id,file_name,file_uploader,file_writer,paper_name,file_type,file_info,file_size,file_time) values(?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd HH24:MI:SS'))";
		//数组存储sql语句传递参数(若是整形参数都得转换成字符串型，转换方法直接在整形变量后加上空字符，如user.getUserid()+"")
		String[] params = {studentupfile.getDiskId(),studentupfile.getFileName(),studentupfile.getFileUploader(),studentupfile.getFileWriter(),studentupfile.getPaperName(),studentupfile.getFileType(),studentupfile.getFileInfo(),studentupfile.getFileSize(),studentupfile.getFileTime()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//修改论文
	public int update(StudentUpfile studentupfile)throws SQLException, ClassNotFoundException{
		int n=0;
		String sql = "update tg_supfile set disk_id=?,file_name=?,file_uploader=?,file_writer=?,paper_name=?,file_type=?,file_info=?,file_size=?,file_time=to_date(?,'yyyy-mm-dd HH24:MI:SS') where file_id=?";
		//参数对应JSP页面获取值
		String[] params = {studentupfile.getDiskId(),studentupfile.getFileName(),studentupfile.getFileUploader(),studentupfile.getFileWriter(),studentupfile.getPaperName(),studentupfile.getFileType(),studentupfile.getFileInfo(),studentupfile.getFileSize(),studentupfile.getFileTime(),studentupfile.getFileId()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//删除论文
	public int delete(String id)throws SQLException, ClassNotFoundException{
		int n=0;
		String sql= "delete from tg_supfile where file_id = ?";
		String[] params = {id};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//根据论文ID查询每条上传记录
	@SuppressWarnings("unchecked")
	public StudentUpfile findById(String fileid)throws SQLException, ClassNotFoundException{
		StudentUpfile studentupfile=null;//必须在开始定义了，才能在最后return ,在中间定义则不可以
		String sql = "select * from tg_supfile where file_id=?";
		String[] params = {fileid};
		//返回查询结果
		Result r = DBHelp.executeQuery(sql, params);
		Map map=null;
		   
		if(r.getRowCount() == 1){//如果结果集中有一条数据，即查找成功
			map = r.getRows()[0];//将查询结果赋值给map对象
			studentupfile=new StudentUpfile();
			studentupfile.setFileId(map.get("file_id").toString());
			studentupfile.setDiskId(map.get("disk_id").toString());
			studentupfile.setFileName(map.get("file_name").toString());
			studentupfile.setFileUploader(map.get("file_uploader").toString());
			studentupfile.setFileWriter(map.get("file_writer").toString());
			studentupfile.setPaperName(map.get("paper_name").toString());
			studentupfile.setFileType(map.get("file_type").toString());
			studentupfile.setFileInfo(map.get("file_info")==null?"":map.get("file_info").toString());
			studentupfile.setFileSize(map.get("file_size").toString());
			studentupfile.setFileTime(map.get("file_time").toString());
			
		}
		   return studentupfile;
	}
	//根据论文名和上传日期查询每条上传记录
	@SuppressWarnings("unchecked")
	public StudentUpfile findUpfile(String papername,String date)throws SQLException, ClassNotFoundException{
		StudentUpfile studentupfile=null;//必须在开始定义了，才能在最后return ,在中间定义则不可以
		String sql = "select * from tg_supfile where paper_name = ? and file_time=to_date(?,'yyyy-mm-dd HH24:MI:SS')";
		String[] params = {papername,date};
		//返回查询结果
		Result r = DBHelp.executeQuery(sql, params);
		Map map=null;
		   
		if(r.getRowCount() == 1){//如果结果集中有一条数据，即查找成功
			map = r.getRows()[0];//将查询结果赋值给map对象
			studentupfile=new StudentUpfile();
			studentupfile.setFileId(map.get("file_id").toString());
			studentupfile.setDiskId(map.get("disk_id").toString());
			studentupfile.setFileName(map.get("file_name").toString());
			studentupfile.setFileUploader(map.get("file_uploader").toString());
			studentupfile.setFileWriter(map.get("file_writer").toString());
			studentupfile.setPaperName(map.get("paper_name").toString());
			studentupfile.setFileType(map.get("file_type").toString());
			studentupfile.setFileInfo(map.get("file_info")==null?"":map.get("file_info").toString());
			studentupfile.setFileSize(map.get("file_size").toString());
			studentupfile.setFileTime(map.get("file_time").toString());
			
		}
		   return studentupfile;
	}
	//查询每个论文的相关上传记录
	 @SuppressWarnings("unchecked")
	 public List<StudentUpfile> findPaperNotes(String studentno,String papername)throws SQLException, ClassNotFoundException{
		 List<StudentUpfile> list = new ArrayList<StudentUpfile>();
		 StudentUpfile studentupfile=null;
		 String sql = "select * from tg_supfile where file_uploader=? and paper_name = ? order by file_time asc";	
		 String[] params = {studentno,papername};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 studentupfile=new StudentUpfile();
	    	 studentupfile.setFileId(map.get("file_id").toString());
			 studentupfile.setDiskId(map.get("disk_id").toString());
			 studentupfile.setFileName(map.get("file_name").toString());
			 studentupfile.setFileUploader(map.get("file_uploader").toString());
			 studentupfile.setFileWriter(map.get("file_writer").toString());
			 studentupfile.setPaperName(map.get("paper_name").toString());
			 studentupfile.setFileType(map.get("file_type").toString());
			 studentupfile.setFileInfo(map.get("file_info")==null?"":map.get("file_info").toString());
			 studentupfile.setFileSize(map.get("file_size").toString());
			 studentupfile.setFileTime(map.get("file_time").toString());
	    	 
	    	 list.add(studentupfile);
	     }
	     return list;
	 }
	
}
