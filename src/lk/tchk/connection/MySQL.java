
package lk.tchk.connection;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class MySQL {
    
    private static final String DATABASE = "acadex";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "ChathuHare@1232006";
    private static Connection connection;
    
    public static Connection getConnection(){
       
        if(connection == null){
            
            try{
                
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DATABASE, USERNAME, PASSWORD);
                
            }catch(SQLException | ClassNotFoundException e){
                e.printStackTrace();
            }
            
            
        }
        
        return connection;
        
    }
    
    public static  ResultSet execute(String query) throws SQLException{
        
        Statement smt = getConnection().createStatement();
        if(query.toUpperCase().startsWith("SELECT")){
            return smt.executeQuery(query);
        }else{
            smt.execute(query);
            return null;
        }
        
    }
    
    
    
    
}
