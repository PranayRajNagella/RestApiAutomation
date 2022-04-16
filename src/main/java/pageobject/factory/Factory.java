package pageobject.factory;

import com.aventstack.extentreports.ExtentTest;


public class Factory {

    private static ThreadLocal<ExtentTest> ExtentTest = new ThreadLocal<ExtentTest>();
    private static Factory factory;
    private Factory() {
    }

    public static Factory getFactory() {
        if (factory == null) {
            factory = new Factory();
        }
        return factory;
    }

	public synchronized void setExtentObject(ExtentTest test) {
		ExtentTest.set(test);
	}

    public synchronized ExtentTest getExtentObject() {
        synchronized (ExtentTest) {
            try {
                ExtentTest.wait(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return ExtentTest.get();
    }

    public synchronized void removeReport() {
        ExtentTest.remove();
    }

}
