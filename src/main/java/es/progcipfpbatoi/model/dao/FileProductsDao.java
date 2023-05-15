package es.progcipfpbatoi.model.dao;

import es.progcipfpbatoi.exceptions.DatabaseErrorException;
import es.progcipfpbatoi.exceptions.NotFoundException;
import es.progcipfpbatoi.model.dto.producttypes.Product;
import es.progcipfpbatoi.model.dto.producttypes.types.Desert;
import es.progcipfpbatoi.model.dto.producttypes.types.Drink;
import es.progcipfpbatoi.model.dto.producttypes.types.Sandwich;
import es.progcipfpbatoi.model.dto.producttypes.types.Starter;

import java.io.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

public class FileProductsDao implements ProductosDAO {
    private static final String DATABASE_FILE = "resources/database/tareas.txt";

    private static final int COD = 0;

    private static final int NAME = 1;

    private static final int PRIZE = 2;

    private static final int DISCOUNT = 3;

    private static final int VAT = 4;
    private static final int TIPO = 5;

    private File file;

    public FileProductsDao() {
        this.file = new File(DATABASE_FILE);
    }

    @Override
    public ArrayList<Product> findAll() {
        ArrayList<Product> listaProductos = new ArrayList<>();
        try (FileReader fileReader = new FileReader(this.file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            do {
                String register = bufferedReader.readLine();
                if (register == null) {
                    break;
                } else if (!register.isBlank()) {
                    String[] fields = register.split(";");
                    String cod = fields[COD];
                    String name = fields[NAME];
                    float prize = Float.parseFloat(fields[PRIZE]);
                    float discount = Float.parseFloat(fields[DISCOUNT]);
                    float vat = Float.parseFloat(fields[VAT]);
                    String tipo = fields[TIPO];
                    if (Objects.equals(tipo, "Desert")) {
                        Desert desert = new Desert(cod, name, prize, discount, vat);
                        listaProductos.add(desert);
                    } else if (Objects.equals(tipo, "Drink")) {
                        Drink drink = new Drink(cod, name, prize, discount, vat);
                        listaProductos.add(drink);
                    } else if (Objects.equals(tipo, "Sandwich")) {
                        Sandwich sandwich = new Sandwich(cod, name, prize, discount, vat);
                        listaProductos.add(sandwich);
                    }else {
                        Starter starter = new Starter(cod, name, prize, discount, vat);
                        listaProductos.add(starter);
                    }
                }
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaProductos;
    }

    @Override
    public ArrayList<Product> findAll(String text) {
        ArrayList<Product> listaProductos = new ArrayList<>();
        try (FileReader fileReader = new FileReader(this.file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            do {
                String register = bufferedReader.readLine();
                if (register == null) {
                    break;
                } else if (!register.isBlank()) {
                    String[] fields = register.split(";");
                    String cod = fields[COD];
                    String name = fields[NAME];
                    float prize = Float.parseFloat(fields[PRIZE]);
                    float discount = Float.parseFloat(fields[DISCOUNT]);
                    float vat = Float.parseFloat(fields[VAT]);
                    String tipo = fields[TIPO];
                    Product product;
                    if (Objects.equals(tipo, "Postre")) {
                        product = new Desert(cod, name, prize, discount, vat);
                    } else if (Objects.equals(tipo, "Bebida")) {
                        product = new Drink(cod, name, prize, discount, vat);
                    } else if (Objects.equals(tipo, "Montadito")) {
                        product = new Sandwich(cod, name, prize, discount, vat);
                    }else {
                        product = new Starter(cod, name, prize, discount, vat);
                    }
                    if (product.empiezaPor(text)) {
                        listaProductos.add(product);
                    }
                }
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaProductos;
    }

    @Override
    public Product getById(int id) throws NotFoundException, DatabaseErrorException {
        return null;
    }

    @Override
    public boolean save(Product product) throws DatabaseErrorException {
        for (Product product1:findAll()) {
            if (Objects.equals(product.getCod(), product1.getCod())){
                return false;
            }

        }
        ArrayList<Product> listaProductos = new ArrayList<>();
        try (FileReader fileReader = new FileReader(this.file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            FileWriter fileWriter = new FileWriter(this.file,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            do {
                String register = bufferedReader.readLine();
                if (register == null) {
                    String tareaNueva = product.getCod() + ";" + product.getName() + ";" + product.getPrecio() + ";"
                            + product.getDiscount() + ";" + product.getVat() + ";" + tipoObjeto(product);
                    bufferedWriter.newLine();
                    bufferedWriter.write(tareaNueva);
                    bufferedWriter.close();
                    break;
                }
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private String tipoObjeto(Product product){
        if (product instanceof Drink) {
            return "Bebida";
        } else if (product instanceof Desert) {
            return "Postre";
        } else if (product instanceof Sandwich) {
            return "Montadito";
        } else {
            return "Entrante";
        }
    }
}
