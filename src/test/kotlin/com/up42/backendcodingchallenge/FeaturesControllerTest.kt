package com.up42.backendcodingchallenge

import io.restassured.RestAssured
import io.restassured.RestAssured.given
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FeaturesControllerTest {
    @LocalServerPort
    var port: Int = 0

    @BeforeAll
    fun beforeAll() {
        RestAssured.port = port
    }
    @Autowired
    protected lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun featureListTest(){
        val result = testRestTemplate.getForEntity("/features", String::class.java)
        Assertions.assertNotNull(result)
    }

    @Test
    fun getImage(){
        val result = testRestTemplate.getForEntity("/features/{featureId}/quicklook", String::class.java,"39c2f29e-c0f8-4a39-a98b-deed547d6aea")
        Assertions.assertNotNull(result)
    }
    @Test
    fun `should return all features`() {
        given()
            .get("/features")
            .then()
            .statusCode(200)

    }
}
