package io.vnicius.github.kmmtest.api.rest

import io.ktor.client.*
import io.ktor.client.engine.ios.*


/**
 * Created by Vinícius Veríssimo on 09/11/21.
 * github: @vnicius
 * vinicius.matheus252@gmail.com
 */
actual fun httpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient = HttpClient(Ios) {
    config(this)

    engine {
        configureRequest {
            setAllowsCellularAccess(true)
        }
    }
}
