package com.example.hydromon.ui.profile

import android.annotation.SuppressLint
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.example.hydromon.R
import com.example.hydromon.databinding.FragmentHomeBinding
import com.example.hydromon.databinding.ProfileFragmentBinding
import com.example.hydromon.ui.authentication.login.LoginActivity
import com.example.hydromon.ui.authentication.login.LoginCookie
import com.example.hydromon.ui.home.HomeViewModel

class ProfileFragment : Fragment() {

    private var _binding: ProfileFragmentBinding? = null
    private lateinit var loginCookie : LoginCookie

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = ProfileFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        loginCookie = activity?.let { LoginCookie(it) }!!
        val userid = loginCookie.getCookie().id
        val token = loginCookie.getCookie().token
        Log.d("Test COOKIE -===", userid)


        profileViewModel.setUserProfile(token, userid)
        profileViewModel.getProfile().observe(viewLifecycleOwner){
            var namalengkap: String
            val username: String? = it.data?.username
            val email: String? = it.data?.email

            if(it.data?.nama_lengkap != null){
                namalengkap = it.data.nama_lengkap
            }else{
                namalengkap = "-"
            }
                binding.etName.setText(namalengkap)
                binding.etUsername.setText(username)
                binding.etEmail.setText(email)
                binding.etPassword.setText("********")
        }


//
//        val photo: ImageView = binding.imgItemPhoto
//        Glide.with(this)
//            .load(photo)
//            .circleCrop()
//            .into(binding.imgItemPhoto)
//        val textView: TextView = binding.textProfile
//        profileViewModel.text.observe(viewLifecycleOwner) {
//            photo.crop
//        }
//
        disableET(binding.etName)
        disableET(binding.etUsername)
        disableET(binding.etPassword)
        disableET(binding.etEmail)
        var flag = false

        val logoutButton : Button = binding.logoutButton
        logoutButton.setOnClickListener{
            loginCookie = LoginCookie(requireContext())
            loginCookie.deleteCookie()
            val moveToLogin = Intent(activity, LoginActivity::class.java)
            activity?.finish()
            startActivity(moveToLogin)

        }

        binding.editButton.setOnClickListener{
            if(flag){
                flag = false
                binding.editButton.setText("Save")
                enableET(binding.etName)
                enableET(binding.etUsername)
                enableET(binding.etPassword)
                enableET(binding.etEmail)
            }else{
                flag = true
                val etNameData = binding.etName.text.toString()
                val etUsernameData = binding.etUsername.text.toString()
                val etEmailData = binding.etEmail.text.toString()

                profileViewModel.updateProfile(
                    token,
                    userid,
                    etNameData,
                    etUsernameData,
                    etEmailData
                )

                binding.editButton.setText("Edit Profile")
                binding.etName.setText(etNameData)
                binding.etUsername.setText(etUsernameData)
                binding.etEmail.setText(etEmailData)
                Toast.makeText(it.context, "User is updated successfully", Toast.LENGTH_SHORT).show()
                disableET(binding.etName)
                disableET(binding.etUsername)
                disableET(binding.etPassword)
                disableET(binding.etEmail)

            }
        }


//        val textView: TextView = binding.textProfile
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun disableET(editText: EditText){
        with(editText) {
            isFocusableInTouchMode = false
            isEnabled = false
            setSelectAllOnFocus(false)
        }
    }

    private fun enableET(editText: EditText){
        with(editText) {
            isFocusableInTouchMode = true
            isEnabled = true
            setSelectAllOnFocus(true)
        }
    }

}