package dbconnection;

import java.sql.*;

public class dbconnections {

    public static Connection getconnection(){
        Connection con=null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("Driver is registered");

            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/market", "root","");

            System.out.println("created");

            if(con.isClosed())
            {

                System.out.println("connection closed ");
            }
            else
            {
                System.out.println("Connection created ");

            }

        }catch(Exception e){

            System.out.println(e.getMessage());
        }
        return con;

    }
    public static void main(String arg[])
    {

        dbconnections c=new dbconnections();
        c.getconnection();


    }}

