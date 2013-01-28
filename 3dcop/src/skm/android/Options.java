package skm.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import gueei.binding.Binder;
import skm.android.ViewModle.ViewModles.Shared;
import skm.android.ViewModle.ViewModles.SpinnerArrayAdapter;
import skm.android.ViewModle.ViewModles.main.BallViewViewModle;
import skm.android.ViewModle.ViewModles.main.OptionsViewModle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class Options extends ActivityBase
{
    private static Options options;
    private OptionsViewModle modle;

    public static final String PREF_NAME = "CONFIG_TEST";
    public static final int MODE = Context.MODE_PRIVATE;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Options.options =this;
        modle= OptionsViewModle.getInstance(this);
        Binder.init(this.getApplication());
        Binder.setAndBindContentView(this,R.layout.options,modle);
        initSpinners();

        SharedPreferences pref = getApplicationContext().getSharedPreferences("PREF_NAME", Context.MODE_PRIVATE);

        //SharedPreferences pref = context.getSharedPreferences("PREF_NAME", Context.MODE_PRIVATE);
    }




    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();
        if(checked == true)
        {
            Shared.setOptionAtribute(getString(R.string.distractBalls), getString(R.string.distract), "true", this);
        }else{
            Shared.setOptionAtribute(getString(R.string.distractBalls), getString(R.string.distract), "false", this);
        }

        checkDistractionSliderState();

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioRandomize:
                if (checked)
                    ((RadioButton) findViewById(R.id.radioRandomize)).setChecked(true);
                    ((RadioButton) findViewById(R.id.radioNonRandomize)).setChecked(false);
                    Shared.setOptionAtribute(getString(R.string.BallRadio), getString(R.string.checked), "true" , this);
                    checkDistractionSliderState();
                    // Pirates are the best
                    break;
            case R.id.radioNonRandomize:
                if (checked)
                    ((RadioButton) findViewById(R.id.radioRandomize)).setChecked(false);
                    ((RadioButton) findViewById(R.id.radioNonRandomize)).setChecked(true);
                    Shared.setOptionAtribute(getString(R.string.BallRadio), getString(R.string.checked), "false" , this);
                    checkDistractionSliderState();
                    // Ninjas rule
                    break;
        }
    }

