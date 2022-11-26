package com.redeyesncode.write.utils

import java.text.SimpleDateFormat
import java.util.*

class AppUtils {

   public fun getCurrentDateAndroid():String{
       val c = Calendar.getInstance().time
       println("Current time => $c")

       val df = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
       val formattedDate = df.format(c)
       return formattedDate
   }
}