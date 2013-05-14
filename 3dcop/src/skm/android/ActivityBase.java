package skm.android;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import skm.android.ViewModle.ViewModles.Shared;
import skm.android.ViewModle.ViewModles.SpinnerArrayAdapter;

import java.util.Vector;

public abstract class ActivityBase  extends Activity{
    static String currentSize=null;
    static String textColour=null;
    static String acentColour=null;
    static String font=null;
    static Typeface face= null;
    public ActivityBase() {
        super();

    }
    public abstract void initText();
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU&& this instanceof Options) {
            this.finish();
            return true;
        } else if(keyCode == KeyEvent.KEYCODE_MENU){
            this.startActivity(new Intent(this,Options.class));
            return true;
        }else return super.onKeyUp(keyCode, event);

    }
    @Override
    public void onResume(){
    super.onResume();
        initAll();
    }
    public void initAll(boolean statics){
        if(statics)initStatics();
        initAll();
    }
    public void initAll(){
        if(currentSize ==null||textColour ==null|| acentColour == null|| font == null|| face ==null) initStatics();
        initText();
        initBackground();
    }

    public void initBackground() {
        String backgroundColour = Shared.getOptionAtribute(getString(R.string.Colours), getString(R.string.bg), getApplication());
        this.getWindow().getDecorView().setBackgroundColor(Color.parseColor(Shared.getOption(getString(R.string.Colours) + "/" + backgroundColour, getApplicationContext()).getTextContent()));
    }
    public void initStatics(){
        currentSize = Shared.getOptionAtribute(getString(R.string.FontSize), getString(R.string.current), getApplication());
        textColour = Shared.getOptionAtribute(getString(R.string.Colours), getString(R.string.text), getApplication());
        acentColour = Shared.getOptionAtribute(getString(R.string.Colours), getString(R.string.acent), getApplication());
        String f = getString(R.string.Font);
        String c = getString(R.string.current);

        font = Shared.getOptionAtribute(getString(R.string.Font), getString(R.string.current), getApplication());
        face=Typeface.createFromAsset(getAssets(), Shared.getOption(getString(R.string.Font)+"/"+font,getApplication()).getTextContent());
    }
    protected void initText(Vector<View> elements) {


        if(currentSize ==null||textColour ==null|| acentColour == null|| font == null|| face ==null) initStatics();

        for(View e :elements){
            if(e instanceof Button){
                Button b = (Button)e;
                b.setTextSize(TypedValue.COMPLEX_UNIT_SP, new Float(Shared.getOption(getString(R.string.FontSize) + "/" + currentSize, getApplicationContext()).getTextContent()));
                b.setTextColor(Color.parseColor(Shared.getOption(getString(R.string.Colours) + "/" + acentColour, getApplicationContext()).getTextContent()));
                b.setTypeface(face);
            }else if(e instanceof TextView){
                TextView t = (TextView)e;
                t.setTextSize(TypedValue.COMPLEX_UNIT_SP, new Float(Shared.getOption(getString(R.string.FontSize) +"/"+ currentSize,getApplicationContext()).getTextContent()));
                t.setTextColor(Color.parseColor(Shared.getOption(getString(R.string.Colours) + "/" + textColour, getApplicationContext()).getTextContent()));
                t.setTypeface(face);
            }else if(e instanceof Spinner){
                Spinner s = (Spinner)e;
                SpinnerAdapter spingAdapt =s.getAdapter();
                if(spingAdapt instanceof SpinnerArrayAdapter) ((SpinnerArrayAdapter)spingAdapt).initAll();

//                //s.setBackgroundColor();

            }
        }
    }
}