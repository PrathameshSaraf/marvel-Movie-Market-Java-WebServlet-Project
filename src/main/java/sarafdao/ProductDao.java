package sarafdao;

import saraftutorial.Product;
import saraftutorial.cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private Connection con;
    private String query;
    private PreparedStatement pst;
    private ResultSet rs;

    public ProductDao(Connection con) {
        this.con = con;
    }

    public List<Product> getAllProducts(){
        List<Product> products=new ArrayList<Product>();
        try{
            query="SELECT * FROM `products`";
            pst=this.con.prepareStatement(query);
           rs=pst.executeQuery();

           while(rs.next()){
               Product row=new Product();
               row.setId(rs.getInt("id"));
               row.setMovieName(rs.getString("movieName"));
               row.setReleaseDate(rs.getString("releaseDate"));
               row.setPrice(rs.getDouble("price"));
               row.setImage(rs.getString("image"));

               products.add(row);
           }
        }catch (Exception ex){
                ex.printStackTrace();
        }
        return products;
    }
    public List<cart> getCartProducts(ArrayList<cart> cartList){
     List<cart> products=new ArrayList<cart>();
     try{
         if(cartList.size()>0){
             for(cart item:cartList){
                 query="SELECT * FROM `products` WHERE`id`=?";
                 pst=this.con.prepareStatement(query);
                 pst.setInt(1,item.getId());
                 rs=pst.executeQuery();
                 while(rs.next()){
                     cart row=new cart();
                     row.setId(rs.getInt("id"));
                     row.setMovieName(rs.getString("movieName"));
                     row.setReleaseDate(rs.getString("releaseDate"));
                     row.setPrice(rs.getDouble("price")*item.getResolution()/120);
                     row.setResolution(item.getResolution());
                     products.add(row);
                 }
             }
         }
     }catch (Exception e)
     {
         System.out.println(e.getMessage());
         e.printStackTrace();
     }
     return products;
    }
   public  Product getSingleProduct(int id){
        Product row=null;

        try{
            query="select * from products where id=?";
            pst=this.con.prepareStatement(query);
            pst.setInt(1,id);
            rs=pst.executeQuery();

            while(rs.next()){
                row=new Product();
                row.setId(rs.getInt("id"));
                row.setMovieName(rs.getString("movieName"));
                row.setReleaseDate(rs.getString("releaseDate"));
                row.setPrice(rs.getDouble("price"));
                row.setImage(rs.getString("image"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return row;
   }

    public double getTotalCartPrice(ArrayList<cart> cartList){
        double sum=0;
        try{
            if(cartList.size()>0){
                for(cart item:cartList){
                    query="SELECT * FROM `products` WHERE`id`=?";
                    pst=this.con.prepareStatement(query);
                    pst.setInt(1,item.getId());
                    rs= pst.executeQuery();

                    while (rs.next()){
                        sum+=rs.getDouble("price")* item.getResolution()/120;
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return sum;

    }
}
