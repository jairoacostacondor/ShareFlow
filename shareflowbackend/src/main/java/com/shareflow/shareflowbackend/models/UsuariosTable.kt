package com.shareflow.shareflowbackend.models

import org.jetbrains.exposed.sql.Table

object UsuariosTable : Table("Usuarios") {
    val id = integer("id_usuario").autoIncrement()
    val nombre = varchar("nombre", length = 100)
    val email = varchar("email", length = 100)
    val contrasena = varchar("contrasena", length = 255)

    override val primaryKey = PrimaryKey(id)
}
