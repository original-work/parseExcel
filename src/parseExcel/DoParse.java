package parseExcel;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import dbUtil.JdbcUtils;

public class DoParse {
	
	static Logger logger = Logger.getLogger(DoParse.class.getName());
	
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement upps = null;
		try{
	        File file = new File("E:/MyWorkspace/export_ims.xlsx");
	        ArrayList<ArrayList<Object>> result = ExcelUtil.readExcel(file);
	        
	        conn = JdbcUtils.getConnection();
//	        PreparedStatement ps = conn.prepareStatement(
//	     		   "INSERT into itms_user_info values (?, ?, ?, ?, ?, ?, ?, ?)");    
	        
	        upps = conn.prepareStatement(
		     		   "UPDATE app_user_info SET BAC_Main_AddressIP=?, BAC_Main_AddressPort=?, BAC_Backup_AddressIP=?, "
		     		   + "BAC_Backup_AddressPort=?, IMS_Domain=?, IMS_Password=? WHERE mdn=?");
	        logger.info("result.size() is "+result.size());
	        for(int i = 0 ;i < result.size() ;i++){  
	            for(int j = 0;j<result.get(i).size(); j++){  
	                logger.info(i+"ÐÐ "+j+"ÁÐ  "+ result.get(i).get(j).toString());
	                String row = result.get(i).get(j).toString();
	                row = row.substring(1,row.length()-1);
	                
	                int k = 0;
	                String mdn="",imsi="",ims_ip="",ims_port="",ims_ip_backup="",ims_port_backup="",domain="",password="";
	                for(int begin = 0;row.indexOf("~") < row.length();k++){
	                	String item = row.substring(begin, row.indexOf("~"));
	                	
//	                	logger.info("row.indexOf(\"~\") is "+row.indexOf("~"));
//	                	logger.info("row.length() is "+row.length());
	                	logger.info("item["+k+"] is "+item);
	                	
	                	
	                	String p1,p2,p3;
	                	p1 = item.substring(0, item.indexOf("="));
	                	p2 = item.substring(item.indexOf("=")+1, item.indexOf("|"));
	                	p3 = item.substring(item.indexOf("|")+1, item.length());
	                	logger.info("p1 is "+p1);
	                	logger.info("p2 is "+p2);
	                	logger.info("p3 is "+p3);
	                	switch(p1){
		                	case "WIPHONE_PhoneNo":{
		                		mdn=p2;
		                		break;
		                	}
		                	case "WIPHONE_IMSI":{
		                		imsi=p2;
		                		break;
		                	}
		                	case "WIPHONE_BAC_Main_AddressIP":{
		                		ims_ip=p2;
		                		break;
		                	}
		                	case "WIPHONE_BAC_Main_AddressPort":{
		                		ims_port=p2;
		                		break;
		                	}
		                	case "WIPHONE_BAC_Backup_AddressIP":{
		                		ims_ip_backup=p2;
		                		break;
		                	}
		                	case "WIPHONE_BAC_Backup_AddressPort":{
		                		ims_port_backup=p2;
		                		break;
		                	}
		                	case "WIPHONE_Domain":{
		                		domain=p2;
		                		break;
		                	}
		                	case "WIPHONE_Password":{
		                		password=p2;
		                		break;
		                	}
	                	}
	                	
	                	
	                	
	                	row = row.substring(row.indexOf("~")+1,row.lastIndexOf("~")+1);
	                	if(row.length()==0){
	                		logger.info("");
	                		logger.info("--------next user info--------");
	                		logger.info("");
	                		break;
	                	}
//	                	logger.info("row["+k+"] is "+row);
	                }
	                
//					ps.setString(1,mdn);
//					ps.setString(2,imsi);
//					ps.setString(3,ims_ip);
//					ps.setString(4,ims_port);
//					ps.setString(5,ims_ip_backup);
//					ps.setString(6,ims_port_backup);
//					ps.setString(7,domain);
//					ps.setString(8,password);
//					ps.addBatch();
					
					
					upps.setString(1,ims_ip);
			        upps.setString(2,ims_port);
			        upps.setString(3,ims_ip_backup);
			        upps.setString(4,ims_port_backup);
			        upps.setString(5,domain);
			        upps.setString(6,password);
			        upps.setString(7,mdn);
			        upps.addBatch();
	            }  
	            
	            if(i%1000==0){
	            	upps.executeBatch();
	    	        upps.clearBatch();
	            }
	        }  
//	        ps.executeBatch();
	        upps.executeBatch();
	        upps.clearBatch();
	    }catch(Exception e){
	    	logger.info("Exception is "+e);
	    }finally{
	    	JdbcUtils.releaseResources(null, upps, conn);
	    }
	}


}
