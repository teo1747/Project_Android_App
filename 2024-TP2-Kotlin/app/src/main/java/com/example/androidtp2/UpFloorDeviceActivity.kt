package com.example.androidtp2

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class UpFloorDeviceActivity : AppCompatActivity() {

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
            "Bedroom 1" -> setupBedroom1Controls(homeId, token)
            "Bedroom 2" -> setupBedroom2Controls(homeId, token)
            "Office" -> setupOfficeControls(homeId, token)
            "Bathroom" -> setupUpBathroomControls(homeId, token)
            "Dressing" -> setupDressingControls(homeId, token)
            "Bedroom 3" -> setupBedroom3Controls(homeId, token)
            else -> Toast.makeText(this, "Unknown Room: $roomName", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getLayoutForRoom(roomName: String): Int {
        return when (roomName) {
            "Bedroom 1" -> R.layout.activity_room_device_bedroom1
            "Bedroom 2" -> R.layout.activity_room_device_bedroom2
            "Office" -> R.layout.activity_room_device_office
            "Bathroom" -> R.layout.activity_room_device_bathroom_up
            "Dressing" -> R.layout.activity_room_device_dressing
            "Bedroom 3" -> R.layout.activity_room_device_bedroom3
            else -> R.layout.activity_room_device_default // Default fallback layout
        }
    }

    private fun setupBedroom1Controls(homeId: Int, token: String) {
        findViewById<Button>(R.id.bedroom1LightSwitch).setOnClickListener {
            sendDeviceCommand(homeId, token, "bedroom1-light", "ON")
        }
        findViewById<Button>(R.id.bedroom1LightSwitch1).setOnClickListener {
            sendDeviceCommand(homeId, token, "bedroom1-light", "OFF")
        }

        findViewById<Button>(R.id.bedroom1ShutterSwitch).setOnClickListener {
            sendDeviceCommand(homeId, token, "bedroom2-light", "OPEN")
        }
        findViewById<Button>(R.id.bedroom1ShutterSwitch1).setOnClickListener {
            sendDeviceCommand(homeId, token, "bedroom2-light", "CLOSE")
        }

        findViewById<Button>(R.id.bedroom1Shutter2Switch).setOnClickListener {
            sendDeviceCommand(homeId, token, "bedroom2-light", "OPEN")
        }
        findViewById<Button>(R.id.bedroom1Shutter2Switch1).setOnClickListener {
            sendDeviceCommand(homeId, token, "bedroom2-light", "CLOSE")
        }
    }

    private fun setupBedroom2Controls(homeId: Int, token: String) {
        findViewById<Button>(R.id.bedroom2LightSwitch).setOnClickListener {
            sendDeviceCommand(homeId, token, "bedroom2-light", "ON")
        }
        findViewById<Button>(R.id.bedroom2LightSwitch1).setOnClickListener {
            sendDeviceCommand(homeId, token, "bedroom2-light", "OFF")
        }

        findViewById<Button>(R.id.bedroom2ShutterSwitch).setOnClickListener {
            sendDeviceCommand(homeId, token, "office-light", "OPEN")
        }
        findViewById<Button>(R.id.bedroom2ShutterSwitch1).setOnClickListener {
            sendDeviceCommand(homeId, token, "office-light", "CLOSE")
        }

        findViewById<Button>(R.id.bedroom2Shutter2Switch).setOnClickListener {
            sendDeviceCommand(homeId, token, "office-light", "OPEN")
        }
        findViewById<Button>(R.id.bedroom2Shutter2Switch1).setOnClickListener {
            sendDeviceCommand(homeId, token, "office-light", "CLOSE")
        }


    }

    private fun setupBedroom3Controls(homeId: Int, token: String) {
        findViewById<Button>(R.id.bedroom3LightSwitch).setOnClickListener {
            sendDeviceCommand(homeId, token, "bedroom1-light", "ON")
        }
        findViewById<Button>(R.id.bedroom3LightSwitch1).setOnClickListener {
            sendDeviceCommand(homeId, token, "bedroom1-light", "OFF")
        }

        findViewById<Button>(R.id.bedroom3ShutterSwitch).setOnClickListener {
            sendDeviceCommand(homeId, token, "bedroom2-light", "OPEN")
        }
        findViewById<Button>(R.id.bedroom3ShutterSwitch1).setOnClickListener {
            sendDeviceCommand(homeId, token, "bedroom2-light", "CLOSE")
        }

        findViewById<Button>(R.id.bedroom3Shutter2Switch).setOnClickListener {
            sendDeviceCommand(homeId, token, "bedroom2-light", "OPEN")
        }
        findViewById<Button>(R.id.bedroom3Shutter2Switch1).setOnClickListener {
            sendDeviceCommand(homeId, token, "bedroom2-light", "CLOSE")
        }
    }

    private fun setupOfficeControls(homeId: Int, token: String) {
        findViewById<Button>(R.id.officeLightSwitch).setOnClickListener {
            sendDeviceCommand(homeId, token, "office-light", "ON")
        }
        findViewById<Button>(R.id.officeLightSwitch1).setOnClickListener {
            sendDeviceCommand(homeId, token, "office-light", "OFF")
        }

        findViewById<Button>(R.id.officeShutterSwitch).setOnClickListener {
            sendDeviceCommand(homeId, token, "bedroom2-light", "OPEN")
        }
        findViewById<Button>(R.id.officeShutterSwitch1).setOnClickListener {
            sendDeviceCommand(homeId, token, "bedroom2-light", "CLOSE")
        }

        findViewById<Button>(R.id.officeShutter2Switch).setOnClickListener {
            sendDeviceCommand(homeId, token, "bedroom2-light", "OPEN")
        }

        findViewById<Button>(R.id.officeShutter2Switch1).setOnClickListener {
            sendDeviceCommand(homeId, token, "bedroom2-light", "CLOSE")
        }

        findViewById<Button>(R.id.officeShutter3Switch).setOnClickListener {
            sendDeviceCommand(homeId, token, "bedroom2-light", "OPEN")
        }

        findViewById<Button>(R.id.officeShutter3Switch1).setOnClickListener {
            sendDeviceCommand(homeId, token, "bedroom2-light", "CLOSE")
        }
    }

    private fun setupUpBathroomControls(homeId: Int, token: String) {
        findViewById<Button>(R.id.bathroomUpShutterButton).setOnClickListener {
            sendDeviceCommand(homeId, token, "bathroom-up-shutter", "OPEN")
        }
        findViewById<Button>(R.id.bathroomUpShutterButton1).setOnClickListener {
            sendDeviceCommand(homeId, token, "bathroom-up-shutter", "CLOSE")
        }
        findViewById<Button>(R.id.bathroomUpLightSwitch).setOnClickListener {
            sendDeviceCommand(homeId, token, "bathroom-up-light", "ON")
        }
        findViewById<Button>(R.id.bathroomUpLightSwitch1).setOnClickListener {
            sendDeviceCommand(homeId, token, "bathroom-up-light", "OFF")
        }
    }

    private fun setupDressingControls(homeId: Int, token: String) {
        findViewById<Button>(R.id.DressingLightSwitch).setOnClickListener {
            sendDeviceCommand(homeId, token, "terrace-light", "ON")
        }
        findViewById<Button>(R.id.DressingLightSwitch1).setOnClickListener {
            sendDeviceCommand(homeId, token, "terrace-light", "OFF")
        }

        findViewById<Button>(R.id.DressingShutterSwitch).setOnClickListener {
            sendDeviceCommand(homeId, token, "bathroom-up-shutter", "OPEN")
        }
        findViewById<Button>(R.id.DressingShutterSwitch1).setOnClickListener {
            sendDeviceCommand(homeId, token, "bathroom-up-shutter", "CLOSE")
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
