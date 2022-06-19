package com.example.hydromon.ui.control

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.hydromon.databinding.FragmentControlBinding
import com.example.hydromon.databinding.FragmentVariableBinding
import com.example.hydromon.ml.Model
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer

class ControlFragment : Fragment() {

    private var _binding: FragmentControlBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentControlBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var byteBuffer : ByteBuffer = ByteBuffer.allocateDirect(3*4)
        byteBuffer.putInt(1139)
        byteBuffer.putInt(962)
        byteBuffer.putFloat(5.709F)


        val model = context?.let { Model.newInstance(it) }

// Creates inputs for reference.
        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 3), DataType.FLOAT32)
        inputFeature0.loadBuffer(byteBuffer)

// Runs model inference and gets result.
        val outputs = model?.process(inputFeature0)
        val outputFeature0 = outputs?.outputFeature0AsTensorBuffer?.floatArray

        var outputModel : TextView = binding.recommendationValue

        if (outputFeature0 != null) {
            var label: Int? = outputFeature0.indices.maxByOrNull { outputFeature0[it] }

            if (label == 1) {
                outputModel.text = "Pump Water"
            }else if (label == 2) {
                outputModel.text = "Light On"
            }else if (label == 3) {
                outputModel.text = "Pump Nutrient"
            }else if (label == 4) {
                outputModel.text = "pH Up"
            }else if (label == 5) {
                outputModel.text = "pH Down"
            }else if (label == 6) {
                outputModel.text = "Pump Water\nLight On"
            }else if (label == 7) {
                outputModel.text = "Pump Water\npH Up"
            }else if (label == 8) {
                outputModel.text = "Pump Water\npH Down"
            }else if (label == 9) {
                outputModel.text = "Pump Water\nLight On\npH Up"
            }else if (label == 10) {
                outputModel.text = "Pump Water\nLight On\npH Down"
            }else if (label == 11) {
                outputModel.text = "Light On\nPump Nutrient"
            }else if (label == 12) {
                outputModel.text = "Light On\npH Up"
            }else if (label == 13) {
                outputModel.text = "Light on\npH Down"
            }else if (label == 14) {
                outputModel.text = "Light On\nPump Nutrient\npH Up"
            }else if (label == 15) {
                outputModel.text = "Light On\nPump Nutrient\npH Down"
            }else if (label == 16) {
                outputModel.text = "Pump Nutrient\npH Up"
            }else if (label == 17) {
                outputModel.text = "Pump Nutrient\npH Down"
            }else{
                outputModel.text = "Your Hydroponics is safe, There is no action needed"
            }
        }

// Releases model resources if no longer used.
        if (model != null) {
            model.close()
        }


        val dashboardViewModel =
            ViewModelProvider(this).get(ControlViewModel::class.java)


//
//        val textView: TextView = binding.ontextDashboard
//        dashboardViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}