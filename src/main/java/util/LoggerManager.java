package util;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * created by zhouliang on 2020/7/19
 * @author zhouliang
 * @version 1.0
 */
public class LoggerManager {

    private final static Logger logger = Logger.getLogger("com.ahba1.homework");

    private LoggerManager(){}

    private static class SingleTon{
        static LoggerManager instance = new LoggerManager();
    }

    public static LoggerManager getLoggerManager() {
        return SingleTon.instance;
    }

    public void debug(String msg){
        logger.log(Level.FINE, msg);
    }

    public void warn(String msg){
        logger.log(Level.WARNING, msg);
    }

    public void error(String msg){
        logger.log(Level.SEVERE, msg);
    }

    public void info(String msg){
        logger.log(Level.INFO, msg);
    }

    public void close(){
        logger.setLevel(Level.OFF);
    }
}
