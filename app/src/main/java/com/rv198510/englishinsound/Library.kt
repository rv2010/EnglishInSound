package com.rv198510.englishinsound

object Library {

    private val images = arrayListOf(
        R.drawable.black,
        R.drawable.blue,
        R.drawable.boy,
        R.drawable.giraffe,
        R.drawable.girl,
        R.drawable.green,
        R.drawable.man,
        R.drawable.monkey,
        R.drawable.red,
        R.drawable.white,
        R.drawable.woman,
        R.drawable.yellow,

        )
    private val Names = arrayListOf(
        "black",
        "blue",
        "boy",
        "giraffe",
        "girl",
        "green",
        "man",
        "monkey",
        "red",
        "white",
        "woman",
        "yellow",
        )
    private val soundIds = arrayListOf(
        R.raw.black,
        R.raw.blue,
        R.raw.boy,
        R.raw.giraffe,
        R.raw.girl,
        R.raw.green,
        R.raw.man,
        R.raw.monkey,
        R.raw.red,
        R.raw.white,
        R.raw.woman,
        R.raw.yellow,
    )

    var list: ArrayList<EnglishData>? = null
        get() {

            if (field != null)
                return field

            field = ArrayList()
            for (i in images.indices) {

                val imageId = images[i]
                val name = Names[i]
                val soundId = soundIds[i]
//                val destination = EnglishData(imageId, name,soundId)
//                field!!.add(destination)
            }

            return field
        }
}