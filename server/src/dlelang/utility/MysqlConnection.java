package dlelang.utility;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


/**
 * Created by Ultrabook on 3/26/2017.
 */
public class MysqlConnection {
    private Connection con;
    Properties prop = new Properties();
    InputStream input = null;


    public Connection connect(){
        String dbname = "";
        String dbhost = "";
        String dbuser = "";
        String dbpassword = "";
        try {
            input = new FileInputStream("config.properties");
            prop.load(input);

            dbname = prop.getProperty("database");
            dbuser = prop.getProperty("dbuser");
            dbpassword = prop.getProperty("dbpassword");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(con == null){
            MysqlDataSource db = new MysqlDataSource();
            db.setDatabaseName(dbname);
            db.setUser(dbuser);
            db.setPassword(dbpassword);
            try {
                con = db.getConnection();
            } catch (SQLException e) {
                System.out.println(e.getMessage().toString());
            }
        }else{
            System.out.println("Mysql Not Null");
        }
        return con;
    }
}
