package skm.android.ViewModle.ViewModles.main;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import gueei.binding.Command;
import skm.android.*;
import skm.android.ViewModle.ViewModles.Shared;
import skm.android.ViewModle.ViewModles.base.YesNoViewModleBase;

import java.io.Serializable;


/**
 * Created by IntelliJ IDEA.
 * User: Sean
 * Date: 24/10/11
 * Time: 21:10
 * To change this template use File | Settings | File Templates.
 */
public class MainMenuViewModle extends YesNoViewModleBase implements Serializable{

    public MainMenuViewModle(Context context){
         super(context);
        String[] files =fileList();

        if(files.length>0){
            boolean found = false;
            for(int i=0;i<files.length;i++) found|=files[i].equalsIgnoreCase(getString(R.string.user));
            if(!found){
                 Shared.resetOptions(getApplicationContext());
            }
        }
        else if(getFilesDir().canRead())  Shared.resetOptions(getApplicationContext());

    }
     public final Command LaunchExam = new Command(){
        @Override
	        public void Invoke(View arg0, Object... arg1) {
//
           Intent LaunchLightHouseTest = new Intent(MainMenu.getCurrentInstance(), Lighthouse.class);
           LaunchLightHouseTest.putExtra(Shared.MAIN_MENU_LAUNCHED,true);
           LightHouseViewModle2D.getInstance(getApplicationContext(),null).clear();


           // Menu.getCurrentInstance().finish();
            MainMenu.getCurrentInstance().startActivity(LaunchLightHouseTest);
        }
    };
     public final Command LaunchExamMenu = new Command(){
        @Override
	        public void Invoke(View arg0, Object... arg1) {

           Intent LaunchMenu = new Intent(MainMenu.getCurrentInstance(), Menu.class);
           // Menu.getCurrentInstance().finish();
            MainMenu.getCurrentInstance().startActivity(LaunchMenu);
        }
    };


     public final Command LaunchOptionsScreen = new Command(){
        @Override
	    public void Invoke(View arg0, Object... arg1) {
              MainMenu.getCurrentInstance().startActivity(new Intent(MainMenu.getCurrentInstance(), Options.class));
       }
    };
    public final Command LaunchAboutScreen = new Command(){
        @Override
	    public void Invoke(View arg0, Object... arg1) {
            MainMenu.getCurrentInstance().startActivity(new Intent(MainMenu.getCurrentInstance(), About.class));
        }
    };

    public final Command LaunchConfigScreen = new Command(){
        @Override
        public void Invoke(View arg0, Object... arg1) {
            //BallViewModle2D.getInstance(getApplicationContext(),null).clear();
            MainMenu.getCurrentInstance().startActivity(new Intent(MainMenu.getCurrentInstance(), Config_screen.class));
        }
    };

}
