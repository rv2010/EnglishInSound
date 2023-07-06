package com.rv198510.englishinsound

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

private const val ARG_URI = "uri"

class EnglishFragment : Fragment() {
    private var uri: Uri? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_english, container, false)
        val itemNameTextView = view.findViewById<TextView>(R.id.nameTextView)
        val itemImageView = view.findViewById<ImageView>(R.id.imageView)


        val args = arguments
        itemNameTextView.text = args?.getString(JsonHelper.KEY_NAME)
        val imageName =   args?.getString(JsonHelper.KEY_IMAGE_ID)?.lowercase()
        val imageUrl ="${resources.getIdentifier("$imageName", "drawable", activity?.packageName)}"

        if (imageUrl != null && imageUrl.toInt() != 0) {
            Picasso.get()
                ?.load(
                    resources.getIdentifier(
                        "$imageName",
                        "drawable",
                        activity?.packageName
                    )
                )
                ?.into(itemImageView)
        }else{
            Picasso.get()
                ?.load(
                    resources.getIdentifier(
                        "dawn",
                        "drawable",
                        activity?.packageName
                    )
                )
                ?.into(itemImageView)
        }




        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(item: EnglishData) =
            EnglishFragment().apply {
                arguments = Bundle().apply {

                    putString(JsonHelper.KEY_NAME, item.name)
                    putString(JsonHelper.KEY_IMAGE_ID, item.imageId)
                    putString(JsonHelper.KEY_SOUND_ID, item.soundId)
                }
            }
    }
}