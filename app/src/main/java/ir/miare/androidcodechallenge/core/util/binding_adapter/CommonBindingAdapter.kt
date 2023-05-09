package ir.miare.androidcodechallenge.core.util.binding_adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter

class CommonBindingAdapter {
    companion object {
        @BindingAdapter("intToText")
        @JvmStatic
        fun setIntToTextView(textView: TextView, text: Int) {
            textView.text = text.toString()
        }
    }
}