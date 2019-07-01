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
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import java.util.concurrent.TimeUnit




@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
class BluetoothActivity : AppCompatActivity() {
    val TAG = BluetoothActivity::class.java.simpleName

    private var tryCount = 1
    private val CHECK_COUNT = 3
    private val DEVICE_FILTER_BY_NAME = true
//    private val DEVICE_NAME = "PINKFONG_TRAIN"
    private val DEVICE_NAME = "raspberrypi"
    private val REQUEST_ENABLE_BT = 10001
    private val UUID = java.util.UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
    private val BYTE_BUFFER = 1024
    private val BYTE_ARRAY = ByteArray(BYTE_BUFFER)

    private val bluetoothAdapter by lazy {
        (getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager).adapter
    }

    private val pairedDevices by lazy {
        when (DEVICE_FILTER_BY_NAME) {
            true -> bluetoothAdapter.bondedDevices
                .filter {
                        device ->
                    device.name.equals(DEVICE_NAME, true)
                }
//                .filter {
//                        device -> device.bondState == BOND_BONDED// || device.bondState == BOND_BONDING
//                }

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
        BluetoothAdapter.getDefaultAdapter().cancelDiscovery()
        pairedDevice.createInsecureRfcommSocketToServiceRecord(UUID).apply {
            connect()
        }
    }

    private val bluetoothOutputStream by lazy {
        bluetoothSocket.let {
            bluetoothSocket.outputStream
        }
    }

    private val bluetoothInputStream by lazy {
        bluetoothSocket.let {
            bluetoothSocket.inputStream
        }
    }

    private val subscribeReceiver by lazy {
        Observable.interval(3000, 1000, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe { receiveData() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bluetooth)

        initBluetooth()

        Log.d(TAG, "Ready To Send")
        Observable.timer(2000, TimeUnit.MILLISECONDS)
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .subscribe{ sendData("go") }

        /*
        Runnable {
            Thread.sleep(1000)
            try {
                sendData("TEST")
            } catch (e: Exception){ Log.d("TEST1234", "E: $e") }
        }.run()
        */
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
        Log.d(TAG, "checkBondedDevices")
        val pairedDevices = when (DEVICE_FILTER_BY_NAME) {
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

        if(pairedDevices.isNotEmpty()) {
           Log.d(TAG, "Ok has list")
        }
        else {
            Log.d(TAG, "Need Pair")
            // 페어링 가능한 장치가 없습니다.
        }
    }

    private fun sendData(data: String) {
        Log.d(TAG, "send! :  $data")
        try {
            if (bluetoothSocket.isConnected) {
                bluetoothOutputStream.write(data.toByteArray())
            } else {
                // Stop Send & close
                Log.d(TAG, "Closed 1")
            }
        }
        catch (e: Exception) {
            Log.d(TAG, "E: ${e.message}")
            // Stop Send
        }
    }

//    private fun sendData(data: ByteArray) {
//        bluetoothOutputStream.write(data)
//    }

    private fun receiveData() {
        Log.d(TAG, "Read : receiveData")
        try {
            val bytes = bluetoothInputStream.read(BYTE_ARRAY)
            val message = String(BYTE_ARRAY, 0, bytes)
            Log.d(TAG, "Read : $message")
        } catch (e: Exception) {
            Log.d(TAG, "E: ${e.message}")
            // Stop Receive
        }
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
        IntentFilter()
            .apply {
                addAction(BluetoothDevice.ACTION_FOUND)
            }
            .run {
                registerReceiver(mReceiver, this)
            }

        subscribeReceiver
    }

    private fun unregisterReceiver() {
        unregisterReceiver(mReceiver)

        if(!subscribeReceiver.isDisposed)
            subscribeReceiver.dispose()
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