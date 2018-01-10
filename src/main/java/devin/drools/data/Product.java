package devin.drools.data;

import java.math.BigDecimal;

/**
 * 商品
 * @author devin
 */
public class Product {
    /** 商品的 id */
    private Long id;

    /** 商品的编号 */
    private String number;

    /** 商品的名称 */
    private String name;

    /** 商品的价格 */
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
