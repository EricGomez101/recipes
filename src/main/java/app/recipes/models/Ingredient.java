package app.recipes.models;

import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
public class Ingredient
{
    private String id;
    private String description;
    private BigDecimal amount;
    private Recipe recipe;
    private UnitOfMeasure uom;

    public Ingredient()
    {
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom, Recipe recipe)
    {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
        this.recipe = recipe;

    }

}
