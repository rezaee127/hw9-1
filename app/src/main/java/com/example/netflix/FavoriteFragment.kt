package com.example.netflix

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.netflix.Repository.arrayOfVideos
import com.example.netflix.databinding.FragmentFavoriteBinding


class FavoriteFragment : Fragment() {
    lateinit var  binding : FragmentFavoriteBinding
    private var arrayOfFavoriteVideo=ArrayList<Video>(30)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate (inflater, container, false)
        return binding.root

        //return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title="Favorites"


        val pref = requireActivity().getSharedPreferences("share", Context.MODE_PRIVATE)
        val array=  Array(30){""}
        val size: Int = pref.getInt("array_size", 0)

        if (size != 0) {
            for (i in 0 until size)
               array[i]= pref.getString("array_$i", null).toString()
        }


        for (i in array.indices){
            if (array[i]!="")
                arrayOfFavoriteVideo.add(arrayOfVideos[array[i].toInt()])
        }

        binding.favoriteFragmentRecyclerView.adapter=VideoAdapter(arrayOfFavoriteVideo,{foo(it)})

    }

    fun foo(i:Int){}
}