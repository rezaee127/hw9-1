package com.example.netflix

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.netflix.databinding.FragmentFavoriteBinding


class FavoriteFragment : Fragment() {
    lateinit var  binding : FragmentFavoriteBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate (inflater, container, false)
        return binding.root

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title="Favorites"



        val arrayOfTextViews= arrayOf(binding.textView1,binding.textView2,binding.textView3,
            binding.textView4,binding.textView5,binding.textView6,binding.textView7,
            binding.textView8,binding.textView9,binding.textView10,binding.textView11,binding.textView12)

        val arrayOfImagView= arrayOf(binding.imageView1,binding.imageView2,binding.imageView3,
            binding.imageView4,binding.imageView5,binding.imageView6,binding.imageView7,
            binding.imageView8,binding.imageView9,binding.imageView10,
            binding.imageView11,binding.imageView12,)

        val arrayOfPictures= arrayOf(R.drawable.avatar_1,R.drawable.avatar_2,R.drawable.avatar_3,
            R.drawable.avatar_4,R.drawable.avatar_5,R.drawable.avatar_6,R.drawable.avatar_7,
            R.drawable.avatar_8,R.drawable.avatar_9,R.drawable.avatar_10,R.drawable.avatar_11,
            R.drawable.avatar_12)
        val pref = requireActivity().getSharedPreferences("sha", Context.MODE_PRIVATE)
        val array=  Array(12){""}
        val size: Int = pref.getInt("array_size", 0)

        if (size != 0) {
            for (i in 0 until size)
               array[i]= pref.getString("array_$i", null).toString()
        }
        var count=0

        for (i in array.indices){
            if (array[i]!=""){
                arrayOfTextViews[count].text=array[i]
                arrayOfImagView[count].setImageResource(arrayOfPictures[i])
                count++
            }
        }



        
        


    /*val set= mutableSetOf<String>()
        var setId= mutableSetOf<String>()
        var pref = activity?.getSharedPreferences("sha", Context.MODE_PRIVATE)
         setId= pref?.getStringSet("liked",set) as MutableSet<String>
        if (setId != null) {
            for (i in setId){
                arrayOfConstraintLayouts[(i.toInt()) -1].visibility=View.VISIBLE
            }
        }
         */

    }


/*    shared = getSharedPreferences("App_settings", MODE_PRIVATE);
    // add values for your ArrayList any where...
    arrPackage = new ArrayList<>();

    private fun packagesharedPreferences() {
        val editor: SharedPreferences.Editor = shared.edit()
        val set: MutableSet<String> = HashSet()
        set.addAll(arrPackage)
        editor.putStringSet("DATE_LIST", set)
        editor.apply()
        Log.d("storesharedPreferences", "" + set)
    }


    private fun retriveSharedValue() {
        val set: Set<String> = shared.getStringSet("DATE_LIST", null)
        arrPackage.addAll(set)
        Log.d("retrivesharedPreferences", "" + set)
    }

 */




}