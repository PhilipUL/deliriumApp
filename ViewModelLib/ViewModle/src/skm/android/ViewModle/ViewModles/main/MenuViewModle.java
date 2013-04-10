package skm.android.ViewModle.ViewModles.main;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import gueei.binding.Command;
import skm.android.*;
import skm.android.ViewModle.ViewModles.CAM.CAMManager;
import skm.android.ViewModle.ViewModles.Shared;
import skm.android.ViewModle.ViewModles.TwoItemScreener.TwoItemScreenerManager;
import skm.android.ViewModle.ViewModles.amts.AMTSQManager;
import skm.android.ViewModle.ViewModles.base.YesNoViewModleBase;
import skm.android.ViewModle.ViewModles.gds_5.GDS5Manager;


/**
 * Created by IntelliJ IDEA.
 * User: Sean
 * Date: 24/10/11
 * Time: 21:10
 * To change this template use File | Settings | File Templates.
 */
public class MenuViewModle extends YesNoViewModleBase {

    public MenuViewModle(Context context){
         super(context);

    }

    public final Command LaunchLightHouseTestCommandSequence = new Command(){
        @Override
        public void Invoke(View arg0, Object... arg1) {
//
            Intent LaunchLightHouseTest = new Intent(Menu.getCurrentInstance(), Lighthouse.class);
            LaunchLightHouseTest.putExtra(Shared.MAIN_MENU_LAUNCHED,false);
            LaunchLightHouseTest.putExtra(Shared.LIGHTHOUSE_SEQUENCE,true);
            // Menu.getCurrentInstance().finish();
            Menu.getCurrentInstance().startActivity(LaunchLightHouseTest);
        }
    };
     public final Command LaunchLightHouseTestCommand = new Command(){
        @Override
	        public void Invoke(View arg0, Object... arg1) {
//
           Intent LaunchLightHouseTest = new Intent(Menu.getCurrentInstance(), Lighthouse.class);
           LaunchLightHouseTest.putExtra(Shared.MAIN_MENU_LAUNCHED,false);
            LaunchLightHouseTest.putExtra(Shared.LIGHTHOUSE_SEQUENCE,false);
           // Menu.getCurrentInstance().finish();
            Menu.getCurrentInstance().startActivity(LaunchLightHouseTest);
        }
    };
     public final Command LaunchDRSTestCommand = new Command(){
        @Override
	        public void Invoke(View arg0, Object... arg1) {

           Intent LaunchTest = new Intent(Menu.getCurrentInstance(), DRSQuestion.class);
           // Menu.getCurrentInstance().finish();
            Menu.getCurrentInstance().startActivity(LaunchTest);
        }
    };
     public final Command Launch3dCopExamCommand = new Command(){
        @Override
	        public void Invoke(View arg0, Object... arg1) {
            AMTSQManager.getInstance(getApplicationContext(),null).clear();
            Menu.getCurrentInstance().startActivity(new Intent(Menu.getCurrentInstance(), AMTS.class));
        }
    };

     public final Command LaunchTwoItemScreenerCommand = new Command(){
        @Override
	        public void Invoke(View arg0, Object... arg1) {
            TwoItemScreenerManager.getInstance(getApplicationContext(),Menu.getCurrentInstance().getIntent()).clear();
            Intent intent = new Intent(Menu.getCurrentInstance(), TwoItemScreener.class);
            intent.putExtra(Shared.MAIN_MENU_LAUNCHED,false);
            Menu.getCurrentInstance().startActivity(intent);

        }
    };
    public final Command LaunchCAMCommand = new Command(){
        @Override
	        public void Invoke(View arg0, Object... arg1) {
            CAMManager.getInstance(getApplicationContext(),null).clear();
            Menu.getCurrentInstance().startActivity(new Intent(Menu.getCurrentInstance(), CAM.class));

        }
    };
     public final Command LaunchGDS5Command = new Command(){
        @Override
	        public void Invoke(View arg0, Object... arg1) {
            GDS5Manager.getInstance(getApplicationContext(),null).clear();
            Menu.getCurrentInstance().startActivity(new Intent(Menu.getCurrentInstance(), GDS5.class));

        }
    };
     public final Command LaunchBallTestCommand = new Command(){
        @Override
	    public void Invoke(View arg0, Object... arg1) {
            BallViewModle2D.getInstance(getApplicationContext(),null).clear();
            MainMenu.getCurrentInstance().startActivity(new Intent(MainMenu.getCurrentInstance(), Ball.class));
        }
    };

    public final Command SpaceCog = new Command(){
        @Override
        public void Invoke(View arg0, Object... arg1) {
            BallViewModle2D.getInstance(getApplicationContext(),null).clear();
            MainMenu.getCurrentInstance().startActivity(new Intent(MainMenu.getCurrentInstance(), Space_cog.class));
        }
    };

    public final Command ReverseMonth = new Command(){
        @Override
        public void Invoke(View arg0, Object... arg1) {
            BallViewModle2D.getInstance(getApplicationContext(),null).clear();
            MainMenu.getCurrentInstance().startActivity(new Intent(MainMenu.getCurrentInstance(), Reverse_Month.class));
        }
    };

}
