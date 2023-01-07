package com.redeyesncode.write.dashboard.books.model

import com.google.gson.annotations.SerializedName

data class BookChapterResponseModel( @SerializedName("status"  ) var status  : String?         = null,
                                     @SerializedName("code"    ) var code    : Int?            = null,
                                     @SerializedName("message" ) var message : String?         = null,
                                     @SerializedName("data"    ) var data    : ArrayList<Data> = arrayListOf()
){
    data class Data (

        @SerializedName("chapterId"    ) var chapterId    : Int?    = null,
        @SerializedName("bookId"       ) var bookId       : Int?    = null,
        @SerializedName("chapterName"  ) var chapterName  : String? = null,
        @SerializedName("sectionCount" ) var sectionCount : String? = null

    )
}
