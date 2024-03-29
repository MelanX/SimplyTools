package de.melanx.simplytools.data.recipes;

import de.melanx.simplytools.ModItems;
import de.melanx.simplytools.compat.CompatHelper;
import de.melanx.simplytools.items.BaseTool;
import de.melanx.simplytools.util.VanillaCondition;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.registries.ForgeRegistries;
import org.moddingx.libx.datagen.DatagenContext;
import org.moddingx.libx.datagen.provider.recipe.RecipeProviderBase;
import org.moddingx.libx.datagen.provider.recipe.crafting.CraftingExtension;

import javax.annotation.Nonnull;
import java.util.List;

public class CopperizedRecipes extends RecipeProviderBase implements CraftingExtension {

    public CopperizedRecipes(DatagenContext context) {
        super(context);
    }

    @Override
    protected void setup() {
        this.createHammer(ModItems.copperHammer, Ingredient.of(Tags.Items.INGOTS_COPPER));

        this.createExcavator(ModItems.copperExcavator, Ingredient.of(Tags.Items.INGOTS_COPPER));
    }

    @Override
    protected List<ICondition> conditions() {
        return List.of(
                new ModLoadedCondition(CompatHelper.COPPERIZED),
                new VanillaCondition()
        );
    }

    private ResourceLocation output(@Nonnull Item item) {
        //noinspection ConstantConditions
        return this.mod.resource(CompatHelper.COPPERIZED + "/" + ForgeRegistries.ITEMS.getKey(item).getPath());
    }

    private void createHammer(Item result, Ingredient material) {
        BaseTool tool = (BaseTool) result;
        this.shaped(this.output(tool.getHead()), RecipeCategory.TOOLS, tool.getHead(),
                "mm ",
                "mmm",
                " mm",
                'm', material);
        this.shaped(this.output(result), RecipeCategory.TOOLS, result,
                "  h",
                " s ",
                "s  ",
                'h', tool.getHead(),
                's', Tags.Items.RODS_WOODEN);
    }

    private void createExcavator(Item result, Ingredient material) {
        BaseTool tool = (BaseTool) result;
        this.shaped(this.output(tool.getHead()), RecipeCategory.TOOLS, tool.getHead(),
                "mmm",
                "mmm",
                " m ",
                'm', material);
        this.shaped(this.output(result), RecipeCategory.TOOLS, result,
                "  h",
                " s ",
                "s  ",
                'h', tool.getHead(),
                's', Tags.Items.RODS_WOODEN);
    }
}
