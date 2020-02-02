package com.milo.recipes.service;

import com.milo.recipes.command.RecipeCommand;
import com.milo.recipes.model.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
    Recipe getRecipeById(Long id);
    RecipeCommand saveRecipeCommand(RecipeCommand command);

}
