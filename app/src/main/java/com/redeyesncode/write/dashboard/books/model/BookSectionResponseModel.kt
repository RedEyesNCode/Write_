package com.redeyesncode.write.dashboard.books.model

import com.google.gson.annotations.SerializedName

data class BookSectionResponseModel(
    @SerializedName("status"  ) var status  : String?         = null,
    @SerializedName("code"    ) var code    : Int?            = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<Data> = arrayListOf()){


    data class Data (

        @SerializedName("id"        ) var id        : Int?    = null,
        @SerializedName("chapterId" ) var chapterId : String? = null,
        @SerializedName("bookId"    ) var bookId    : Int?    = null,
        @SerializedName("content"   ) var content   : String? = null

    )
}
