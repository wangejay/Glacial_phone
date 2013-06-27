/*
  NTL_Bluetooth_Car - Reference MultiColorLamp's Source code to build
  Copyright (c) 2012 Nathaniel_Chen 

  Ver 3.0_beta

  Not allow commercial use.
  E-Mail: s99115247@stu.edu.tw 

  thanks MIT research help me so that I can do this project ;) 


  [Next to do:add level meter control, menu select function.]

 version history
 3.0_beta--New UI Construe, use RelativeLayout. 
  		 --Add English Language support. 
  		 --Add rotation function, but not use right code, will re Activity when every rotation. 
  		 (need to fix...I spend 2 hours to fix but too noob to fail lol!)
  		 --Improve code, button use ClickListener, clear some unnecessary comment but keep some important.
  		 @2012/08/13
  		 
 2.1_beta--Fix Program Crash when execute Amarino.connect(due to ADT update reason). @ 2012/08/09
 1.5_beta--Improve UI Experience.
 1.0_beta--First re-modify control program.
 =========================================================================

  MultiColorLamp - Example to use with Amarino
  Copyright (c) 2009 Bonifaz Kaufmann. 

  This library is free software; you can redistribute it and/or
  modify it under the terms of the GNU Lesser General Public
  License as published by the Free Software Foundation; either
  version 2.1 of the License, or (at your option) any later version.

  This library is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
  Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public
  License along with this library; if not, write to the Free Software
  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA

 */
package edu.stu.cc.amarino.NTL_Bluetooth_Car;

import edu.stu.cc.amarino.NTL_Bluetooth_Car.R;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.widget.SeekBar.OnSeekBarChangeListener;
import at.abraxas.amarino.Amarino;

