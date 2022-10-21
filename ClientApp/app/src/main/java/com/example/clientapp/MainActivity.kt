package com.example.clientapp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.clientapp.data.ImageData
import com.example.clientapp.databinding.ActivityMainBinding
import com.example.clientapp.interfaces.Base_URL
import com.example.clientapp.interfaces.ImageService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.ByteArrayOutputStream


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val PICK_IMAGE = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.randomImage.setOnClickListener{
            getRandomImage()
        }

        binding.addImage.setOnClickListener{
            addImage()
        }
        getRandomImage()




    }
    private fun addImage(){
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PICK_IMAGE && resultCode== RESULT_OK) {
            var imageUri=data?.data
            var base64String=getImageBase64(imageUri!!)
            val image=ImageData("",base64String)
            uploadImage(image)


        }
    }

    private fun getImageBase64(imageUri:Uri):String{
        var str=""
        val bitmap = MediaStore.Images.Media.getBitmap(contentResolver,imageUri)
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        str = Base64.encodeToString(byteArray, Base64.DEFAULT)
        return str
    }




    private fun uploadImage(image:ImageData){
        val api = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Base_URL)
            .build()
            .create(ImageService::class.java)

        val data=api.uploadImage(image)
        data.enqueue(object : Callback<ImageData?> {
            override fun onResponse(call: Call<ImageData?>, response: Response<ImageData?>) {

                val imageBytes = Base64.decode(response.body()!!.image, Base64.DEFAULT)
                val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                binding.randomImageView.setImageBitmap(decodedImage)

            }

            override fun onFailure(call: Call<ImageData?>, t: Throwable) {
            }
        })
    }





    private fun getRandomImage() {
        val api = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Base_URL)
            .build()
            .create(ImageService::class.java)

        val data=api.getRandomImage()
        data.enqueue(object : Callback<ImageData?> {
            override fun onResponse(call: Call<ImageData?>, response: Response<ImageData?>) {

                val imageBytes = Base64.decode(response.body()!!.image, Base64.DEFAULT)
                val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                binding.randomImageView.setImageBitmap(decodedImage)

            }

            override fun onFailure(call: Call<ImageData?>, t: Throwable) {
            }
        })




    }
}
