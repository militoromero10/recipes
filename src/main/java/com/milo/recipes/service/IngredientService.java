package com.milo.recipes.service;

import com.milo.recipes.command.IngredientCommand;
import com.milo.recipes.model.Ingredient;

public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
