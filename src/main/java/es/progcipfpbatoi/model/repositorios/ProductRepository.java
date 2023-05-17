package es.progcipfpbatoi.model.repositorios;

import es.progcipfpbatoi.exceptions.DatabaseErrorException;
import es.progcipfpbatoi.exceptions.NotFoundException;
import es.progcipfpbatoi.model.dao.ProductosDAO;
import es.progcipfpbatoi.model.dto.producttypes.Product;
import es.progcipfpbatoi.model.dto.producttypes.types.*;

import java.util.ArrayList;
import java.util.Objects;

public class ProductRepository{
    private ArrayList<Product> productList;
    private ArrayList<Product> productsCreadosLista;
    private ProductosDAO productosDAO;

    public ProductRepository(ProductosDAO productosDAO) {
        this.productosDAO = productosDAO;
        this.productList = productosDAO.findAll();
        this.productsCreadosLista = productosDAO.findAllCreadas();
    }

    public ArrayList<Product> getProductsCreadosLista() {
        return productsCreadosLista;
    }
    public ArrayList<Product> findAll() {
        return productosDAO.findAll();
    }

    public ArrayList<Product> findAll(String text) {
        return productosDAO.findAll(text);
    }

    public Product findProduct(String text) {
        for (Product producto:productosDAO.findAll()) {
            if (producto.toString().equals(text)){
                return producto;
            }
        }
        return null;
    }
    public void eliminarProducto(Product product){
        productList.remove(product);
        productsCreadosLista.remove(product);
    }

    public Product getById(int id) throws DatabaseErrorException, NotFoundException {
        return productosDAO.getById(id);
    }
    public String codigo(){
        return productosDAO.findAll().size() + "";
    }

    public boolean save(Product product) throws DatabaseErrorException {
        productList.add(product);
        productsCreadosLista.add(product);
        return productosDAO.save(product);
    }
    public void update(Product product) throws DatabaseErrorException {
        for (Product product1 : productsCreadosLista) {
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
