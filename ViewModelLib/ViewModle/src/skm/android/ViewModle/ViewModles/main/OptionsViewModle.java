package skm.android.ViewModle.ViewModles.main;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SeekBar;
import gueei.binding.Command;
import gueei.binding.observables.FloatObservable;
import gueei.binding.observables.StringObservable;
import skm.android.Options;
import skm.android.R;
import skm.android.ViewModle.ViewModles.Shared;
import skm.android.ViewModle.ViewModles.base.ViewModleBase;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Sean
 * Date: 15/01/12
 * Time: 03:37
 * To change this template use File | Settings | File Templates.
 */
public class OptionsViewModle extends ViewModleBase {



    protected OptionsViewModle(Context context) {
        super(context);
        initStatics();
    }


    private static OptionsViewModle instance;
    public static OptionsViewModle getInstance(Context c){
        return instance!=null?instance:(instance=new OptionsViewModle(c));
    }
    public static void clear(){
              instance=null;
        }

    static private List<String> Colours;
    static private List<String> Fonts;
    static private List<String> intOptions;

    public List<String> getColours(){return Colours;}
    public List<String> getFonts(){return Fonts;}
    public List<String> getIntOptions(){return intOptions;}
    public FloatObservable seekBar1 = new FloatObservable(0f);
    public FloatObservable seekBar2 = new FloatObservable(0f);
    public FloatObservable SeekBarTargetSpeed = new FloatObservable(0f);

    public FloatObservable SeekBarDistractionSpeed = new FloatObservable(0f);
    public FloatObservable SeekBarMinDistractionSpeed = new FloatObservable(0f);
    public FloatObservable SeekBarMaxDistractionSpeed = new FloatObservable(0f);

    public FloatObservable SeekBarFlashRotateSpeedNumber = new FloatObservable(0f);
    public FloatObservable SeekBarFlashSpeed = new FloatObservable(0f);
    public FloatObservable SeekBarFlashSizeProgress = new FloatObservable(0f);
    public StringObservable lightHouseSequence = new StringObservable("");



    public int ballcount=0;
    public final Command smallText = new Command(){
        @Override
	    public void Invoke(View arg0, Object... arg1) {
            Shared.setOptionAtribute(getString(R.string.FontSize), getString(R.string.current), getString(R.string.small), getApplicationContext());
            Options.getCurrentInstance().initAll(true);
        }
    };

    public final Command SliderMovedFlashSize = new Command(){
        @Override
        public void Invoke(View arg0, Object... arg1) {
            SeekBar bar = (SeekBar)  arg0;
            Shared.setOptionAtribute(getString(R.string.lighthouseflashkey), getString(R.string.flashSize), String.valueOf(bar.getProgress()), getApplicationContext());
        }
    };

    public final Command SliderMovedFlashSpeed = new Command(){
        @Override
        public void Invoke(View arg0, Object... arg1) {
            SeekBar bar = (SeekBar)  arg0;
            Shared.setOptionAtribute(getString(R.string.lighthouseflashkey), getString(R.string.headonSpeed), String.valueOf(bar.getProgress()), getApplicationContext());
        }
    };

    public final Command SliderMovedRotateSpeed = new Command(){
        @Override
        public void Invoke(View arg0, Object... arg1) {
            SeekBar bar = (SeekBar)  arg0;
            Shared.setOptionAtribute(getString(R.string.lighthouseflashkey), getString(R.string.rotateSpeed), String.valueOf(bar.getProgress()), getApplicationContext());
        }
    };

    public final Command SliderMovedMaxSpeed = new Command(){
        @Override
        public void Invoke(View arg0, Object... arg1) {
            SeekBar bar = (SeekBar)  arg0;
            Shared.setOptionAtribute(getString(R.string.TargetSpeed), getString(R.string.TargetMaxSpeed), String.valueOf(bar.getProgress()), getApplicationContext());

        }
    };

    public final Command SliderMovedMinSpeed = new Command(){
        @Override
        public void Invoke(View arg0, Object... arg1) {
            SeekBar bar = (SeekBar)  arg0;
            Shared.setOptionAtribute(getString(R.string.TargetSpeed), getString(R.string.TargetMinSpeed), String.valueOf(bar.getProgress()), getApplicationContext());
        }
    };


    public final Command SliderMovedTargetBallSpeed = new Command(){
        @Override
        public void Invoke(View arg0, Object... arg1) {
            SeekBar bar = (SeekBar)  arg0;
            Shared.setOptionAtribute(getString(R.string.TargetSpeed), getString(R.string.TargetSpeedBall), String.valueOf(bar.getProgress()), getApplicationContext());

        }
    };

    public final Command SliderMovedDistractMaxSpeed = new Command(){
        @Override
        public void Invoke(View arg0, Object... arg1) {
            SeekBar bar = (SeekBar)  arg0;
            Shared.setOptionAtribute(getString(R.string.DistractionTargetSpeed), getString(R.string.MaxSpeed), String.valueOf(bar.getProgress()), getApplicationContext());
        }
    };


    public final Command SliderMovedDistractMinSpeed = new Command(){
        @Override
        public void Invoke(View arg0, Object... arg1) {
            SeekBar bar = (SeekBar)  arg0;
            Shared.setOptionAtribute(getString(R.string.DistractionTargetSpeed), getString(R.string.MinSpeed), String.valueOf(bar.getProgress()), getApplicationContext());

        }
    };

