package dbUtil;



public class JdbcTest {
    
    public static void main(String[] args) {
        try{
	        JdbcCURD curd=new JdbcCURD();
	        System.out.println("user.dir is "+System.getProperty("user.dir"));
	        String sql = null;

	        //��ӱ�Teacher
	        sql="create table Teacher (Tid char(9) primary key,name char(9) unique)";
	        curd.createTable(sql);
	        
	        //���Ԫ��
	        sql = "insert into Teacher (Tid,name) values ('0001','Tom')";
	        curd.addElement(sql);
	        
	        //��ѯTeacher��
	        sql="select * from Teacher";
	        curd.Query(sql);
	        
	        //ɾ��Ԫ��
	        sql="delete from Teacher where Tid='0001'";
	        curd.removeElement(sql);
	        
	        //ɾ����Teacher
	        sql="drop table Teacher";
	        curd.dropTable(sql);
        }catch(Exception e){
        	System.out.println("error is "+e);
        }
    }

}
