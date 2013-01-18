package skm.android.ViewModle.ViewModles.TwoItemScreener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import skm.android.*;
import skm.android.ViewModle.ViewModles.Shared;
import skm.android.ViewModle.ViewModles.base.ViewModelManagerBase;
import skm.android.ViewModle.ViewModles.main.ResultModle;

/**
 * Created by IntelliJ IDEA.
 * User: Sean
 * Date: 04/11/11
 * Time: 07:09
 * To change this template use File | Settings | File Templates.
 */
public class TwoItemScreenerManager extends ViewModelManagerBase {
    private static TwoItemScreenerManager instance;
    protected TwoItemScreenerManager(Context c){super(c);initQuestions(getString(R.string.twoItemPath));init();instance=this;}

    public static TwoItemScreenerManager getInstance(Context c, Intent intent){
        instance =instance!=null?instance:(instance=new TwoItemScreenerManager(c));
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
            TwoItemScreener.getCurrentInstance().finish();
            Activity caller;
            Intent intent;
            boolean result = getResult();
            caller = this.mainMenuLaunched?MainMenu.getCurrentInstance():Menu.getCurrentInstance();
            if(this.mainMenuLaunched&&!result){
               intent = new Intent(caller, GDS5.class);
            }
            else {
               intent = new Intent(caller, Result.class);
               ResultModle.getInstance(getApplicationContext(),null).clear();
            }
            intent.putExtra(Shared.MAIN_MENU_LAUNCHED,this.mainMenuLaunched);
            String[] list= new String[10];
            list[0]=result?getString(R.string.DiagnosisDepression):getString(R.string.DiagnosisUnclear);
            intent.putExtra(Shared.RESULT_MESSAGES, list);
            caller.startActivity(intent);
        }
    }



}

