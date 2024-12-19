package com.example.androidtp2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment

class DownFloorFragment : Fragment() {

    private var homeId: Int = -1
    private var token: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_down_floor, container, false)

        // Retrieve HOME_ID and TOKEN from arguments
        homeId = arguments?.getInt("HOME_ID", -1) ?: -1
        token = arguments?.getString("TOKEN") ?: ""

        if (homeId == -1 || token.isEmpty()) {
            Toast.makeText(requireContext(), "Missing Home ID or Token", Toast.LENGTH_SHORT).show()
            return view
        }

        // Find all icons
        val garageIcon = view.findViewById<ImageView>(R.id.iconGarage)
        val entryIcon = view.findViewById<ImageView>(R.id.iconEntry)
        val livingRoomIcon = view.findViewById<ImageView>(R.id.iconLivingRoom)
        val kitchenIcon = view.findViewById<ImageView>(R.id.iconKitchen)
        val diningRoomIcon = view.findViewById<ImageView>(R.id.iconDiningRoom)
        val laundryIcon = view.findViewById<ImageView>(R.id.iconLaundry)
        val playroomIcon = view.findViewById<ImageView>(R.id.iconPlayroom)
        val bathroomIcon = view.findViewById<ImageView>(R.id.iconBathroom)

        // Set click listeners to open RoomDeviceActivity
        garageIcon.setOnClickListener { openRoomDeviceActivity("Garage") }
        entryIcon.setOnClickListener { openRoomDeviceActivity("Entry") }
        livingRoomIcon.setOnClickListener { openRoomDeviceActivity("Living Room") }
        kitchenIcon.setOnClickListener { openRoomDeviceActivity("Kitchen") }
        diningRoomIcon.setOnClickListener { openRoomDeviceActivity("Dining Room") }
        laundryIcon.setOnClickListener { openRoomDeviceActivity("Laundry") }
        playroomIcon.setOnClickListener { openRoomDeviceActivity("Playroom") }
        bathroomIcon.setOnClickListener { openRoomDeviceActivity("Bathroom") }

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
