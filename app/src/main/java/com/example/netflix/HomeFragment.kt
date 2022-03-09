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

object favorite{
    val arrayOfTitle= arrayOf("","","","","","","","","","","","")
    var count=0
}

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

        val arrayOfTextViews= arrayOf(binding.textView1,binding.textView2,binding.textView3,
            binding.textView4,binding.textView5,binding.textView6,binding.textView7,
            binding.textView8,binding.textView9,binding.textView10,binding.textView11,binding.textView12)


        val likedButtons= ArrayList<Button>()
        val array=ArrayList<Int>()
        var s=pref?.getString("list","")
        val listOfIndex= s?.split(",")
        val size = pref?.getInt("array_size", 0)
       val array2 = size?.let { arrayOfNulls<String>(it) }
        for (i in 0 until size!!)
            pref?.getString("array_$i", null)


            for (title in favorite.arrayOfTitle){
                 when(title){
                     "Film 1" -> binding.button1.setIconTintResource(R.color.red)
                     "Film 2" -> binding.button2.setIconTintResource(R.color.red)
                     "Film 3" -> binding.button3.setIconTintResource(R.color.red)
                     "Film 4" -> binding.button4.setIconTintResource(R.color.red)
                     "Film 5" -> binding.button5.setIconTintResource(R.color.red)
                     "Film 6" -> binding.button6.setIconTintResource(R.color.red)
                     "Film 7" -> binding.button7.setIconTintResource(R.color.red)
                     "Film 8" -> binding.button8.setIconTintResource(R.color.red)
                     "Film 9" -> binding.button9.setIconTintResource(R.color.red)
                     "Film 10" -> binding.button10.setIconTintResource(R.color.red)
                     "Film 11" -> binding.button11.setIconTintResource(R.color.red)
                     "Film 12" -> binding.button12.setIconTintResource(R.color.red)
                 }
            }

        for(i in arrayOfButtons.indices){
            arrayOfButtons[i].setOnClickListener {
                if (pref.getString("name", "").isNullOrBlank()) {
                    goToProfileFragment()
                } else {
                    (arrayOfButtons[i] as MaterialButton).setIconTintResource(R.color.red)
                    favorite.arrayOfTitle[i]=arrayOfTextViews[i].text.toString()
                    findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)

                   /* (arrayOfButtons[i] as MaterialButton).setIconTintResource(R.color.red)
                    array.add(i)
                    val edit = pref?.edit()
                    edit?.putInt("array_size", array.size)
                    for (i in 0 until array.size)
                        edit?.putString("array_$i", array[i].toString())
                    edit?.commit()

                    */
                    /*val sb = StringBuilder()
                    sb.append(pref?.getString("list","")).append(",")
                    for (j in 0 until array.size) {
                        sb.append(array[j]).append(",")
                    }
                    pref?.edit()?.putString("list", sb.toString())
                    pref?.edit()?.apply()

                     */
                    //var bundle = bundleOf("liked" to i)

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
            findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
        }
    }


    private fun goToComingSoonFragment() {
            findNavController().navigate(R.id.action_homeFragment_to_comingSoonFragment)
        }



}
