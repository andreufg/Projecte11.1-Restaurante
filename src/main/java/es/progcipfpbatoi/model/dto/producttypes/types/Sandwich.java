package es.progcipfpbatoi.model.dto.producttypes.types;

import es.progcipfpbatoi.model.dto.producttypes.Product;

public class Sandwich extends Product {

    public Sandwich(String cod, String name) {
        super(cod, name, "m");
    }
    public Sandwich(String cod, String name, float prize, float disccount, float vat) {
        super(cod, name, prize,disccount,vat,"m");
    }

}
