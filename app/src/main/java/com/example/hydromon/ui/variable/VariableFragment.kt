package com.example.hydromon.ui.variable

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.hydromon.databinding.FragmentVariableBinding

class VariableFragment : Fragment() {

    private var _binding: FragmentVariableBinding? = null

    private val binding get() = _binding!!
    private lateinit var variableValue: VariableValue

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val notificationsViewModel = ViewModelProvider(this).get(VariableViewModel::class.java)

        _binding = FragmentVariableBinding.inflate(inflater, container, false)
        val root: View = binding.root
        variableValue = loadVariable()

        Log.d("fragment created", "${variableValue}")

        val tdsValueScreen: TextView = binding.tdsValueScreen
        var tdsValue = variableValue.tds

        tdsValueScreen.text = tdsValue.toString()
        val tdsEditText : EditText = binding.tdsEditText

        tdsEditText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0.toString().isEmpty()) {
                    tdsEditText.error = "tes error"
                }else{
                    if (p0.toString().toFloat() > 14 || p0.toString().toFloat() < 1) {
                        tdsEditText.error = "invalid input"
                        variableValue.tds = p0.toString().toInt()
                    }else{
                        variableValue.tds = p0.toString().toInt()
                    }
                }
            }

        })

        var phEditText : EditText = binding.phEditText
        var phValueScreen = binding.phValueScreen
        phValueScreen.text = variableValue.ph.toString()

        phEditText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0.toString().isEmpty()) {
                    phEditText.error = "tes error"
                }else{
//                    Log.d("error we ","${p0.toString().toFloat()}")
                    if (p0.toString().toFloat() > 14 || p0.toString().toFloat() < 1) {
                        phEditText.error = "invalid input"
                        variableValue.ph = p0.toString().toFloat()
                    }else{
                        variableValue.ph = p0.toString().toFloat()
                    }
                }
            }
        })

        val ecValueScreen: TextView = binding.ecValueScreen
        var ecValue = variableValue.ec

        ecValueScreen.text = ecValue.toString()
        val ecEditText : EditText = binding.ecEditText

        ecEditText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0.toString().isEmpty()) {
                    ecEditText.error = "tes error"
                }else{
//                    Log.d("error we ","${p0.toString().toFloat()}")
                    if (p0.toString().toFloat() > 14 || p0.toString().toFloat() < 1) {
                        ecEditText.error = "invalid input"
                        variableValue.ec = p0.toString().toInt()
                    }else{
                        variableValue.ec = p0.toString().toInt()
                    }
                }
            }
        })

        val humidityValueScreen: TextView = binding.humidityValueScreen
        var humidityValue = variableValue.humidity
        humidityValueScreen.text = humidityValue.toString()

        val humidityEditText : EditText = binding.humidityEditText

        humidityEditText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0.toString().isEmpty()) {
                    humidityEditText.error = "tes error"
                }else{
                    if (p0.toString().toFloat() > 14 || p0.toString().toFloat() < 1) {
                        humidityEditText.error = "invalid input"
                        variableValue.humidity = p0.toString().toInt()
                    }else{
                        variableValue.humidity = p0.toString().toInt()
                    }
                }
            }
        })

        val temperatureValueScreen: TextView = binding.temperatureValueScreen
        var temperatureValue = variableValue.temperature
        temperatureValueScreen.text = temperatureValue.toString()

        val temperatureEditText : EditText = binding.temperatureEditText

        temperatureEditText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0.toString().isEmpty()) {
                    temperatureEditText.error = "tes error"
                }else{
                    if (p0.toString().toFloat() > 14 || p0.toString().toFloat() < 1) {
                        temperatureEditText.error = "invalid input"
                        variableValue.temperature = p0.toString().toInt()
                    }else{
                        variableValue.temperature = p0.toString().toInt()
                    }
                }
            }
        })

        val lightIntensityValueScreen: TextView = binding.lightIntensityValueScreen
        var lightIntensityValue = variableValue.light_intensity
        lightIntensityValueScreen.text = lightIntensityValue.toString()

        val lightIntensityEditText : EditText = binding.lightIntensityEditText

        lightIntensityEditText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0.toString().isEmpty()) {
                    lightIntensityEditText.error = "teks kosong"
                }else{
                    if (p0.toString().toFloat() > 14 || p0.toString().toFloat() < 1) {
                        lightIntensityEditText.error = "invalid input"
                        variableValue.light_intensity = p0.toString().toInt()
                    }else{
                        variableValue.light_intensity = p0.toString().toInt()
                    }
                }
            }
        })

        val saveButton : Button = binding.saveButton

        saveButton.setOnClickListener{
            if((variableValue.tds>14 || variableValue.tds<1) || (variableValue.ph>14 || variableValue.ph<1) || (variableValue.ec>14 || variableValue.ec<1) || (variableValue.humidity>14 || variableValue.humidity<1) || (variableValue.temperature>14 || variableValue.temperature<1) || (variableValue.light_intensity>14 || variableValue.light_intensity<1)){
                Log.d("variable inv", "INV VARIABLE")
            }else{
                saveData(variableValue)
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun saveData(variableValue: VariableValue) {
        val preference = context?.getSharedPreferences("save_variable", Context.MODE_PRIVATE)

        val prefSave = preference?.edit()
        prefSave?.apply {
            putInt("TDS_VALUE", variableValue.tds)
            putFloat("PH_VALUE", variableValue.ph)
            putInt("EC_VALUE", variableValue.ec)
            putInt("HUMIDITY_VALUE", variableValue.humidity)
            putInt("TEMPERATURE_VALUE", variableValue.temperature)
            putInt("LIGHT_INTENSITY_VALUE", variableValue.light_intensity)
        }?.apply()

        Log.d("berhasil SAVE", "${variableValue}")
//        val mIntent = intent
//        finish()
//        startActivity(intent)
    }

    private fun loadVariable(): VariableValue {
        val preference : SharedPreferences = requireContext().getSharedPreferences("save_variable", Context.MODE_PRIVATE)

        val variableValue = VariableValue()
        variableValue.tds = preference.getInt("TDS_VALUE", 23)
        variableValue.ph = preference.getFloat("PH_VALUE", 7.0F)
        variableValue.ec = preference.getInt("EC_VALUE",5)
        variableValue.humidity = preference.getInt("HUMIDITY_VALUE",22)
        variableValue.temperature = preference.getInt("TEMPERATURE_VALUE",23)
        variableValue.light_intensity = preference.getInt("LIGHT_INTENSITY_VALUE",23)
        return variableValue
    }
}