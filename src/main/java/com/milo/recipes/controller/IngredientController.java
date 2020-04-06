package com.milo.recipes.controller;

import com.milo.recipes.command.IngredientCommand;
import com.milo.recipes.command.RecipeCommand;
import com.milo.recipes.command.UnitOfMeasureCommand;
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
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.parseLong(recipeId), Long.parseLong(id)));
        return "recipe/ingredient/show";
    }

    @GetMapping
    @RequestMapping({"recipe/{recipeId}/ingredient/new"})
    public String newRecipe(@PathVariable String recipeId, Model model){
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.parseLong(recipeId));

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.parseLong(recipeId));
        model.addAttribute("ingredient", ingredientCommand);

        ingredientCommand.setUnitOfMeasure(new UnitOfMeasureCommand());

        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
        return "recipe/ingredient/ingredientform";
    }

    @GetMapping
    @RequestMapping({"recipe/{recipeId}/ingredient/{id}/update"})
    public String updateRecipeIngredient(@PathVariable String recipeId,
                                         @PathVariable String id, Model model){
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.parseLong(recipeId), Long.parseLong(id)));
        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
        return "recipe/ingredient/ingredientform";
    }

    @PostMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command){
        IngredientCommand saveCommand = ingredientService.saveIngredientCommand(command);
        log.debug("saved recipe id: "+saveCommand.getRecipeId());
        log.debug("saved ingredient id: "+saveCommand.getId());

        return "redirect:/recipe/"+saveCommand.getRecipeId()+"/ingredient/"+saveCommand.getId()+"/show";
    }

    @GetMapping
    @RequestMapping({"recipe/{recipeId}/ingredient/{id}/delete"})
    public String deleteIngredient(@PathVariable String recipeId,
                                   @PathVariable String id){
        log.debug("deleting ingredient id: "+id);
        ingredientService.deleteById(Long.parseLong(recipeId), Long.parseLong(id));
        return "redirect:/recipe/"+recipeId+"/ingredients";
    }

}
