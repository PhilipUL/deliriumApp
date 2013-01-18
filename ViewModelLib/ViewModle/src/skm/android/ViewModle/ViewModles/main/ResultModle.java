package skm.android.ViewModle.ViewModles.main;

import android.content.Context;
import android.content.Intent;
import gueei.binding.DependentObservable;
import skm.android.ViewModle.ViewModles.Shared;
import skm.android.ViewModle.ViewModles.base.YesNoViewModleBase;

/**
 * Created by IntelliJ IDEA.
 * User: Sean
 * Date: 19/11/11
 * Time: 18:53
 * To change this template use File | Settings | File Templates.
 */
public class ResultModle extends YesNoViewModleBase {

    public ResultModle(Context context){
         super(context);
         instance=this;
    }

    private static ResultModle instance;
    public static ResultModle getInstance(Context c,Intent intent){
//        return instance!=null?instance:(instance=new ResultModle(c));
        if (instance==null)instance=new ResultModle(c);
        if(intent!=null&&intent.hasExtra(Shared.RESULT_MESSAGES)){
            instance.messages = intent.getExtras().getStringArray(Shared.RESULT_MESSAGES);
            instance.launcher=intent;
        }
        return instance;
    }
    public String[] messages= new String[10];
    public DependentObservable<String> message = new DependentObservable<String>(String.class) {
        @Override
           public String calculateValue(Object... objects) throws Exception {
               return messages.length==0?"no messages":messages[0];
           }
        };
             public  void clear(){instance=null;}
}
