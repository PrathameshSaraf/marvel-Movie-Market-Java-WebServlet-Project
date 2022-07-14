package sarafdao;

import dbconnection.dbconnections;
import saraftutorial.*;


import java.sql.*;

import java.util.*;

public class orderDao {

    private Connection con;
    private String query;
    private PreparedStatement pst;
    private ResultSet rs;

    public orderDao(Connection con){
        this.con =con;
    }

    public boolean insertOrder(Order model){
        boolean result=false;
        int pv=720;




        try{

            query="INSERT INTO `orders`(`product_id`, `user_id`, `resolution`, `order_date`) VALUES (?,?,?,?)";

            pst=this.con.prepareStatement(query);
            pst.setInt(1,model.getId());
            pst.setInt(2,model.getuId());
            pst.setInt(3,model.getResolution());
            pst.setString(4, model.getDate());
            pst.executeUpdate();
            result=true;

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public List<Order> userOrders(int id){
        List<Order>list =new ArrayList<>();
        try{
          query="select * from orders where user_id=? order by orders.order_id desc";
          pst=this.con.prepareStatement(query);
          pst.setInt(1,id);
          rs=pst.executeQuery();

          while(rs.next()){
              Order od=new Order();
              ProductDao pd=new ProductDao(this.con);
              int pId=rs.getInt("product_id");

              Product product=pd.getSingleProduct(pId);
              od.setOrderId(rs.getInt("order_id"));
              od.setId(pId);
              od.setMovieName(product.getMovieName());
              od.setReleaseDate(product.getReleaseDate());
              od.setPrice(product.getPrice()*rs.getInt("resolution")/120);
              od.setResolution(rs.getInt("resolution"));
              od.setDate(rs.getString("order_Date"));
              list.add(od);
          }

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;

    }
    public void cancelOrder(int id){
            try{
                query="delete from orders where order_id=?";
                pst=this.con.prepareStatement(query);
                pst.setInt(1,id);
                pst.execute();
            }catch (Exception e){
                e.printStackTrace();
            }
    }
}
