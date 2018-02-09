package dbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcCURD {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    //更新操作
    public void update(String sql) {
        try {
            connection = JdbcUtils.getConnection();
            statement = connection.createStatement();
            //可执行创建、修改、删除表，添加、删除、修改元组以及查询sql语句
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.releaseResources(resultSet, statement, connection);
        }
    }
    
    //查询操作
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
    
    //添加操作
    public void addElement(String sql) {
        update(sql);
    }
    
    //删除操作
    public void removeElement(String sql) {
        update(sql);
    }

    //创建一个表
    public void createTable(String sql){
        update(sql);
    }
    
    //删除一个表
    public void dropTable(String sql){
        update(sql);
    }

}
