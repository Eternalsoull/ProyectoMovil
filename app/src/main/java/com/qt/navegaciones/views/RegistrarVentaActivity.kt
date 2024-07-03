package com.qt.navegaciones

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.qt.navegaciones.models.Globals
import com.qt.navegaciones.models.ProductoCarrito
import com.qt.navegaciones.models.database.entities.ClienteEntity
import com.qt.navegaciones.models.database.entities.ProductoEntity
import com.qt.navegaciones.models.database.entities.VentaEntity
import com.qt.navegaciones.adapters.ProductoCarritoAdapter
import java.util.Date

class RegistrarVentaActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var spinnerProducto: Spinner
    private lateinit var spinnerCliente: Spinner
    private lateinit var etCantidad: EditText
    private lateinit var btnAgregarProducto: ImageButton
    private lateinit var btnRegistrarVenta: Button
    private lateinit var btnVerVentas: Button
    private lateinit var lvProductosCarrito: ListView
    private lateinit var adapter: ProductoCarritoAdapter
    private lateinit var btnVolver: Button
    private val productosCarrito = ArrayList<ProductoCarrito>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_venta)

        spinnerProducto = findViewById(R.id.spinnerProducto)
        spinnerCliente = findViewById(R.id.spinnerCliente)
        etCantidad = findViewById(R.id.etCantidad)
        btnAgregarProducto = findViewById(R.id.btnAgregarProducto)
        btnRegistrarVenta = findViewById(R.id.btnRegistrarVenta)
        btnVerVentas = findViewById(R.id.btnListarVentas)
        lvProductosCarrito = findViewById(R.id.lvProductosCarrito)
        btnVolver = findViewById(R.id.btnVolver)

        adapter = ProductoCarritoAdapter(this, productosCarrito)
        lvProductosCarrito.adapter = adapter

        btnAgregarProducto.setOnClickListener(this)
        btnRegistrarVenta.setOnClickListener(this)
        btnVerVentas.setOnClickListener(this)
        btnVolver.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnAgregarProducto -> agregarProductoAlCarrito()
            R.id.btnRegistrarVenta -> registrarVenta()
            R.id.btnListarVentas -> verVentas()
            R.id.btnVolver -> {
                val intent = Intent(this, PantallaBienvenida::class.java)
                startActivity(intent)
            }
        }
    }


    override fun onResume() {
        super.onResume()
        val productos: List<ProductoEntity> = Globals.getdataBase(this)?.productoDao()?.getAllProductos()!!
        val productosString: MutableList<String> = mutableListOf()
        for (producto in productos) {
            productosString.add(producto.Nombre)
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, productosString)
        spinnerProducto.adapter = adapter

        val clientes: List<ClienteEntity> = Globals.getdataBase(this)?.clienteDao()?.getAllclientes()!!
        val clientesString: MutableList<String> = mutableListOf()
        for (cliente in clientes) {
            clientesString.add(cliente.Nombre)
        }
        val adapterCliente = ArrayAdapter(this, android.R.layout.simple_spinner_item, clientesString)
        spinnerCliente.adapter = adapterCliente
    }

    private fun agregarProductoAlCarrito() {
        val productoNombre = spinnerProducto.selectedItem as String
        val producto: ProductoEntity = Globals.getdataBase(this)?.productoDao()?.getProductoByNombre(productoNombre)!!
        val cantidad = etCantidad.text.toString().toInt()

        val productoCarrito = productosCarrito.find { it.producto.id_Producto == producto.id_Producto }

        if (productoCarrito != null) {
            // Si el producto ya está en el carrito, simplemente incrementa la cantidad
            productoCarrito.cantidad += cantidad
        } else {
            // Si el producto no está en el carrito, añádelo
            productosCarrito.add(ProductoCarrito(producto, cantidad))
        }

        adapter.notifyDataSetChanged()
    }

    private fun registrarVenta() {
        val db = Globals.getdataBase(this)
        for (productoCarrito in productosCarrito) {
            val producto = productoCarrito.producto
            val venta = VentaEntity(
                id_Producto = producto.id_Producto,
                Nombre = producto.Nombre,
                Precio = producto.Precio,
                Cantidad = productoCarrito.cantidad,
                PrecioTotal = producto.Precio * productoCarrito.cantidad,
                Fecha = Date() // Registrar la fecha y hora actual
            )
            db?.ventaDao()?.insertVenta(venta)
        }
        productosCarrito.clear()
        adapter.notifyDataSetChanged()
        Toast.makeText(this, "Venta registrada correctamente", Toast.LENGTH_SHORT).show()


    }

    private fun verVentas() {
        //pasar el nombre del ncliente a la actividad de listar ventas
        val intent = Intent(this, ListarVentasActivity::class.java)
        intent.putExtra("nombreCliente", spinnerCliente.selectedItem as String)
        startActivity(intent)
    }
}
