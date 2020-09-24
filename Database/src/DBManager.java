import java.sql.*;


public class DBManager {
    
    public static void main(String[] args) {
        
        try{
            
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            
            Connection conn=DriverManager.getConnection("jdbc:ucanaccess://D:/DS Project/Database1.accdb");
            
            Statement st=conn.createStatement();
            
            String sql="Select * from Resources";
            
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                System.out.println("\n"+rs.getString(1)+"\t"+rs.getString(2));
                
            }
            conn.close();
            
        } catch(Exception e){
            
            System.out.println(e.getMessage());
        }
    }
    
}