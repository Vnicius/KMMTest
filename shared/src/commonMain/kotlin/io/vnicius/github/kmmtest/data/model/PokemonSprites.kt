package io.vnicius.github.kmmtest.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * Created by Vinícius Veríssimo on 09/11/21.
 * github: @vnicius
 * vinicius.matheus252@gmail.com
 */
@Serializable
data class PokemonSprites(
    @SerialName("front_default")
    val frontDefault: String? = null
)
