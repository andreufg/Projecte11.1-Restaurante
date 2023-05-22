package es.progcipfpbatoi.model.dto.producttypes.types;

import es.progcipfpbatoi.model.dto.producttypes.Product;

public class Drink extends Product {

    private boolean refillable;

    private Size size;

    public Drink(int cod, String name, float prize, float disccount, float vat) {
        super(cod, name, prize, disccount, vat, "b");
        this.refillable = false;
        this.size = Size.NORMAL;
    }

    public Drink(int cod, String name) {
        this(cod, name, 1.0f, 0f, 0.1f);
    }

    public Size getSize() {
        return size;
    }
    public String getTamanyo(){
        if (size == Size.NORMAL){
            return "MEDIANO";
        } else if (size == Size.SMALL) {
            return "PEQUEÑO";
        }
        return "GRANDE";
    }

    public boolean isRefillable() {
        return refillable;
    }
    public int esRellenable(){
        if (refillable){
            return 1;
        }
        return 0;
    }


    @Override
    public String getExtras() {
        return String.format("Rellenable: %b, Tamaño: %s", (refillable)? "Sí": "No", size);
    }
}
