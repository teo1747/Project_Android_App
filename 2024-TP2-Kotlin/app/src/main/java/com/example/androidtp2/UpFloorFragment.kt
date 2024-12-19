package com.example.androidtp2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment

class UpFloorFragment : Fragment() {

    private var homeId: Int = -1
    private var token: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_up_floor, container, false)

        // Retrieve HOME_ID and TOKEN from arguments
        homeId = arguments?.getInt("HOME_ID", -1) ?: -1
        token = arguments?.getString("TOKEN") ?: ""

        if (homeId == -1 || token.isEmpty()) {
            Toast.makeText(requireContext(), "Missing Home ID or Token", Toast.LENGTH_SHORT).show()
            return view
        }

        // Find all icons
        val bedroom1Icon = view.findViewById<ImageView>(R.id.iconBedroom1)
        val bedroom2Icon = view.findViewById<ImageView>(R.id.iconBedroom2)
        val bathroomIcon = view.findViewById<ImageView>(R.id.iconUpBathroom)
        val hallwayIcon = view.findViewById<ImageView>(R.id.iconHallway)
        val officeIcon = view.findViewById<ImageView>(R.id.iconOffice)
        val terraceIcon = view.findViewById<ImageView>(R.id.iconTerrace)

        // Set click listeners to open RoomDeviceActivity
        bedroom1Icon.setOnClickListener { openRoomDeviceActivity("Bedroom 1") }
        bedroom2Icon.setOnClickListener { openRoomDeviceActivity("Bedroom 2") }
        bathroomIcon.setOnClickListener { openRoomDeviceActivity("Bathroom") }
        hallwayIcon.setOnClickListener { openRoomDeviceActivity("Hallway") }
        officeIcon.setOnClickListener { openRoomDeviceActivity("Office") }
        terraceIcon.setOnClickListener { openRoomDeviceActivity("Terrace") }

        return view
    }

    private fun openRoomDeviceActivity(roomName: String) {
        if (homeId == -1 || token.isEmpty()) {
            Toast.makeText(requireContext(), "Invalid Home ID or Token", Toast.LENGTH_SHORT).show()
            return
        }

        // Start RoomDeviceActivity and pass the room name, home ID, and token
        val intent = Intent(requireContext(), RoomDeviceActivity::class.java).apply {
            putExtra("ROOM_NAME", roomName)
            putExtra("HOME_ID", homeId)
            putExtra("TOKEN", token)
        }
        startActivity(intent)
    }
}
