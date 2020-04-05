package com.milo.recipes.controller;

import com.milo.recipes.command.RecipeCommand;
import com.milo.recipes.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"/recipe/{id}/show"})
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.getRecipeById(Long.parseLong(id)));
        return "recipe/show";
    }

    @RequestMapping({"recipe/new"})
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeform";
    }

    @RequestMapping({"recipe/{id}/update"})
    public String updateRecipe(@PathVariable String id,  Model model){
        model.addAttribute("recipe", recipeService.findCommandById(Long.parseLong(id)));
        return "recipe/recipeform";
    }

    @PostMapping
    @RequestMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand){
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(recipeCommand);
        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

    @GetMapping
    @RequestMapping("recipe/{id}/delete")
    public String deleteById(@PathVariable String id){
        log.debug("Deleting id: "+id);
        recipeService.deleteById(Long.parseLong(id));
        return "redirect:/";
    }
}
