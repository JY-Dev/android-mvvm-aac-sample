package com.jydev.riiidsimapleapllication.ui.main.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.Window
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import com.jydev.riiidsimapleapllication.R
import com.jydev.riiidsimapleapllication.databinding.DialogModifyBinding

class ModifyDialog(context: Context , title: String , val update : (title : String) -> Unit) : Dialog(context) {
    val titleLiveData = MutableLiveData<String>().apply {
        value = title
    }
    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        val binding = DataBindingUtil.inflate<DialogModifyBinding>(LayoutInflater.from(context),R.layout.dialog_modify,null,false)
        setContentView(binding.root)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.apply {
            dialog = this@ModifyDialog
        }
    }

    fun updateData(title: String) {
        if(title.isNotBlank()){
            update(title)
            dismiss()
        } else
            Toast.makeText(context,context.resources.getString(R.string.input_title),Toast.LENGTH_SHORT).show()
    }
}