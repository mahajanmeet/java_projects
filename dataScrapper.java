import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

 class SimpleWebScraper {
    public static void main(String[] args) throws Exception{
        Scanner sc=new Scanner(System.in);
        System.out.println("write or paste the url link of website you want to scrap");
        String url = sc.next();//"https://www.imdb.com/list/ls055386972/";
        System.out.println("Mention the search engine from url belongs too !");
        String se = sc.next(); // "chrome"

            Document document = Jsoup.connect(url).get();

            System.out.println("Please enter the data location which you want to scrap");
            String path = sc.next();
            Elements articleElements = document.select(path); //"div.lister-item-content"

            System.out.println("Enter a filename to store scrap data");
            String fname = sc.next();
            File f= new File(fname+".txt");
            FileWriter fw = new FileWriter(f);
            
            for (Element articleElement : articleElements) {    
                String articleTitle = articleElement.text();
                System.out.println("Title: " + articleTitle+"\n");
                fw.write(articleTitle);
    
        } 
        // uploading data in database
         //s - 1 : load driver
        String driverName = "com.mysql.cj.jdbc.Driver";
        Class.forName(driverName);
        System.out.println("driver loaded ");
        //s - 2 : create connection
        String dburl = "jdbc:mysql://localhost:3306/datascrapper";
        String dbuser = "root";
        String dbpass = "";
        Connection con = DriverManager.getConnection(dburl, dbuser, dbpass);
        if(con != null)
        {
            System.out.println("connection done");
        }

        else{
            System.out.println("not connected ! try again");
        }

        //s - 3 : write sql 
        String sql = "insert into websdata(file_name,web_link,web_data) values (?,?,?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1,fname);
        pst.setString(2, url);
        FileReader fr = new FileReader(f);
        pst.setCharacterStream(3,fr);
        int i = pst.executeUpdate();
        if (i>0)
        {
            System.out.println("insertion is done");
        }        
        else{
            System.out.println("insertion failed");
        }

    }
}
