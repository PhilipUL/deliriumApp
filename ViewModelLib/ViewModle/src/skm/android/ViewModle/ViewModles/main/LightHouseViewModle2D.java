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
import skm.android.ViewModle.ViewModles.nudesc.NudescManager;


/**
 * Created by IntelliJ IDEA.
 * User: Sean
 * Date: 24/10/11
 * Time: 21:10
 * To change this template use File | Settings | File Templates.
 */
public class LightHouseViewModle2D extends YesNoViewModleBase {

    private Drawable imageSource;
    private Toast toast;
    int selected;
    public static boolean useSequence = false;
    private static LightHouseViewModle2D instance=null;
    protected LightHouseViewModle2D(Context c){
        super(c);
        instance=this;
        init();

    }

    public static LightHouseViewModle2D getInstance(Context c, Intent intent){
        instance =instance!=null?instance:(instance=new LightHouseViewModle2D(c));
        if(intent!=null&&intent.hasExtra(Shared.MAIN_MENU_LAUNCHED)){
            instance.mainMenuLaunched = intent.getExtras().getBoolean(Shared.MAIN_MENU_LAUNCHED);
            instance.launcher=intent;
        }
        if(intent!=null&&intent.hasExtra(Shared.LIGHTHOUSE_SEQUENCE)){
            useSequence = intent.getExtras().getBoolean(Shared.LIGHTHOUSE_SEQUENCE);
//            LightHouseViewViewModle.getInstance(c,null).setUseSequence(useSequence);

        }
        return instance;
    }
   public void clear(){

       instance=null;

    }


//    public DependentObservable<Drawable>  image = new DependentObservable<Drawable>(Drawable.class) {
//        @Override
//        public Drawable calculateValue(Object... objects) throws Exception {
//            return imageSource;  //To change body of implemented methods use File | Settings | File Templates.
//        }
//    };

    public final Command update = new Command(){
        @Override
	    public void Invoke(View arg0, Object... arg1) {

        }
    };
    private void advance() {

        Lighthouse.getCurrentInstance().finish();
        if(toast!=null)toast.show();
        Activity caller = this.mainMenuLaunched ? MainMenu.getCurrentInstance():  Menu.getCurrentInstance();
        Intent intent =new Intent(caller, this.mainMenuLaunched ?Nudesc.class:Result.class);
        intent.putExtra(Shared.MAIN_MENU_LAUNCHED, this.mainMenuLaunched);
        NudescManager.getInstance(getApplicationContext(),null).clear();
        ResultModle.getInstance(getApplicationContext(),null).clear();
        String[] list= new String[10];
        list[0]=getString(R.string.Count) + LightHouseViewViewModle.getInstance(getApplicationContext(),null).getFlashcount()
                +"\n"+ getString(R.string.SelectedValue) + selected;
        intent.putExtra(Shared.RESULT_MESSAGES, list);
        caller.startActivity(intent);
    }
    public Command yes;
    public Command no;
    private void init(){
//        LightHouseViewViewModle.getInstance(getApplicationContext(),null).setUseSequence(useSequence);
//        imageSource = getResources().getDrawable(R.drawable.lighthouse);
//        image.onPropertyChanged(null, new ArrayList<Object>());
          yes = no =new Command(){
              @Override
              public void Invoke(View view, Object... objects) {
                 advance();
              }
          };
    }
    public AdapterView.OnItemSelectedListener flashCounterListener = new AdapterView.OnItemSelectedListener(){
         boolean initFinished = false;
           public void onItemSelected(AdapterView<?> parent,View view, int pos, long id) {
               selected= Integer.parseInt((String)parent.getItemAtPosition(pos));
               result=new BooleanObservable(selected==LightHouseViewViewModle.getInstance(getApplicationContext(),view).getFlashcount());
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
