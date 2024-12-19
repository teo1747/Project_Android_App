package com.example.androidtp2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class SettingsFragment : Fragment() {

    companion object {
        fun newInstance(houseId: Int, token: String): SettingsFragment {
            val fragment = SettingsFragment()
            val args = Bundle()
            args.putInt("HOUSE_ID", houseId)
            args.putString("TOKEN", token)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }
}
