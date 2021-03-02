package com.example.androiddevchallenge.ViewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.Models.Response.GetImage
import com.example.androiddevchallenge.Models.Response.PuppyModel
import com.example.androiddevchallenge.Models.Response.ResponseModel
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.HttpException
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.fuel.coroutines.awaitObject
import com.github.kittinunf.fuel.coroutines.awaitObjectResponseResult
import com.github.kittinunf.result.Result
import com.github.kittinunf.fuel.gson.responseObject
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking


class MainViewModel : ViewModel() {

    val puppies : MutableState<List<PuppyModel>> = mutableStateOf(ArrayList())

    val query =  mutableStateOf("")

    val loading = mutableStateOf(false)

    fun onQueryChange(value : String){
        query.value = value
    }

    init {
        getPuppies()
    }

    fun getPuppies(){
        val items = mutableListOf<PuppyModel>()

        runBlocking {
            loading.value = true
            try {
                var result =  Fuel.get("https://dog.ceo/api/breeds/list/all").awaitObject(DeserializerResponseModel())
                for ((key, value) in result.message) {
                    var url = "https://dog.ceo/api/breed/${key}/images/random"
                    val data = Fuel.get(url).awaitObject(Deserializer())
                    items.add(PuppyModel(breed = key,imageUrl = data.message, subBreed = value))
                }
                puppies.value = items
                loading.value = false

            } catch (exception: Exception) {
                when (exception){
                    is HttpException -> println("A network request exception was thrown: ${exception.message}")
                    else -> println("An exception [${exception.javaClass.simpleName}\"] was thrown")
                }
                loading.value = false
            }
        }

    }


    class Deserializer : ResponseDeserializable<GetImage> {
        override fun deserialize(reader: java.io.Reader) = Gson().fromJson(reader, GetImage::class.java)!!
    }

    class DeserializerResponseModel : ResponseDeserializable<ResponseModel> {
        override fun deserialize(reader: java.io.Reader) = Gson().fromJson(reader, ResponseModel::class.java)!!
    }

//    class ListDeserializer : ResponseDeserializable<List<Issue>> {
//        override fun deserialize(reader: Reader): List<Issue> {
//            val type = object : TypeToken<List<Issue>>() {}.type
//            return Gson().fromJson(reader, type)
//        }
//    }

}

