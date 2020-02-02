package com.milo.recipes.service.impl;

import com.milo.recipes.converter.RecipeCommandToRecipe;
import com.milo.recipes.converter.RecipeToRecipeCommand;
import com.milo.recipes.model.Recipe;
import com.milo.recipes.repository.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Prueba unitaria
 */
public class RecipeServiceImplTest {

    private RecipeServiceImpl recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    private RecipeCommandToRecipe recipeCommandToRecipe;

    @Before
    public void setUp() throws Exception {
        // inicializar los Mocks
        // Esto inicializara las dependencias que necesitemos en este caso el Repository
        MockitoAnnotations.initMocks(this);

        // inicializar nuetro RecipeService
        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void getRecipes() {
        //given, lo que queremos retornar cuando llamemos al repository
        Recipe recipe = new Recipe();
        HashSet<Recipe> recipesData = new HashSet();
        recipesData.add(recipe);

        //when, cuando llamemos findAll, retornamos el recipesData
        when(recipeRepository.findAll()).thenReturn(recipesData);

        // el getRecipes esta llamando al findAll, por lo tanto recipes = recipesData
        Set<Recipe> recipes = recipeService.getRecipes();

        // con el llamado anterior, nuestro objeto debe tener un elemento no mas, pues asi lo configuramos en el GIVEN
        assertEquals(recipes.size(),1);

        //verificamos la interaccion, queremos verificar que el findAll sea llamado una sola vez desde el repository
        verify(recipeRepository, times(1)).findAll();
    }

}