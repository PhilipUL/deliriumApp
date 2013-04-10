package skm.android.ViewModle.ViewModles.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import gueei.binding.Command;
import gueei.binding.observables.BooleanObservable;
import skm.android.*;
import skm.android.ViewModle.ViewModles.Shared;
import skm.android.ViewModle.ViewModles.base.YesNoViewModleBase;


/**
 * Created by IntelliJ IDEA.
 * User: Sean
 * Date: 24/10/11
 * Time: 21:10
 * To change this template use File | Settings | File Templates.
 */
public class BallViewModle2D extends YesNoViewModleBase {

    private Drawable imageSource;
    private Toast toast;
    private static BallViewModle2D instance=null;
    int selected;
    protected BallViewModle2D(Context c){
        super(c);
        instance=this;
//        BallViewViewModle.getInstance(c,null).clear();
        init();

    }
    public static BallViewModle2D getInstance(Context c, Intent intent){
        instance =instance!=null?instance:(instance=new BallViewModle2D(c));
        if(intent!=null&&intent.hasExtra(Shared.MAIN_MENU_LAUNCHED)){
            instance.mainMenuLaunched = intent.getExtras().getBoolean(Shared.MAIN_MENU_LAUNCHED);
            instance.launcher=intent;
        }
        return instance;
    }
   public void clear(){

       instance=null;

    }



    public final Command update = new Command(){
        @Override
	    public void Invoke(View arg0, Object... arg1) {

        }
    };
    private void advance() {

        Ball.getCurrentInstance().finish();
        if(toast!=null)toast.show();
        Activity caller = this.mainMenuLaunched ? MainMenu.getCurrentInstance():  Menu.getCurrentInstance();
        Intent intent =new Intent(caller, this.mainMenuLaunched ?Result.class:Result.class);
        intent.putExtra(Shared.MAIN_MENU_LAUNCHED, this.mainMenuLaunched);
        ResultModle.getInstance(getApplicationContext(),null).clear();
        String[] list= new String[10];
        list[0]=getString(R.string.Count) + BallViewViewModle.getInstance(getApplicationContext(),null).getBounceCount()
                +"\n"+ getString(R.string.SelectedValue) + selected;
        intent.putExtra(Shared.RESULT_MESSAGES, list);
        caller.startActivity(intent);
    }

    public Command yes;
    public Command no;
    private void init(){
          yes = no =new Command(){
              @Override
              public void Invoke(View view, Object... objects) {
                 advance();
              }
          };
    }
    public AdapterView.OnItemSelectedListener bounceCounterListener = new AdapterView.OnItemSelectedListener(){
         boolean initFinished = false;
           public void onItemSelected(AdapterView<?> parent,View view, int pos, long id) {
               selected= Integer.parseInt((String)parent.getItemAtPosition(pos));
               result=new BooleanObservable(selected==BallViewViewModle.getInstance(getApplicationContext(),view).getBounceCount());
               if(initFinished){
                   toast =Toast.makeText(getApplicationContext(), result.get() ? getString(R.string.ValueCorrect) : getString(R.string.ValueIncorrect), Toast.LENGTH_LONG);
                   toast.setGravity(Gravity.CENTER,0,0);
                   toast.show();
               }
               else initFinished=true;
           }

           public void onNothingSelected(AdapterView parent) {

           }
       };

}
