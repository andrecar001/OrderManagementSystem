package org.example.ordermanagementsystem.data.parser

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.example.ordermanagementsystem.data.data_transfer_objects.JSONOrderWrapper
import org.example.ordermanagementsystem.domain.model.Item
import kotlin.test.*


class JSONParserTest () {
   val json = Json {
    //  encodeDefaults = true
      ignoreUnknownKeys = true
   }
   var parser = JSONParser(json)

   @Test
   fun canParse_JSONFileExtension_ReturnTrue() {
      val fileName = "order1.json"
      assertTrue(parser.canParse(fileName))
      //assertTrue(fileName.endsWith(".json"))
   }


   // val dto = json.decodeFromString<JSONOrderWrapper>(content)


     // override fun canParse(fileName: String) =
    //     fileName.endsWith(".json")

}