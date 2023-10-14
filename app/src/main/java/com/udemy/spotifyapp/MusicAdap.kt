package com.udemy.spotifyapp

import android.content.Context
import android.media.Image
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso




class  MusicAdap<T>(val context: Context, val list: List<Data>):
    RecyclerView.Adapter<MusicAdap.MusicViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {

        val itemView = LayoutInflater.from(context).inflate(R.layout.music_card,parent,false)
        return MusicViewHolder(itemView)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
       val currentData = list[position]

        val mediaPlayer = MediaPlayer.create(context,currentData.preview.toUri())
       holder.titletext.text = currentData.title
        holder.artname.text = currentData.artist.name
        Picasso.get().load(currentData.album.cover).into(holder.pic)

        holder.playBtn.setOnClickListener(){
            mediaPlayer.start()
        }
        holder.pauseBtn.setOnClickListener(){
            mediaPlayer.stop()
        }
    }


    class MusicViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        val titletext = itemView.findViewById<TextView>(R.id.songTitle)
        val pic = itemView.findViewById<ImageView>(R.id.icon)
        val artname = itemView.findViewById<TextView>(R.id.ArtistName)
        val playBtn = itemView.findViewById<ImageButton>(R.id.btnstart)
        val pauseBtn = itemView.findViewById<ImageButton>(R.id.btnstop)

    }

}