package com.example.netflix

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class VideoAdapter(var dataSet: ArrayList<Video>, val onClickButtonFavorite:()->Unit) : RecyclerView.Adapter<VideoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textViewTitle=view.findViewById<TextView>(R.id.textViewTitle)
        var buttonFavorite=view.findViewById<Button>(R.id.buttonFavorite)
        var videoImagView=view.findViewById<ImageView>(R.id.imageView)

        fun bind(video:Video,onClickButtonFavorite:()->Unit){
            textViewTitle.text=video.title
            videoImagView.setImageResource(video.imageView)
            if (video.isFavorite)
                (buttonFavorite as MaterialButton).setIconTintResource(R.color.red)
            else
                (buttonFavorite as MaterialButton).setIconTintResource(R.color.white)
            buttonFavorite.setOnClickListener {
                onClickButtonFavorite()
            }
        }

    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.video_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.bind (dataSet[position],onClickButtonFavorite)
    }


    override fun getItemCount() = dataSet.size

}
