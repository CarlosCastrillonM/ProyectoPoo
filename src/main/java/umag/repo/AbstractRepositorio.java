package umag.repo;

import umag.model.Cargable;
import umag.model.Repositorio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractRepositorio<T extends Cargable> implements Repositorio<T> {

    protected List<T> list = new LinkedList<>();

    public AbstractRepositorio() {
        loadAll();
    }

    @Override
    public List<T> getAll() {
        return list;
    }

    public abstract Class<T> getEntityClass();

    @Override
    public void loadRow(ResultSet rs) throws SQLException {
        T t = createInstance();
        t.loadRow(rs);
        list.add(t);
    }

    public T createInstance() {
        try {
            return getEntityClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void loadAll() {
        SQLManager.executeQuery("SELECT * FROM " + getTableName()).thenAccept(rs -> {
            try {
                while (rs.next()) {
                    loadRow(rs);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }
}
