package com.milo.recipes.controller;

import com.milo.recipes.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;

    public IngredientController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    @RequestMapping({"/recipe/{id}/ingredients"})
    public String listIngredients(@PathVariable String id, Model model){
        log.debug("Obteniendo lista de ingredientes por id de la receta :"+id);

        model.addAttribute("recipe", recipeService.findCommandById(Long.parseLong(id)));
        return "recipe/ingredient/list";
    }
}
