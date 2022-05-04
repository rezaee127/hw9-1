package com.example.netflix


import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.netflix.databinding.FragmentHomeBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.util.Util
import com.google.android.material.button.MaterialButton



class HomeFragment : Fragment() {
    lateinit var  binding : FragmentHomeBinding

    val array=  Array(12){""}

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

        val arrayOfButtons= arrayListOf(binding.button1,binding.button2,binding.button3,
            binding.button4,binding.button5,binding.button6,binding.button7,binding.button8,
            binding.button9,binding.button10,binding.button11,binding.button12)

        val arrayOfTextViews= arrayOf(binding.textView1,binding.textView2,binding.textView3,
            binding.textView4,binding.textView5,binding.textView6,binding.textView7,
            binding.textView8,binding.textView9,binding.textView10,binding.textView11,binding.textView12)

        val pref = requireActivity().getSharedPreferences("share", Context.MODE_PRIVATE)
        getArrayFromShared()

        for (i in array.indices){
            if (array[i]!=""){
                (arrayOfButtons[i] as MaterialButton).setIconTintResource(R.color.red)
            }
        }

        for(i in arrayOfButtons.indices){
            arrayOfButtons[i].setOnClickListener {
                if (pref.getString("name", "").isNullOrBlank()) {
                    goToProfileFragment()
                } else if(array[i]!=""){
                        (arrayOfButtons[i] as MaterialButton).setIconTintResource(R.color.white)
                        array[i]=""
                        saveArrayToShared()
                        findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
                    }else{
                        (arrayOfButtons[i] as MaterialButton).setIconTintResource(R.color.red)
                        array[i]=arrayOfTextViews[i].text.toString()
                        saveArrayToShared()
                        findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
                    }

            }
        }
    }

    private fun getArrayFromShared() {
        val pref = requireActivity().getSharedPreferences("share", Context.MODE_PRIVATE)
        val size: Int = pref.getInt("array_size", 0)

        if (size != 0 ) {
            for (i in 0 until size)
                array[i]= pref.getString("array_$i", null).toString()
        }

    }

    private fun saveArrayToShared() {
        val pref = requireActivity().getSharedPreferences("share", Context.MODE_PRIVATE)
        val edit= pref.edit()
        edit.putInt("array_size", array.size)
        for (j in array.indices)
            edit.putString("array_$j", array[j])
        edit.apply()
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
        val pref = requireActivity().getSharedPreferences("share", Context.MODE_PRIVATE)
        if (pref.getString("name","").isNullOrBlank()){
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }else {
            findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
        }
    }


    private fun goToComingSoonFragment() {
            findNavController().navigate(R.id.action_homeFragment_to_comingSoonFragment)
        }







    private var player: ExoPlayer? = null
    private var playWhenReady = false
    private var currentWindow = 0
    private var playbackPosition = 0L

    private fun releasePlayer() {
        player?.run {
            playbackPosition = this.currentPosition
            currentWindow = this.currentWindowIndex
            playWhenReady = this.playWhenReady
            release()
        }
        player = null
    }


    private fun initializePlayer() {
        player = ExoPlayer.Builder(requireContext())
            .build()
            .also { exoPlayer ->
                binding.videoView.player = exoPlayer
                val mediaItem = MediaItem.fromUri(getString(R.string.video1))
                exoPlayer.setMediaItem(mediaItem)
                exoPlayer.playWhenReady = playWhenReady
                exoPlayer.seekTo(currentWindow, playbackPosition)
                exoPlayer.prepare()
            }
    }

    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT >= 24) {
            initializePlayer()
        }
    }

    override fun onResume() {
        super.onResume()
        //hideSystemUi()
        if ((Util.SDK_INT < 24 || player == null)) {
            initializePlayer()
        }
    }

    private fun hideSystemUi() {
        binding.videoView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }

    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT < 24) {
            releasePlayer()
        }
    }


    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT >= 24) {
            releasePlayer()
        }
    }


}
