package kpk.com.tacocloud.controllers

import kpk.com.tacocloud.model.Ingredient
import kpk.com.tacocloud.model.IngredientType
import kpk.com.tacocloud.model.Order
import kpk.com.tacocloud.model.Taco
import kpk.com.tacocloud.repository.IngredientsRepository
import kpk.com.tacocloud.repository.TacoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
class TacoDesignController constructor(@Autowired @Qualifier("IngredientsJDBCRepo") val ingredientsRepository: IngredientsRepository,
    @Autowired @Qualifier("TacoJDBCRepository") val tacosRepository: TacoRepository) {

    @ModelAttribute("taco")
    fun taco() = Taco()

    @ModelAttribute
    fun order() = Order()

    @GetMapping
    fun getDesignForm(model: Model, @ModelAttribute("taco") taco: Taco): String {
        model.addAttribute("taco", taco)
        putIngredientsIntoModel(model)
        return "design"
    }

    @PostMapping
    fun submitDesign(@Valid taco: Taco, errors: Errors, @ModelAttribute order: Order): String {
        if(errors.hasErrors()) {
            return "design"
        }
        val savedTaco = tacosRepository.save(taco)
        savedTaco?.let {
            order.tacos.add(savedTaco)
        } ?: print("Could not save taco")

        return "redirect:/orders/current"
    }

    private fun putIngredientsIntoModel(model: Model) {
        val ingredients = ingredientsRepository.findAll()
        ingredients?.let {
            model.addAttribute(IngredientType.WRAP.name.lowercase(), it[IngredientType.WRAP.name]?: listOf<Ingredient>())
            model.addAttribute(IngredientType.CHEESE.name.lowercase(), it[IngredientType.CHEESE.name]?: listOf<Ingredient>())
            model.addAttribute(IngredientType.PROTEIN.name.lowercase(), it[IngredientType.PROTEIN.name]?: listOf<Ingredient>())
            model.addAttribute(IngredientType.SAUSE.name.lowercase(), it[IngredientType.SAUSE.name]?: listOf<Ingredient>())
            model.addAttribute(IngredientType.VEGGIES.name.lowercase(), it[IngredientType.VEGGIES.name]?: listOf<Ingredient>())
        }

    }
}