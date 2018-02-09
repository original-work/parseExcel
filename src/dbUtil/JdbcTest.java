package dbUtil;



public class JdbcTest {
    
    public static void main(String[] args) {
        try{
	        JdbcCURD curd=new JdbcCURD();
	        System.out.println("user.dir is "+System.getProperty("user.dir"));
	        String sql = null;

	        //添加表Teacher
	        sql="create table Teacher (Tid char(9) primary key,name char(9) unique)";
	        curd.createTable(sql);
	        
	        //添加元组
	        sql = "insert into Teacher (Tid,name) values ('0001','Tom')";
	        curd.addElement(sql);
	        
	        //查询Teacher表
	        sql="select * from Teacher";
	        curd.Query(sql);
	        
	        //删除元组
	        sql="delete from Teacher where Tid='0001'";
	        curd.removeElement(sql);
	        
	        //删除表Teacher
	        sql="drop table Teacher";
	        curd.dropTable(sql);
        }catch(Exception e){
        	System.out.println("error is "+e);
        }
    }

}
