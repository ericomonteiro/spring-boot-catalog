package ericomonteiro.com.github.catalog.httpclients

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(value = "viaCepClient", url = "https://viacep.com.br")
interface ViaCepClient {

    @GetMapping("/ws/{zipCode}/json/")
    fun getAddressByZipCode(@PathVariable("zipCode") zipCode: String): Map<String, Any>

}