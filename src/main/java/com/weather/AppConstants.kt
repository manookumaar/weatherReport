package com.weather

var BASEURL = "http://api.openweathermap.org/data/2.5/"
val _ID_REQUEST = "2172797"
val APP_ID = "5ad7218f2e11df834b0eaf3a33a39d2a"

val _KEY_ID = "id"
val _KEY_APP_ID = "appid"

val request = HashMap<String, String>().apply {
    put(_KEY_APP_ID, APP_ID)
    put(_KEY_ID, _ID_REQUEST)

}