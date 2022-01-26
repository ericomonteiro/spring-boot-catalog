package ericomonteiro.com.github.catalog.config

import ericomonteiro.com.github.catalog.config.interceptors.TagsTracingGeneratorInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class InterceptorConfig(
    private val tagsTracingGeneratorInterceptor: TagsTracingGeneratorInterceptor
): WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(tagsTracingGeneratorInterceptor)
        super.addInterceptors(registry)
    }

}