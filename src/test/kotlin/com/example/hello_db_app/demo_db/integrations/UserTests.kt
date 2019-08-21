package com.example.hello_db_app.demo_db.integrations

import com.example.hello_db_app.demo_db.MainController
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional

@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest
class UserTests {

    var mockMvc: MockMvc? = null

    @Autowired
    var target: MainController? = null

    @Before
    fun setup() {
       mockMvc = MockMvcBuilders.standaloneSetup(target).build()
    }

    @Test
    @Sql(statements = ["TRUNCATE user;"])
    fun userIntegrationTest() {
        mockMvc?.perform(
                get("/all")
        )
        ?.andExpect(status().isOk)
        ?.andExpect(content().json("[]"))

        mockMvc?.perform(
                post("/add")
                        .param("name", "unit_test")
        )
        ?.andExpect(status().isOk)
        ?.andExpect(content().string("Saved"))

        mockMvc?.perform(
                get("/all")
        )
        ?.andExpect(status().isOk)
        ?.andExpect(content().json("[{\"id\":1,\"name\":\"unit_test\"}]"))

        mockMvc?.perform(
                post("/update")
                        .param("id", "1")
                        .param("name", "update_user")
        )
        ?.andExpect(status().isOk)
        ?.andExpect(content().string("Updated"))

        mockMvc?.perform(
                get("/all")
        )
        ?.andExpect(status().isOk)
        ?.andExpect(content().json("[{\"id\":1,\"name\":\"update_user\"}]"))

        mockMvc?.perform(
                post("/delete")
                        .param("id", "1")
        )
        ?.andExpect(status().isOk)
        ?.andExpect(content().string("Deleted"))

        mockMvc?.perform(
                get("/all")
        )
        ?.andExpect(status().isOk)
        ?.andExpect(content().json("[]"))
    }
}