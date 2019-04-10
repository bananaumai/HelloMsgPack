package dev.bananaumai.hellomsgpack

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.msgpack.jackson.dataformat.MessagePackFactory
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.*

data class Person(val name: String)

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val objectMapper = ObjectMapper(MessagePackFactory())
        objectMapper.registerModule(KotlinModule())
        // objectMapper.registerKotlinModule() // same effect

        val p = Person("Yuta Shimakawa")
        val bytes = objectMapper.writeValueAsBytes(p)
        Log.d("bananaumai", bytes.toString())

        // Deserialize the byte array to a Java object
        val deserialized = objectMapper.readValue<Person>(bytes)
        Log.d("bananaumai", deserialized.name)
    }
}
