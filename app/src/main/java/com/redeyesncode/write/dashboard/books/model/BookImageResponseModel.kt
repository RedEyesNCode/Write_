package com.redeyesncode.write.dashboard.books.model

import com.google.gson.annotations.SerializedName

data class BookImageResponseModel(  @SerializedName("status"  ) var status  : String?         = null,
                                    @SerializedName("code"    ) var code    : Int?            = null,
                                    @SerializedName("message" ) var message : String?         = null,
                                    @SerializedName("data"    ) var data    : ArrayList<Data> = arrayListOf()
){
    data class Data (

        @SerializedName("id"       ) var id       : Int?    = null,
        @SerializedName("bookId"   ) var bookId   : Int?    = null,
        @SerializedName("imageUrl" ) var imageUrl : String? = null

    )
}
