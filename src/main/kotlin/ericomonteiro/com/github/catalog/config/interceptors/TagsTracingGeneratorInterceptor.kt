package ericomonteiro.com.github.catalog.config.interceptors

import ericomonteiro.com.github.catalog.enums.CustomTags
import io.opentracing.util.GlobalTracer
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.jose4j.jwt.consumer.JwtConsumerBuilder
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor


@Component
class TagsTracingGeneratorInterceptor: HandlerInterceptor {

    private val logger: Logger = LoggerFactory.getLogger(TagsTracingGeneratorInterceptor::class.java)

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        request.getHeader(HttpHeaders.AUTHORIZATION)?.let {
            authorizationHeader ->
            val jwtConsumer = JwtConsumerBuilder()
                .setSkipSignatureVerification()
                .setSkipAllValidators().build()

            val jwt = jwtConsumer.process(authorizationHeader)
            val userId = jwt.jwtClaims.subject
            val tracer = GlobalTracer.get()
            val span = tracer.activeSpan() ?: tracer.buildSpan("auth_check").start()
            span.setTag(CustomTags.AUTHENTICATED_USER_ID, userId)
        }

        logger.info(request.toString())
        return super.preHandle(request, response, handler)
    }
}
