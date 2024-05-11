import java.sql.*;
public class dataScrapper {
    public static void main(String[] args) throws Exception {
    //s-1
    String drivername = "com.mysql.cj.jdbc.Driver";
    Class.forName(drivername);
    System.out.println("Driver loaded successfully");
    //s-2
    String dburl = "jdbc:mysql://localhost:3306/datascrapper";
    String dbuser = "root";
    String dbpass = "";
    Connection con = DriverManager.getConnection(dburl, dbuser, dbpass);
    if(con != null)
    {
        System.out.print("connection success");
    }
    else{
        System.out.println("Not done connection");
    }
    }    
}
