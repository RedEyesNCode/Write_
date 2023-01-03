package com.redeyesncode.write.base

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity :AppCompatActivity() {

    lateinit var commonDialogBox: CommonDialogBox

    lateinit var commonProgressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        commonDialogBox = CommonDialogBox(this)

        commonProgressDialog = ProgressDialog(this)
        commonProgressDialog.setCancelable(false)
        commonProgressDialog.setCanceledOnTouchOutside(false)
        commonProgressDialog.setTitle("Please wait")
        commonProgressDialog.setMessage("Loading....")

    }


    fun showToast(message:String){

        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()

    }
    fun showDialogImportantAlert(message:String){
        commonDialogBox.showDialog(message,"Important Alert")
    }
    fun showDialog(message: String,title:String){
        commonDialogBox.showDialog(message,title)
    }

    fun showLog(message:String){

        Log.i("DEV_ASHUTOSH", "showLog: $message")


    }

    fun showLoader(){
        if(commonProgressDialog.isShowing){
            commonProgressDialog.hide()
            commonProgressDialog.show()
        }else{
            commonProgressDialog.show()
        }



    }

    fun hideLoader(){
        commonProgressDialog.hide()


    }

}