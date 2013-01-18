package skm.android.ViewModle.ViewModles.main;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import gueei.binding.Command;
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

    public int ballcount=0;
    public final Command smallText = new Command(){
        @Override
	    public void Invoke(View arg0, Object... arg1) {
            Shared.setOptionAtribute(getString(R.string.FontSize), getString(R.string.current), getString(R.string.small), getApplicationContext());
            Options.getCurrentInstance().initAll(true);
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

    private void initStatics() {
        
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
