package skm.android.ViewModle.ViewModles;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import skm.android.R;

/**
 * Created by IntelliJ IDEA.
 * User: 0884588
 * Date: 07/03/12
 * Time: 16:56
 * To change this template use File | Settings | File Templates.
 */
public class SpinnerArrayAdapter<T> extends ArrayAdapter<T>
{
    String currentSize = Shared.getOptionAtribute(getContext().getString(R.string.FontSize), getContext().getString(R.string.current), getContext());
    String textColourString = Shared.getOptionAtribute(getContext().getString(R.string.Colours), getContext().getString(R.string.text), getContext());
    String acentColour = Shared.getOptionAtribute(getContext().getString(R.string.Colours), getContext().getString(R.string.acent), getContext());
    String font = Shared.getOptionAtribute(getContext().getString(R.string.Font), getContext().getString(R.string.current), getContext());
    Typeface face=Typeface.createFromAsset(getContext().getAssets(), Shared.getOption(getContext().getString(R.string.Font)+"/"+font,getContext()).getTextContent());
    String backgroundColour = Shared.getOptionAtribute(getContext().getString(R.string.Colours), getContext().getString(R.string.bg), getContext());
    float textSize = new Float(Shared.getOption(getContext().getString(R.string.FontSize) +"/"+ currentSize,getContext()).getTextContent());
    int textColour = Color.parseColor(Shared.getOption(getContext().getString(R.string.Colours) + "/" + acentColour,getContext()).getTextContent());

    public SpinnerArrayAdapter(Context context, T [] objects)
    {
        super(context, android.R.layout.simple_spinner_item, objects);
    }

    public void initAll(){
    currentSize = Shared.getOptionAtribute(getContext().getString(R.string.FontSize), getContext().getString(R.string.current), getContext());
    textColourString = Shared.getOptionAtribute(getContext().getString(R.string.Colours), getContext().getString(R.string.text), getContext());
    acentColour = Shared.getOptionAtribute(getContext().getString(R.string.Colours), getContext().getString(R.string.acent), getContext());
    font = Shared.getOptionAtribute(getContext().getString(R.string.Font), getContext().getString(R.string.current), getContext());
    face=Typeface.createFromAsset(getContext().getAssets(), Shared.getOption(getContext().getString(R.string.Font)+"/"+font,getContext()).getTextContent());
    backgroundColour = Shared.getOptionAtribute(getContext().getString(R.string.Colours), getContext().getString(R.string.bg), getContext());
    textSize = new Float(Shared.getOption(getContext().getString(R.string.FontSize) +"/"+ currentSize,getContext()).getTextContent());
    textColour = Color.parseColor(Shared.getOption(getContext().getString(R.string.Colours) + "/" + acentColour,getContext()).getTextContent());

    }
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent)
    {
        View view = super.getView(position, convertView, parent);
        TextView t = (TextView)view.findViewById(android.R.id.text1);
        t.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        t.setTextColor(textColour);
        t.setTypeface(face);
        return view;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view = super.getView(position, convertView, parent);
        TextView t = (TextView)view.findViewById(android.R.id.text1);
        t.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        t.setTextColor(textColour);
        t.setTypeface(face);
        return view;
    }
}
