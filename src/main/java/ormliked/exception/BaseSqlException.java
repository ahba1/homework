package ormliked.exception;


/**
 * this exception is the super exception of all the exception occured when do some sql tasks
 * created by zhouliang on 2020/7/19
 * @author zhouliang
 * @version 1.0
 */
public class BaseSqlException extends RuntimeException {

    public BaseSqlException(){
        super();
    }

    public BaseSqlException(String msg){
        super(msg);
    }
}
