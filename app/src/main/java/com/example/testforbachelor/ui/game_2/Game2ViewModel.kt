package com.example.testforbachelor.ui.game_2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testforbachelor.R

class Game2ViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Please select the correct image!"
          }


    val text: LiveData<String> = _text

    private fun getImageList(): List<ImageItem> { //Makes an array for images
        val imageList = mutableListOf<ImageItem>()

        //Adds all the images to the array, could probably automate this somehow but for now this works
        imageList.add(ImageItem("Banana", R.drawable.bananatest))
        imageList.add(ImageItem("Apple", R.drawable.appletest))
        imageList.add(ImageItem("Orange", R.drawable.orangetest))
        imageList.add(ImageItem("Pear", R.drawable.peartest))
        imageList.add(ImageItem("Cherry", R.drawable.cherrytest))
        imageList.add(ImageItem("Lychee", R.drawable.lycheetest))
        imageList.add(ImageItem("Broccoli", R.drawable.broccolitest))
        imageList.add(ImageItem("Tomato", R.drawable.tomatotest))
        return imageList //Once its done returns the array
    }
    fun getRandomImage(): Int { //Function to get a random image from the array
        //It makes no sense to me why the data is an int but apparently path ID's are ints
        val imageList = getImageList()
        val listLength = imageList.size - 1
        val random = (0..listLength).random()
        return imageList[random].imageResId
    }
}
data class ImageItem(val name: String, val imageResId: Int){ //Makes an object for the images


}