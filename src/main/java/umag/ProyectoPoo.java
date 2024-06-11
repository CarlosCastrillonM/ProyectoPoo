/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package umag;

import java.sql.*;
import java.util.Properties;
import umag.gui.FormMisOfertas;

/**
 *
 * @author carla
 */
public class ProyectoPoo {

    private static final String GCP_URL = "35.199.121.103";
    private static final String GCP_USER = "proyectopoo";
    private static final String GCP_PASS = "alksjhdasdlkj";

    public static void main(String[] args) throws SQLException {
        FormMisOfertas.main(args);
        if (true) {
            return;
        }
        
        String url = "jdbc:postgresql://%s:5432/postgres".formatted(GCP_URL);
        Properties props = new Properties();
        props.setProperty("user", GCP_USER);
        props.setProperty("password", GCP_PASS);
        props.setProperty("ssl", "false");
        Connection conn = DriverManager.getConnection(url, props);

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT 1");
        rs.next();
        rs.close();
//
//        while (rs.next()) {
//            System.out.print("Column 1 returned ");
//            System.out.println(rs.getString(3));
//        }
//        rs.close();
//        st.close();
        conn.close();
    }

}
