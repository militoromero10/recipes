package com.milo.recipes.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity(name = "ingredient")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private BigDecimal amount;

    // si agregamos cascade, eliminaremos recipe
    // si eliminamos ingredientes, no tenemos que eliminar recipe
    @ManyToOne
    private Recipe recipe;

    @OneToOne(fetch = FetchType.EAGER) // cada que pidamos la entidad, la consultamos
    private UnitOfMeasure uom;

    public Ingredient() {
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom, Recipe recipe) {
        this.description = description;
        this.amount = amount;
        this.recipe = recipe;
        this.uom = uom;
    }

}
