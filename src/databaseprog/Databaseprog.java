package databaseprog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Databaseprog {

    public static void main(String[] args) {

        Connection con = null;
        ResultSet rs = null;
        Statement stm = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@ 144.217.163.57:1521:XE", "hr", "inf5180");
            String sql = "select COUNTRY_NAME, COUNTRY_ID , REGION_NAME from COUNTRIES INNER JOIN REGIONS ON COUNTRIES.REGION_ID = REGIONS.REGION_ID";
            stm = con.createStatement();

            rs = stm.executeQuery(sql);

            String region_name, country_name, id;
         
            while (rs.next()) {

                id = rs.getString("COUNTRY_ID");
                country_name = rs.getString("COUNTRY_NAME");
                region_name = rs.getString("REGION_NAME");
                System.out.println(id + " - " + country_name + " - " +region_name);

            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Databaseprog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Databaseprog.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Databaseprog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Databaseprog.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Databaseprog.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }

        }
    }
}
