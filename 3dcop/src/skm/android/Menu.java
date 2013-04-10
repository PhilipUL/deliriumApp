package skm.android;

import android.os.Bundle;
import android.view.View;
import gueei.binding.Binder;
import skm.android.ViewModle.ViewModles.main.MenuViewModle;

import java.util.Vector;

public class Menu extends ActivityBase
{
    private static Menu menu;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Menu.menu=this;
        MenuViewModle modle = new MenuViewModle(this.getApplicationContext());
        Binder.init(this.getApplication());
        Binder.setAndBindContentView(menu,R.layout.menu, modle);

    }

    public static Menu getCurrentInstance(){
       return menu;
        //menu==null?(menu=new Menu()):menu;
    }

    @Override
    public void initText() {
        Vector<View> elements = new Vector<View>();
        elements.add(findViewById(R.id.textview));
        elements.add(findViewById(R.id.lighthouseMenuButton));
        elements.add(findViewById(R.id.DRSButton));
        elements.add(findViewById(R.id.ExamButton));
        elements.add(findViewById(R.id.TwoItemButton));
        elements.add(findViewById(R.id.CAMButton));
        elements.add(findViewById(R.id.GDS5Button));
        elements.add(findViewById(R.id.BallTestButton));
        elements.add(findViewById(R.id.SpaceCogButton));
        elements.add(findViewById(R.id.lighthouseMenuButtonSequence));
        initText(elements);
    }
}
