package pageobject.factory;

import pageobject.restapiUtilites.RestBase;

public class RestFactory {

    ThreadLocal<RestBase> restBase=new ThreadLocal<RestBase>();
    private static RestFactory restFactory;
    private RestFactory()
    {

    }

    public static RestFactory getRestFactory()
    {
        if(restFactory==null)
        {
            restFactory=new RestFactory();
        }
        return restFactory;
    }

    public synchronized RestBase getRestBase()
    {
        return restBase.get();
    }

    public void setRestBase(RestBase base)
    {
        restBase.set(base);
    }

    public void removeResBase()
    {
        restBase.remove();
    }
}
