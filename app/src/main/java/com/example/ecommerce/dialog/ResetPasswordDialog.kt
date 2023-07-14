package com.example.ecommerce.dialog

import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.ecommerce.R
import com.google.android.material.bottomsheet.BottomSheetDialog

fun Fragment.setupBottomSheetDialog(
  onSendClick: (String) -> Unit
){
  val dialog = BottomSheetDialog(requireContext())
  val view = layoutInflater.inflate(R.layout.reset_password_dailog,null)
  dialog.setContentView(view)
  dialog.show()

  val edEmail= view.findViewById<EditText>(R.id.etResetPassword)
  val buttonSend= view.findViewById<Button>(R.id.sendBtn)
  val buttonCancel= view.findViewById<Button>(R.id.cancelBtn)

  buttonSend.setOnClickListener {
    val email = edEmail.text.toString().trim()
    onSendClick(email)
  }

  buttonCancel.setOnClickListener {
    dialog.dismiss()
  }
}