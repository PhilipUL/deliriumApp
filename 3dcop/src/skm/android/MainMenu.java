package skm.android;

import android.os.Bundle;
import android.view.View;
import gueei.binding.Binder;
import skm.android.ViewModle.ViewModles.main.MainMenuViewModle;

import java.io.Serializable;
import java.util.Vector;

public class MainMenu extends ActivityBase implements Serializable
{
    private static MainMenu menu;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        MainMenu.menu=this;
        MainMenuViewModle modle = new MainMenuViewModle(this.getApplicationContext());
        Binder.init(this.getApplication());
        Binder.setAndBindContentView(menu,R.layout.main_menu, modle);
        initText();
    }

    public static MainMenu getCurrentInstance(){
       return menu;
        //menu==null?(menu=new Menu()):menu;
    }

    @Override
    public void initText() {
        Vector<View> elements = new Vector<View>();
        elements.add(findViewById(R.id.MenuText));
        elements.add(findViewById(R.id.AboutButton));
        elements.add(findViewById(R.id.OptionsButton));
        elements.add(findViewById(R.id.ExamButton));
        elements.add(findViewById(R.id.IndivigualExamButton));
        elements.add(findViewById(R.id.ConfigButton));
        initText(elements);
    }
}
