package ericomonteiro.com.github.catalog.controller

import ericomonteiro.com.github.catalog.httpclients.ViaCepClient
import io.opentracing.util.GlobalTracer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
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
        logger.info("here I'm")
        val address = viaCepClient.getAddressByZipCode("13081060")
        logger.info(address.toString())
        return "Catalog System Called"
    }

}