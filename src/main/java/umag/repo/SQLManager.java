package umag.repo;

import org.intellij.lang.annotations.Language;
import umag.util.SafeFunction;

import java.sql.*;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;

public class SQLManager {
    @Language("postgresql")
    private static final String CREATE_TABLE_APTITUDES = """
        CREATE TABLE IF NOT EXISTS `aptitudes` (
            `id` INT NOT NULL AUTO_INCREMENT,
            `nombre` VARCHAR(45) NOT NULL,
            PRIMARY KEY (`id`)
        );
        """;
    @Language("postgresql")
    private static final String CREATE_TABLE_CUENTA = """
        CREATE TABLE IF NOT EXISTS `cuenta` (
            `id` INT NOT NULL AUTO_INCREMENT,
            `nombre` VARCHAR(45) NOT NULL,
            `correo` VARCHAR(45) NOT NULL,
            PRIMARY KEY (`id`)
        );
        """;
    @Language("postgresql")
    private static final String CREATE_TABLE_HOJADEVIDA = """
        CREATE TABLE IF NOT EXISTS `hoja de vida` (
            `id` INT NOT NULL AUTO_INCREMENT,
            `nombre` VARCHAR(45) NOT NULL,
            `apellido` VARCHAR(45) NOT NULL,
            `cedula` INT NOT NULL,
            `fecha de nacimiento` DATE NOT NULL,
            `direccion` VARCHAR(45) NOT NULL,
            `telefono` INT NOT NULL,
            `correo` VARCHAR(45) NOT NULL,
            `profesion` VARCHAR(45) NOT NULL,
            `aptitudes` VARCHAR(45) NOT NULL,                                    
            PRIMARY KEY (`id`)
        );
        """;
    @Language("postgresql")
    private static final String CREATE_TABLE_OFERTA = """
        CREATE TABLE IF NOT EXISTS `oferta` (
            `id` INT NOT NULL AUTO_INCREMENT,
            `nombre` VARCHAR(45) NOT NULL,
            `descripcion` VARCHAR(200) NOT NULL,
            `aptitudes` VARCHAR(45) NOT NULL,
            PRIMARY KEY (`id`)
        );
        """;

    private static final String GCP_URL = "35.199.121.103";
    private static final String GCP_USER = "proyectopoo";
    private static final String GCP_PASS = "alksjhdasdlkj";
    private static final Connection conn;

    static {
        String url = "jdbc:postgresql://%s:5432/postgres".formatted(GCP_URL);
        Properties props = new Properties();
        props.setProperty("user", GCP_USER);
        props.setProperty("password", GCP_PASS);
        props.setProperty("ssl", "false");
        try {
            conn = DriverManager.getConnection(url, props);

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT 1");
            rs.next();
            rs.close();

            createTables();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createTables() {
        try {
            Statement st = conn.createStatement();
            st.execute(CREATE_TABLE_APTITUDES);
//            st.execute(CREATE_TABLE_CUENTA);
//            st.execute(CREATE_TABLE_);
//            st.execute(CREATE_TABLE_A);
            st.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static CompletableFuture<ResultSet> executeQuery(@Language("postgresql") String query,  Object... params) {
        return executeOperation(query, params, PreparedStatement::executeQuery);
    }

    public static CompletableFuture<Integer> executeUpdate(@Language("postgresql") String query,  Object... params) {
        return executeOperation(query, params, PreparedStatement::executeUpdate);
    }

    public static PreparedStatement createStatement(@Language("postgresql") String query,  Object... params) throws SQLException {
        PreparedStatement st = conn.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            st.setObject(i + 1, params[i]);
        }
        return st;
    }

    private static synchronized <T> CompletableFuture<T> executeOperation(@Language("postgresql") String query, Object[] params, SafeFunction<PreparedStatement, T> supplier) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                PreparedStatement ps = createStatement(query, params);
                T t = supplier.applySafe(ps);
                ps.close();
                return t;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