    public final Command SliderMovedDistractBallSpeed = new Command(){
        @Override
        public void Invoke(View arg0, Object... arg1) {
            SeekBar bar = (SeekBar)  arg0;
            Shared.setOptionAtribute(getString(R.string.DistractionTargetSpeed), getString(R.string.Speed), String.valueOf(bar.getProgress()), getApplicationContext());

        }
    };

    public final Command standardText = new Command(){
        @Override
	    public void Invoke(View arg0, Object... arg1) {
            Shared.setOptionAtribute(getString(R.string.FontSize), getString(R.string.current), getString(R.string.standard), getApplicationContext());
            Options.getCurrentInstance().initAll(true);
        }
    };

    public final Command largeText = new Command(){
        @Override
	    public void Invoke(View arg0, Object... arg1) {
            Shared.setOptionAtribute(getString(R.string.FontSize), getString(R.string.current), getString(R.string.large), getApplicationContext());
            Options.getCurrentInstance().initAll(true);

        }
    };

    public final Command resetOptions = new Command(){
        @Override
	    public void Invoke(View arg0, Object... arg1) {
            Shared.resetOptions(getApplicationContext());
            initStatics();
            Options.getCurrentInstance().initAll(true);

        }
    };


    public final TextWatcher lightHouseSequenceWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        public void onTextChanged(CharSequence s, int i, int i1, int i2) {
            try{
                Shared.setOptionAtribute(getString(R.string.lighthouseflashkey), getString(R.string.sequence), s.toString(), getApplicationContext());
            } catch (NullPointerException e)
            {
                System.out.println(e);
            }
        }

        public void afterTextChanged(Editable editable) {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    } ;
//
    private void initStatics() {



         lightHouseSequence.set(Shared.getOptionAtribute(getString(R.string.lighthouseflashkey),getString(R.string.sequence),getApplicationContext()));
        Colours = Shared.getOptionStates(getString(R.string.Colours), getApplicationContext());
        Fonts= Shared.getOptionStates(getString(R.string.Font), getApplicationContext());

        //intOptions = Shared.getOptionStates(getString(R.string.balltestkey),getString(R.string.ballcount), this);

        ballcount= Integer.parseInt(Shared.getOptionAtribute(getString(R.string.balltestkey),getString(R.string.ballcount),this));

    }
    public AdapterView.OnItemSelectedListener TextColourListener = new AdapterView.OnItemSelectedListener(){
        public void onItemSelected(AdapterView<?> parent,View view, int pos, long id) {
            Shared.setOptionAtribute(getString(R.string.Colours),getString(R.string.text),(String)parent.getItemAtPosition(pos),getApplicationContext());
            Options.getCurrentInstance().initAll(true);
        }

        public void onNothingSelected(AdapterView parent) {

        }
    };

    public AdapterView.OnItemSelectedListener backgroundColourListener = new AdapterView.OnItemSelectedListener(){
        public void onItemSelected(AdapterView<?> parent,View view, int pos, long id) {
            Shared.setOptionAtribute(getString(R.string.Colours),getString(R.string.bg),(String)parent.getItemAtPosition(pos),getApplicationContext());
            Options.getCurrentInstance().initAll(true);
        }

        public void onNothingSelected(AdapterView parent) {

        }
    };
     public AdapterView.OnItemSelectedListener acentColourListener= new AdapterView.OnItemSelectedListener(){
        public void onItemSelected(AdapterView<?> parent,View view, int pos, long id) {
            Shared.setOptionAtribute(getString(R.string.Colours),getString(R.string.acent),(String)parent.getItemAtPosition(pos),getApplicationContext());
            Options.getCurrentInstance().initAll(true);
        }

        public void onNothingSelected(AdapterView parent) {

        }
    };
     public AdapterView.OnItemSelectedListener fontListener = new AdapterView.OnItemSelectedListener(){
        public void onItemSelected(AdapterView<?> parent,View view, int pos, long id) {
            Shared.setOptionAtribute(getString(R.string.Font),getString(R.string.current),(String)parent.getItemAtPosition(pos),getApplicationContext());
            Options.getCurrentInstance().initAll(true);
        }

        public void onNothingSelected(AdapterView parent) {

        }
    };

    public AdapterView.OnItemSelectedListener ballCountListener = new AdapterView.OnItemSelectedListener(){
        public void onItemSelected(AdapterView<?> parent,View view, int pos, long id) {
            Shared.setOptionAtribute(getString(R.string.balltestkey),getString(R.string.ballcount),(String)parent.getItemAtPosition(pos),getApplicationContext());
            Options.getCurrentInstance().initAll(true);
        }

        public void onNothingSelected(AdapterView parent) {

        }
    };

    public AdapterView.OnItemSelectedListener lighthouseFlashCountListener = new AdapterView.OnItemSelectedListener(){
        public void onItemSelected(AdapterView<?> parent,View view, int pos, long id) {
            Shared.setOptionAtribute(getString(R.string.lighthouseflashkey),getString(R.string.flashcount),(String)parent.getItemAtPosition(pos),getApplicationContext());
            Options.getCurrentInstance().initAll(true);
        }

        public void onNothingSelected(AdapterView parent) {

        }
    };

    public AdapterView.OnItemSelectedListener distractorBallsListener = new AdapterView.OnItemSelectedListener(){
        public void onItemSelected(AdapterView<?> parent,View view, int pos, long id) {
            Shared.setOptionAtribute(getString(R.string.distractBalls),getString(R.string.distract),(String)parent.getItemAtPosition(pos),getApplicationContext());
            Options.getCurrentInstance().initAll(true);
        }

        public void onNothingSelected(AdapterView parent) {

        }
    };

}
