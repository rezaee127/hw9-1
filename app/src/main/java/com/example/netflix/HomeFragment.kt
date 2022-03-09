package com.example.netflix


import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.netflix.databinding.FragmentHomeBinding
import com.google.android.material.button.MaterialButton

class HomeFragment : Fragment() {
    lateinit var  binding : FragmentHomeBinding
    var arrayOfButtons=ArrayList<Button>()
    var arrayOfConstraintLayout=ArrayList<ConstraintLayout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate (inflater, container, false)

        return binding.root

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title="Netflix"
        val pref = activity?.getSharedPreferences("sha",MODE_PRIVATE)
        arrayOfButtons= arrayListOf(binding.button1,binding.button2,binding.button3,
            binding.button4,binding.button5,binding.button6,binding.button7,binding.button8,
            binding.button9,binding.button10,binding.button11,binding.button12)

        arrayOfConstraintLayout= arrayListOf(binding.const1,binding.const2,binding.const3,
            binding.const4,binding.const5,binding.const6,binding.const7,binding.const8,
            binding.const9,binding.const10,binding.const11,binding.const12)
        val likedButtons= ArrayList<Button>()
        val array=ArrayList<Int>()
        var s=pref?.getString("list","")
        val listOfIndex= s?.split(",")

        if (listOfIndex != null) {
            for (i in listOfIndex){
                if(i!="") {
                    var j = i.toInt()
                    (arrayOfButtons[j] as MaterialButton).setIconTintResource(R.color.red)
                }
            }
        }

        for(i in arrayOfButtons.indices){
            arrayOfButtons[i].setOnClickListener {
                if (pref?.getString("name", "").isNullOrBlank()) {
                    goToProfileFragment()
                } else {
                    (arrayOfButtons[i] as MaterialButton).setIconTintResource(R.color.red)
                    array.add(i)
                    val sb = StringBuilder()
                    sb.append(pref?.getString("list","")).append(",")
                    for (j in 0 until array.size) {
                        sb.append(array[j]).append(",")
                    }
                    pref?.edit()?.putString("list", sb.toString())
                    pref?.edit()?.apply()
                    var bundle = bundleOf("liked" to i)
                    findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment, bundle)
                }
            }
        }



    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.profileMenu  -> {
                goToProfileFragment()
                true
            }
            R.id.favoriteMenu  -> {
                goToFavoriteFragment()
                true
            }
            R.id.comingSoonMenu->{
                goToComingSoonFragment()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun goToProfileFragment() {
        findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
    }

    private fun goToFavoriteFragment() {
        var pref = activity?.getSharedPreferences("sha",MODE_PRIVATE)
        if (pref?.getString("name","").isNullOrBlank()){
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }else {
            var bundle = bundleOf("liked" to -1)
            findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment,bundle)
        }
    }


    private fun goToComingSoonFragment() {
            findNavController().navigate(R.id.action_homeFragment_to_comingSoonFragment)
        }



}
