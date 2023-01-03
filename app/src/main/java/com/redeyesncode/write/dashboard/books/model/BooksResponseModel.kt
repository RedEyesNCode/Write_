package com.redeyesncode.write.dashboard.books.model

import com.google.gson.annotations.SerializedName

data class BooksResponseModel(@SerializedName("status"  ) var status  : String?         = null,
                              @SerializedName("code"    ) var code    : Int?            = null,
                              @SerializedName("message" ) var message : String?         = null,
                              @SerializedName("data"    ) var data    : ArrayList<Data> = arrayListOf()
)
data class Data (

    @SerializedName("id"             ) var id             : Int?              = null,
    @SerializedName("bookName"       ) var bookName       : String?           = null,
    @SerializedName("bookSize"       ) var bookSize       : String?           = null,
    @SerializedName("numberOfPages"  ) var numberOfPages  : String?           = null,
    @SerializedName("bookUrl"        ) var bookUrl        : String?           = null,
    @SerializedName("bookCoverImage" ) var bookCoverImage : String?           = null,
    @SerializedName("topics"         ) var topics         : ArrayList<String> = arrayListOf()

)