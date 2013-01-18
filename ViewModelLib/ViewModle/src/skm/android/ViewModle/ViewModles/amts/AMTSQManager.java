package skm.android.ViewModle.ViewModles.amts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import skm.android.*;
import skm.android.ViewModle.ViewModles.Shared;
import skm.android.ViewModle.ViewModles.base.ViewModelManagerBase;
import skm.android.ViewModle.ViewModles.base.ViewModleQuestion;
import skm.android.ViewModle.ViewModles.main.ResultModle;

/**
 * Created by IntelliJ IDEA.
 * User: Sean
 * Date: 04/11/11
 * Time: 07:09
 * To change this template use File | Settings | File Templates.
 */
public class AMTSQManager extends ViewModelManagerBase {
    protected AMTSQManager(Context c){super(c);initQuestions(getString(R.string.amtsPath));init();instance=this;}
    protected static AMTSQManager instance;
    public static ViewModelManagerBase getInstance(Context c, Intent intent){
        instance =instance!=null?instance :(instance =new AMTSQManager(c));
        if(intent!=null&&intent.hasExtra(Shared.MAIN_MENU_LAUNCHED)){
            instance.mainMenuLaunched = intent.getExtras().getBoolean(Shared.MAIN_MENU_LAUNCHED);
            instance.launcher=intent;
        }
        return instance;
    }
    public void clear(){
        super.clear();
       instance=null;

    }
    @Override
    protected void advance() {
        if(current.get()>-1&&current.get()<questions.size()-1)current.set(current.get()+1);
        else {
            ResultModle.getInstance(getApplicationContext(),null).clear();
            AMTS.getCurrentInstance().finish();
            Activity caller;
            Intent intent;
            caller = this.mainMenuLaunched?MainMenu.getCurrentInstance():Menu.getCurrentInstance();
            intent = new Intent(caller, Result.class);
            intent.putExtra(Shared.MAIN_MENU_LAUNCHED,this.mainMenuLaunched);
            String[] list= new String[10];
            list[0]=getResult()?getString(R.string.ProbableDementia):getString(R.string.DiagnosisUnclear);
            intent.putExtra(Shared.RESULT_MESSAGES, list);
            caller.startActivity(intent);
        }
    }
    @Override
    public boolean getResult(){
       int count = 4;
       for(ViewModleQuestion viewMoldle : getResults()) if(viewMoldle.result.get())count--;
       return  count!=0;
   }
}

