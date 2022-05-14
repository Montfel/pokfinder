package com.montfel.pokedex.data

import android.util.Log
import com.montfel.pokedex.domain.Pokemon
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


fun Api() {
    val retrofit = Retrofit
        .Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service = retrofit.create<PokemonDataSource>()
    val call = service.getPokemon("charmander")
    call.enqueue(object : Callback<Pokemon> {
        override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
            if (response.isSuccessful) {
                Log.i("teste", "Api: ${response.body()?.height}. ")
            } else {
                Log.i("teste", "Api: Negado. ")
            }
        }

        override fun onFailure(call: Call<Pokemon>, t: Throwable) {
            Log.i("TAG", "onFailure: " + t.message)
        }
    })
}
