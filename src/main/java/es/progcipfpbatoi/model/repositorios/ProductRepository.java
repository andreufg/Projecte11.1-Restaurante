package es.progcipfpbatoi.model.repositorios;

import es.progcipfpbatoi.exceptions.DatabaseErrorException;
import es.progcipfpbatoi.exceptions.NotFoundException;
import es.progcipfpbatoi.model.dao.ProductosDAO;
import es.progcipfpbatoi.model.dto.producttypes.Product;
import es.progcipfpbatoi.model.dto.producttypes.types.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class ProductRepository{
    private ProductosDAO productosDAO;

    public ProductRepository(ProductosDAO productosDAO) {
        this.productosDAO = productosDAO;
    }

    public ArrayList<Product> getProductsCreadosLista() {
        return productosDAO.findAllCreadas();
    }
    public ArrayList<Product> findAll() {
        return productosDAO.findAll();
    }

    public Product findAll(int cod) throws SQLException {
        return productosDAO.findAll(cod);
    }

    public Product findProduct(String text) {
        for (Product producto:productosDAO.findAll()) {
            if (producto.toString().equals(text)){
                return producto;
            }
        }
        return null;
    }
    public void eliminarProducto(Product product) throws DatabaseErrorException {
        productosDAO.eliminar(product);
    }

    public Product getById(int cod) throws DatabaseErrorException, NotFoundException {
        return productosDAO.getById(cod);
    }
    public int codigo(){
        return productosDAO.findAll().size();
    }

    public boolean save(Product product) throws DatabaseErrorException, NotFoundException, SQLException {
        return productosDAO.save(product);
    }
    public void update(Product product) throws DatabaseErrorException {
        for (Product product1 : getProductsCreadosLista()) {
            if (Objects.equals(product.getCod(), product1.getCod())) {
                product1.setName(product.getName());
                product1.setVat(product.devolvertVat());
                product1.setDiscount(product.devolverDiscount());
                product1.setPrize(product.devolverPrecio());
            }
        }
        productosDAO.update(product);
    }




}
