package com.milo.recipes.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
public class IngredientCommand {

    private Long id;
    private String description;
    private BigDecimal amount;
//    private Recipe recipe;
    private UnitOfMeasureCommand unitOfMeasure;

}
