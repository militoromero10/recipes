package com.milo.recipes.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Prueba unitaria
 */
public class CategoryTest {

    private Category category;

    /** configuracion para antes de que corran las pruebas unitarias*/
    @BeforeEach
    public void setUp(){
        category = new Category();
    }


    @Test
    public void getId() throws Exception {
        Long id = 4L;
        category.setId(id);
        assertEquals(id, category.getId());
    }

    @Test
    public void getDescription() throws Exception {
    }

    @Test
    public void getRecipes() throws Exception {
    }
}