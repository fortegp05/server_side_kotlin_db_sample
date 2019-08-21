package com.example.hello_db_app.demo_db

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping
class MainController {

    @Autowired
    val userRepository : UserRepository? = null

    @PostMapping("/add")
    @ResponseBody
    fun addNewUser (@RequestParam name: String): String {
        val user : User = User(null, name)
        userRepository?.save(user)
        return "Saved"
    }

    @GetMapping("/all")
    @ResponseBody
    fun getAllUsers(): Iterable<User>? {
        return userRepository?.findAll()
    }

    @PostMapping("/update")
    @ResponseBody
    fun updateUser (@RequestParam id: Int, name: String): String {
        val user : User = User(id, name)
        userRepository?.save(user)
        return "Updated"
    }

    @PostMapping("/delete")
    @ResponseBody
    fun deleteUser (@RequestParam id: Int): String {
        if (userRepository?.existsById(id) == true) {
            userRepository?.deleteById(id)
            return "Deleted"
        } else
            return "Non User"
    }
}