//    public void onRadioBoxClickedRandomize(View view)
//    {
//        Shared.setOptionAtribute(getString(R.string.BallRadio), getString(R.string.checked), "true" , this);
//        if(!((RadioButton)view).isChecked())// if the randomize radio is not checked, then check it, using toggle
//        {
//            ((RadioButton) findViewById(R.id.radioRandomize)).toggle();
//        }
//        if(((RadioButton)findViewById(R.id.radioNonRandomize)).isChecked())
//        {
//            ((RadioButton) findViewById(R.id.radioNonRandomize)).toggle();
//        }
//
//    }
//    public void onRadioBoxClickedNonRandomize(View view)
//    {
//        Shared.setOptionAtribute(getString(R.string.BallRadio), getString(R.string.checked), "false" , this);
//        if(!((RadioButton)view).isChecked())// if the non-randomize radio is not checked, then check it, using toggle
//        {
//            ((RadioButton) findViewById(R.id.radioNonRandomize)).toggle();
//        }
//        if(((RadioButton)findViewById(R.id.radioRandomize)).isChecked())// if the randomize radio is not checked, then check it, using toggle
//        {
//            ((RadioButton) findViewById(R.id.radioRandomize)).toggle();
//        }
//    }

    // To read from sharedPreferences, we need the SharedPreferences object
    // to edit variables in sharedPreferences, we need to create an editor for it, using getEditor

    // gets the sharedPreferences object, is used by getEditor
    public static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREF_NAME, MODE);
    }

    // getEditor gets the editor needed to store variables in shared preferences
    public static SharedPreferences.Editor getEditor(Context context) {
        return getPreferences(context).edit();
    }

    public void initCheckBoxes()
    {

        final CheckBox checkBox = (CheckBox)findViewById(R.id.checkbox_DistractBalls);
        checkBox.setChecked(Boolean.parseBoolean(Shared.getOptionAtribute(getString(R.string.distractBalls), getString(R.string.distract), this)));

        SeekBar distractionBarMaxSpeed = (SeekBar) findViewById(R.id.seekbarDistractionBallSpeed);
        SeekBar distractionBarMinSpeed = (SeekBar) findViewById(R.id.seekbarDistractionMaxSpeed);
        SeekBar distractionBarSpeed = (SeekBar) findViewById(R.id.seekDistractionMinSpeed);
        distractionBarMaxSpeed.setEnabled(false);
        distractionBarMinSpeed.setEnabled(false);
        distractionBarSpeed.setEnabled(false);

        checkDistractionSliderState();

    }

    public void checkDistractionSliderState()
    {

        SeekBar distractionBarMaxSpeed = (SeekBar) findViewById(R.id.seekbarDistractionMaxSpeed);
        SeekBar distractionBarMinSpeed = (SeekBar) findViewById(R.id.seekDistractionMinSpeed);
        SeekBar distractionBarSpeed = (SeekBar) findViewById(R.id.seekbarDistractionBallSpeed);
        SeekBar TargetBarMaxSpeed = (SeekBar) findViewById(R.id.seekbarTargetMaxSpeed);
        SeekBar TargetBarMinSpeed = (SeekBar) findViewById(R.id.seekTargetMinSpeed);
        SeekBar barSpeed = (SeekBar) findViewById(R.id.seekbarTargetBallSpeed);

        if(((RadioButton) findViewById(R.id.radioRandomize)).isChecked() && ((CheckBox)findViewById(R.id.checkbox_DistractBalls)).isChecked())  // if random and distract balls are set to true
        {
            distractionBarMaxSpeed.setEnabled(true);
            distractionBarMinSpeed.setEnabled(true);
            TargetBarMaxSpeed.setEnabled(true);
            TargetBarMinSpeed.setEnabled(true);

            distractionBarSpeed.setEnabled(false);
            barSpeed.setEnabled(false);
        }else if(((RadioButton) findViewById(R.id.radioRandomize)).isChecked() == false)
        {
            barSpeed.setEnabled(true);
            TargetBarMaxSpeed.setEnabled(false);
            TargetBarMinSpeed.setEnabled(false);

            distractionBarMaxSpeed.setEnabled(false);
            distractionBarMinSpeed.setEnabled(false);

            if(((CheckBox)findViewById(R.id.checkbox_DistractBalls)).isChecked())
            {
                distractionBarSpeed.setEnabled(true);
            } else {
                distractionBarSpeed.setEnabled(false);
            }
        } else if(((RadioButton) findViewById(R.id.radioRandomize)).isChecked() == true)
        {
            barSpeed.setEnabled(false);
            TargetBarMaxSpeed.setEnabled(true);
            TargetBarMinSpeed.setEnabled(true);

            distractionBarSpeed.setEnabled(false);
            if(((CheckBox)findViewById(R.id.checkbox_DistractBalls)).isChecked())
            {
                distractionBarMaxSpeed.setEnabled(true);
                distractionBarMinSpeed.setEnabled(true);
            } else {
                distractionBarMaxSpeed.setEnabled(false);
                distractionBarMinSpeed.setEnabled(false);
            }
        }
        //BallViewViewModle.getInstance(getApplicationContext(), null).setPreviewMode();
    }

    private void initSpinners() {
        Spinner spinner = (Spinner) findViewById(R.id.textColourChoser);
        List<String> temp = modle.getColours();
        String colour = getString(R.string.Colours);
        bindSpinner(spinner, temp, getString(R.string.text),colour ,modle.TextColourListener, this);
        spinner = (Spinner) findViewById(R.id.backgroundColourChoser);
        bindSpinner(spinner, temp, getString(R.string.bg), colour,modle.backgroundColourListener, this);
        spinner = (Spinner) findViewById(R.id.AcentColourChoser);
        bindSpinner(spinner, temp, getString(R.string.acent), colour,modle.acentColourListener, this);
        spinner = (Spinner) findViewById(R.id.fontChoser);

        List<String> temp2 = new ArrayList<String>();
        temp2.add("0");
        temp2.add("1");
        temp2.add("2");
        temp2.add("3");
        temp2.add("4");
        temp2.add("5");
        temp2.add("6");
        temp2.add("7");
        temp2.add("8");
        temp2.add("9");
        temp2.add("10");

        Spinner intSpinner = (Spinner) findViewById(R.id.BallCountChoser);
        bindSpinner(intSpinner, temp2, getString(R.string.ballcount),getString(R.string.balltestkey), modle.ballCountListener, this );

        Spinner otherSpinner = (Spinner) findViewById(R.id.LighthouseFlashCountChoser);
        //intSpinner = (Spinner) findViewById(R.id.LighthouseFlashCountChoser);
        bindSpinner(otherSpinner, temp2, getString(R.string.flashcount), getString(R.string.lighthouseflashkey), modle.lighthouseFlashCountListener, this);
        //bindSpinner(otherSpinner, temp2, getString(R.string.ballcount),getString(R.string.balltestkey), modle.ballCountListener, this );

        temp = modle.getFonts();
        bindSpinner(spinner, temp, getString(R.string.current), getString(R.string.Font),modle.fontListener,this);
    }

    private void bindSpinner(Spinner spinner, List<String> temp, String attribute, String key,AdapterView.OnItemSelectedListener modle, Context applicationContext) {
        String[] strings;
        SpinnerArrayAdapter<String> adapter;
        strings = new String[temp.size()];
        Collections.sort(temp);
        for(int i=0; i<temp.size();i++){
        strings[i] = temp.get(i);
        }
        adapter = new SpinnerArrayAdapter<String>(this,strings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(adapter.getPosition(Shared.getOptionAtribute(key, attribute, applicationContext)));
        spinner.setOnItemSelectedListener(modle);

    }

    public static Options getCurrentInstance(){
       return options;
        //about==null?(about=new Menu()):about;
    }

    @Override
    public void initAll(){
        super.initAll();
        initCheckBoxes();
        initSliders();
        this.getWindow().getDecorView().invalidate();

        BallViewViewModle.getInstance(getApplicationContext(), null).setPreviewMode();

    }

    public void initSliders()
    {
        SeekBar bar = (SeekBar) findViewById(R.id.seekbarTargetMaxSpeed);
        bar.setProgress(Integer.decode(Shared.getOptionAtribute(getString(R.string.TargetSpeed), getString(R.string.TargetMaxSpeed), this))); // get the string back from the xml file, and convert it to an int

        SeekBar barTarMin = (SeekBar) findViewById(R.id.seekTargetMinSpeed);
        barTarMin.setProgress(Integer.decode(Shared.getOptionAtribute(getString(R.string.TargetSpeed), getString(R.string.TargetMinSpeed), this)));

        SeekBar barTarSpeed = (SeekBar) findViewById(R.id.seekbarTargetBallSpeed);
        barTarSpeed.setProgress(Integer.decode(Shared.getOptionAtribute(getString(R.string.TargetSpeed), getString(R.string.TargetSpeedBall), this)));




        SeekBar distractionBarMax = (SeekBar) findViewById(R.id.seekbarDistractionMaxSpeed);
        distractionBarMax.setProgress(Integer.decode(Shared.getOptionAtribute(getString(R.string.DistractionTargetSpeed), getString(R.string.MaxSpeed), this))); // get the string back from the xml file, and convert it to an int

        SeekBar distractionBarMin = (SeekBar) findViewById(R.id.seekDistractionMinSpeed);
        distractionBarMin.setProgress(Integer.decode(Shared.getOptionAtribute(getString(R.string.DistractionTargetSpeed), getString(R.string.MinSpeed), this)));

        SeekBar distractionBarSpeed = (SeekBar) findViewById(R.id.seekbarDistractionBallSpeed);
        distractionBarSpeed.setProgress(Integer.decode(Shared.getOptionAtribute(getString(R.string.DistractionTargetSpeed), getString(R.string.Speed), this)));

    }


   @Override
    public void initText() {
       Vector<View> elements = new Vector<View>();
       elements.add(findViewById(R.id.smallButton));
       elements.add(findViewById(R.id.mediumButton));
       elements.add(findViewById(R.id.largeButton));
       elements.add(findViewById(R.id.TextSize));
       elements.add(findViewById(R.id.textColourTextView));
       elements.add(findViewById(R.id.backgroundColourText));
       elements.add(findViewById(R.id.AcentColourText));
       elements.add(findViewById(R.id.FontText));
       elements.add(findViewById(R.id.Reset));
       elements.add(findViewById(R.id.backgroundColourChoser));
       elements.add(findViewById(R.id.AcentColourChoser));
       elements.add(findViewById(R.id.fontChoser));
       elements.add(findViewById(R.id.textColourChoser));
       initText(elements);


//       TextView targetMaxSpeed = (TextView) findViewById(R.id.FontTextTargetMaxSpeed);
//       targetMaxSpeed.setText("Target Ball Max Speed: "+Shared.getOptionAtribute(getString(R.string.TargetSpeed), getString(R.string.TargetMaxSpeed), this));
    }


}
