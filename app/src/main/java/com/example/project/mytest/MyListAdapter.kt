package com.example.project.mytest

import android.widget.Toast
import com.example.project.R
import com.example.project.databinding.ButtonItemBinding
import com.example.project.databinding.ImageItemBinding
import com.example.project.databinding.TextItemBinding
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

class MyListAdapter : ListDelegationAdapter<List<Any>>() {

    init {
        delegatesManager.addDelegate(textDelegate())
        delegatesManager.addDelegate(buttonDelegate())
        delegatesManager.addDelegate(imageDelegate())
    }

    //    fun cat2AdapterDelegate(itemClickedListener : (Cat) -> Unit) = adapterDelegateViewBinding<Cat, DisplayableItem, ItemCatBinding>(
//        { layoutInflater, root -> ItemCatBinding.inflate(layoutInflater, root, false) }
//    ) {
//        binding.name.setOnClickListener {
//            itemClickedListener(item)
//        }
//        bind {
//            binding.name.text = item.name
//        }
//    }
    private fun imageDelegate(): AdapterDelegate<List<Any>> =
        adapterDelegateViewBinding<ImageItem, Any, ImageItemBinding>({ layoutInflater, parent ->
            ImageItemBinding.inflate(layoutInflater, parent, false)
        }) {
            bind {
                binding.imageView.setImageResource(R.drawable.ic_launcher_background)
            }
        }

    private fun textDelegate(): AdapterDelegate<List<Any>> =
        adapterDelegateViewBinding<TextItem, Any, TextItemBinding>({ layoutInflater, parent ->
            TextItemBinding.inflate(layoutInflater, parent, false)
        }) {
            bind {
                binding.textView.text = item.text
            }
        }

    private fun buttonDelegate(): AdapterDelegate<List<Any>> =
        adapterDelegateViewBinding<ButtonItem, Any, ButtonItemBinding>({ layoutInflater, parent ->
            ButtonItemBinding.inflate(layoutInflater, parent, false)
        }) {

            binding.button.setOnClickListener {
                Toast.makeText(it.context, "Дарова", Toast.LENGTH_SHORT).show()
            }

            bind {
                binding.button.text = item.buttonText
            }
        }
}