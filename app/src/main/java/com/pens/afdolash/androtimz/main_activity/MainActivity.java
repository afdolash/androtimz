package com.pens.afdolash.androtimz.main_activity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.pens.afdolash.androtimz.R;
import com.pens.afdolash.androtimz.bluetooth_activity.DeviceAdapter;
import com.pens.afdolash.androtimz.main_activity.color_fragment.ColorFragment;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    public static final String MAIN_TAG = MainActivity.class.getSimpleName();
    public static ColorData CHOOSEN_COLOR = null;

    private BluetoothAdapter bluetoothAdapter;
    private BluetoothSocket bluetoothSocket;
    private OutputStream outputStream;
    private boolean doubleBackToExit = false;
    private String bluetoothAddress = null;

    // UUID service - This is the type of Bluetooth device that the BT module is
    // It is very likely yours will be the same, if not google UUID for your manufacturer
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ColorFragment colorFragment = new ColorFragment();
        fragmentTransaction.add(R.id.container, colorFragment, ColorFragment.class.getSimpleName());
        fragmentTransaction.commit();

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        checkBTState();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        bluetoothAddress = intent.getStringExtra(DeviceAdapter.EXTRA_DEVICE_ADDRESS);

        BluetoothDevice device = bluetoothAdapter.getRemoteDevice(bluetoothAddress);

        try {
            bluetoothSocket = device.createRfcommSocketToServiceRecord(MY_UUID);
        } catch (IOException e1) {
            Toast.makeText(this, "Could not create bluetooth socket.", Toast.LENGTH_SHORT).show();
        }

        try {
            bluetoothSocket.connect();
        } catch (IOException e) {
            try {
                bluetoothSocket.close();
            } catch (IOException e2) {
                Toast.makeText(getBaseContext(), "Could not close Bluetooth socket.", Toast.LENGTH_SHORT).show();
            }
        }

        try {
            outputStream = bluetoothSocket.getOutputStream();
        } catch (IOException e) {
            Toast.makeText(getBaseContext(), "Could not create bluetooth outstream.", Toast.LENGTH_SHORT).show();
        }

        sendData("X");
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            bluetoothSocket.close();
        } catch (IOException e2) {
            Toast.makeText(getBaseContext(), "Failed to close bluetooth socket.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sendData("Y");
        try {
            outputStream.close();
            bluetoothSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void checkBTState() {
        if(bluetoothAdapter == null) {
            Toast.makeText(this, "Device does not support bluetooth.", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            if (!bluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
            }
        }
    }

    public boolean sendData(String message) {
        byte[] msgBuffer = message.getBytes();

        try {
            outputStream.write(msgBuffer);
            return true;
        } catch (IOException e) {
            Toast.makeText(this, "Device not found.", Toast.LENGTH_SHORT).show();
            finish();
            return false;
        }
    }
}
