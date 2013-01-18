package skm.android;

import android.os.Bundle;

public class About extends ActivityBase
{
    private static About about;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        About.about =this;
       setContentView(R.layout.about);
    }

    public static About getCurrentInstance(){
       return about;
        //about==null?(about=new Menu()):about;
    }

    @Override
    public void initText() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
