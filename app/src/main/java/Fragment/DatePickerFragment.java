package Fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import java.util.Calendar;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.marc.truckdelivery.R;

/**
 * Created by Marc on 23/11/2016.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        Button bt1 = (Button) getActivity().findViewById(R.id.buttonDate);
        bt1.setText("Year: " + view.getYear() + " Month: " + view.getMonth() + " Day: " + view.getDayOfMonth());
    }
}
