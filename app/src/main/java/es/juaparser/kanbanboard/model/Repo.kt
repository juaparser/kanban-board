package es.juaparser.kanbanboard.model

import java.util.*

/*
* Modelo Repo, almacenará la información de todos los repositorios del usuario
*   name: String con el nombre del repositorio
*   author: String con el autor del repositorio
*   link: Enlace al repositorio en Github
*   created: Fecha de creación del repositorio
*   issues: Lista de issues del tablero kanban asociado al repositorio
* */

data class Repo(
    var name: String,
    var author: String,
    var link: String,
    var created: Date,
    var issues: MutableList<Issue>
    )
