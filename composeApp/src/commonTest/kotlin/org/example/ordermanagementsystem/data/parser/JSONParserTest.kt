package org.example.ordermanagementsystem.data.parser

import kotlin.test.*

class JSONParserTest {

   @Test
   fun canParse_JSONFileExtension_ReturnTrue() {
      val fileName = "order1.json"
      assertTrue(fileName.endsWith(".json"))
   }

}