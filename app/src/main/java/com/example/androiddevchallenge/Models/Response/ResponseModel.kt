package com.example.androiddevchallenge.Models.Response

import kotlin.collections.HashMap

data class ResponseModel (val message : HashMap<String,List<String>>, val status : String )

