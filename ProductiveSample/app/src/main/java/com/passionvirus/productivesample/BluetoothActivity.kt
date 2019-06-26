package com.passionvirus.productivesample

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothDevice.BOND_BONDED
import android.bluetooth.BluetoothManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity


@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
class BluetoothActivity : AppCompatActivity() {

    private val bluetoothAdapter by lazy {
        (getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager).adapter
    }

    private val pairedDevices by lazy {
        when (DEVICE_FILTER_BY_NAME) {
            true -> bluetoothAdapter.bondedDevices
                .filter {
                        device -> device.name.equals(DEVICE_NAME, true)
                }
                .filter {
                        device -> device.bondState == BOND_BONDED// || device.bondState == BOND_BONDING
                }

            false -> bluetoothAdapter.bondedDevices
                .filter {
                        device -> device.bondState == BOND_BONDED// || device.bondState == BOND_BONDING
                }
        }
    }

    private val pairedDevice by lazy {
        pairedDevices[0]
    }

    private val bluetoothSocket by lazy {
        val uuid = java.util.UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
//        val uuid = java.util.UUID.fromString("00000000-0000-1000-8000-00805F9B34FB")

        pairedDevice.createRfcommSocketToServiceRecord(uuid)
    }

    private val bluetoothOutputStream by lazy {
        bluetoothSocket.outputStream
    }

    private val bluetoothInputStream by lazy {
        bluetoothSocket.inputStream
    }

    private var tryCount = 1
    private val CHECK_COUNT = 3
    private val DEVICE_FILTER_BY_NAME = true
//    private val DEVICE_NAME = "PINKFONG_TRAIN"
    private val DEVICE_NAME = "PaMu Scroll"
    private val REQUEST_ENABLE_BT = 10001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bluetooth)

        initBluetooth()
    }

    private fun initBluetooth() {
        if (checkBluetoothFeature()) {
            checkBluetoothEnabled()
        } else {
            checkBondedDevices()
        }
    }

    private fun checkBluetoothFeature(): Boolean {
        return packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)
    }

    private fun checkBluetoothEnabled() {
        when (bluetoothAdapter.isEnabled) {
            true -> checkBondedDevices()
            false -> bluetoothEnable()
        }
    }

    private fun bluetoothEnable() {
        val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
        enableBtIntent.run {
            startActivityForResult(this, REQUEST_ENABLE_BT)
        }
    }

    private fun checkBondedDevices() {
        // Use lazy
        /*
        pairedDevices = when (DEVICE_FILTER_BY_NAME) {
            true -> bluetoothAdapter.bondedDevices
                .filter {
                    device -> device.name.equals(DEVICE_NAME, true)
                }
                .filter {
                    device -> device.bondState == BOND_BONDED// || device.bondState == BOND_BONDING
                }

            false -> bluetoothAdapter.bondedDevices
                .filter {
                        device -> device.bondState == BOND_BONDED// || device.bondState == BOND_BONDING
                }
        }
        */
    }

    private fun sendData(data: String) {
        bluetoothOutputStream.write(data.toByteArray())
    }

    private fun sendData(data: ByteArray) {
        bluetoothOutputStream.write(data)
    }

    private val mReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND == action) {
                // Get the BluetoothDevice object from the Intent
                val device = intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
            }
            else {
                // Bluetooth not found
            }

        }
    }

    private fun registerReceiver() {
        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        filter.run {
            registerReceiver(mReceiver, this)
        }
    }

    private fun unregisterReceiver() {
        unregisterReceiver(mReceiver)
    }

    override fun onResume() {
        super.onResume()
        registerReceiver()
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_ENABLE_BT && resultCode == Activity.RESULT_CANCELED) {

            if (tryCount < CHECK_COUNT) {
                bluetoothEnable()
                tryCount++
            }
            else {
                // Bluetooth setting canceled
            }

        }

        if (requestCode == REQUEST_ENABLE_BT && resultCode == Activity.RESULT_OK) {
            // Bluetooth Enabled
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
}