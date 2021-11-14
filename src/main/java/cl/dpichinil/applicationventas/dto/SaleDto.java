package cl.dpichinil.applicationventas.dto;

import java.util.List;

public class SaleDto {
    private int id;
    private String date;
    private int iva;
    private int discount;
    private int total;
    private List<SaleDetailDto> details;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIva() {
        return iva;
    }

    public void setIva(int iva) {
        this.iva = iva;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<SaleDetailDto> getDetails() {
        return details;
    }

    public void setDetails(List<SaleDetailDto> details) {
        this.details = details;
    }
}
