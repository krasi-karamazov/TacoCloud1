package kpk.com.tacocloud.controllers

import kpk.com.tacocloud.model.Order
import kpk.com.tacocloud.repository.OrdersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.support.SessionStatus
import javax.validation.Valid

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
class OrdersController (@Autowired @Qualifier("OrdersJDBCRepository") val ordersRepository: OrdersRepository) {

    @GetMapping("/current")
    fun getOrderForm(model: Model, @SessionAttribute("order") order: Order): String {
        model.addAttribute("order", order)
        return "order_form"
    }

    @PostMapping
    fun submitForm(@Valid order:Order, errors: Errors, ): String {
        if(errors.hasErrors()) {
            return "order_form"
        }

        ordersRepository.saveOrder(order)
        println(order.toString())

        return "redirect:/orders/order_submitted"
    }

    @GetMapping("order_submitted")
    fun orderSubmitted(sessionStatus: SessionStatus): String {
        sessionStatus.setComplete()
        return "order_submitted"
    }


    @GetMapping("/complete")
    fun formSubmitted() {

    }
}