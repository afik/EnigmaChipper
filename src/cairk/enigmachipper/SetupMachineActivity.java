package cairk.enigmachipper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class SetupMachineActivity extends ActionBarActivity {
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup_machine);
		
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.setup_machine, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_setup_machine,
					container, false);
			return rootView;
		}
	}
	
	public void SetMachine (View view) {
		Intent intent = new Intent(this, MainActivity.class);
        Enigma myEnigmaEnc = (Enigma)getApplicationContext();
        String Inner = new String();
    	String Middle = new String();
    	String Outer = new String();
    	
		EditText editInner = (EditText) findViewById(R.id.edit_inner);
		EditText editMiddle = (EditText) findViewById(R.id.edit_middle);
		EditText editOuter = (EditText) findViewById(R.id.edit_outer);
		Inner = editInner.getText().toString();
		Middle = editMiddle.getText().toString();
		Outer = editOuter.getText().toString();
		myEnigmaEnc.setPlate(Inner, Middle, Outer);
		startActivity(intent);
	}

}
