package com.milo.recipes.controller;

import com.milo.recipes.command.IngredientCommand;
import com.milo.recipes.service.IngredientService;
import com.milo.recipes.service.RecipeService;
import com.milo.recipes.service.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping
    @RequestMapping({"/recipe/{id}/ingredients"})
    public String listIngredients(@PathVariable String id, Model model){
        log.debug("Obteniendo lista de ingredientes por id de la receta :"+id);

        model.addAttribute("recipe", recipeService.findCommandById(Long.parseLong(id)));
        return "recipe/ingredient/list";
    }

    @GetMapping
    @RequestMapping({"recipe/{recipeId}/ingredient/{id}/show"})
    public String showRecipeIngredient(@PathVariable String recipeId,
                                       @PathVariable String id, Model model){
        model.addAttribute("ingredients", ingredientService.findByRecipeIdAndIngredientId(Long.parseLong(recipeId), Long.parseLong(id)));
        return "recipe/ingredient/show";
    }

    @GetMapping
    @RequestMapping({"recipe/{recipeId}/ingredient/{id}/update"})
    public String updateRecipeIngredient(@PathVariable String recipeId,
                                         @PathVariable String id, Model model){
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.parseLong(recipeId), Long.parseLong(id)));
        model.addAttribute("umoList", unitOfMeasureService.listAllUoms());
        return "recipe/ingredient/ingredientform";
    }

    @PostMapping
    @RequestMapping({"recipe/{recipeId}/ingredient"})
    public String saveOrUpdate(@ModelAttribute IngredientCommand command){
        IngredientCommand saveCommand = ingredientService.saveIngredientCommand(command);
        log.debug("saved recipe id: "+saveCommand.getRecipeId());
        log.debug("saved ingredient id: "+saveCommand.getId());

        return "redirect:/recipe/"+saveCommand.getRecipeId()+"/ingredient/"+saveCommand.getId()+"/show";
    }

}
