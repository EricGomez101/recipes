package app.recipes.services;

import app.recipes.commands.RecipeCommand;
import app.recipes.models.Recipe;

import java.util.Set;

public interface RecipeService
{
    Set<Recipe> getRecipes();


    Recipe findById(Long id);

    RecipeCommand findCommandById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand command);



    void deleteById(Long id);

}
