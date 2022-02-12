package com.acsp.imcapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import kotlinx.android.synthetic.main.main_relative.*
import kotlin.math.pow
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_relative)
        setListeners()
    }

    private fun setListeners() {
        ed_altura?.doAfterTextChanged { text ->
            Toast.makeText(this, text.toString(), Toast.LENGTH_SHORT).show()
        }

        ed_peso?.doOnTextChanged { text, _, _, _ ->
            //  tv_imc?.text = text
        }

        btn_calcular?.setOnClickListener {
            calcularIMC(ed_peso.text.toString(), ed_altura.text.toString())
        }

    }

    private fun calcularIMC(p: String, a: String) {
        var peso = p.toFloatOrNull()
        var altura = a.toFloatOrNull()
        altura = altura?.div(100)

        if (peso != null && altura != null) {

            if (peso.toString().trim().isEmpty() || altura.toString().trim().isEmpty()) {
                tv_imc.text = "Campos incompletos"
            } else {

                val resultadoIMC = (peso / (altura.pow(2)))
                var classificacao: String = ""
                when {
                    (resultadoIMC < 18.5) -> classificacao = "Magreza"
                    (resultadoIMC > 18.5 && resultadoIMC < 24.9) -> classificacao = "Saudável"
                    (resultadoIMC > 25.0 && resultadoIMC < 29.9) -> classificacao = "Sobrepeso"
                    (resultadoIMC > 30.0 && resultadoIMC < 34.9) -> classificacao = "Obesidade grau 1"
                    (resultadoIMC > 35.0 && resultadoIMC < 39.9) -> classificacao = "Obesidade"
                    (resultadoIMC > 40.0) -> classificacao = "Obesidade morbita"
                    else -> {
                        tv_imc.text = "Valores invalidos"
                    }
                }
                tv_imc.text = "Classificação " + classificacao
            }
        }


    }
}