public class NTL_Bluetooth_Car extends Activity implements
		OnSeekBarChangeListener {

	private static final String TAG = "NTL_Bluetooth_Car";

	/*
	 * TODO: change the address to the address of your Bluetooth module and
	 * ensure your device is added to Amarino 
	 * �n�N�o���������v������������mac���m�I
	 */

	private String DEVICE_ADDRESS = "00:12:09:29:52:57"; // �o�O310����

	// private static final String DEVICE_ADDRESS = "00:12:01:30:02:69";
	// //�o�Oxbee BT

	final int DELAY = 150;
	int power_Val;
	long lastChange;
	SeekBar power_SB;
	TextView powerval_Text;
	EditText setmac_ET;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Amarino.connect(this, DEVICE_ADDRESS);

		power_SB = (SeekBar) findViewById(R.id.power_seekBar);
		//power_SB.setOnSeekBarChangeListener(this);

		powerval_Text = (TextView) findViewById(R.id.powerval_LargeText);
		setmac_ET = (EditText) findViewById(R.id.setmac_EditText);

		// �H�U�Obutton���i��listener
		//Button btnSetmac = (Button) findViewById(R.id.setmac_Button);
		//btnSetmac.setTag(0);
		//btnSetmac.setOnClickListener(btnOnClick);

		Button btnForward = (Button) findViewById(R.id.Power);
		btnForward.setTag(1);
		btnForward.setOnClickListener(btnOnClick);

		Button btnBack = (Button) findViewById(R.id.Volume_up);
		btnBack.setTag(2);
		btnBack.setOnClickListener(btnOnClick);

		Button btnLeft = (Button) findViewById(R.id.Volume_down);
		btnLeft.setTag(3);
		btnLeft.setOnClickListener(btnOnClick);

		Button btnRight = (Button) findViewById(R.id.up);
		btnRight.setTag(4);
		btnRight.setOnClickListener(btnOnClick);

		Button btnStop = (Button) findViewById(R.id.down);
		btnStop.setTag(5);
		btnStop.setOnClickListener(btnOnClick);
		
		Button btnnum1 = (Button) findViewById(R.id.button1);
		btnnum1.setTag(11);
		btnnum1.setOnClickListener(btnOnClick);
		
		Button btnnum2 = (Button) findViewById(R.id.button2);
		btnnum2.setTag(12);
		btnnum2.setOnClickListener(btnOnClick);
		
		Button btnnum3 = (Button) findViewById(R.id.button3);
		btnnum3.setTag(13);
		btnnum3.setOnClickListener(btnOnClick);

		Button btnhdmi = (Button) findViewById(R.id.Button06);
		btnhdmi.setTag(21);
		btnhdmi.setOnClickListener(btnOnClick);

		Button btnled1 = (Button) findViewById(R.id.Button07);
		btnled1.setTag(22);
		btnled1.setOnClickListener(btnOnClick);
		
		Button btnled2 = (Button) findViewById(R.id.Button08);
		btnled2.setTag(23);
		btnled2.setOnClickListener(btnOnClick);
		
		Button btnled3 = (Button) findViewById(R.id.Button09);
		btnled3.setTag(24);
		btnled3.setOnClickListener(btnOnClick);
		
		Button btnrecipe = (Button) findViewById(R.id.Button10);
		btnrecipe.setTag(25);
		btnrecipe.setOnClickListener(btnOnClick);

		Button btntv = (Button) findViewById(R.id.Button01);
		btntv.setTag(26);
		btntv.setOnClickListener(btnOnClick);
		
	}

	private Button.OnClickListener btnOnClick = new Button.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			switch ((Integer) v.getTag()) {
			case 0:
				SetMacOnClick();
				break;
			case 1:
				power();
				break;
			case 2:
				Volume_up();
				break;
			case 3:
				Volume_down();
				break;
			case 4:
				up();
				break;
			case 5:
				Log.d(TAG, "Case 5");
				down();
				break;
			case 11:
				num1();
				break;
			case 12:
				num2();
				break;
			case 13:
				Log.d(TAG, "Case 3");
				num3();
				break;
			case 21:
				HDMI();
				break;
			case 22:
				LED1();
				break;
			case 23:
				LED2();
				break;
			case 24:
				LED3();
				break;
			case 25:
				Recipe();
				break;
			case 26:
				TV();
				break;
			}
			
			
		}

	};

	public void SetMacOnClick() { // �]�wMAC���s���U���������@
		Amarino.disconnect(this, DEVICE_ADDRESS);
		DEVICE_ADDRESS = setmac_ET.toString();
		setmac_ET.setHint(DEVICE_ADDRESS);
		Amarino.connect(this, DEVICE_ADDRESS);
	}

	// ����AlertDialog
	/*
	 * private void ShowAlertDialog() { Builder MyAlertDialog = new
	 * AlertDialog.Builder(this); MyAlertDialog.setTitle("���D");
	 * MyAlertDialog.setMessage("���O���e"); MyAlertDialog.show(); }
	 */

	@Override
	protected void onStart() {
		super.onStart();

		// load last state
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(this);
		power_Val = prefs.getInt("power_Val", 0);

		// set seekbars and feedback color according to last state
		/*
		 * redSB.setProgress(red); greenSB.setProgress(green);
		 * blueSB.setProgress(blue);
		 */

		//power_SB.setProgress(power_Val);

		// colorIndicator.setBackgroundColor(Color.rgb(red, green, blue));

		new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(6000);
				} catch (InterruptedException e) {
				}
				Log.d(TAG, "update data");
				updateAlldata();
			}
		}.start();

	}

	@Override
	protected void onStop() {
		super.onStop();
		// save state
		PreferenceManager.getDefaultSharedPreferences(this).edit()
		/*
		 * .putInt("red", red) .putInt("green", green) .putInt("blue", blue)
		 */
		.putInt("power_Val", power_Val).commit();

		// stop Amarino's background service, we don't need it any more
		Amarino.disconnect(this, DEVICE_ADDRESS);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// do not send to many updates, Arduino can't handle so much
		if (System.currentTimeMillis() - lastChange > DELAY) {
			updateState(seekBar);
			lastChange = System.currentTimeMillis();
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		lastChange = System.currentTimeMillis();
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		updateState(seekBar);
	}

	private void updateState(final SeekBar seekBar) {

		switch (seekBar.getId()) {
		/*
		 * case R.id.SeekBarRed: red = seekBar.getProgress(); updateRed();
		 * break;
		 */
		case R.id.power_seekBar:
			power_Val = seekBar.getProgress();

			powerval_Text.setText(Integer.toString(power_Val)); // �Npower_Val��������textbox����

			update_PowerVal();

			break;
		/*
		 * case R.id.SeekBarBlue: blue = seekBar.getProgress(); updateBlue();
		 * break;
		 */
		}

		update_PowerVal();
		// provide user feedback
		// colorIndicator.setBackgroundColor(Color.rgb(red, green, blue));
	}

	private void updateAlldata() { // �o�������e�X���O��arduino
		// send state to Arduino
		// updateRed();
		update_PowerVal();

		// updateBlue();
	}

	private void update_PowerVal() {
		Amarino.sendDataToArduino(this, DEVICE_ADDRESS, 'P', power_Val);
		Log.d(TAG, "update power value");
	}

	private void power() {
		//Amarino.sendDataToArduino(this, DEVICE_ADDRESS, 'L', 0);	
		//Amarino.sendDataToArduino(this, DEVICE_ADDRESS, 'R', 0);
		//Amarino.sendDataToArduino(this, DEVICE_ADDRESS, 'B', 0);
		Amarino.sendDataToArduino(this, DEVICE_ADDRESS, 'A', 100);
		//Log.d(TAG, DEVICE_ADDRESS);
		Log.d(TAG, "Power");
	}

	private void Volume_up() {
		//Amarino.sendDataToArduino(this, DEVICE_ADDRESS, 'F', 0);
		Amarino.sendDataToArduino(this, DEVICE_ADDRESS, 'B', 100);
		Log.d(TAG, "Vol up");
	}

	private void Volume_down() {
		//Amarino.sendDataToArduino(this, DEVICE_ADDRESS, 'R', 0);
		Amarino.sendDataToArduino(this, DEVICE_ADDRESS, 'C', power_Val);
		Log.d(TAG, "Vol down");
	}

	private void up() {
		//Amarino.sendDataToArduino(this, DEVICE_ADDRESS, 'L', 0);	
		Amarino.sendDataToArduino(this, DEVICE_ADDRESS, 'D', power_Val);
		Log.d(TAG, "up");
	}

	private void down() {
		//Amarino.sendDataToArduino(this, DEVICE_ADDRESS, 'F', 0);
		//Amarino.sendDataToArduino(this, DEVICE_ADDRESS, 'L', 0);	
		//Amarino.sendDataToArduino(this, DEVICE_ADDRESS, 'R', 0);
		Amarino.sendDataToArduino(this, DEVICE_ADDRESS, 'E', 0);
		Log.d(TAG, "down");
	}
	private void num1() {
		Amarino.sendDataToArduino(this, DEVICE_ADDRESS, '1', 0);
		Log.d(TAG, "1");
	}
	private void num2() {
		Amarino.sendDataToArduino(this, DEVICE_ADDRESS, '2', 0);
		Log.d(TAG, "2");
	}
	private void num3() {
		Amarino.sendDataToArduino(this, DEVICE_ADDRESS, '3', 0);
		Log.d(TAG, "3");
	}
	private void num4() {
		Amarino.sendDataToArduino(this, DEVICE_ADDRESS, '4', 0);
		Log.d(TAG, "4");
	}
	private void num5() {
		Amarino.sendDataToArduino(this, DEVICE_ADDRESS, '5', 0);	
	}
	private void num6() {
		Amarino.sendDataToArduino(this, DEVICE_ADDRESS, '6', 0);
	}	
	private void num7() {
		Amarino.sendDataToArduino(this, DEVICE_ADDRESS, '7', 0);
	}
	private void num8() {
		Amarino.sendDataToArduino(this, DEVICE_ADDRESS, '9', 0);	
	}
	private void num9() {
		Amarino.sendDataToArduino(this, DEVICE_ADDRESS, '9', 0);
	}
	private void num0() {
		Amarino.sendDataToArduino(this, DEVICE_ADDRESS, '0', 0);
	}
	private void LED1() {
		Amarino.sendDataToArduino(this, DEVICE_ADDRESS, 'X', 0);
		Log.d(TAG, "A, LED1");
	}
	private void LED2() {
		Amarino.sendDataToArduino(this, DEVICE_ADDRESS, 'Y', 0);
		Log.d(TAG, "B, LED2");
	}
	private void LED3() {
		Amarino.sendDataToArduino(this, DEVICE_ADDRESS, 'Z', 0);
		Log.d(TAG, "C, LED3");
	}
	private void HDMI() {
		Amarino.sendDataToArduino(this, DEVICE_ADDRESS, 'F', 100);
		Log.d(TAG, "D, HDMI");
	}
	private void Recipe() {
		Amarino.sendDataToArduino(this, DEVICE_ADDRESS, 'F', 100);
		Amarino.sendDataToArduino(this, DEVICE_ADDRESS, 'H', 100);
		Amarino.sendDataToArduino(this, DEVICE_ADDRESS, 'G', 100);
		Log.d(TAG, "D, HDMI");
	}
	private void TV() {
		Amarino.sendDataToArduino(this, DEVICE_ADDRESS, 'F', 100);
		Amarino.sendDataToArduino(this, DEVICE_ADDRESS, 'I', 100);
		Amarino.sendDataToArduino(this, DEVICE_ADDRESS, 'G', 100);
		Log.d(TAG, "D, HDMI");
	}

}