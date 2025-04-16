package com.shareflow.shareflowbackend

import org.jetbrains.exposed.sql.Database

object DatabaseFactory {
    fun init() {
        Database.connect(
            url = "jdbc:mysql://shareflow-db.cl6ywkwe4u8x.eu-north-1.rds.amazonaws.com:3306/shareflowDDBB",
            driver = "com.mysql.cj.jdbc.Driver",
            user = "admin",
            password = "cykwy7-kixpap-Xuqgyh"
        )
        println("✅ Conexión a RDS establecida con éxito")
    }
}
