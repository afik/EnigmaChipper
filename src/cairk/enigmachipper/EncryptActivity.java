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

public class EncryptActivity extends ActionBarActivity {
	public final static String EXTRA_MESSAGE = "cairk.enigmachipper.MESSAGE";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_encrypt);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.encrypt, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_encrypt,
					container, false);
			return rootView;
		}
	}
	
	public void EncryptMessage(View view) {
		Intent intent = new Intent(this, ShowCode.class);
		String Inner    ="RYELSZFMT#GNUAHOVBIPWCJQXDK";
		String Middle  = "#EJOTYCHMRWAFKPUZDINSXBGLQV";
		String Outside = "#BDFHJLNPRTVXZACEGIKMOQSUWY";
		
		EditText editText = (EditText) findViewById(R.id.edit_message);
		String Input = editText.getText().toString() + ".";
		
		//Melakukan enkripsi
		Enigma myEnigmaEnc = new Enigma(Inner,Middle,Outside,Input);

		String code = myEnigmaEnc.Encrypt();
    	intent.putExtra(EXTRA_MESSAGE, code);
    	System.out.println(code);
    	System.out.println("kjdnakfn");
    	startActivity(intent);
	}

}
