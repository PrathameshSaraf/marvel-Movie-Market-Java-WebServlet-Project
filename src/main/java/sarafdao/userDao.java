package sarafdao;
import saraftutorial.user;

import java.sql.*;
public class userDao {
    private Connection con;
    private String query;
    private PreparedStatement pst;
    private ResultSet rs;

    public userDao(Connection con){
        this.con=con;
    }

    public user userLogin(String email,String password){
        user user=null;
        try{
            query="SELECT * FROM `users` WHERE `email`=? and `password`=?";
            pst=this.con.prepareStatement(query);
            pst.setString(1,email);
            pst.setString(2,password);
            rs= pst.executeQuery();

            if(rs.next()){
                user=new user();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
            }

        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return user;
    }
}
