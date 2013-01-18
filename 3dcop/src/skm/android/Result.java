package skm.android;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.TextView;
import gueei.binding.Binder;
import skm.android.ViewModle.ViewModles.Shared;
import skm.android.ViewModle.ViewModles.main.ResultModle;

/**
 * Created by IntelliJ IDEA.
 * User: Sean
 * Date: 19/11/11
 * Time: 18:49
 * To change this template use File | Settings | File Templates.
 */
public class Result extends ActivityBase {


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {


        ResultModle modle = ResultModle.getInstance(this.getApplicationContext(), getIntent());

        Binder.init(this.getApplication());
        Binder.setAndBindContentView(this,R.layout.amts_result, modle);
        initText();
        super.onCreate(savedInstanceState);

    }


    @Override
    public void initText() {
        TextView t =(TextView)findViewById(R.id.amtsMessage);
        String currentSize = Shared.getOptionAtribute(getString(R.string.FontSize), getString(R.string.current), getApplication());
        t.setTextSize(TypedValue.COMPLEX_UNIT_SP, new Float(Shared.getOption(getString(R.string.FontSize) +"/"+ currentSize,getApplicationContext()).getTextContent()));
        String textColour = Shared.getOptionAtribute(getString(R.string.Colours), getString(R.string.text), getApplication());
        t.setTextColor(Color.parseColor(Shared.getOption(getString(R.string.Colours) + "/" + textColour, getApplicationContext()).getTextContent()));
        String font = Shared.getOptionAtribute(getString(R.string.Font), getString(R.string.current), getApplication());
        Typeface face=Typeface.createFromAsset(getAssets(), Shared.getOption(getString(R.string.Font)+"/"+font,getApplication()).getTextContent());
        t.setTypeface(face);
    }
}
