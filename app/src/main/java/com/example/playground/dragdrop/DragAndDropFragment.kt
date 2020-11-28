package com.example.playground.dragdrop

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipDescription
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginBottom
import androidx.core.view.setMargins
import androidx.databinding.DataBindingUtil
import com.example.playground.R
import com.example.playground.databinding.FragmentDragAndDropBinding
import com.google.android.material.button.MaterialButton
import timber.log.Timber

class DragAndDropFragment : Fragment() {
    private lateinit var binding: FragmentDragAndDropBinding
    private lateinit var testButton: MaterialButton
    private lateinit var area1: LinearLayout
    private lateinit var area2: LinearLayout
    private lateinit var area3: LinearLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_drag_and_drop, container, false)

        testButton = makeButton()
        area1 = binding.linearLayout1
        area2 = binding.linearLayout2
        area3 = binding.linearLayout3

        area1.addView(testButton)
        testButton.setOnLongClickListener {
            val item = ClipData.Item("드래그앤드롭")
            val dragData = ClipData(it.tag as CharSequence, arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN), item)

            
            it.startDragAndDrop(dragData, View.DragShadowBuilder(it), it, 0)
            true
        }

        // Single Touch
//        testButton.setOnTouchListener { v, event ->
//            val item = ClipData.Item("드래그앤드롭")
//            val dragData = ClipData(v.tag as CharSequence, arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN), item)
//
//            v.startDragAndDrop(dragData, View.DragShadowBuilder(v), v, 0)
//        }

        return binding.root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val normalShape = resources.getDrawable(R.drawable.normal_shape, null)
        val canBeDroppedShape = resources.getDrawable(R.drawable.can_be_dropped_shape, null)
        val nowOnShape = resources.getDrawable(R.drawable.now_on_shape, null)

        val dragListener = View.OnDragListener { v, event ->
            when(event.action){
                DragEvent.ACTION_DRAG_STARTED -> {
                    Timber.d("Drag Started")
                    if (event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                        v.background = canBeDroppedShape
                        v.invalidate()

                        true
                    } else {
                        // Can not be dropped place.
                        false
                    }
                }
                DragEvent.ACTION_DRAG_ENTERED -> {
                    Timber.d("Drag Entered")
                    v.background = nowOnShape
                    v.invalidate()

                    true
                }
                DragEvent.ACTION_DRAG_LOCATION -> {
                    Timber.d("Drag Location: ${event.x}, ${event.y}")
                    true
                }
                DragEvent.ACTION_DRAG_EXITED -> {
                    Timber.d("Drag Exited")
                    v.background = canBeDroppedShape
                    v.invalidate()

                    true
                }
                DragEvent.ACTION_DROP -> {
                    Timber.d("Drag Drop")

                    if (v == view.findViewById(R.id.linear_layout3)){
                        val item = event.clipData.getItemAt(0)
                        val dragData = item.text

                        Toast.makeText(context, "Data is $dragData", Toast.LENGTH_SHORT).show()

//                        val dragShadow = event.localState as View
//                        val parentView = dragShadow.parent as ViewGroup
//                        parentView.removeView(dragShadow)

                        val dragShadow = makeButton()
                        dragShadow.setOnLongClickListener {
                            val parent = it.parent as ViewGroup
                            parent.removeView(it)

                            true
                        }

                        (v as LinearLayout).addView(dragShadow)
                        v.invalidate()

                        true
                    } else{
                        false
                    }
                }
                DragEvent.ACTION_DRAG_ENDED -> {
                    Timber.d("Drag Ended")
                    v.background = normalShape
                    v.invalidate()

                    when (event.result) {
                        true -> Timber.d("${v.tag}: drop was handled.")
                        else -> Timber.d("${v.tag}: drop wasn't handled.")
                    }

                    true
                }
                else -> {
                    Timber.e("Unknown action in OnDragListener")
                    false
                }
            }
        }

        area1.setOnDragListener(dragListener)
//        area2.setOnDragListener(dragListener)
        area3.setOnDragListener(dragListener)

    }

    private fun makeButton(): MaterialButton{
        return MaterialButton(requireContext()).apply {
            val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            params.setMargins(resources.getDimension(R.dimen.test_button_margin).toInt())

            text = getString(R.string.test_button)
            layoutParams = params
            tag = "Test_tag1"
        }
    }

    companion object{
        const val TAG = "Drag and Drop"
    }

}