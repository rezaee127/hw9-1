package com.example.netflix

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.netflix.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    lateinit var binding:FragmentProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate (inflater, container, false)

        return binding.root
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.registerBtn.setOnClickListener {
            if (binding.nameEtx.text.isNullOrBlank())
                binding.nameEtx.error = "نام را وارد کنید"
            else  if (binding.emailEtx .text.isNullOrBlank())
                binding.emailEtx.error = "ایمیل را وارد کنید"
            else {
                saveOnSharedPreferences()
                goToHomeFragment()
            }
        }
    }

    private fun saveOnSharedPreferences() {
        val name=binding.nameEtx.text.toString()
        val email=binding.emailEtx.text.toString()
        val phone=binding.phoneEtx.text.toString()
        val userName=binding.userNameEtx.text.toString()
        var pref = activity?.getSharedPreferences("sha",Context.MODE_PRIVATE)
        var editor=pref?.edit()
        editor?.putString("name",name)
        editor?.putString("email",email)
        editor?.putString("phone",phone)
        editor?.putString("userName",userName)
        editor?.apply()
        Toast.makeText(activity, "ذخیره اطلاعات انجام شد", Toast.LENGTH_LONG).show()
    }

    private fun goToHomeFragment() {
       findNavController().navigate(R.id.action_profileFragment_to_homeFragment)
    }

}