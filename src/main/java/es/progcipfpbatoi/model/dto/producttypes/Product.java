package es.progcipfpbatoi.model.dto.producttypes;

import java.text.DecimalFormat;

public abstract class Product {

    private String cod;

    private String name;

    private float prize;

    private float discount;

    private float vat;

    private String prefixCode;
    private boolean creadoPorUsuario;
    private boolean dadoDeBaja;

    public Product(String cod, String name, float prize, float disccount, float vat, String prefixCode) {
        this.cod = cod;
        this.name = name;
        this.prize = prize;
        this.discount = disccount;
        this.vat = vat;
        this.prefixCode = prefixCode;
        this.creadoPorUsuario = false;
        this.dadoDeBaja = false;
    }

    public boolean isDadoDeBaja() {
        return dadoDeBaja;
    }

    public void setDadoDeBaja(boolean dadoDeBaja) {
        this.dadoDeBaja = dadoDeBaja;
    }

    public void setCreadoPorUsuario(boolean creadoPorUsuario) {
        this.creadoPorUsuario = creadoPorUsuario;
    }

    public boolean isCreadoPorUsuario() {
        return creadoPorUsuario;
    }

    public String getPrefixCode() {
        return prefixCode;
    }

    public Product(String cod, String name, String prefixCode) {
        this(cod, name, 1.25f, 0f, 0.1f, prefixCode);
    }

    public boolean empiezaPor(String text) {
        return this.cod.startsWith(text);
    }

    public String getCod() {
        return this.cod;
    }
    public String getPrefixCod() {
        return prefixCode+this.cod;
    }

    public String getPrecio(){
        DecimalFormat formato1 = new DecimalFormat("#.00");
        return formato1.format(prize);
    }
    public float devolverPrecio(){
        return prize;
    }
    public float getPrize() {
        return prize * (1 + (vat/100)) - (prize * discount/100);
    }
    public String getPrizeString() {
        DecimalFormat formato1 = new DecimalFormat("#.00");
        return formato1.format(getPrize());
    }

    public float getPrizeWithoutDiscount() {
        return prize * (1 + vat);
    }

    public String getDiscount() {
        DecimalFormat formato1 = new DecimalFormat("#");
        return formato1.format(discount);
    }
    public float devolverDiscount() {
        return discount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrize(float prize) {
        this.prize = prize;
    }


    public void setVat(float vat) {
        this.vat = vat;
    }

    public String getVat() {
        DecimalFormat formato1 = new DecimalFormat("#");
        return formato1.format(vat);
    }
    public float devolvertVat() {
        return vat;
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

        return getPrefixCod() + " " + name + "  Precio: " + getPrizeString() + "€";
    }

    public String getExtras() {
        return "";
    }
}
