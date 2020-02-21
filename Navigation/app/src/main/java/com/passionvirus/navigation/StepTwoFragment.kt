package com.passionvirus.navigation

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class StepTwoFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_step_two, container, false)
        val buttonStart = v.findViewById<Button>(R.id.navigate_action_button_to_start)
        val buttonTwo = v.findViewById<Button>(R.id.navigate_action_button_to_one)

        buttonStart.setOnClickListener {
            Log.d("TEST1234", "Click Start:FromTwo")
            Navigation.findNavController(v).navigate(R.id.action_stepTwoFragment_to_startFragment)
        }

        buttonTwo.setOnClickListener {
            Log.d("TEST1234", "Click Two:FromTwo")
            Navigation.findNavController(v).navigate(R.id.action_stepTwoFragment_to_stepOneFragment)
        }

        return v
    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
         @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StepTwoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
