package skm.android;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import gueei.binding.Binder;
import skm.android.ViewModle.ViewModles.SpinnerArrayAdapter;
import skm.android.ViewModle.ViewModles.main.LightHouseViewModle2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: Sean
 * Date: 18/11/11
 * Time: 21:00
 * To change this template use File | Settings | File Templates.
 */

public class Lighthouse extends ActivityBase {

    private static Lighthouse lighthouse;

    public static Lighthouse getCurrentInstance(){
       return lighthouse;
        //menu==null?(menu=new Menu()):menu;
    }
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        lighthouse =this;
        LightHouseViewModle2D modle = LightHouseViewModle2D.getInstance(this.getApplicationContext(), getIntent());
        //LightHouseViewModleSequence modle = LightHouseViewModleSequence.getInstance(this.getApplicationContext(), getIntent());
        Binder.init(this.getApplication());
        Binder.setAndBindContentView(this,R.layout.light_house_test, modle);
        Spinner spinner = (Spinner) findViewById(R.id.flashCount);
        List<String> temp = new ArrayList<String>();
        for(int i=1; i<15; i++)temp.add(""+i);
        bindSpinner(spinner, temp, modle.flashCounterListener, getApplicationContext());

    }

    private void bindSpinner(Spinner spinner, List<String> temp, AdapterView.OnItemSelectedListener modle, Context applicationContext) {
        String[] strings;SpinnerArrayAdapter<String> adapter;
        strings = new String[temp.size()];
        for(int i=0; i<temp.size();i++){
        strings[i] = temp.get(i);
        }
        adapter = new SpinnerArrayAdapter<String>(this,strings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
//        spinner.setSelection(adapter.getPosition(Shared.getOptionAtribute(key, attribute, applicationContext)));
        spinner.setOnItemSelectedListener(modle);
    }


    @Override
    public void initText() {
        Vector<View> elements = new Vector<View>();
        elements.add(findViewById(R.id.submitButton));
        elements.add(findViewById(R.id.flashCount));

        initText(elements);
    }
}
