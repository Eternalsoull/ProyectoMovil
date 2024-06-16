package com.qt.navegaciones.models.database.dao
import androidx.room.*
import com.qt.navegaciones.models.database.entities.ProductoEntity
@Dao
interface ProductoDao {
    @Insert
    fun insertProducto( producto: ProductoEntity)

    @Delete
    fun deleteProducto( producto: ProductoEntity )

    @Update
    fun updateProducto( producto: ProductoEntity )

    @Query("SELECT * FROM producto")
    fun getAllproductos(): List<ProductoEntity>
}



