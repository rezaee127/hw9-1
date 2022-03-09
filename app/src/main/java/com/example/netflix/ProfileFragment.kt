package com.example.netflix

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.netflix.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    var name=""
    var email=""
    var phone=""
    var userName=""
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
        var pref = activity?.getSharedPreferences("sha",Context.MODE_PRIVATE)
        if (pref?.getString("name","").isNullOrBlank()) {
            gone1()
            binding.registerBtn.setOnClickListener {
                if (binding.nameEtx.text.isNullOrBlank())
                    binding.nameEtx.error = "نام را وارد کنید"
                else if (binding.emailEtx.text.isNullOrBlank())
                    binding.emailEtx.error = "ایمیل را وارد کنید"
                else {
                    saveOnSharedPreferences()
                }
            }
        } else{
            viewInformation()
        }
    }

    private fun saveOnSharedPreferences() {
        name=binding.nameEtx.text.toString()
        email=binding.emailEtx.text.toString()
        phone=binding.phoneEtx.text.toString()
        userName=binding.userNameEtx.text.toString()
        val pref = activity?.getSharedPreferences("sha",Context.MODE_PRIVATE)
        val editor=pref?.edit()
        editor?.putString("name",name)
        editor?.putString("email",email)
        editor?.putString("phone",phone)
        editor?.putString("userName",userName)
        editor?.apply()
        Toast.makeText(activity, "ذخیره اطلاعات انجام شد", Toast.LENGTH_LONG).show()

        viewInformation()
    }

    @SuppressLint("SetTextI18n")
    private fun viewInformation() {
        val pref = activity?.getSharedPreferences("sha",Context.MODE_PRIVATE)
        gone2()
        visible()
        binding.nameTxv.text="نام : ${pref?.getString("name","")}"
        binding.emailTxv.text="ایمیل : ${pref?.getString("email","")}"
        binding.phoneTxv.text="تلفن : ${pref?.getString("phone","")}"
        binding.userNameTxv.text="نام کاربری : ${pref?.getString("userName","")}"

    }

    private fun visible() {
        binding.nameTxv.visibility=View.VISIBLE
        binding.emailTxv.visibility=View.VISIBLE
        binding.phoneTxv.visibility=View.VISIBLE
        binding.userNameTxv.visibility=View.VISIBLE
    }

    private fun gone2(){
        binding.nameEtx.visibility=View.GONE
        binding.emailEtx.visibility=View.GONE
        binding.phoneEtx.visibility=View.GONE
        binding.userNameEtx.visibility=View.GONE
        binding.registerBtn.visibility=View.GONE
        binding.textView.text="اطلاعات ثبت شده"


    }
    fun gone1(){
        binding.nameTxv.visibility=View.GONE
        binding.emailTxv.visibility=View.GONE
        binding.phoneTxv.visibility=View.GONE
        binding.userNameTxv.visibility=View.GONE
    }


}