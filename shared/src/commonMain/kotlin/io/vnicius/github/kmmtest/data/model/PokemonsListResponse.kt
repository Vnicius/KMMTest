package io.vnicius.github.kmmtest.data.model

import kotlinx.serialization.Serializable


/**
 * Created by Vinícius Veríssimo on 09/11/21.
 * github: @vnicius
 * vinicius.matheus252@gmail.com
 */
@Serializable
data class PokemonsListResponse(
    val count: Long,
    val next: String?,
    val previous: String?,
    val results: List<PokemonItemResponse>?
)
