package dbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcCURD {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    //���²���
    public void update(String sql) {
        try {
            connection = JdbcUtils.getConnection();
            statement = connection.createStatement();
            //��ִ�д������޸ġ�ɾ������ӡ�ɾ�����޸�Ԫ���Լ���ѯsql���
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.releaseResources(resultSet, statement, connection);
        }
    }
    
    //��ѯ����
    public void Query(String sql) {
        try {
            connection = JdbcUtils.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            
            while(resultSet.next()){
                System.out.println("name:"+resultSet.getString("name"));
                System.out.println("id:"+resultSet.getString("Tid"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.releaseResources(resultSet, statement, connection);
        }
    }
    
    //��Ӳ���
    public void addElement(String sql) {
        update(sql);
    }
    
    //ɾ������
    public void removeElement(String sql) {
        update(sql);
    }

    //����һ����
    public void createTable(String sql){
        update(sql);
    }
    
    //ɾ��һ����
    public void dropTable(String sql){
        update(sql);
    }

}
