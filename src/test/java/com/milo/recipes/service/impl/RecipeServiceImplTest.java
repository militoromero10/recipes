package com.milo.recipes.service.impl;

import com.milo.recipes.command.RecipeCommand;
import com.milo.recipes.converter.RecipeCommandToRecipe;
import com.milo.recipes.converter.RecipeToRecipeCommand;
import com.milo.recipes.exceptions.NotFoundException;
import com.milo.recipes.model.Recipe;
import com.milo.recipes.repository.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Prueba unitaria
 */
public class RecipeServiceImplTest {

    public static final Long ID = 1L;
    private RecipeServiceImpl recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    private RecipeCommandToRecipe recipeCommandToRecipe;
    private Recipe recipe;

    @Before
    public void setUp() throws Exception {
        // inicializar los Mocks
        // Esto inicializara las dependencias que necesitemos en este caso el Repository
        MockitoAnnotations.initMocks(this);
        recipe = new Recipe();
        recipe.setId(ID);
        // inicializar nuetro RecipeService
        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void getRecipes() {
        //given, lo que queremos retornar cuando llamemos al repository

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

    @Test(expected = NotFoundException.class)
    public void getRecipeByIdTestNotFound() throws Exception {
        Optional<Recipe> recipeOptional = Optional.empty();
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        Recipe recipeReturned = recipeService.getRecipeById(1L);
    }

    @Test
    public void getRecipeById(){
        when(recipeRepository.findById(any())).thenReturn(Optional.of(recipe));

        Recipe recipe = recipeService.getRecipeById(ID);
        assertNotNull("Null recipe returned",recipe);
        assertEquals(ID, recipe.getId());
        verify(recipeRepository,times(1)).findById(any());
        verify(recipeRepository,never()).findAll();
    }

    @Test
    public void getRecipeCommandByIdTest() throws Exception {
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(ID);

        when(recipeToRecipeCommand.convert(any())).thenReturn(recipeCommand);

        RecipeCommand commandById = recipeService.findCommandById(ID);

        assertNotNull("Null recipe returned", commandById);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }


    @Test
    public void testDeleteById() throws Exception {
        //given
        Long idDelete = Long.parseLong("2");
        //when
        recipeService.deleteById(idDelete);
        //then
        verify(recipeRepository,times(1)).deleteById(anyLong());

    }
}