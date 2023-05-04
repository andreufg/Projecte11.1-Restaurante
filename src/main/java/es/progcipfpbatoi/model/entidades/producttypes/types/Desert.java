package es.progcipfpbatoi.model.entidades.producttypes.types;

import es.progcipfpbatoi.model.entidades.producttypes.Product;

import java.util.HashSet;
import java.util.List;

public class Desert extends Product {

   private HashSet<Characteristic> characteristic;

    public Desert(String cod, String name, Characteristic... characteristic) {
        super(cod, name, "p");
        this.characteristic = new HashSet<>(List.of(characteristic));
    }

    @Override
    public String getExtras() {
        return String.format("%s", characteristic);
    }

}
