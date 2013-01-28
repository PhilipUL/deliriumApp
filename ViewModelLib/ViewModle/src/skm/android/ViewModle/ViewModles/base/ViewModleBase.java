package skm.android.ViewModle.ViewModles.base;

import android.content.Context;
import android.content.ContextWrapper;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Sean
 * Date: 27/09/11
 * Time: 19:41
 * To change this template use File | Settings | File Templates.
 */
public abstract class ViewModleBase extends ContextWrapper implements Serializable {

    public ViewModleBase(Context context){
        super(context);
    }


}
