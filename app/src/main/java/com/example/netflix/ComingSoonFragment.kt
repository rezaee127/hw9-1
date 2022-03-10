package com.example.netflix

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.netflix.databinding.FragmentComingSoonBinding


class ComingSoonFragment : Fragment() {
    lateinit var  binding : FragmentComingSoonBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentComingSoonBinding.inflate (inflater, container, false)

        return binding.root

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_coming_soon, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button1.setOnClickListener {
            shareText(binding.textView1)
        }
        binding.button2.setOnClickListener {
            shareText(binding.textView2)
        }
        binding.button3.setOnClickListener {
            shareText(binding.textView3)
        }


    }

    fun shareText(textview:TextView){
        val pref = requireActivity().getSharedPreferences("share", Context.MODE_PRIVATE)
        if (pref.getString("name","").isNullOrBlank()){
            findNavController().navigate(R.id.action_comingSoonFragment_to_profileFragment)
        }else{
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Name of film is : ${textview.text}")
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

    }
}