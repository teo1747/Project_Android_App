package com.example.androidtp2

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RoomDeviceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve room name and other extras
        val roomName = intent.getStringExtra("ROOM_NAME") ?: "Unknown Room"
        val homeId = intent.getIntExtra("HOME_ID", -1)
        val token = intent.getStringExtra("TOKEN") ?: ""

        // Select layout based on room name
        val layoutId = getLayoutForRoom(roomName)
        setContentView(layoutId)

        // Initialize controls based on the room name
        when (roomName) {
            "Garage" -> setupGarageControls(homeId, token)
            "Entry" -> setupEntryControls(homeId, token)
            "Living Room" -> setupLivingRoomControls(homeId, token)
            "Kitchen" -> setupKitchenControls(homeId, token)
            "Dining Room" -> setupDiningRoomControls(homeId, token)
            "Laundry" -> setupLaundryControls(homeId, token)
            "Playroom" -> setupPlayroomControls(homeId, token)
            "Bathroom" -> setupBathroomControls(homeId, token)
            else -> Toast.makeText(this, "Unknown Room: $roomName", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getLayoutForRoom(roomName: String): Int {
        return when (roomName) {
            "Garage" -> R.layout.activity_room_device_garage
            "Entry" -> R.layout.activity_room_device_entry
            "Living Room" -> R.layout.activity_room_device_livingroom
            "Kitchen" -> R.layout.activity_room_device_kitchen
            "Dining Room" -> R.layout.activity_room_device_diningroom
            "Laundry" -> R.layout.activity_room_device_laundry
            "Playroom" -> R.layout.activity_room_device_playroom
            "Bathroom" -> R.layout.activity_room_device_bathroom
            else -> R.layout.activity_room_device_default // Default fallback layout
        }
    }

    private fun setupGarageControls(homeId: Int, token: String) {
        findViewById<Button>(R.id.garageDoorButton).setOnClickListener {
            sendDeviceCommand(homeId, token, "GarageDoor 1.1", "OPEN")
        }
        findViewById<Button>(R.id.garageDoorButton1).setOnClickListener {
            sendDeviceCommand(homeId, token, "GarageDoor 1.1", "CLOSE")
        }

        findViewById<Button>(R.id.garageDoorButton2).setOnClickListener {
            sendDeviceCommand(homeId, token, "GarageDoor 1.1", "STOP")
        }

        findViewById<Button>(R.id.garageLightSwitch).setOnClickListener {
            sendDeviceCommand(homeId, token, "Light 1.5", "TURN ON")
        }
        findViewById<Button>(R.id.garageLightSwitch1).setOnClickListener {
            sendDeviceCommand(homeId, token, "Light 1.5", "TURN OFF")
        }
        findViewById<Button>(R.id.shutterOpenButton).setOnClickListener {
            sendDeviceCommand(homeId, token, "Shutter 1.9", "OPEN")
        }
        findViewById<Button>(R.id.shutterCloseButton).setOnClickListener {
            sendDeviceCommand(homeId, token, "Shutter 1.9", "CLOSE")
        }

        findViewById<Button>(R.id.shutterStopButton).setOnClickListener {
            sendDeviceCommand(homeId, token, "Shutter 1.9", "STOP")
        }

    }

    private fun setupEntryControls(homeId: Int, token: String) {
        findViewById<Button>(R.id.entryLightButton).setOnClickListener {
            sendDeviceCommand(homeId, token, "Light 1.3", "TURN ON")
        }
        findViewById<Button>(R.id.entryLightButton1).setOnClickListener {
            sendDeviceCommand(homeId, token, "Light 1.3", "TURN OFF")
        }

        findViewById<Button>(R.id.shutterButton1).setOnClickListener {
            sendDeviceCommand(homeId, token, "Shutter 1.5", "OPEN")
        }

        findViewById<Button>(R.id.shutterButton1).setOnClickListener {
            sendDeviceCommand(homeId, token, "Shutter 1.5", "CLOSE")
        }

        findViewById<Button>(R.id.shutterButton2).setOnClickListener {
            sendDeviceCommand(homeId, token, "Shutter 1.5", "STOP")
        }

    }

    private fun setupLivingRoomControls(homeId: Int, token: String) {
        findViewById<Button>(R.id.livingshutter1Button).setOnClickListener {
            sendDeviceCommand(homeId, token, "Shutter 1.3", "OPEN")
        }
        findViewById<Button>(R.id.livingshutter1Button1).setOnClickListener {
            sendDeviceCommand(homeId, token, "Shutter 1.3", "CLOSE")
        }
        findViewById<Button>(R.id.livingshutter1Button2).setOnClickListener {
            sendDeviceCommand(homeId, token, "Shutter 1.3", "STOP")
        }

        findViewById<Button>(R.id.livingshutter2Button).setOnClickListener {
            sendDeviceCommand(homeId, token, "Shutter 1.4", "OPEN")
        }

        findViewById<Button>(R.id.livingshutter2Button1).setOnClickListener {
            sendDeviceCommand(homeId, token, "Shutter 1.4", "CLOSE")
        }

        findViewById<Button>(R.id.livingshutter2Button2).setOnClickListener {
            sendDeviceCommand(homeId, token, "Shutter 1.4", "STOP")
        }

        findViewById<Button>(R.id.livingRoomLightSwitch).setOnClickListener {
            sendDeviceCommand(homeId, token, "Light 1.2", "TURN ON")
        }
        findViewById<Button>(R.id.livingRoomLightSwitch1).setOnClickListener {
            sendDeviceCommand(homeId, token, "Light 1.2", "TURN OFF")
        }
    }

    private fun setupKitchenControls(homeId: Int, token: String) {
        findViewById<Button>(R.id.Kitchenshutter1Button).setOnClickListener {
            sendDeviceCommand(homeId, token, "Shutter 1.6", "OPEN")
        }
        findViewById<Button>(R.id.Kitchenshutter1Button1).setOnClickListener {
            sendDeviceCommand(homeId, token, "Shutter 1.6", "CLOSE")
        }
        findViewById<Button>(R.id.Kitchenshutter1Button2).setOnClickListener {
            sendDeviceCommand(homeId, token, "Shutter 1.6", "STOP")
        }

        findViewById<Button>(R.id.Kitchenshutter2Button).setOnClickListener {
            sendDeviceCommand(homeId, token, "Shutter 1.7", "OPEN")
        }
        findViewById<Button>(R.id.Kitchenshutter2Button1).setOnClickListener {
            sendDeviceCommand(homeId, token, "Shutter 1.7", "CLOSE")
        }
        findViewById<Button>(R.id.Kitchenshutter2Button2).setOnClickListener {
            sendDeviceCommand(homeId, token, "Shutter 1.7", "STOP")
        }

        findViewById<Button>(R.id.Kitchenshutter3Button).setOnClickListener {
            sendDeviceCommand(homeId, token, "Shutter 1.8", "OPEN")
        }
        findViewById<Button>(R.id.Kitchenshutter3Button1).setOnClickListener {
            sendDeviceCommand(homeId, token, "Shutter 1.8", "CLOSE")
        }
        findViewById<Button>(R.id.Kitchenshutter3Button2).setOnClickListener {
            sendDeviceCommand(homeId, token, "Shutter 1.8", "STOP")
        }

        findViewById<Button>(R.id.kitchenLightSwitch).setOnClickListener {
            sendDeviceCommand(homeId, token, "Light 1.4", "TURN ON")
        }
        findViewById<Button>(R.id.kitchenLightSwitch1).setOnClickListener {
            sendDeviceCommand(homeId, token, "Light 1.4", "TURN OFF")
        }
    }

    private fun setupDiningRoomControls(homeId: Int, token: String) {
        findViewById<Button>(R.id.diningShutterButton).setOnClickListener {
            sendDeviceCommand(homeId, token, "Shutter 1.1", "OPEN")
        }
        findViewById<Button>(R.id.diningShutterButton1).setOnClickListener {
            sendDeviceCommand(homeId, token, "Shutter 1.1", "CLOSE")
        }
        findViewById<Button>(R.id.diningShutterButton2).setOnClickListener {
            sendDeviceCommand(homeId, token, "Shutter 1.1", "STOP")
        }

        findViewById<Button>(R.id.diningShutter1Button).setOnClickListener {
            sendDeviceCommand(homeId, token, "Shutter 1.2", "OPEN")
        }
        findViewById<Button>(R.id.diningShutter1Button1).setOnClickListener {
            sendDeviceCommand(homeId, token, "Shutter 1.2", "CLOSE")
        }
        findViewById<Button>(R.id.diningShutter1Button2).setOnClickListener {
            sendDeviceCommand(homeId, token, "Shutter 1.2", "STOP")
        }

        findViewById<Button>(R.id.diningShutter2Button).setOnClickListener {
            sendDeviceCommand(homeId, token, "Shutter 1.11", "OPEN")
        }
        findViewById<Button>(R.id.diningShutter2Button1).setOnClickListener {
            sendDeviceCommand(homeId, token, "Shutter 1.11", "CLOSE")
        }
        findViewById<Button>(R.id.diningShutter2Button2).setOnClickListener {
            sendDeviceCommand(homeId, token, "Shutter 1.11", "STOP")
        }


        findViewById<Button>(R.id.dininglightButton).setOnClickListener {
            sendDeviceCommand(homeId, token, "Light 1.1", "TURN ON")
        }
        findViewById<Button>(R.id.dininglightButton1).setOnClickListener {
            sendDeviceCommand(homeId, token, "Light 1.1", "TURN OFF")
        }
    }

    private fun setupLaundryControls(homeId: Int, token: String) {
        findViewById<Button>(R.id.laundryShutterButton).setOnClickListener {
            sendDeviceCommand(homeId, token, "Shutter 1.10", "OPEN")
        }
        findViewById<Button>(R.id.laundryShutterButton1).setOnClickListener {
            sendDeviceCommand(homeId, token, "Shutter 1.10", "CLOSE")
        }
        findViewById<Button>(R.id.laundryShutterButton2).setOnClickListener {
            sendDeviceCommand(homeId, token, "Shutter 1.10", "STOP")
        }
        findViewById<Button>(R.id.laundryLightSwitch).setOnClickListener {
            sendDeviceCommand(homeId, token, "Light 1.6", "TURN ON")
        }
        findViewById<Button>(R.id.laundryLightSwitch1).setOnClickListener {
            sendDeviceCommand(homeId, token, "Light 1.6", "TURN OFF")
        }
    }

    private fun setupPlayroomControls(homeId: Int, token: String) {
        findViewById<Button>(R.id.playroomShutterButton).setOnClickListener {
            sendDeviceCommand(homeId, token, "playroom-shutter", "TURN ON")
        }
        findViewById<Button>(R.id.playroomShutterButton1).setOnClickListener {
            sendDeviceCommand(homeId, token, "playroom-shutter", "TURN OFF")
        }
        findViewById<Button>(R.id.playroomLightSwitch).setOnClickListener {
            sendDeviceCommand(homeId, token, "playroom-light", "ON")
        }
        findViewById<Button>(R.id.playroomLightSwitch1).setOnClickListener {
            sendDeviceCommand(homeId, token, "playroom-light", "OFF")
        }
    }

    private fun setupBathroomControls(homeId: Int, token: String) {
        findViewById<Button>(R.id.bathroomShutterButton).setOnClickListener {
            sendDeviceCommand(homeId, token, "bathroom-shutter", "TURN ON")
        }
        findViewById<Button>(R.id.bathroomShutterButton1).setOnClickListener {
            sendDeviceCommand(homeId, token, "bathroom-shutter", "TURN OFF")
        }
        findViewById<Button>(R.id.bathroomLightSwitch).setOnClickListener {
            sendDeviceCommand(homeId, token, "bathroom-light", "ON")
        }
        findViewById<Button>(R.id.bathroomLightSwitch1).setOnClickListener {
            sendDeviceCommand(homeId, token, "bathroom-light", "OFF")
        }
    }

    private fun sendDeviceCommand(homeId: Int, token: String, deviceId: String, command: String) {
        Api().post(
            path = "https://polyhome.lesmoulinsdudev.com/api/houses/$homeId/devices/$deviceId/command",
            data = mapOf("command" to command),
            onSuccess = { responseCode ->
                runOnUiThread {
                    if (responseCode == 200) {
                        Toast.makeText(this, "Command sent successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Failed to send command", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            securityToken = token
        )
    }
}
