package es.progcipfpbatoi.model.entidades.producttypes;

import javafx.scene.image.Image;

public abstract class Product {

    private String cod;

    private String name;

    private float prize;

    private float discount;

    private float vat;

    private Image imagen;
    private String prefixCode;

    public Product(Image imagen, String cod, String name, float prize, float disccount, float vat, String prefixCode) {
        this.cod = prefixCode + cod;
        this.imagen = imagen;
        this.name = name;
        this.prize = prize;
        this.discount = disccount;
        this.vat = vat;
        this.prefixCode = prefixCode;
    }
    public Product(Image imagen, String name, float prize, float disccount, float vat) {
        this.name = name;
        this.prize = prize;
        this.imagen = imagen;
        this.discount = disccount;
        this.vat = vat;
    }

    public Product(Image imagen, String name, String prefixCode) {
        this(imagen, name, 1.25f, 0f, 0.1f);
    }

        public boolean empiezaPor(String text) {
        return this.cod.startsWith(text);
    }

    public String getCod() {
        return this.cod;
    }

    public float getPrize() {
        return prize * (1 + vat) - (prize * discount);
    }

    public float getPrizeWithoutDiscount() {
        return prize * (1 + vat);
    }


    public String getName() {
        return name;
    }

    public void setDiscount(float disccount) {
        this.discount = disccount;
    }

    public float getPercentageDiscount() {
        return discount * 100;
    }

    public boolean containsThisCode(String cod) {
        return this.cod.equals(cod);
    }

    @Override
    public String toString() {
        return cod + " " + name + "  Precio: " + getPrize() + "€";
    }

    public String getExtras() {
        return "";
    }
}
