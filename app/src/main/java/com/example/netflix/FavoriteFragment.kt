package com.example.netflix

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.netflix.databinding.FragmentFavoriteBinding
import com.google.android.material.button.MaterialButton


class FavoriteFragment : Fragment() {
    lateinit var  binding : FragmentFavoriteBinding
    val arrayOfLiked=ArrayList<ConstraintLayout>()
    val array=ArrayList<Int>()

    var pref = activity?.getSharedPreferences("sha", Context.MODE_PRIVATE)
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
        var id=-1
        if ((requireArguments().getInt("liked"))!= -1){
            id=requireArguments().getInt("liked")
        }

        val arrayOfConstraintLayouts= arrayListOf(binding.const1,binding.const2,binding.const3,
            binding.const4,binding.const5,binding.const6,binding.const7,binding.const8,
            binding.const9,binding.const10,binding.const11,binding.const12)
        for (const in arrayOfConstraintLayouts){
            const.visibility=View.GONE
        }


        var s=pref?.getString("list","")
        val listOfIndex= s?.split(",")

        if (listOfIndex != null) {
            for (k in listOfIndex){
                if(k!="") {
                    var j = k.toInt()
                    array.add(j)
                }
            }
        }

        if(id!=-1){
            array.add(id)
        }
        val sb = StringBuilder()
        for (i in 0 until array.size) {
            sb.append(array[i]).append(",")
        }

        val editor= pref?.edit()
        editor?.putString("list", sb.toString())
        editor?.apply()

        for(i in array){
            arrayOfConstraintLayouts[i].visibility=View.VISIBLE
        }

     /*   arrayOfLiked.add(arrayOfConstraintLayouts[id ])
        for (const in arrayOfLiked){
            const.visibility=View.VISIBLE
        }


      */
        
        


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





       // var y=requireArguments().getInt("number")
     //   binding.const2.visibility=View.VISIBLE

      /*  var favoriteCount=1
        if(favoriteCount!=0){
            binding.imageView2=requireArguments().getInt("image")
            binding.textView2.text=requireArguments().getString("text")
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