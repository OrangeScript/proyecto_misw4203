package com.example.vinilos.modelos

sealed class Artista {
    abstract val id: Int
    abstract val name: String
    abstract val image: String
    abstract val description: String
}

data class Banda (
    override val id: Int,
    override val name: String,
    override val image: String,
    override val description: String,
    val creationdate: String,
    ) : Artista()

data class Musico(
    override val id: Int,
    override val name: String,
    override val image: String,
    override val description: String,
    val birthdate: String,
    ) : Artista()
