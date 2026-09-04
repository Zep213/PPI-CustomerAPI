package br.edu.ppi.seller.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Product extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotBlank
    private String description;

    @NotNull
    @Positive
    private BigDecimal price;

    @Column(nullable = false, name = "seller_id")
    private Long sellerId;

    private Boolean stock;

    @Override
    public void prePersist() {
        this.stock = true;
        super.prePersist();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(description, product.description) && Objects.equals(sellerId, product.sellerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, sellerId);
    }
}
