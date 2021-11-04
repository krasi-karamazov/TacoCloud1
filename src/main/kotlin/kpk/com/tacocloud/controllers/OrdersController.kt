package kpk.com.tacocloud.controllers

import kpk.com.tacocloud.model.Order
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

@Controller
@RequestMapping("/orders")
class OrdersController {
    @GetMapping("/current")
    fun getOrderForm(model: Model): String {
        model.addAttribute("order", Order())
        return "order_form"
    }

    @PostMapping
    fun submitForm(@Valid order:Order) {
        println(order.toString())
    }
}