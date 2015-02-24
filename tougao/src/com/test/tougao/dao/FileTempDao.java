package com.test.tougao.dao;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.jsp.jstl.sql.Result;

import com.test.tougao.entity.FileTemp;

public class FileTempDao {
	//添加已审论文
	public int add(FileTemp filetemp) throws SQLException, ClassNotFoundException{
		int n=0;
		String sql="insert into tg_filetemp(expert_no,doc_name,doc_type,doc_level,allot_time,save_time,message_name,doc_message,if_agreed) values(?,?,?,?,to_date(?,'yyyy-mm-dd HH24:MI:SS'),to_date(?,'yyyy-mm-dd HH24:MI:SS'),?,?,?)";
		//数组存储sql语句传递参数(若是整形参数都得转换成字符串型，转换方法直接在整形变量后加上空字符，如user.getUserid()+"")
		String[] params = {filetemp.getExpertNo(),filetemp.getDocName(),filetemp.getDocType(),filetemp.getDocLevel(),filetemp.getAllotTime(),filetemp.getSaveTime(),filetemp.getMessageName(),filetemp.getDocMessage(),filetemp.getIsAgreed()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//修改待审论文
	public int update(FileTemp filetemp)throws SQLException, ClassNotFoundException{
		int n=0;
		String sql = "update tg_filetemp set expert_no=?,doc_name=?,doc_type=?,doc_level=?,allot_time=to_date(?,'yyyy-mm-dd HH24:MI:SS'),save_time=to_date(?,'yyyy-mm-dd HH24:MI:SS'),message_name=?,doc_message=?,if_agreed=? where filetemp_id = ?";
		//参数对应JSP页面获取值
		String[] params = {filetemp.getExpertNo(),filetemp.getDocName(),filetemp.getDocType(),filetemp.getDocLevel(),filetemp.getAllotTime(),filetemp.getSaveTime(),filetemp.getMessageName(),filetemp.getDocMessage(),filetemp.getIsAgreed(),filetemp.getFileTempId()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//删除待审论文
	public int delete(String id)throws SQLException, ClassNotFoundException{
		int n=0;
		String sql= "delete from tg_filetemp where filetemp_id = ?";
		String[] params = {id};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	
	//根据论文名和分配时间查询专家已保存的评语
	 @SuppressWarnings("unchecked")
	 public FileTemp findFileTemp(String expertno,String docname,String allottime)throws SQLException, ClassNotFoundException{
		 FileTemp filetemp = null;
		 String sql = "select * from tg_filetemp where expert_no=? and doc_name=? and allot_time=to_date(?,'yyyy-mm-dd HH24:MI:SS')";	
		 String[] params = {expertno,docname,allottime};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
		 if(r.getRowCount() == 1){//如果结果集中有一条数据，即查找成功
			 map = r.getRows()[0];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 filetemp=new FileTemp();
	    	 filetemp.setFileTempId(map.get("filetemp_id").toString());
	    	 filetemp.setExpertNo(map.get("expert_no").toString());
	    	 filetemp.setDocName(map.get("doc_name").toString());
	    	 filetemp.setDocType(map.get("doc_type").toString());
	    	 filetemp.setDocLevel(map.get("doc_level").toString());
	    	 filetemp.setAllotTime(map.get("allot_time").toString());
	    	 filetemp.setSaveTime(map.get("save_time").toString());
	    	 filetemp.setMessageName(map.get("message_name").toString());
	    	 filetemp.setDocMessage(map.get("doc_message")==null?"":map.get("doc_message").toString());
	    	 filetemp.setIsAgreed(map.get("if_agreed").toString());
	     }
	     return filetemp;
	 }

}
