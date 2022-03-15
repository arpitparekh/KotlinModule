package com.example.kotlinmodule.bluetooth_wifi

import android.bluetooth.BluetoothAdapter
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kotlinmodule.databinding.ActivityBluetoothWifiBinding

private lateinit var binding:ActivityBluetoothWifiBinding
//private lateinit var bluetoothAdapter: BluetoothAdapter
private lateinit var wifiManager:WifiManager

class BluetoothWifiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityBluetoothWifiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bluetoothAdapter: BluetoothAdapter=BluetoothAdapter.getDefaultAdapter()
        wifiManager=applicationContext.getSystemService(WIFI_SERVICE) as WifiManager

        binding.btnBluetooth.setOnClickListener {

            if(bluetoothAdapter.isEnabled){
                binding.btnBluetooth.text = "Bluetooth On"
                bluetoothAdapter.disable()
                Toast.makeText(this,"Bluetooth is Off",Toast.LENGTH_SHORT).show()
            }else{
                binding.btnBluetooth.text="Bluetooth Off"
                bluetoothAdapter.enable()
                Toast.makeText(this,"Bluetooth is On",Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnWifi.setOnClickListener {

            wifiManager.isWifiEnabled=!wifiManager.isWifiEnabled

            if(wifiManager.isWifiEnabled){
                binding.btnWifi.text = "Wifi On"


            }else{
                binding.btnWifi.text="Wifi Off"
            }
        }
    }
}