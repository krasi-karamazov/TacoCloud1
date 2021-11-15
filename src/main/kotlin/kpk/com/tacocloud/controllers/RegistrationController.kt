package kpk.com.tacocloud.controllers

import kpk.com.tacocloud.forms.RegistrationForm
import kpk.com.tacocloud.repository.jpa.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/register")
class RegistrationController (val userRepository: UserRepository, val passwordEncoder: PasswordEncoder) {

    @GetMapping
    fun showRegistration(model: Model): String {
        model.addAttribute("registrationForm", RegistrationForm())
        return "registration"
    }

    @PostMapping
    fun processRegistrationForm(registrationForm: RegistrationForm): String {
        val user = registrationForm.toUser(passwordEncoder)
        val savedUser = userRepository.save(user)
        return "redirect:/login"
    }
}