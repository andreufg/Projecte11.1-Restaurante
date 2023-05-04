package es.progcipfpbatoi.model.repositorios;

import es.progcipfpbatoi.model.entidades.producttypes.Product;
import es.progcipfpbatoi.model.entidades.producttypes.types.*;

import java.util.ArrayList;

public class ProductRepository {
    private ArrayList<Product> productList;
    private int numericCode;

    public ProductRepository() {
        numericCode = 1;
        productList = new ArrayList<>();
        setDefaultSandwichList();
        setDefaultDrinkList();
        setDefaultStarterList();
        setDefaultDesertList();
    }

    public ArrayList<Product> findAll(Class<? extends Product> productClass) {
        ArrayList<Product> productsFiltered = new ArrayList<>();
        for (Product current: productList) {
            if (current.getClass() == productClass) {
                productsFiltered.add(current);
            }
        }

        return productsFiltered;
    }

    public Product findByCod(String cod) {
        for (Product product: productList) {
            if (product.containsThisCode(cod)){
                return product;
            }
        }
        return null;
    }

    private void setDefaultSandwichList() {

        productList.add(new Sandwich(String.valueOf(numericCode++), "lechuga, tomate y mayonesa"));
        productList.add(new Sandwich(String.valueOf(numericCode++), "HUEVO DURO lechuga, tomate y mayonesa"));
        productList.add(new Sandwich(String.valueOf(numericCode++), "VEGETAL CON QUESO lechuga, tomate y queso"));
        productList.add(new Sandwich(String.valueOf(numericCode++), "Burger, bacon ahumado, cebolla crujiente y alioli"));
        productList.add(new Sandwich(String.valueOf(numericCode++), "Pollo, bacon ahumado y salsa brava"));
        productList.add(new Sandwich(String.valueOf(numericCode++), "Pollo kebab, cebolla, pimiento verde y mayonesa"));
        productList.add(new Sandwich(String.valueOf(numericCode++), "CUATRO QUESOS: Queso ibérico, queso brie, queso de cabra y crema de queso"));
        productList.add(new Sandwich(String.valueOf(numericCode++), "CAPRESE: Jamón gran reserva, queso mozzarella, tomate y pesto"));
        productList.add(new Sandwich(String.valueOf(numericCode++), "Pulled pork y guacamole"));
        productList.add(new Sandwich(String.valueOf(numericCode++), "PULLED PORK y queso brie"));
        productList.add(new Sandwich(String.valueOf(numericCode++), "FILETE RUSO, cebolla caramelizada y salsa de queso cheddar"));
        productList.add(new Sandwich(String.valueOf(numericCode++), "SALMÓN AHUMADO y crema de queso"));
        productList.add(new Sandwich(String.valueOf(numericCode++), "CARNE MECHADA DESHILACHADA y cebolla crujiente"));
        productList.add(new Sandwich(String.valueOf(numericCode++), "JAMÓN GRAN RESERVA, tomate y aceite de oliva virgen extra"));
        productList.add(new Sandwich(String.valueOf(numericCode++), "CARRILLERA AL VINO TINTO y queso ibérico"));
        productList.add(new Sandwich(String.valueOf(numericCode++), "QUESO IBÉRICO, tortilla de patatas y mayonesa"));
        productList.add(new Sandwich(String.valueOf(numericCode++), "ALBÓNDIGAS y salsa BBQ"));
        productList.add(new Sandwich(String.valueOf(numericCode++), "Pollo, cebolla caramelizada y mayonesa trufada"));
        Product product = new Sandwich(String.valueOf(numericCode++), "CHISTORRA, bacon ahumado y salsa brava");
        product.setDiscount(0.2f);
        productList.add(product);
        productList.add(new Sandwich(String.valueOf(numericCode++), "Tortilla de patatas"));
    }

    private void setDefaultDrinkList() {
        productList.add(new Drink(String.valueOf(numericCode++), "Coca-Cola"));
        productList.add(new Drink(String.valueOf(numericCode++), "Agua"));
        productList.add(new Drink(String.valueOf(numericCode++), "Fanta Limón"));
        productList.add(new Drink(String.valueOf(numericCode++), "Fanta Naranja"));
        productList.add(new Drink(String.valueOf(numericCode++), "Cerveza bote 33cl"));
        productList.add(new Drink(String.valueOf(numericCode++), "Caña Cerveza"));
        productList.add(new Drink(String.valueOf(numericCode++), "Jarra Cerveza"));
    }

    private void setDefaultDesertList() {
        productList.add(new Desert(String.valueOf(numericCode++), "Pastel de Queso",
                Characteristic.CELIAC_SUITABLE));
        productList.add(new Desert(String.valueOf(numericCode++), "Pastel Chocolate"));
        productList.add(new Desert(String.valueOf(numericCode++), "Helado Chocolate",
                Characteristic.DIABETIC_SUITABLE));
        productList.add(new Desert(String.valueOf(numericCode++), "Helado Vainilla",
                Characteristic.CELIAC_SUITABLE));
        productList.add(new Desert(String.valueOf(numericCode++), "Helado Limón",
                Characteristic.CELIAC_SUITABLE, Characteristic.DIABETIC_SUITABLE));
        productList.add(new Desert(String.valueOf(numericCode++), "Helado Fresa",
                Characteristic.CELIAC_SUITABLE, Characteristic.DIABETIC_SUITABLE));
    }

    private void setDefaultStarterList() {
        productList.add(new Starter(String.valueOf(numericCode++), "Patatas 4 Quesos"));
        productList.add(new Starter(String.valueOf(numericCode++), "Bolas de pollo"));
        productList.add(new Starter(String.valueOf(numericCode++), "Aceitunas"));
        productList.add(new Starter(String.valueOf(numericCode++), "Nachos"));
        productList.add(new Starter(String.valueOf(numericCode++), "Ensalada de la casa"));
        productList.add(new Starter(String.valueOf(numericCode++), "Bolas de queso"));
        productList.add(new Starter(String.valueOf(numericCode++), "Alitas de pollo"));
        productList.add(new Starter(String.valueOf(numericCode++), "Patatas fritas"));
        productList.add(new Starter(String.valueOf(numericCode++), "Ensalada cesar"));
    }
}
