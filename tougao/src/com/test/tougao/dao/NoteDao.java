package com.test.tougao.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.jstl.sql.Result;

import com.test.tougao.entity.Note;

public class NoteDao {
	//添加公告
	public int add(Note note) throws SQLException, ClassNotFoundException{
		int n=0;
		String sql="insert into tg_note(admin_no,admin_name,note_name,note_date,note_content,disk_name,file_name,file_size,if_attach) values(?,?,?,to_date(?,'yyyy-mm-dd HH24:MI:SS'),?,?,?,?,?)";
		//数组存储sql语句传递参数(若是整形参数都得转换成字符串型，转换方法直接在整形变量后加上空字符，如user.getUserid()+"")
		String[] params = {note.getAdminNo(),note.getAdminName(),note.getNoteName(),note.getNoteDate(),note.getNoteContent(),note.getDiskName(),note.getFileName(),note.getFileSize(),note.getIsAttach()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//删除公告
	public int delete(String id)throws SQLException, ClassNotFoundException{
		int n=0;
		String sql= "delete from tg_note where note_id = ?";
		String[] params = {id};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//查询单个公告信息
	@SuppressWarnings("unchecked")
	public Note findById(String id)throws SQLException, ClassNotFoundException{
		Note note=null;//必须在开始定义了，才能在最后return ,在中间定义则不可以
		String sql = "select * from tg_note where note_id = ?";
		String[] params = {id};
		//返回查询结果
		Result r = DBHelp.executeQuery(sql, params);
		Map map=null;
		   
		if(r.getRowCount() == 1){//如果结果集中有一条数据，即查找成功
			map = r.getRows()[0];//将查询结果赋值给map对象
			note=new Note();
			note.setNoteId(map.get("note_id").toString());
			note.setAdminNo(map.get("admin_no").toString());
			note.setAdminName(map.get("admin_name").toString());
			note.setNoteName(map.get("note_name").toString());
			note.setNoteDate(map.get("note_date").toString());
			note.setNoteContent(map.get("note_content")==null?"":map.get("note_content").toString());
			note.setDiskName(map.get("disk_name").toString());
			note.setFileName(map.get("file_name").toString());
			note.setFileSize(map.get("file_size").toString());
			note.setIsAttach(map.get("if_attach").toString()); 
		}
		   return note;
	}
	//查询所有公告
	 @SuppressWarnings("unchecked")
	 public List<Note> findAllNotes()throws SQLException, ClassNotFoundException{
		 List<Note> list = new ArrayList<Note>();
		 Note note = null;
		 String sql = "select * from tg_note order by note_date desc ";	
		 Result r = DBHelp.executeQuery(sql, null);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 note=new Note();
			 note.setNoteId(map.get("note_id").toString());
			 note.setAdminNo(map.get("admin_no").toString());
			 note.setAdminName(map.get("admin_name").toString());
			 note.setNoteName(map.get("note_name").toString());
			 note.setNoteDate(map.get("note_date").toString());
			 note.setNoteContent(map.get("note_content")==null?"":map.get("note_content").toString());
			 note.setDiskName(map.get("disk_name").toString());
			 note.setFileName(map.get("file_name").toString());
			 note.setFileSize(map.get("file_size").toString());
			 note.setIsAttach(map.get("if_attach").toString()); 
				
	    	 list.add(note);
	     }
	     return list;
	 }

}
