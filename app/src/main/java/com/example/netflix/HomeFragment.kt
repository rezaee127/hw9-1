package com.example.netflix


import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.netflix.databinding.FragmentHomeBinding
import com.google.android.material.button.MaterialButton
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.navigation.fragment.findNavController

class HomeFragment : Fragment() {
    lateinit var  binding : FragmentHomeBinding
    var arrayOfFavoriteButtons=ArrayList<Button>()


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
        arrayOfFavoriteButtons= arrayListOf(binding.button1,binding.button2,binding.button3,
            binding.button4,binding.button5,binding.button6,binding.button7,binding.button8,
            binding.button9,binding.button10,binding.button11,binding.button12)

       for (button in arrayOfFavoriteButtons){
           button.setOnClickListener {
               (button as MaterialButton).setIconTintResource(R.color.red)
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
                goToFragment(ProfileFragment())
                true
            }
            R.id.favoriteMenu  -> {
                goToFragment(FavoriteFragment())
                true
            }
            R.id.comingSoonMenu->{
                goToFragment(ComingSoonFragment())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun goToFragment(fragment:Fragment) {
        var pref2 = activity?.getSharedPreferences("sha",MODE_PRIVATE)
        if (pref2?.getString("name","").isNullOrBlank()){
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }else {
            findNavController().navigate(R.id.action_homeFragment_to_comingSoonFragment)
        }
    }


}
