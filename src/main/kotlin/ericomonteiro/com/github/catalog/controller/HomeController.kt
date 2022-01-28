package ericomonteiro.com.github.catalog.controller

import ericomonteiro.com.github.catalog.httpclients.ViaCepClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/home")
class HomeController(
    private val viaCepClient: ViaCepClient
) {

    private val logger: Logger = LoggerFactory.getLogger(HomeController::class.java)

    @GetMapping
    fun hello(): String {
        return "Catalog System Called"
    }

    @PostMapping
    fun hello(@RequestBody anyBody: Map<String, Any>?): String {
        logger.info("here I'm")
        logger.info("body request: $anyBody")

        val address = viaCepClient.getAddressByZipCode("01001000")
        logger.info(address.toString())
        return "Catalog System Called - POST"
    }

}