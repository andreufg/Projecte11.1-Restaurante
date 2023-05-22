package es.progcipfpbatoi.model.dao;

import es.progcipfpbatoi.exceptions.DatabaseErrorException;
import es.progcipfpbatoi.exceptions.NotFoundException;
import es.progcipfpbatoi.model.dto.producttypes.Product;
import es.progcipfpbatoi.model.dto.producttypes.types.*;
import es.progcipfpbatoi.services.MySqlConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

import static es.progcipfpbatoi.model.dto.producttypes.types.Characteristic.CELIAC_SUITABLE;


public class SQLFileProductsDAO implements ProductosDAO {
   private Connection connection;
    private static final String TABLE_NAME = "productos";

    @Override
    public ArrayList<Product> findAll() {
        String sql = String.format("SELECT * FROM %s", TABLE_NAME);
        ArrayList<Product> productos = new ArrayList<>();

        try {
            connection = new MySqlConnection().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Product product = getProductFromResultset(resultSet);
                productos.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }

    @Override
    public ArrayList<Product> findAllCreadas() {
        String sql = String.format("SELECT * FROM %s", TABLE_NAME);
        ArrayList<Product> tareas = new ArrayList<>();

        try {
            connection = new MySqlConnection().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                if (resultSet.getTimestamp(7) == null) {
                    Product product = getProductFromResultset(resultSet);
                    tareas.add(product);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tareas;
    }

    @Override
    public Product findAll(int cod) throws SQLException {
        String sql = String.format("SELECT * FROM %s WHERE id = ?", TABLE_NAME);
            connection = new MySqlConnection().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, cod);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product product = getProductFromResultset(resultSet);
                if (Objects.equals(product.getCod(), cod)) {
                    return product;
                }
            }


        return null;
    }

    @Override
    public Product getById(int cod) throws NotFoundException, DatabaseErrorException {
        String sql = String.format("SELECT * FROM %s WHERE id = ?", TABLE_NAME);

        try {
            connection = new MySqlConnection().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, cod);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product product = getProductFromResultset(resultSet);
                if (Objects.equals(product.getCod(), cod)) {
                    return product;
                }
            }

            throw new NotFoundException("No existe un producto con el código " + cod);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseErrorException("Ha ocurrido un error en el acceso o conexión a la base de datos (select)");
        }
    }

    @Override
    public boolean save(Product product) throws DatabaseErrorException, NotFoundException, SQLException {
        if (findAll(product.getCod()) == null) {
            return insert(product);
        } else {
            return update(product);
        }
    }
    private boolean insert(Product product) throws DatabaseErrorException {
        String sql = String.format("INSERT INTO %s (id, nombre, descripcion, precio_base, descuento, iva, creado_en, id_categoria, tamanyo, rellenable, raciones, caracteristica, estado) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)", TABLE_NAME);

        connection = new MySqlConnection().getConnection();

        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setInt(1, product.getCod());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setNull(3, Types.VARCHAR);
            preparedStatement.setFloat(4, product.getPrize());
            preparedStatement.setFloat(5, product.devolverDiscount());
            preparedStatement.setFloat(6, product.getIVA());
            preparedStatement.setNull(7, Types.TIMESTAMP);
            preparedStatement.setInt(8,idCategoria(product));
            if (idCategoria(product) == 1) {
                preparedStatement.setString(9, ((Drink) product).getTamanyo());
                preparedStatement.setInt(10, ((Drink) product).esRellenable());
                preparedStatement.setNull(11, Types.INTEGER);  // raciones
                preparedStatement.setNull(12, Types.VARCHAR);  // caracteristica
                preparedStatement.setString(13, "ALTA"); // estado
            } else if (idCategoria(product) == 2) {
                preparedStatement.setNull(9, Types.VARCHAR);  // tamanyo
                preparedStatement.setNull(10, Types.INTEGER);  // rellenable
                preparedStatement.setInt(11, ((Starter) product).getRation());
                preparedStatement.setNull(12, Types.VARCHAR);  // caracteristica
                preparedStatement.setString(13, "ALTA"); // estado
            } else if (idCategoria(product) == 4) {
                preparedStatement.setNull(9, Types.VARCHAR);  // tamanyo
                preparedStatement.setNull(10, Types.INTEGER);  // rellenable
                preparedStatement.setNull(11, Types.INTEGER);  // raciones
                if (((Desert)product).getExtras().equals("[CELIAC_SUITABLE]")){
                    preparedStatement.setString(12, "CELIACOS");
                }else if (((Desert)product).getExtras().equals("[DIABETIC_SUITABLE]")){
                    preparedStatement.setString(12, "DIABETICOS");
                }else {
                    preparedStatement.setNull(12, Types.VARCHAR);
                }
                preparedStatement.setString(13, "ALTA"); // estado
            } else {
                preparedStatement.setNull(9, Types.VARCHAR);  // tamanyo
                preparedStatement.setNull(10, Types.INTEGER);  // rellenable
                preparedStatement.setNull(11, Types.INTEGER);  // raciones
                preparedStatement.setNull(12, Types.VARCHAR);  // caracteristica
                preparedStatement.setString(13, "ALTA"); // estado
            }
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                product.setCod(resultSet.getInt(1));
            }
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseErrorException("Ha ocurrido un error en el acceso o conexión a la base de datos (insert)");
        }
    }

    @Override
    public boolean update(Product product) throws DatabaseErrorException {
        String sql = String.format("UPDATE %s SET nombre = ?, descripcion = ?, precio_base = ?, " +
                        "descuento = ?, iva = ?, creado_en = ?, id_categoria = ?, tamanyo = ?, rellenable = ?" +
                        ", raciones = ?, caracteristica = ?, estado = ? WHERE id = ?",
                TABLE_NAME);

        try (
                Connection connection =  new MySqlConnection().getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            statement.setInt(1, product.getCod());
            statement.setString(2, product.getName());
            statement.setNull(3, Types.VARCHAR);
            statement.setFloat(4, product.getPrize());
            statement.setFloat(5, product.devolverDiscount());
            statement.setFloat(6, product.getIVA());
            statement.setNull(7, Types.TIMESTAMP);
            statement.setInt(8,idCategoria(product));
            if (idCategoria(product) == 1) {
                statement.setString(9, ((Drink) product).getTamanyo());
                statement.setInt(10, ((Drink) product).esRellenable());
                statement.setNull(11, Types.INTEGER);  // raciones
                statement.setNull(12, Types.VARCHAR);  // caracteristica
                statement.setString(13, "ALTA"); // estado
            } else if (idCategoria(product) == 2) {
                statement.setNull(9, Types.VARCHAR);  // tamanyo
                statement.setNull(10, Types.INTEGER);  // rellenable
                statement.setInt(11, ((Starter) product).getRation());
                statement.setNull(12, Types.VARCHAR);  // caracteristica
                statement.setString(13, "ALTA");  // estado
            } else if (idCategoria(product) == 4) {
                statement.setNull(9, Types.VARCHAR);  // tamanyo
                statement.setNull(10, Types.INTEGER);  // rellenable
                statement.setNull(11, Types.INTEGER);  // raciones
                if (((Desert)product).getExtras().equals("CELIAC_SUITABLE")){
                    statement.setString(12, "CELIACOS");
                }else if (((Desert)product).getExtras().equals("DIABETIC_SUITABLE")){
                    statement.setString(12, "DIABETICOS");
                }

                statement.setString(13, "ALTA");  // estado
            } else {
                statement.setNull(9, Types.VARCHAR);  // tamanyo
                statement.setNull(10, Types.INTEGER);  // rellenable
                statement.setNull(11, Types.INTEGER);  // raciones
                statement.setNull(12, Types.VARCHAR);  // caracteristica
                statement.setString(13, "ALTA");  // estado
            }
            statement.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseErrorException("Ha ocurrido un error en el acceso o conexión a la base de datos (update)");
        }
    }
    private int idCategoria(Product product){
        if (product instanceof Drink){
            return 1;
        }else if (product instanceof Starter){
            return 2;
        } else if (product instanceof Sandwich) {
            return 3;
        }
        return 4;
    }

    @Override
    public void eliminar(Product product) throws DatabaseErrorException {
        String sql = String.format("DELETE FROM %s WHERE id = ?", TABLE_NAME);
        connection =  new MySqlConnection().getConnection();
        try (
                PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            statement.setInt(1, product.getCod());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseErrorException("Ha ocurrido un error en el acceso o conexión a la base de datos (delete)");
        }
    }
    private Product getProductFromResultset(ResultSet rs) throws SQLException {
        int cod = rs.getInt("id");
        String nombre = rs.getString("nombre");
        String descripcion = rs.getString("descripcion");
        float precio = rs.getFloat("precio_base");
        float descuento = rs.getFloat("descuento");
        float iva = rs.getFloat("iva");
        int idCategoria = rs.getInt("id_categoria");
        if (idCategoria == 1){
            return new Drink(cod, nombre, precio, descuento, iva);
        }else if (idCategoria == 2){
            return new Starter(cod, nombre, precio, descuento, iva);
        }else if (idCategoria == 3){
            return new Sandwich(cod, nombre, precio, descuento, iva);
        }else {
            String caracteristicas = rs.getString("caracteristica");
            if (Objects.equals(caracteristicas, "CELIACOS")){
                return new Desert(cod, nombre, precio, descuento, iva, CELIAC_SUITABLE);
            }else if (Objects.equals(caracteristicas, "DIABETICOS")){
                return new Desert(cod, nombre, precio, descuento, iva, Characteristic.DIABETIC_SUITABLE);
            }
            return new Desert(cod, nombre, precio, descuento, iva);

        }


    }
}
