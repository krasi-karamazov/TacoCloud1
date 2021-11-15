package kpk.com.tacocloud.controllers

import kpk.com.tacocloud.model.Order
import kpk.com.tacocloud.repository.jpa.OrderJPARepository
import kpk.com.tacocloud.repository.jpa.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.support.SessionStatus
import java.security.Principal
import javax.validation.Valid

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
class OrdersController (@Autowired val ordersRepository: OrderJPARepository, val userRepository: UserRepository) {

    @GetMapping("/current")
    fun getOrderForm(model: Model, @SessionAttribute("order") order: Order): String {
        model.addAttribute("order", order)
        return "order_form"
    }

    @PostMapping
    fun submitForm(@Valid order:Order, errors: Errors): String {
        if(errors.hasErrors()) {
            return "order_form"
        }
        val userName = SecurityContextHolder.getContext().authentication.name
        order.user = userRepository.findUserByUserName(userName)
        ordersRepository.save(order)
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