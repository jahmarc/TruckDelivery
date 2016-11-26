package Fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import java.util.Calendar;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.marc.truckdelivery.R;

/**
 * Created by Marc on 23/11/2016.
 */

public class DatePickerFragment extends DialogFragment{

//} implements DatePickerDialog.OnDateSetListener{



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(),(DatePickerDialog.OnDateSetListener)getActivity(), year, month, day);
    }
}
